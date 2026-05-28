package com.onycom.student.concurrent;

public class ConcurrentTest {

    private static int balance = 1000;

    // ==================== 有锁（正确）====================
    private static synchronized void deduct() {
        int current = balance;
        try { Thread.sleep(10); } catch (Exception e) {}
        balance = current - 100;
        System.out.println("扣款后余额：" + balance);
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        // ==================== 有锁版本（正确，最终余额=0）====================
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> deduct());
        }

        // ==================== 无锁版本（错误，最终余额≠0，注释掉）====================
//        for (int i = 0; i < 10; i++) {
//            threads[i] = new Thread(() -> {
//                int current = balance;
//                try { Thread.sleep(10); } catch (Exception e) {}
//                balance = current - 100;
//                System.out.println("扣款后余额：" + balance);
//            });
//        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("最终余额：" + balance);
    }
}
