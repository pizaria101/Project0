package dev.schulte.entities;

public enum Status {
    PENDING,APPROVED,DENIED;

    public static Status fromString(String status){
        return valueOf(status.toUpperCase());
    }
}
