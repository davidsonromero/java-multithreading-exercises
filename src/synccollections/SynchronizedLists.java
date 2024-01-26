package synccollections;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import static java.util.Collections.synchronizedList;

public class SynchronizedLists {
    private static List<String> list1 = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        list1 = synchronizedList(list1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<10; i++){
                    list1.add("Thread 1: " + i);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 10; i<20; i++){
                    list1.add("Thread 2: " + i);
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 20; i<30; i++){
                    list1.add("Thread 3: " + i);
                }
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
