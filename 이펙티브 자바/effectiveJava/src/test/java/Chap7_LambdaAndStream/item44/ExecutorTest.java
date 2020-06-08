package Chap7_LambdaAndStream.item44;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
    @Test
    void name() {
        new Thread(System.out::println).start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit((Runnable) System.out::println);

        executorService.submit(() -> 1);
        executorService.submit(() -> {
        });
    }
}
