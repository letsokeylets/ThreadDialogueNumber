package org.example;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    private static final int ITTERATION = 4;
    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
        int sum = 1;
        while (sum < ITTERATION) {
            System.out.printf("Я %s Всем привет!\n", name);
            sum++;
            Thread.sleep(2500);
        }
        return name + " вывел приветствие: " + sum + " раз(а)";
    }
}

