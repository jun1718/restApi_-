package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

    @Test
    void contextLoads() {
        Add add = new Add();
        assertThat(add.add(1,3))
            .isEqualTo(4);
    }
}
