//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.features2d;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.List;

// C++: class javaDescriptorExtractor
//javadoc: javaDescriptorExtractor
@Deprecated
public class DescriptorExtractor {

    public static final int
            SIFT = 1,
            SURF = 2,
            ORB = 3,
            BRIEF = 4,
            BRISK = 5,
            FREAK = 6,
            AKAZE = 7;
    //            OPPONENT_SIFT = OPPONENTEXTRACTOR + SIFT,
//            OPPONENT_SURF = OPPONENTEXTRACTOR + SURF,
//            OPPONENT_ORB = OPPONENTEXTRACTOR + ORB,
//            OPPONENT_BRIEF = OPPONENTEXTRACTOR + BRIEF,
//            OPPONENT_BRISK = OPPONENTEXTRACTOR + BRISK,
//            OPPONENT_FREAK = OPPONENTEXTRACTOR + FREAK,
//            OPPONENT_AKAZE = OPPONENTEXTRACTOR + AKAZE;
    private static final int
            OPPONENTEXTRACTOR = 1000;
    protected final long nativeObj;

    protected DescriptorExtractor(long addr) {
        nativeObj = addr;
    }

    // internal usage only
    public static DescriptorExtractor __fromPtr__(long addr) {
        return new DescriptorExtractor(addr);
    }

    //javadoc: javaDescriptorExtractor::create(extractorType)
    public static DescriptorExtractor create(int extractorType) {

        return DescriptorExtractor.__fromPtr__(create_0(extractorType));
    }


    //
    // C++: static Ptr_javaDescriptorExtractor create(int extractorType)
    //

    // C++: static Ptr_javaDescriptorExtractor create(int extractorType)
    private static native long create_0(int extractorType);


    //
    // C++:  bool empty()
    //

    // C++:  bool empty()
    private static native boolean empty_0(long nativeObj);


    //
    // C++:  int descriptorSize()
    //

    // C++:  int descriptorSize()
    private static native int descriptorSize_0(long nativeObj);


    //
    // C++:  int descriptorType()
    //

    // C++:  int descriptorType()
    private static native int descriptorType_0(long nativeObj);


    //
    // C++:  void compute(Mat image, vector_KeyPoint& keypoints, Mat descriptors)
    //

    // C++:  void compute(Mat image, vector_KeyPoint& keypoints, Mat descriptors)
    private static native void compute_0(long nativeObj, long image_nativeObj, long keypoints_mat_nativeObj, long descriptors_nativeObj);


    //
    // C++:  void compute(vector_Mat images, vector_vector_KeyPoint& keypoints, vector_Mat& descriptors)
    //

    // C++:  void compute(vector_Mat images, vector_vector_KeyPoint& keypoints, vector_Mat& descriptors)
    private static native void compute_1(long nativeObj, long images_mat_nativeObj, long keypoints_mat_nativeObj, long descriptors_mat_nativeObj);


    //
    // C++:  void read(String fileName)
    //

    // C++:  void read(String fileName)
    private static native void read_0(long nativeObj, String fileName);


    //
    // C++:  void write(String fileName)
    //

    // C++:  void write(String fileName)
    private static native void write_0(long nativeObj, String fileName);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: javaDescriptorExtractor::empty()
    public boolean empty() {

        return empty_0(nativeObj);
    }

    //javadoc: javaDescriptorExtractor::descriptorSize()
    public int descriptorSize() {

        return descriptorSize_0(nativeObj);
    }

    //javadoc: javaDescriptorExtractor::descriptorType()
    public int descriptorType() {

        return descriptorType_0(nativeObj);
    }

    //javadoc: javaDescriptorExtractor::compute(image, keypoints, descriptors)
    public void compute(Mat image, MatOfKeyPoint keypoints, Mat descriptors) {
        compute_0(nativeObj, image.nativeObj, keypoints.nativeObj, descriptors.nativeObj);

    }

    //javadoc: javaDescriptorExtractor::compute(images, keypoints, descriptors)
    public void compute(List<Mat> images, List<MatOfKeyPoint> keypoints, List<Mat> descriptors) {
        Mat images_mat = Converters.vector_Mat_to_Mat(images);
        List<Mat> keypoints_tmplm = new ArrayList<>((keypoints != null) ? keypoints.size() : 0);
        Mat keypoints_mat = Converters.vector_vector_KeyPoint_to_Mat(keypoints, keypoints_tmplm);
        Mat descriptors_mat = new Mat();
        compute_1(nativeObj, images_mat.nativeObj, keypoints_mat.nativeObj, descriptors_mat.nativeObj);
        Converters.Mat_to_vector_vector_KeyPoint(keypoints_mat, keypoints);
        keypoints_mat.release();
        Converters.Mat_to_vector_Mat(descriptors_mat, descriptors);
        descriptors_mat.release();
    }

    //javadoc: javaDescriptorExtractor::read(fileName)
    public void read(String fileName) {

        read_0(nativeObj, fileName);

    }

    //javadoc: javaDescriptorExtractor::write(fileName)
    public void write(String fileName) {

        write_0(nativeObj, fileName);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
