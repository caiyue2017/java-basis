package thread.resourcesharing;

/**
 * 案例一
 */
public class ResourceSharing1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("------------方式1------------");
        MyThread1 m1 = new MyThread1("窗口一");
        MyThread1 m2 = new MyThread1("窗口二");
        MyThread1 m3 = new MyThread1("窗口三");
        m1.start();
        m2.start();
        m3.start();

        Thread.sleep(2000);

        System.out.println("------------方式2------------");
        MyThread2 mr1 = new MyThread2("窗口一");
        MyThread2 mr2 = new MyThread2("窗口二");
        MyThread2 mr3 = new MyThread2("窗口三");
        Thread t1 = new Thread(mr1);
        Thread t2 = new Thread(mr2);
        Thread t3 = new Thread(mr3);
        t1.start();
        t2.start();
        t3.start();
    }
}

class MyThread1 extends Thread {

    // 每个线程有100张票
    private int ticket = 10;

    public MyThread1() {

    }

    // 注入线程名称
    public MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(ticket-- + " is saled by " + Thread.currentThread().getName());
        }
    }
}

class MyThread2 implements Runnable{

    // 每个线程有100张票
    private int ticket = 10;

    private String name;

    public MyThread2(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (ticket > 0) {
            System.out.println(ticket-- + " is saled by " + name);
        }
    }
}

