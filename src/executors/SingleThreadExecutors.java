package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutors {
    public static void main(String[] args){
        ExecutorService e = Executors.newSingleThreadExecutor();
        e.execute(new MyRunnable());

        ExecutorService e2 = Executors.newSingleThreadExecutor();
        e2.execute(new MyRunnable());

        ExecutorService e3 = Executors.newSingleThreadExecutor();
        e3.execute(new MyRunnable());

        Future<?> f = e.submit(new MyRunnable());

        Future<String> f2 = e.submit(new MyCallable());
        try{
            e.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            e.shutdown();
            e2.shutdown();
            e3.shutdown();
            //Always good to put shutdown() in finally block after handling InterruptedException
        }
        boolean isDone = f.isDone();
        System.out.println("isDone: " + isDone);

        while (!f2.isDone()){
            System.out.println("Waiting...");
        }
        try {
            System.out.println(f2.get());
        } catch (Exception ex){
            ex.printStackTrace();
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
