package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> myCallable1 = new MyCallable("Поток 1");
        Callable<String> myCallable2 = new MyCallable("Поток 2");
        Callable<String> myCallable3 = new MyCallable("Поток 3");
        Callable<String> myCallable4 = new MyCallable("Поток 4");
        List<Callable<String>> callableList = Arrays.asList(myCallable1, myCallable2, myCallable3, myCallable4);
        System.out.println("Запуск потоков и получение результатов через invokeAll:");
        Thread.sleep(2500);
        invokeAllCallable(callableList);
        Thread.sleep(2500);
        System.out.println("Запуск потоков и получение результатов через invokeAny:");
        invokeAnyCallable(callableList);
    }

    private static void invokeAllCallable(List<Callable<String>> callableList) throws InterruptedException, ExecutionException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        // Отправляем все задачи на выполнение
        final List<Future<String>> tasks = threadPool.invokeAll(callableList);
        //Отменяет все запущенные задачи
        threadPool.shutdown();
        //Выведем итоги выполнения всех задач
        for (Future<String> task : tasks) {
            final String resultOfTask = task.get();
            System.out.println(resultOfTask);
        }
    }

    private static void invokeAnyCallable(List<Callable<String>> callableList) throws ExecutionException, InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        //Получение результата задачи, которая завершится первой
        final String invokeAny = threadPool.invokeAny(callableList);
        //Отменяет все запущенные задачи
        threadPool.shutdown();
        //Вывод результата той задачи, которая завершилась первой
        System.out.println("Результат выполнения invokeAny - " + invokeAny);
    }
}
