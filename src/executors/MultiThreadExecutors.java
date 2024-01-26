package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
public class MultiThreadExecutors {
    public static void main(String[] args){
        List<MyCallable> callables = List.of(new MyCallable(), new MyCallable(), new MyCallable());

        ExecutorService e = Executors.newFixedThreadPool(6);
        e.execute(new MyRunnable());
        e.execute(new MyRunnable());

        Future<String> f = e.submit(new MyCallable());

        ExecutorService e2 = Executors.newCachedThreadPool();//Better for limited amount of tasks
        e2.execute(new MyRunnable());
        e2.execute(new MyRunnable());

        Future<String> f2 = e2.submit(new MyCallable());

        try {
            System.out.println(f.get());
            System.out.println(f2.get());
            List<Future<String>> futures = e.invokeAll(callables);
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
            e.awaitTermination(3, java.util.concurrent.TimeUnit.SECONDS);
            e2.awaitTermination(3, java.util.concurrent.TimeUnit.SECONDS);
            e.shutdown();
            e2.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            e.shutdownNow();
            e2.shutdownNow();
        }
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
            }
        }
    }

    public static class MyCallable implements java.util.concurrent.Callable<String> {
        @Override
        public String call() throws Exception {
            return "Hello World!";
        }
    }
}
