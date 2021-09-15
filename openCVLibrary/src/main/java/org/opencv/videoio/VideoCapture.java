//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.videoio;

import org.opencv.core.Mat;

// C++: class VideoCapture
//javadoc: VideoCapture

public class VideoCapture {

    protected final long nativeObj;

    protected VideoCapture(long addr) {
        nativeObj = addr;
    }

    //javadoc: VideoCapture::VideoCapture(filename, apiPreference)
    public VideoCapture(String filename, int apiPreference) {

        nativeObj = VideoCapture_0(filename, apiPreference);

    }

    //javadoc: VideoCapture::VideoCapture(filename)
    public VideoCapture(String filename) {

        nativeObj = VideoCapture_1(filename);

    }

    //
    // C++:   VideoCapture(String filename, int apiPreference)
    //

    //javadoc: VideoCapture::VideoCapture(index)
    public VideoCapture(int index) {

        nativeObj = VideoCapture_2(index);

    }


    //
    // C++:   VideoCapture(String filename)
    //

    //javadoc: VideoCapture::VideoCapture()
    public VideoCapture() {

        nativeObj = VideoCapture_3();

    }


    //
    // C++:   VideoCapture(int index)
    //

    // internal usage only
    public static VideoCapture __fromPtr__(long addr) {
        return new VideoCapture(addr);
    }


    //
    // C++:   VideoCapture()
    //

    // C++:   VideoCapture(String filename, int apiPreference)
    private static native long VideoCapture_0(String filename, int apiPreference);


    //
    // C++:  bool grab()
    //

    // C++:   VideoCapture(String filename)
    private static native long VideoCapture_1(String filename);


    //
    // C++:  bool isOpened()
    //

    // C++:   VideoCapture(int index)
    private static native long VideoCapture_2(int index);


    //
    // C++:  bool open(String filename, int apiPreference)
    //

    // C++:   VideoCapture()
    private static native long VideoCapture_3();


    //
    // C++:  bool open(String filename)
    //

    // C++:  bool grab()
    private static native boolean grab_0(long nativeObj);


    //
    // C++:  bool open(int cameraNum, int apiPreference)
    //

    // C++:  bool isOpened()
    private static native boolean isOpened_0(long nativeObj);


    //
    // C++:  bool open(int index)
    //

    // C++:  bool open(String filename, int apiPreference)
    private static native boolean open_0(long nativeObj, String filename, int apiPreference);


    //
    // C++:  bool read(Mat& image)
    //

    // C++:  bool open(String filename)
    private static native boolean open_1(long nativeObj, String filename);


    //
    // C++:  bool retrieve(Mat& image, int flag = 0)
    //

    // C++:  bool open(int cameraNum, int apiPreference)
    private static native boolean open_2(long nativeObj, int cameraNum, int apiPreference);

    // C++:  bool open(int index)
    private static native boolean open_3(long nativeObj, int index);


    //
    // C++:  bool set(int propId, double value)
    //

    // C++:  bool read(Mat& image)
    private static native boolean read_0(long nativeObj, long image_nativeObj);


    //
    // C++:  double get(int propId)
    //

    // C++:  bool retrieve(Mat& image, int flag = 0)
    private static native boolean retrieve_0(long nativeObj, long image_nativeObj, int flag);


    //
    // C++:  void release()
    //

    private static native boolean retrieve_1(long nativeObj, long image_nativeObj);

    // C++:  bool set(int propId, double value)
    private static native boolean set_0(long nativeObj, int propId, double value);

    // C++:  double get(int propId)
    private static native double get_0(long nativeObj, int propId);

    // C++:  void release()
    private static native void release_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: VideoCapture::grab()
    public boolean grab() {

        return grab_0(nativeObj);
    }

    //javadoc: VideoCapture::isOpened()
    public boolean isOpened() {

        return isOpened_0(nativeObj);
    }

    //javadoc: VideoCapture::open(filename, apiPreference)
    public boolean open(String filename, int apiPreference) {

        return open_0(nativeObj, filename, apiPreference);
    }

    //javadoc: VideoCapture::open(filename)
    public boolean open(String filename) {

        return open_1(nativeObj, filename);
    }

    //javadoc: VideoCapture::open(cameraNum, apiPreference)
    public boolean open(int cameraNum, int apiPreference) {

        return open_2(nativeObj, cameraNum, apiPreference);
    }

    //javadoc: VideoCapture::open(index)
    public boolean open(int index) {

        return open_3(nativeObj, index);
    }

    //javadoc: VideoCapture::read(image)
    public boolean read(Mat image) {

        return read_0(nativeObj, image.nativeObj);
    }

    //javadoc: VideoCapture::retrieve(image, flag)
    public boolean retrieve(Mat image, int flag) {

        return retrieve_0(nativeObj, image.nativeObj, flag);
    }

    //javadoc: VideoCapture::retrieve(image)
    public boolean retrieve(Mat image) {

        return retrieve_1(nativeObj, image.nativeObj);
    }

    //javadoc: VideoCapture::set(propId, value)
    public boolean set(int propId, double value) {

        return set_0(nativeObj, propId, value);
    }

    //javadoc: VideoCapture::get(propId)
    public double get(int propId) {

        return get_0(nativeObj, propId);
    }

    //javadoc: VideoCapture::release()
    public void release() {

        release_0(nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
