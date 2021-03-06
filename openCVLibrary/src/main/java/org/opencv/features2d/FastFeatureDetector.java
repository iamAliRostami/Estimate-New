//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.features2d;

// C++: class FastFeatureDetector
//javadoc: FastFeatureDetector

public class FastFeatureDetector extends Feature2D {

    public static final int
            TYPE_5_8 = 0,
            TYPE_7_12 = 1,
            TYPE_9_16 = 2,
            THRESHOLD = 10000,
            NONMAX_SUPPRESSION = 10001,
            FAST_N = 10002;

    protected FastFeatureDetector(long addr) {
        super(addr);
    }

    // internal usage only
    public static FastFeatureDetector __fromPtr__(long addr) {
        return new FastFeatureDetector(addr);
    }


    //
    // C++: static Ptr_FastFeatureDetector create(int threshold = 10, bool nonmaxSuppression = true, int type = FastFeatureDetector::TYPE_9_16)
    //

    //javadoc: FastFeatureDetector::create(threshold, nonmaxSuppression, type)
    public static FastFeatureDetector create(int threshold, boolean nonmaxSuppression, int type) {

        return FastFeatureDetector.__fromPtr__(create_0(threshold, nonmaxSuppression, type));
    }

    //javadoc: FastFeatureDetector::create()
    public static FastFeatureDetector create() {

        return FastFeatureDetector.__fromPtr__(create_1());
    }


    //
    // C++:  String getDefaultName()
    //

    // C++: static Ptr_FastFeatureDetector create(int threshold = 10, bool nonmaxSuppression = true, int type = FastFeatureDetector::TYPE_9_16)
    private static native long create_0(int threshold, boolean nonmaxSuppression, int type);


    //
    // C++:  bool getNonmaxSuppression()
    //

    private static native long create_1();


    //
    // C++:  int getThreshold()
    //

    // C++:  String getDefaultName()
    private static native String getDefaultName_0(long nativeObj);


    //
    // C++:  int getType()
    //

    // C++:  bool getNonmaxSuppression()
    private static native boolean getNonmaxSuppression_0(long nativeObj);


    //
    // C++:  void setNonmaxSuppression(bool f)
    //

    // C++:  int getThreshold()
    private static native int getThreshold_0(long nativeObj);


    //
    // C++:  void setThreshold(int threshold)
    //

    // C++:  int getType()
    private static native int getType_0(long nativeObj);


    //
    // C++:  void setType(int type)
    //

    // C++:  void setNonmaxSuppression(bool f)
    private static native void setNonmaxSuppression_0(long nativeObj, boolean f);

    // C++:  void setThreshold(int threshold)
    private static native void setThreshold_0(long nativeObj, int threshold);

    // C++:  void setType(int type)
    private static native void setType_0(long nativeObj, int type);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: FastFeatureDetector::getDefaultName()
    public String getDefaultName() {

        return getDefaultName_0(nativeObj);
    }

    //javadoc: FastFeatureDetector::getNonmaxSuppression()
    public boolean getNonmaxSuppression() {

        return getNonmaxSuppression_0(nativeObj);
    }

    //javadoc: FastFeatureDetector::setNonmaxSuppression(f)
    public void setNonmaxSuppression(boolean f) {

        setNonmaxSuppression_0(nativeObj, f);

    }

    //javadoc: FastFeatureDetector::getThreshold()
    public int getThreshold() {

        return getThreshold_0(nativeObj);
    }

    //javadoc: FastFeatureDetector::setThreshold(threshold)
    public void setThreshold(int threshold) {

        setThreshold_0(nativeObj, threshold);

    }

    //javadoc: FastFeatureDetector::getType()
    public int getType() {

        return getType_0(nativeObj);
    }

    //javadoc: FastFeatureDetector::setType(type)
    public void setType(int type) {

        setType_0(nativeObj, type);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
