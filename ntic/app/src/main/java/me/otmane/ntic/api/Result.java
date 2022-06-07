package me.otmane.ntic.api;

public class Result<T> {
    private String status;
    private T data;

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}