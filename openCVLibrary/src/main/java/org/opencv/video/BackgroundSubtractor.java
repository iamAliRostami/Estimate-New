//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.video;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

// C++: class BackgroundSubtractor
//javadoc: BackgroundSubtractor

public class BackgroundSubtractor extends Algorithm {

    protected BackgroundSubtractor(long addr) {
        super(addr);
    }

    // internal usage only
    public static BackgroundSubtractor __fromPtr__(long addr) {
        return new BackgroundSubtractor(addr);
    }

    //
    // C++:  void apply(Mat image, Mat& fgmask, double learningRate = -1)
    //

    // C++:  void apply(Mat image, Mat& fgmask, double learningRate = -1)
    private static native void apply_0(long nativeObj, long image_nativeObj, long fgmask_nativeObj, double learningRate);

    private static native void apply_1(long nativeObj, long image_nativeObj, long fgmask_nativeObj);


    //
    // C++:  void getBackgroundImage(Mat& backgroundImage)
    //

    // C++:  void getBackgroundImage(Mat& backgroundImage)
    private static native void getBackgroundImage_0(long nativeObj, long backgroundImage_nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: BackgroundSubtractor::apply(image, fgmask, learningRate)
    public void apply(Mat image, Mat fgmask, double learningRate) {

        apply_0(nativeObj, image.nativeObj, fgmask.nativeObj, learningRate);

    }

    //javadoc: BackgroundSubtractor::apply(image, fgmask)
    public void apply(Mat image, Mat fgmask) {

        apply_1(nativeObj, image.nativeObj, fgmask.nativeObj);

    }

    //javadoc: BackgroundSubtractor::getBackgroundImage(backgroundImage)
    public void getBackgroundImage(Mat backgroundImage) {

        getBackgroundImage_0(nativeObj, backgroundImage.nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
