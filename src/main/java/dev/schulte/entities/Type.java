package dev.schulte.entities;

public enum Type {
    lodging,travel,food,misc;

    public static Type fromString(String type){
        return valueOf(type.toUpperCase());
    }
    // Case sensitive
}
