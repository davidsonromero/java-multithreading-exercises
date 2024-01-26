//Special thanks to https://www.youtube.com/@rinaldodev

package creating;

public class CreatingThreads {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new Thread(new MyRunnable());
        //t1.run(); // This is not a thread, it's just a method call
        t1.start(); // This is a thread
        Thread t2 = new Thread(new MyRunnable());
        t2.start();
        //Runnable can be a lambda function
        Thread t3 = new Thread(() -> {
            System.out.println("Side thread");
            System.out.println(Thread.currentThread().getName());
            for(long i = 0; i < 1000000; i++){
                System.out.println("i = " + i + " in " + Thread.currentThread().getName() + " thread");
            }
        });
        t3.start();
    }
}
