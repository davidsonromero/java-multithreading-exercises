package synchronizing;

//Should be avoided
//Not so advantageous

public class Synchronized {

    public static int i = -1;

    public static void main(String[] args) {
        Thread t1 = new Thread(new SyncRunnable());
        Thread t2 = new Thread(new SyncRunnable());
        Thread t3 = new Thread(new SyncRunnable());
        Thread t4 = new Thread(new SyncRunnable());
        Thread t5 = new Thread(new SyncRunnable());
        Thread t6 = new Thread(new SyncRunnable());
        Thread t7 = new Thread(new SyncRunnable());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }

    public static class SyncRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (Synchronized.class){
                i++;
                String name = Thread.currentThread().getName();
                System.out.println(name + " - " + i);
            }
        }
    }
}
