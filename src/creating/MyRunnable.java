package creating;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Side thread");
        System.out.println(Thread.currentThread().getName());
        for(long i = 0; i < 1000000; i++){
            System.out.println("i = " + i + " in " + Thread.currentThread().getName() + " thread");
        }
    }
}
