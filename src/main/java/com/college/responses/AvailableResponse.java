package com.college.responses;

public class AvailableResponse extends BasicResponse {
    private boolean availabe;

    public AvailableResponse(boolean success, Integer errorCode, boolean availabe) {
        super(success, errorCode);
        this.availabe = availabe;
    }

    public boolean isAvailabe() {
        return availabe;
    }

    public void setAvailabe(boolean availabe) {
        this.availabe = availabe;
    }
}
