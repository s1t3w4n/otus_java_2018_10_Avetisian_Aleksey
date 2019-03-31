package com.mydomain;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {
    private final int count;

    public Benchmark(int count) {
        this.count = count;
    }

    public void run(int size)  {
        for (int i = 0; i < count; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add((i + j) / 2);
            }
        }
    }
}
