package com.chensen.meizhi.common.http;

/**
 * author：chensen on 2016/11/25 14:11
 * desc：
 */

public class GirlHttpResponse<T> {
    private boolean error;
    private T results;


    public GirlHttpResponse(boolean error, T results) {
        this.error = error;
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
