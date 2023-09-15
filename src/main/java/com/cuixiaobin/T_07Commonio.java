package com.cuixiaobin;

import org.junit.Test;

import java.util.stream.IntStream;

public class T_07Commonio {

    @Test
    public void test_01() {
        int sum = IntStream.range(1, 6)
                .reduce(0, (a, b) -> a + b); // 15 (1 + 2 + 3 + 4 + 5)

    }
}
