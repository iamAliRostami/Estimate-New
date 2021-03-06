//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.videoio;

import org.opencv.core.Mat;
import org.opencv.core.Size;

// C++: class VideoWriter
//javadoc: VideoWriter

public class VideoWriter {

    protected final long nativeObj;

    protected VideoWriter(long addr) {
        nativeObj = addr;
    }

    //javadoc: VideoWriter::VideoWriter(filename, apiPreference, fourcc, fps, frameSize, isColor)
    public VideoWriter(String filename, int apiPreference, int fourcc, double fps, Size frameSize, boolean isColor) {

        nativeObj = VideoWriter_0(filename, apiPreference, fourcc, fps, frameSize.width, frameSize.height, isColor);

    }

    //javadoc: VideoWriter::VideoWriter(filename, apiPreference, fourcc, fps, frameSize)
    public VideoWriter(String filename, int apiPreference, int fourcc, double fps, Size frameSize) {

        nativeObj = VideoWriter_1(filename, apiPreference, fourcc, fps, frameSize.width, frameSize.height);

    }

    //
    // C++:   VideoWriter(String filename, int apiPreference, int fourcc, double fps, Size frameSize, bool isColor = true)
    //

    //javadoc: VideoWriter::VideoWriter(filename, fourcc, fps, frameSize, isColor)
    public VideoWriter(String filename, int fourcc, double fps, Size frameSize, boolean isColor) {

        nativeObj = VideoWriter_2(filename, fourcc, fps, frameSize.width, frameSize.height, isColor);

    }

    //javadoc: VideoWriter::VideoWriter(filename, fourcc, fps, frameSize)
    public VideoWriter(String filename, int fourcc, double fps, Size frameSize) {

        nativeObj = VideoWriter_3(filename, fourcc, fps, frameSize.width, frameSize.height);

    }


    //
    // C++:   VideoWriter(String filename, int fourcc, double fps, Size frameSize, bool isColor = true)
    //

    //javadoc: VideoWriter::VideoWriter()
    public VideoWriter() {

        nativeObj = VideoWriter_4();

    }

    // internal usage only
    public static VideoWriter __fromPtr__(long addr) {
        return new VideoWriter(addr);
    }


    //
    // C++:   VideoWriter()
    //

    //javadoc: VideoWriter::fourcc(c1, c2, c3, c4)
    public static int fourcc(char c1, char c2, char c3, char c4) {

        return fourcc_0(c1, c2, c3, c4);
    }


    //
    // C++:  bool isOpened()
    //

    // C++:   VideoWriter(String filename, int apiPreference, int fourcc, double fps, Size frameSize, bool isColor = true)
    private static native long VideoWriter_0(String filename, int apiPreference, int fourcc, double fps, double frameSize_width, double frameSize_height, boolean isColor);


    //
    // C++:  bool open(String filename, int apiPreference, int fourcc, double fps, Size frameSize, bool isColor = true)
    //

    private static native long VideoWriter_1(String filename, int apiPreference, int fourcc, double fps, double frameSize_width, double frameSize_height);

    // C++:   VideoWriter(String filename, int fourcc, double fps, Size frameSize, bool isColor = true)
    private static native long VideoWriter_2(String filename, int fourcc, double fps, double frameSize_width, double frameSize_height, boolean isColor);


    //
    // C++:  bool open(String filename, int fourcc, double fps, Size frameSize, bool isColor = true)
    //

    private static native long VideoWriter_3(String filename, int fourcc, double fps, double frameSize_width, double frameSize_height);

    // C++:   VideoWriter()
    private static native long VideoWriter_4();


    //
    // C++:  bool set(int propId, double value)
    //

    // C++:  bool isOpened()
    private static native boolean isOpened_0(long nativeObj);


    //
    // C++:  double get(int propId)
    //

    // C++:  bool open(String filename, int apiPreference, int fourcc, double fps, Size frameSize, bool isColor = true)
    private static native boolean open_0(long nativeObj, String filename, int apiPreference, int fourcc, double fps, double frameSize_width, double frameSize_height, boolean isColor);


    //
    // C++: static int fourcc(char c1, char c2, char c3, char c4)
    //

    private static native boolean open_1(long nativeObj, String filename, int apiPreference, int fourcc, double fps, double frameSize_width, double frameSize_height);


    //
    // C++:  void release()
    //

    // C++:  bool open(String filename, int fourcc, double fps, Size frameSize, bool isColor = true)
    private static native boolean open_2(long nativeObj, String filename, int fourcc, double fps, double frameSize_width, double frameSize_height, boolean isColor);


    //
    // C++:  void write(Mat image)
    //

    private static native boolean open_3(long nativeObj, String filename, int fourcc, double fps, double frameSize_width, double frameSize_height);

    // C++:  bool set(int propId, double value)
    private static native boolean set_0(long nativeObj, int propId, double value);

    // C++:  double get(int propId)
    private static native double get_0(long nativeObj, int propId);

    // C++: static int fourcc(char c1, char c2, char c3, char c4)
    private static native int fourcc_0(char c1, char c2, char c3, char c4);

    // C++:  void release()
    private static native void release_0(long nativeObj);

    // C++:  void write(Mat image)
    private static native void write_0(long nativeObj, long image_nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: VideoWriter::isOpened()
    public boolean isOpened() {

        return isOpened_0(nativeObj);
    }

    //javadoc: VideoWriter::open(filename, apiPreference, fourcc, fps, frameSize, isColor)
    public boolean open(String filename, int apiPreference, int fourcc, double fps, Size frameSize, boolean isColor) {

        return open_0(nativeObj, filename, apiPreference, fourcc, fps, frameSize.width, frameSize.height, isColor);
    }

    //javadoc: VideoWriter::open(filename, apiPreference, fourcc, fps, frameSize)
    public boolean open(String filename, int apiPreference, int fourcc, double fps, Size frameSize) {

        return open_1(nativeObj, filename, apiPreference, fourcc, fps, frameSize.width, frameSize.height);
    }

    //javadoc: VideoWriter::open(filename, fourcc, fps, frameSize, isColor)
    public boolean open(String filename, int fourcc, double fps, Size frameSize, boolean isColor) {

        return open_2(nativeObj, filename, fourcc, fps, frameSize.width, frameSize.height, isColor);
    }

    //javadoc: VideoWriter::open(filename, fourcc, fps, frameSize)
    public boolean open(String filename, int fourcc, double fps, Size frameSize) {

        return open_3(nativeObj, filename, fourcc, fps, frameSize.width, frameSize.height);
    }

    //javadoc: VideoWriter::set(propId, value)
    public boolean set(int propId, double value) {

        return set_0(nativeObj, propId, value);
    }

    //javadoc: VideoWriter::get(propId)
    public double get(int propId) {

        return get_0(nativeObj, propId);
    }

    //javadoc: VideoWriter::release()
    public void release() {

        release_0(nativeObj);

    }

    //javadoc: VideoWriter::write(image)
    public void write(Mat image) {

        write_0(nativeObj, image.nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
