package jp.co.world.storedevelopment.utils;

import java.util.List;

public class PushParam {
    private List<String> tokenIdList;

    private String message;

    private String title;

    private String type;

    private String url;

    private String pushSandbox;

    public String getPushSandbox() {
        return pushSandbox;
    }

    public void setPushSandbox(String pushSandbox) {
        this.pushSandbox = pushSandbox;
    }

    protected List<String> getTokenIdList() {
        return tokenIdList;
    }

    public void setTokenIdList(List<String> tokenIdList) {
        this.tokenIdList = tokenIdList;
    }

    protected String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
