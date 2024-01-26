package atomic;

import synchronizing.Synchronized;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    public static AtomicInteger i = new AtomicInteger(-1);
    //Atomic classes are ways to synchronize without using the synchronized keyword.
    //Their value is updated atomically, meaning that the value is updated in a single operation.

    public static void main(String[] args) {
        Thread t1 = new Thread(new Synchronized.SyncRunnable());
        Thread t2 = new Thread(new Synchronized.SyncRunnable());
        Thread t3 = new Thread(new Synchronized.SyncRunnable());

        t1.start();
        t2.start();
        t3.start();
    }

    public static class SyncRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (Synchronized.class){
                String name = Thread.currentThread().getName();
                System.out.println(name + " - " + i.incrementAndGet());
            }
        }
    }
}
