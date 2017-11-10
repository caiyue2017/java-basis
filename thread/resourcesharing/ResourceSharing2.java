package thread.resourcesharing;

/**
 * 案例二：资源共享
 */
public class ResourceSharing2 {
    public static void main(String[] args) {
        MyThread m = new MyThread();
        Thread t1 = new Thread(m);
        Thread t2 = new Thread(m);
        Thread t3 = new Thread(m);
        t1.start();
        t2.start();
        t3.start();
    }
}
class MyThread implements Runnable {

    private int ticket = 10;

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(ticket-- + " is saled by " + Thread.currentThread());
        }
    }
}
