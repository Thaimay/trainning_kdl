package jp.co.world.storedevelopment.utils;

import java.util.List;

public class AndroidReturnResults {

    private String multicast_id;

    private Integer success;

    private Integer failure;

    private Integer canonical_ids;

    private String resultJson;

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    private List<AndroidReturn> results;

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getCanonical_ids() {
        return canonical_ids;
    }

    public void setCanonical_ids(Integer canonical_ids) {
        this.canonical_ids = canonical_ids;
    }

    public List<AndroidReturn> getResults() {
        return results;
    }

    public void setResults(List<AndroidReturn> results) {
        this.results = results;
    }

    public void setRegistration_ids(List<String> registration_ids) {
        int i = 0;
        for (String registration_id : registration_ids) {
            results.get(i).setRegistration_id(registration_id);
            ;
            i++;
        }

    }

}
