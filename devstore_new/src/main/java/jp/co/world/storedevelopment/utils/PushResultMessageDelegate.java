package jp.co.world.storedevelopment.utils;

import com.notnoop.apns.ApnsDelegateAdapter;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.internal.Utilities;
import com.notnoop.exceptions.ApnsDeliveryErrorException;

public class PushResultMessageDelegate extends ApnsDelegateAdapter {

    ApnsNotification message;

    Throwable throwable;

    @Override
    public void messageSent(ApnsNotification message, boolean resent) {
        this.message = message;
    }

    @Override
    public void messageSendFailed(ApnsNotification message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    public String getDeviceToken() {
        return Utilities.encodeHex(message.getDeviceToken());
    }

    public ApnsNotification getMessage() {
        return message;
    }

    public String errMessage() {
        if (null != throwable) {
            ApnsDeliveryErrorException apnsDeliveryErrorException = (ApnsDeliveryErrorException) throwable;
            return apnsDeliveryErrorException.getDeliveryError().name();
        }

        return "";

    }

    public boolean isErr() {
        if (null != throwable) {
            return true;
        }

        return false;

    }

}
