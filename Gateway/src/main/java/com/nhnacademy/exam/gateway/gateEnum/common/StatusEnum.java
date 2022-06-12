package com.nhnacademy.exam.gateway.gateEnum.common;

public enum StatusEnum {
    JOIN("가입"), SECESSION("탈퇴"), HUMAN("휴먼"),
    ACTIVATE("활성"), EXIT("종료");

    private final String value;
    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
