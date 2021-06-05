package com.bluesky.tech.thread;

/**
 * 非线程安全的银行
 */
class NotSafeBank {

    /**
     * 当前余额
     */
    private int balance = 1000;

    /**
     * 存款
     *
     * @param money 存款金额
     */
    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> 当前账户余额为：" + this.balance);
        this.balance += money;
        System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
