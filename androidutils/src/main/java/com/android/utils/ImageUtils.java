package com.android.utils;

import android.graphics.Rect;
import android.view.Choreographer;

import java.nio.ByteBuffer;

public class ImageUtils {

    // Used to load the 'utils' library on application startup.
    static {
        System.loadLibrary("image_utils");
    }

    public static native boolean copyByteBuffer(ByteBuffer src,ByteBuffer dest);

    public static native void readBufferByOpenCV(String path,ByteBuffer buffer,int mode);

    public static native void readRgbBufferByOpenCV(String path,ByteBuffer buffer);

    public static native void readBgrBufferByOpenCV(String path,ByteBuffer buffer);

    public static native void bgr2rgb(ByteBuffer bgr,ByteBuffer rgb,int width,int height);

    public static native void yuvToRgb(ByteBuffer yuv,ByteBuffer rgb,int width,int height);

    private static native byte[] clipData(ByteBuffer src, int srcWidth,int srcHeight,int rectX,int rectY,int rectWidth,int rectHeight);

    public static native void clipData4Rgb(ByteBuffer src,ByteBuffer dest, int srcWidth,int srcHeight,int rectX,int rectY,int rectWidth,int rectHeight);

    private static native ByteBuffer clipData2(String path,  int rectX,int rectY,int rectWidth,int rectHeight);

    public static class ImreadModes {
        public static int IMREAD_UNCHANGED  = -1; //!< If set; return the loaded image as is (with alpha channel, otherwise it gets cropped).
        public static int IMREAD_GRAYSCALE            = 0;  //!< If set, always convert image to the single channel grayscale image (codec internal conversion).
        public static int IMREAD_COLOR                = 1;  //!< If set, always convert image to the 3 channel BGR color image.
        public static int IMREAD_ANYDEPTH             = 2;  //!< If set, return 16-bit/32-bit image when the input has the corresponding depth, otherwise convert it to 8-bit.
        public static int IMREAD_ANYCOLOR             = 4;  //!< If set, the image is read in any possible color format.
        public static int IMREAD_LOAD_GDAL            = 8;  //!< If set, use the gdal driver for loading the image.
        public static int IMREAD_REDUCED_GRAYSCALE_2  = 16; //!< If set, always convert image to the single channel grayscale image and the image size reduced 1/2.
        public static int IMREAD_REDUCED_COLOR_2      = 17; //!< If set, always convert image to the 3 channel BGR color image and the image size reduced 1/2.
        public static int IMREAD_REDUCED_GRAYSCALE_4  = 32; //!< If set, always convert image to the single channel grayscale image and the image size reduced 1/4.
        public static int IMREAD_REDUCED_COLOR_4      = 33; //!< If set, always convert image to the 3 channel BGR color image and the image size reduced 1/4.
        public static int IMREAD_REDUCED_GRAYSCALE_8  = 64; //!< If set, always convert image to the single channel grayscale image and the image size reduced 1/8.
        public static int IMREAD_REDUCED_COLOR_8      = 65; //!< If set, always convert image to the 3 channel BGR color image and the image size reduced 1/8.
        public static int IMREAD_IGNORE_ORIENTATION   = 128; //!< If set, do not rotate the image according to EXIF's orientation flag.

    };
}