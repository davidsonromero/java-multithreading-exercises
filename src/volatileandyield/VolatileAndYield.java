package volatileandyield;

public class VolatileAndYield {
    private static volatile int num = 0;//Volatile guarantees that the value of num will be taken from ram and will not be cached in the cpu.
    //Volatile is used when you want to make sure that the value of a variable is the same for all threads.
    //Some performance is lost when using volatile, since ram access is slower than cpu cache access.
    private static boolean ready = false;
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        t1.start();
        num = 191910;
        ready = true;
    }

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(num);
        }
    }
}
