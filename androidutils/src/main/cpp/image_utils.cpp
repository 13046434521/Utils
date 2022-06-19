#include <jni.h>
#include <string>
#include "android_log.h"
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_android_utils_ImageUtils_copyByteBuffer(JNIEnv *env, jclass clazz, jobject src,
                                                 jobject dest) {
    int src_length = env->GetDirectBufferCapacity(src);
    int dest_length = env->GetDirectBufferCapacity(dest);

    if (src_length != dest_length) {
        return false;
    }

    void *src_addr = env->GetDirectBufferAddress(src);
    void *dest_addr = env->GetDirectBufferAddress(dest);

    memcpy(dest_addr, src_addr, src_length);

    return true;
}


extern "C"
JNIEXPORT void JNICALL
Java_com_android_utils_ImageUtils_readBufferByOpenCV(JNIEnv *env, jclass clazz, jstring path,
                                                     jobject buffer,
                                                     jint mode) {
    void* data = env->GetDirectBufferAddress(buffer);
    string data_path = env->GetStringUTFChars(path,JNI_FALSE);
    Mat mat = imread(data_path,mode);

    memcpy(data,mat.data,mat.channels()* mat.cols* mat.rows);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_android_utils_ImageUtils_readRgbBufferByOpenCV(JNIEnv *env, jclass clazz, jstring path,
                                                      jobject buffer) {
    void* data = env->GetDirectBufferAddress(buffer);
    string data_path = env->GetStringUTFChars(path,JNI_FALSE);
    Mat bgrMat = imread(data_path);
    LOGE("444444444444:%d,%d,%d",bgrMat.channels(),bgrMat.cols,bgrMat.rows);
    Mat rgbMat = Mat(bgrMat.rows,bgrMat.cols,CV_8UC3,data);
    cvtColor(bgrMat,rgbMat,CV_BGR2RGB);
    memcpy(data,rgbMat.data,rgbMat.channels()* rgbMat.cols* rgbMat.rows);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_android_utils_ImageUtils_readBgrBufferByOpenCV(JNIEnv *env, jclass clazz, jstring path,
                                                        jobject buffer) {
    void* data = env->GetDirectBufferAddress(buffer);
    string data_path = env->GetStringUTFChars(path,JNI_FALSE);
    Mat mat = imread(data_path);
    LOGE("444444444444:%d,%d",mat.channels(),mat.cols,mat.rows);
    memcpy(data,mat.data,mat.channels()* mat.cols* mat.rows);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_android_utils_ImageUtils_bgr2rgb(JNIEnv *env, jclass clazz, jobject src, jobject dest,
                                          jint width, jint height) {
    void *srcAddress = env->GetDirectBufferAddress(src);
    void *destAddress = env->GetDirectBufferAddress(dest);

    Mat bgr = Mat(height, width, CV_8UC3, srcAddress);
    Mat rgb = Mat(height, width, CV_8UC3, destAddress);
    cvtColor(bgr, rgb, COLOR_BGR2RGB);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_utils_ImageUtils_yuvToRgb(JNIEnv *env, jclass clazz, jobject yuv_data,
                                           jobject rgb_data, jint width, jint height) {
//    jbyte * yuvData = (jbyte*)env->GetDirectBufferAddress(yuv_data);
//    jbyte * rgbData = (jbyte*)env->GetDirectBufferAddress(rgb_data);
//    Mat rgbMat(height,width,CV_8UC3);
//    Mat yuvMat(height*3/2,width,CV_8UC1);
//
//    //第一个是目标，第二个是源
//    memcpy(yuvMat.data,yuvData,height * width * 3/2);
//    cv::cvtColor(yuvMat,rgbMat, cv::COLOR_YUV2RGB_NV21);
//    memcpy(rgbData,rgbMat.data,height*width*3);


    jbyte *yuvData = (jbyte *) env->GetDirectBufferAddress(yuv_data);
    jbyte *rgbData = (jbyte *) env->GetDirectBufferAddress(rgb_data);
    Mat rgbMat(height, width, CV_8UC3, rgbData);
    Mat yuvMat(height * 3 / 2, width, CV_8UC1, yuvData);

    cv::cvtColor(yuvMat, rgbMat, cv::COLOR_YUV2RGB_NV21);
}

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_android_utils_ImageUtils_clipData(JNIEnv *env, jclass clazz, jobject src, jint src_width,
                                           jint src_height, jint rect_x, jint rect_y,
                                           jint rect_width, jint rect_height) {
    void *srcPtr = env->GetDirectBufferAddress(src);
    Mat srcMat(src_height, src_width, CV_8UC3, srcPtr);
    Mat rectMat = srcMat.clone();
    Rect rect(rect_x, rect_y, rect_width, rect_height);
    Mat clipMat = rectMat(rect);

//    jclass byteBuffer = env->FindClass("java/nio/ByteBuffer");
//    jmethodID  jmethodId = env->GetStaticMethodID(byteBuffer,"allocateDirect","(I)Ljava/nio/ByteBuffer;");
//    jobject jobject1 = env->CallStaticObjectMethod(byteBuffer,jmethodId,src_height*src_width*3);


    jbyteArray bytes = env->NewByteArray(rect_width * rect_height * 3);
    memcpy(&bytes, clipMat.data, rect_width * rect_height * 3);

    return bytes;
}



extern "C"
JNIEXPORT jobject JNICALL
Java_com_android_utils_ImageUtils_clipData2(JNIEnv *env, jclass clazz, jstring path, jint rect_x,
                                            jint rect_y, jint rect_width, jint rect_height) {
    string rgbPath = env->GetStringUTFChars(path, NULL);
    Mat matData = imread(rgbPath, IMREAD_COLOR);
    Mat cloneImage = matData.clone();
    Rect rect(rect_x, rect_y, rect_width, rect_height);
    Mat resultData = cloneImage(rect);
//    cv::imwrite("sdcard/test.png",resultData);

    jobject jobject1;
    return jobject1;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_android_utils_ImageUtils_clipData4Rgb(JNIEnv *env, jclass clazz, jobject src, jobject dest,
                                            jint src_width, jint src_height, jint rect_x,
                                            jint rect_y, jint rect_width, jint rect_height) {
    void *src_ptr = env->GetDirectBufferAddress(src);
    void *dest_ptr = env->GetDirectBufferAddress(dest);

    Mat src_mat = Mat(src_height, src_width, CV_8UC3, src_ptr);
    Mat copy_mat = src_mat.clone();

    Rect rect = Rect(rect_x, rect_y,rect_width, rect_height);

    Mat clip_mat = Mat(copy_mat,rect);

    memcpy(dest_ptr, clip_mat.data, rect_height * rect_width * 3);
}