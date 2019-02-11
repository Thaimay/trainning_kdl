package jp.co.world.storedevelopment.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidPushParam extends PushParam {

    private String title = "";
    private String priority = "";

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(String priority) {
    	this.priority = priority;
    }
    
    public Map<String, String> getData() {

        Map<String, String> data = new HashMap<>();
        data.put("message", super.getMessage());
        data.put("title", this.title);
        data.put("type", super.getType());
        data.put("url", super.getUrl());
        data.put("priority", this.priority);
        return data;
    }

    public List<String> getRegistration_ids() {
        return super.getTokenIdList();
    }

}
