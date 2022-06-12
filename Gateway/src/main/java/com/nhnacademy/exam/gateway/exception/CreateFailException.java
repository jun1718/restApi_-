package com.nhnacademy.exam.gateway.exception;

import javax.management.relation.RoleUnresolved;

public class CreateFailException
    extends RuntimeException {
    public CreateFailException(String s) {
        super(s);
    }
}
