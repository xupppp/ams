package com.ams.utils;

import java.io.Serializable;


public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean success;
    private int codeNum;
    private String codeDesc;
    private T value;

    private Result() {
    }

    public static <T> Result<Object> ofSuccess() {
        return ofSuccess((Object) null);
    }

    public static <T> Result<T> ofSuccess(T value) {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setValue(value);
        return result;
    }

    public static <T> Result<T> ofErrorT(int codeNum, String codeDesc) {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCodeNum(codeNum);
        result.setCodeDesc(codeDesc);
        return result;
    }

    public static Result<Object> ofError(int codeNum, String codeDesc) {
        Result<Object> result = new Result<Object>();
        result.setSuccess(false);
        result.setCodeNum(codeNum);
        result.setCodeDesc(codeDesc);
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCodeNum() {
        return this.codeNum;
    }

    public void setCodeNum(int codeNum) {
        this.codeNum = codeNum;
    }

    public String getCodeDesc() {
        return this.codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return this.success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", codeNum=" + codeNum +
                ", codeDesc='" + codeDesc + '\'' +
                ", value=" + value +
                '}';
    }
}
