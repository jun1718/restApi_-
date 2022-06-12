package com.nhnacademy.exam.accountapi.memberEnum;

public enum MemberAuthorityEnum {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String value;
    MemberAuthorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
