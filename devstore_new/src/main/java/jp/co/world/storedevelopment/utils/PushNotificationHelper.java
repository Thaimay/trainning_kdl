package jp.co.world.storedevelopment.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

import net.arnx.jsonic.JSON;

public class PushNotificationHelper {
    private String gcpPushUrl = "";

    private String androidKey = "";

    private String iOsCertificateFilePath = "";

    private String iOsCertificateFilePass = "";

    public PushNotificationHelper(String gcpPushUrl, String androidKey, String iOsCertificateFilePath, String iOsCertificateFilePass) {
        this.gcpPushUrl = gcpPushUrl;
        this.androidKey = androidKey;
        this.iOsCertificateFilePath = iOsCertificateFilePath;
        this.iOsCertificateFilePass = iOsCertificateFilePass;
    }

    public AndroidReturnResults androidPush(AndroidPushParam prm) throws ClientProtocolException, IOException {

        GcmHttpHelper gcmHttpHelper = new GcmHttpHelper(3000, 3000, androidKey);

        String json = JSON.encode(prm);
        String androidReturnResultsJson = gcmHttpHelper.doPost(gcpPushUrl, json, true);

        AndroidReturnResults androidReturnResults = JSON.decode(androidReturnResultsJson, AndroidReturnResults.class);

        androidReturnResults.setResultJson(androidReturnResultsJson);
        androidReturnResults.setRegistration_ids(prm.getRegistration_ids());

        return androidReturnResults;

    }

    public IosReturnResults iOsPush(PushParam prm) throws ClientProtocolException, IOException, InterruptedException {

        IosReturnResults iosReturnResults = new IosReturnResults();

        PushResultMessageDelegate pushResultMessageDelegate = new PushResultMessageDelegate();
        ApnsService service = null;
        if ("1".equals(prm.getPushSandbox())) {
            service = APNS.newService().withCert(iOsCertificateFilePath, iOsCertificateFilePass).withSandboxDestination().withDelegate(pushResultMessageDelegate).build();
        } else {
            service = APNS.newService().withCert(iOsCertificateFilePath, iOsCertificateFilePass).withProductionDestination().withDelegate(pushResultMessageDelegate).build();
        }

        String payload = APNS.newPayload().alertBody(prm.getMessage()).customField("title", prm.getTitle()).customField("type", prm.getType()).customField("url", prm.getUrl()).build();
        List<String> tokenArray = new ArrayList<>();

        // デバイストークンを全て小文字可
        for (String tokenId : prm.getTokenIdList()) {
            tokenArray.add(tokenId.toLowerCase());
        }

        iosReturnResults.setTotal(tokenArray.size());

        for (int i = 0; i < 100; i++) {

            // プッシュ通知
            service.push(tokenArray, payload);

            // プッシュのエラーコード受信までの少し待つ
            Thread.sleep(1000);

            if (pushResultMessageDelegate.isErr()) {

                // エラー情報の保持
                {
                    IosReturnError iosReturnError = new IosReturnError();
                    iosReturnError.setDeviceToken(pushResultMessageDelegate.getDeviceToken());
                    iosReturnError.setErrMessege(pushResultMessageDelegate.errMessage());
                    iosReturnResults.getIosReturnError().add(iosReturnError);
                }

                // エラーになったdeviceTokenを削除する
                String errDeviceToken = pushResultMessageDelegate.getDeviceToken();
                int index = tokenArray.indexOf(errDeviceToken.toLowerCase());
                tokenArray.subList(0, index + 1).clear();

            } else {
                break;
            }

            pushResultMessageDelegate = new PushResultMessageDelegate();

        }

        service.stop();

        return iosReturnResults;

    }

    public List<IosReturnError> iOsfeedback() throws ClientProtocolException, IOException, InterruptedException {

        List<IosReturnError> returnErrList = new ArrayList<>();

        PushResultMessageDelegate pushResultMessageDelegate = new PushResultMessageDelegate();
        ApnsService service = APNS.newService().withCert(iOsCertificateFilePath, iOsCertificateFilePass).withSandboxDestination().withDelegate(pushResultMessageDelegate).build();

        // feedback取得
        Map<String, Date> errDevices = service.getInactiveDevices();
        for (Map.Entry<String, Date> e : errDevices.entrySet()) {
            IosReturnError iosReturnError = new IosReturnError();
            iosReturnError.setDeviceToken(e.getKey());
            returnErrList.add(iosReturnError);
        }

        // プッシュのエラーコード受信までの少し待つ
        Thread.sleep(1000);

        service.stop();

        return returnErrList;

    }

}
