package com.example.BatDongSan.entity;

public enum TinDangStatus {
    PUBLIC("Công Khai"),
    PRIVATE("Ẩn");

    public final String label;
    private TinDangStatus(String label) {
        this.label = label;
    }
}

