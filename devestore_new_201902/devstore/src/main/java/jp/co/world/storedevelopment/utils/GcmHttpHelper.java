package jp.co.world.storedevelopment.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/**
 * GCM用HTTP通信へルパ
 * 
 * @author yamanaka
 *
 */
public class GcmHttpHelper {

    /**
     * コネクションタイムアウト
     */
    private int ctimeout = 10;

    /**
     * セッションタイムアウト
     */
    private int stimeout = 10;

    /**
     * HTTPヘッダ
     */
    List<Header> headers = new ArrayList<>();

    /**
     * コネクションマネージャ
     */
    BasicHttpClientConnectionManager connectionManager = null;

    /**
     * コンストラクタ
     * コネクションタイムアウト、セッションタイムアウトはともに10秒
     * 
     * @param projectId GoogleCloudプロジェクトID
     */
    public GcmHttpHelper(String projectId) {

        // GCM用HTTPヘッダ生成
        headers.add(new BasicHeader("Accept-Charset", "UTF-8"));
        headers.add(new BasicHeader("Accept-Language", "ja, en;q=0.8"));
        headers.add(new BasicHeader("User-Agent", "http-client"));
        headers.add(new BasicHeader("Content-type", "application/json"));
        headers.add(new BasicHeader("Authorization", "key=" + projectId));
    }

    /**
     * コンストラクタ
     * 
     * @param ctimeout コネクションタイムアウト
     * @param stimeout セッションタイムアウト
     * @param projectId GoogleCloudプロジェクトID
     */
    public GcmHttpHelper(int ctimeout, int stimeout, String projectId) {
        this.ctimeout = ctimeout;
        this.stimeout = stimeout;

        // GCM用HTTPヘッダ生成
        headers.add(new BasicHeader("Accept-Charset", "UTF-8"));
        headers.add(new BasicHeader("Accept-Language", "ja, en;q=0.8"));
        headers.add(new BasicHeader("User-Agent", "http-client"));
        headers.add(new BasicHeader("Content-type", "application/json"));
        headers.add(new BasicHeader("Authorization", "key=" + projectId));
    }

    /**
     * HTTP POST
     * 
     * @param url URL
     * @param params パラメータ
     * @param sslFlag HTTPS時はtrue
     * @return レスポンスデータ
     * @throws ClientProtocolException プロトコルエラー
     * @throws IOException 通信エラー
     */
    public String doPost(String url, String params, boolean sslFlag) throws ClientProtocolException, IOException {

        HttpPost request = null;
        try {
            NameValuePair pair = new BasicNameValuePair("key", params);
            List<NameValuePair> nvp = new ArrayList<>();
            nvp.add(pair);
            request = new HttpPost(url);
            HttpClient client = createHttpClient(sslFlag);
            StringEntity reqEntity = new StringEntity(params, "UTF-8");
            reqEntity.setContentEncoding("UTF-8");
            request.setEntity(reqEntity);
            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode && HttpStatus.SC_CREATED != statusCode) {
                throw new IOException(String.valueOf(statusCode));
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new IOException("entity is null");
            }
            String ret = "";
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    int l;
                    byte[] current = new byte[0];
                    byte[] tmp = new byte[2048];
                    while ((l = instream.read(tmp)) != -1) {
                        byte[] subArray = ArrayUtils.subarray(tmp, 0, l);
                        current = ArrayUtils.addAll(current, subArray);
                    }
                    ret += new String(current);
                } finally {
                    instream.close();
                }
            }
            return ret;
        } finally {
            if (connectionManager != null) {
                connectionManager.shutdown();
                connectionManager.close();
            }
        }
    }

    /**
     * HTTP GET
     * 
     * @param url URL
     * @param sslFlag HTTPS時はtrue
     * @return レスポンスデータ
     * @throws ClientProtocolException プロトコルエラー
     * @throws IOException 通信エラー
     */
    public String doGet(String url, boolean sslFlag) throws ClientProtocolException, IOException {
        HttpClient client = null;
        HttpGet request = null;
        try {
            request = new HttpGet(url);
            client = createHttpClient(sslFlag);

            HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != statusCode && HttpStatus.SC_CREATED != statusCode) {
                throw new IOException(String.valueOf(statusCode));
            }

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new IOException("entity is null");
            }
            return EntityUtils.toString(entity);
        } finally {
            if (connectionManager != null) {
                connectionManager.shutdown();
                connectionManager.close();
            }
        }
    }

    // HTTP クライアントの作成
    private HttpClient createHttpClient(boolean sslFlag) {

        if (sslFlag) {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).loadTrustMaterial(null, new TrustStrategy() {

                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                }).build();
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

                Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslConnectionSocketFactory).build();
                connectionManager = new BasicHttpClientConnectionManager(registry);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            } catch (KeyStoreException e) {
                throw new RuntimeException(e);
            }
        }

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(ctimeout).setSocketTimeout(stimeout).build();

        // headers
        // create client
        if (sslFlag) {
            return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setDefaultHeaders(headers).setConnectionManager(connectionManager).build();
        } else {
            return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setDefaultHeaders(headers).build();
        }
    }

}
