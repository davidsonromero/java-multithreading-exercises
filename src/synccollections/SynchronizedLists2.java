package synccollections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class SynchronizedLists2 {
    private static List<String> list1 = new CopyOnWriteArrayList<>();//Thread-safe - a bit slower, but safer for multi-threading

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                list1.add(Thread.currentThread().getName());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                list1.add(Thread.currentThread().getName());
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                list1.add(Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);

        Stream<String> stream = list1.stream();
        stream.forEach(System.out::println);
    }
}
