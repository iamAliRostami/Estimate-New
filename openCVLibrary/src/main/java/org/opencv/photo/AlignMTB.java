//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.photo;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.utils.Converters;

import java.util.List;

// C++: class AlignMTB
//javadoc: AlignMTB

public class AlignMTB extends AlignExposures {

    protected AlignMTB(long addr) {
        super(addr);
    }

    // internal usage only
    public static AlignMTB __fromPtr__(long addr) {
        return new AlignMTB(addr);
    }

    //
    // C++:  Point calculateShift(Mat img0, Mat img1)
    //

    // C++:  Point calculateShift(Mat img0, Mat img1)
    private static native double[] calculateShift_0(long nativeObj, long img0_nativeObj, long img1_nativeObj);


    //
    // C++:  bool getCut()
    //

    // C++:  bool getCut()
    private static native boolean getCut_0(long nativeObj);


    //
    // C++:  int getExcludeRange()
    //

    // C++:  int getExcludeRange()
    private static native int getExcludeRange_0(long nativeObj);


    //
    // C++:  int getMaxBits()
    //

    // C++:  int getMaxBits()
    private static native int getMaxBits_0(long nativeObj);


    //
    // C++:  void computeBitmaps(Mat img, Mat& tb, Mat& eb)
    //

    // C++:  void computeBitmaps(Mat img, Mat& tb, Mat& eb)
    private static native void computeBitmaps_0(long nativeObj, long img_nativeObj, long tb_nativeObj, long eb_nativeObj);


    //
    // C++:  void process(vector_Mat src, vector_Mat dst, Mat times, Mat response)
    //

    // C++:  void process(vector_Mat src, vector_Mat dst, Mat times, Mat response)
    private static native void process_0(long nativeObj, long src_mat_nativeObj, long dst_mat_nativeObj, long times_nativeObj, long response_nativeObj);


    //
    // C++:  void process(vector_Mat src, vector_Mat dst)
    //

    // C++:  void process(vector_Mat src, vector_Mat dst)
    private static native void process_1(long nativeObj, long src_mat_nativeObj, long dst_mat_nativeObj);


    //
    // C++:  void setCut(bool value)
    //

    // C++:  void setCut(bool value)
    private static native void setCut_0(long nativeObj, boolean value);


    //
    // C++:  void setExcludeRange(int exclude_range)
    //

    // C++:  void setExcludeRange(int exclude_range)
    private static native void setExcludeRange_0(long nativeObj, int exclude_range);


    //
    // C++:  void setMaxBits(int max_bits)
    //

    // C++:  void setMaxBits(int max_bits)
    private static native void setMaxBits_0(long nativeObj, int max_bits);


    //
    // C++:  void shiftMat(Mat src, Mat& dst, Point shift)
    //

    // C++:  void shiftMat(Mat src, Mat& dst, Point shift)
    private static native void shiftMat_0(long nativeObj, long src_nativeObj, long dst_nativeObj, double shift_x, double shift_y);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: AlignMTB::calculateShift(img0, img1)
    public Point calculateShift(Mat img0, Mat img1) {

        return new Point(calculateShift_0(nativeObj, img0.nativeObj, img1.nativeObj));
    }

    //javadoc: AlignMTB::getCut()
    public boolean getCut() {

        return getCut_0(nativeObj);
    }

    //javadoc: AlignMTB::setCut(value)
    public void setCut(boolean value) {

        setCut_0(nativeObj, value);

    }

    //javadoc: AlignMTB::getExcludeRange()
    public int getExcludeRange() {

        return getExcludeRange_0(nativeObj);
    }

    //javadoc: AlignMTB::setExcludeRange(exclude_range)
    public void setExcludeRange(int exclude_range) {

        setExcludeRange_0(nativeObj, exclude_range);

    }

    //javadoc: AlignMTB::getMaxBits()
    public int getMaxBits() {

        return getMaxBits_0(nativeObj);
    }

    //javadoc: AlignMTB::setMaxBits(max_bits)
    public void setMaxBits(int max_bits) {

        setMaxBits_0(nativeObj, max_bits);

    }

    //javadoc: AlignMTB::computeBitmaps(img, tb, eb)
    public void computeBitmaps(Mat img, Mat tb, Mat eb) {

        computeBitmaps_0(nativeObj, img.nativeObj, tb.nativeObj, eb.nativeObj);

    }

    //javadoc: AlignMTB::process(src, dst, times, response)
    public void process(List<Mat> src, List<Mat> dst, Mat times, Mat response) {
        Mat src_mat = Converters.vector_Mat_to_Mat(src);
        Mat dst_mat = Converters.vector_Mat_to_Mat(dst);
        process_0(nativeObj, src_mat.nativeObj, dst_mat.nativeObj, times.nativeObj, response.nativeObj);

    }

    //javadoc: AlignMTB::process(src, dst)
    public void process(List<Mat> src, List<Mat> dst) {
        Mat src_mat = Converters.vector_Mat_to_Mat(src);
        Mat dst_mat = Converters.vector_Mat_to_Mat(dst);
        process_1(nativeObj, src_mat.nativeObj, dst_mat.nativeObj);

    }

    //javadoc: AlignMTB::shiftMat(src, dst, shift)
    public void shiftMat(Mat src, Mat dst, Point shift) {

        shiftMat_0(nativeObj, src.nativeObj, dst.nativeObj, shift.x, shift.y);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
