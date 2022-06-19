package com.android.imageutils;

import android.graphics.Rect;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Looper;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.utils.ImageUtils;
import com.imi.sdk.faceid.dl.internal.ImiUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class MainActivity extends AppCompatActivity {
    ByteBuffer buffer = ByteBuffer.allocateDirect(640*480*3).order(ByteOrder.nativeOrder());
    String path ="/sdcard/IMIFace/FaceImage/1647939316751_x_228_y_146_w_115_y_173.rgb";
    int x = 228;
    int y = 146;
    int w = 115;
    int h = 173;
    ByteBuffer buffer1 = ByteBuffer.allocateDirect(w*h*3).order(ByteOrder.nativeOrder());
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageUtils.readBufferByOpenCV(path,buffer,1,480,640,3);

        ImiUtil.readBufferFromFile(path,buffer,480*640*3);
        ImageUtils.clipData4Rgb(buffer,buffer1,640,480,x,y,w,h);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void  aaa(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Log.w("ttt",Thread.currentThread().getName()+":"+Thread.currentThread().getState().name()+":" );
                Looper.loop();

            }
        });

        thread.setName("xixihaha");
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.e("ttt",Looper.getMainLooper().getThread().getName()+":"+Looper.getMainLooper().getThread().getState().name()+":" );
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
    }
}