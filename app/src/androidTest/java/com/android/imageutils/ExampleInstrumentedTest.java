package com.android.imageutils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        aaa();
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.android.imageutils", appContext.getPackageName());

    }
    public void  aaa() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Log.w("ttt", "1111");
                Looper.loop();
                Log.w("ttt", "2222");
            }
        });
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.e("ttt",thread.getState().toString());
    }
}