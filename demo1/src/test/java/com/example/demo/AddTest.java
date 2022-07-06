package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void add() {
        Add add = new Add();
        assertThat(add.add(1,3))
            .isEqualTo(4);
    }
}