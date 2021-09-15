//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.features2d;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;

// C++: class BOWImgDescriptorExtractor
//javadoc: BOWImgDescriptorExtractor

public class BOWImgDescriptorExtractor {

    protected final long nativeObj;

    protected BOWImgDescriptorExtractor(long addr) {
        nativeObj = addr;
    }

    // internal usage only
    public static BOWImgDescriptorExtractor __fromPtr__(long addr) {
        return new BOWImgDescriptorExtractor(addr);
    }

    // C++:  Mat getVocabulary()
    private static native long getVocabulary_0(long nativeObj);

    //
    // C++:   BOWImgDescriptorExtractor(Ptr_DescriptorExtractor dextractor, Ptr_DescriptorMatcher dmatcher)
    //

    // Unknown type 'Ptr_DescriptorExtractor' (I), skipping the function


    //
    // C++:  Mat getVocabulary()
    //

    // C++:  int descriptorSize()
    private static native int descriptorSize_0(long nativeObj);


    //
    // C++:  int descriptorSize()
    //

    // C++:  int descriptorType()
    private static native int descriptorType_0(long nativeObj);


    //
    // C++:  int descriptorType()
    //

    // C++:  void compute2(Mat image, vector_KeyPoint keypoints, Mat& imgDescriptor)
    private static native void compute_0(long nativeObj, long image_nativeObj, long keypoints_mat_nativeObj, long imgDescriptor_nativeObj);


    //
    // C++:  void compute2(Mat image, vector_KeyPoint keypoints, Mat& imgDescriptor)
    //

    // C++:  void setVocabulary(Mat vocabulary)
    private static native void setVocabulary_0(long nativeObj, long vocabulary_nativeObj);


    //
    // C++:  void setVocabulary(Mat vocabulary)
    //

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: BOWImgDescriptorExtractor::getVocabulary()
    public Mat getVocabulary() {

        return new Mat(getVocabulary_0(nativeObj));
    }

    //javadoc: BOWImgDescriptorExtractor::setVocabulary(vocabulary)
    public void setVocabulary(Mat vocabulary) {

        setVocabulary_0(nativeObj, vocabulary.nativeObj);

    }

    //javadoc: BOWImgDescriptorExtractor::descriptorSize()
    public int descriptorSize() {

        return descriptorSize_0(nativeObj);
    }

    //javadoc: BOWImgDescriptorExtractor::descriptorType()
    public int descriptorType() {

        return descriptorType_0(nativeObj);
    }

    //javadoc: BOWImgDescriptorExtractor::compute2(image, keypoints, imgDescriptor)
    public void compute(Mat image, MatOfKeyPoint keypoints, Mat imgDescriptor) {
        compute_0(nativeObj, image.nativeObj, keypoints.nativeObj, imgDescriptor.nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
