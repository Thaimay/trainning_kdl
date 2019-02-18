package jp.co.world.storedevelopment.utils;

import java.util.ArrayList;
import java.util.List;

public class IosReturnResults {

    private Integer total;

    private List<IosReturnError> iosReturnError = new ArrayList<>();

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSuccess() {
        return total - iosReturnError.size();
    }

    public Integer getFailure() {
        return iosReturnError.size();
    }

    public List<IosReturnError> getIosReturnError() {
        return iosReturnError;
    }

    public void setIosReturnError(List<IosReturnError> iosReturnError) {
        this.iosReturnError = iosReturnError;
    }

}
