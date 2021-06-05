package com.bluesky.tech.thread;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 线程安全的银行
 */
class SafeBank {

    /**
     * 当前余额
     */
    private ThreadLocal<Integer> balance = ThreadLocal.withInitial(() -> 1000);
    private static final ThreadLocal<DecimalFormat> DECIMAL = ThreadLocal.withInitial(() -> {
        // the following will ensure a dot ('.') as decimal separator
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        return new DecimalFormat("##0.0#####",otherSymbols);
    });
    private static final ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat;
    });
    private static ThreadLocal<SimpleDateFormat> timeFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd mm:ss"));
    /**
     * 存款
     *
     * @param money 存款金额
     */
    public void deposit(int money) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " -> 当前账户余额为：" + this.balance.get());
        this.balance.set(this.balance.get() + money);
        System.out.println(threadName + " -> 存入 " + money + " 后，当前账户余额为：" + this.balance.get());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
