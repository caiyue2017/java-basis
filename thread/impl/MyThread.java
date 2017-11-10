package thread.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 实现Java多线程的三种方式
 */
public class MyThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方式一
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        // 方式二
        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();

        // 方式三
        System.out.println("----方式三----");
        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable callable = new MyThread3(i + "");
            // 执行任务并获取Future对象
            Future future = pool.submit(callable);
            list.add(future);
        }
        // 关闭线程池
        pool.shutdown();

        // 获取所有并发任务的运行结果
        for (Future future : list)
            System.out.println(future.get().toString());
    }
}

/**
 * 方式一：继承Thread
 */
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread1.run()!");
    }
}

/**
 * 方式二：实现Runnable接口
 */
class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("MyThread2.run()!");
    }
}

/**
 * 方式三：使用ExecutorService、Callable、Future实现有返回结果的多线程
 */
class MyThread3 implements Callable {

    private String taskNum;

    public MyThread3(String taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * 返回结果方法（结果用Future获取）
     * @return
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        System.out.println("线程" + taskNum + "启动");
        Date date = new Date();
        Thread.sleep(1000);
        Date date1 = new Date();
        long time = date1.getTime() - date.getTime();
        System.out.println("线程" + taskNum + "停止");
        return "线程" + taskNum + "返回结果，执行时间【" + time + "】毫秒";
    }
}

