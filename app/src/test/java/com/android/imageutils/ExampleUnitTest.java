package com.android.imageutils;

import android.os.Handler;
import android.os.MessageQueue;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private boolean flag = false;
    @Test
    public void sss() throws InterruptedException {

        for (int i=0;i<Integer.MAX_VALUE;i++) {
            int finalI = i;
            flag = false;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!flag) {
                    }
                    System.out.println("结束了:" + "  第" + finalI + "次");

                }
            });
            thread.start();
            Thread.sleep(100);
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    flag = true;
                }
            });

            thread1.start();
            thread1.join();
            thread.join();
            System.out.println("over:" + "  第" + finalI + "次");
        }
    }
}