package com.nhnacademy.exam.accountapi.memberEnum;

public enum MemberStatusEnum {
    JOIN("가입"), SECESSION("탈퇴"), HUMAN("휴먼");

    private final String value;
    MemberStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
