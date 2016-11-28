package com.chensen.meizhi.common.http;

import java.util.List;

/**
 * author：chensen on 2016/11/25 14:10
 * desc：
 */

public class HttpResponse<T> {
    private String resaon;
    private int error_code;
    private List<T> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getResaon() {
        return resaon;
    }

    public void setResaon(String resaon) {
        this.resaon = resaon;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
