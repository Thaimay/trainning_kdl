package jp.co.world.storedevelopment.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpHelper {

	/**
	 * コネクションタイムアウト
	 */
	private int connectionTimeout = 3;
	
	/**
	 * セッションタイムアウト
	 */
	private int sessionTimeout = 3;
	
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
	 * コネクションタイムアウト、セッションタイムアウトはともに3秒
	 */
	public HttpHelper() {
		//HTTPヘッダ生成
        headers.add(new BasicHeader("Accept-Charset", "UTF-8"));
        headers.add(new BasicHeader("Accept-Language", "ja, en;q=0.8"));
	}
	
	public HttpHelper(String xClientApiByKey, String xAccessApiByKey, String xSecretApiByKey) {
		//HTTPヘッダ生成
        headers.add(new BasicHeader("Accept-Charset", "UTF-8"));
        headers.add(new BasicHeader("Accept-Language", "ja, en;q=0.8"));
        headers.add(new BasicHeader("X-CLIENT-API-BY", xClientApiByKey));
        headers.add(new BasicHeader("X-ACCESS-API-BY", xAccessApiByKey));
        headers.add(new BasicHeader("X-SECRET-API-BY", xSecretApiByKey));

	}
	
	/**
	 * HTTP GET
	 * 
	 */
	public String doGet(String url) throws ClientProtocolException, IOException {
		HttpClient client = null;
		HttpGet request = null;
		
		try {
			request = new HttpGet(url);
			client = createHttpClient();
			
			HttpResponse response = client.execute(request);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != statusCode) {
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
    private HttpClient createHttpClient() {

        // 証明書チェーンの検証をスキップ
        KeyManager[] keyManagers = null;
        TrustManager[] transManagers = { new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        } };

        SSLContext sslContext = null;
        try {

            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagers, transManagers, new SecureRandom());

            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslConnectionSocketFactory).build();
            connectionManager = new BasicHttpClientConnectionManager(registry);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout * 1000).setSocketTimeout(sessionTimeout * 1000).build();

        // headers
        // create client
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setDefaultHeaders(headers).setConnectionManager(connectionManager).build();
    }
}
