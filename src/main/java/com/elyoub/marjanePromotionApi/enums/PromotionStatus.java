package com.elyoub.marjanePromotionApi.enums;

public enum PromotionStatus {
    ACCEPTED("Accepted"),
    REFUSED("Refused"),
    PENDING("Pending");

    private final String status;

    PromotionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}