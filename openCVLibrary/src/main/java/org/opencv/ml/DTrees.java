//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;

// C++: class DTrees
//javadoc: DTrees

public class DTrees extends StatModel {

    public static final int
            PREDICT_AUTO = 0,
            PREDICT_SUM = (1 << 8),
            PREDICT_MAX_VOTE = (2 << 8),
            PREDICT_MASK = (3 << 8);

    protected DTrees(long addr) {
        super(addr);
    }

    // internal usage only
    public static DTrees __fromPtr__(long addr) {
        return new DTrees(addr);
    }


    //
    // C++:  Mat getPriors()
    //

    //javadoc: DTrees::create()
    public static DTrees create() {

        return DTrees.__fromPtr__(create_0());
    }


    //
    // C++: static Ptr_DTrees create()
    //

    //javadoc: DTrees::load(filepath, nodeName)
    public static DTrees load(String filepath, String nodeName) {

        return DTrees.__fromPtr__(load_0(filepath, nodeName));
    }


    //
    // C++: static Ptr_DTrees load(String filepath, String nodeName = String())
    //

    //javadoc: DTrees::load(filepath)
    public static DTrees load(String filepath) {

        return DTrees.__fromPtr__(load_1(filepath));
    }

    // C++:  Mat getPriors()
    private static native long getPriors_0(long nativeObj);


    //
    // C++:  bool getTruncatePrunedTree()
    //

    // C++: static Ptr_DTrees create()
    private static native long create_0();


    //
    // C++:  bool getUse1SERule()
    //

    // C++: static Ptr_DTrees load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);


    //
    // C++:  bool getUseSurrogates()
    //

    private static native long load_1(String filepath);


    //
    // C++:  float getRegressionAccuracy()
    //

    // C++:  bool getTruncatePrunedTree()
    private static native boolean getTruncatePrunedTree_0(long nativeObj);


    //
    // C++:  int getCVFolds()
    //

    // C++:  bool getUse1SERule()
    private static native boolean getUse1SERule_0(long nativeObj);


    //
    // C++:  int getMaxCategories()
    //

    // C++:  bool getUseSurrogates()
    private static native boolean getUseSurrogates_0(long nativeObj);


    //
    // C++:  int getMaxDepth()
    //

    // C++:  float getRegressionAccuracy()
    private static native float getRegressionAccuracy_0(long nativeObj);


    //
    // C++:  int getMinSampleCount()
    //

    // C++:  int getCVFolds()
    private static native int getCVFolds_0(long nativeObj);


    //
    // C++:  void setCVFolds(int val)
    //

    // C++:  int getMaxCategories()
    private static native int getMaxCategories_0(long nativeObj);


    //
    // C++:  void setMaxCategories(int val)
    //

    // C++:  int getMaxDepth()
    private static native int getMaxDepth_0(long nativeObj);


    //
    // C++:  void setMaxDepth(int val)
    //

    // C++:  int getMinSampleCount()
    private static native int getMinSampleCount_0(long nativeObj);


    //
    // C++:  void setMinSampleCount(int val)
    //

    // C++:  void setCVFolds(int val)
    private static native void setCVFolds_0(long nativeObj, int val);


    //
    // C++:  void setPriors(Mat val)
    //

    // C++:  void setMaxCategories(int val)
    private static native void setMaxCategories_0(long nativeObj, int val);


    //
    // C++:  void setRegressionAccuracy(float val)
    //

    // C++:  void setMaxDepth(int val)
    private static native void setMaxDepth_0(long nativeObj, int val);


    //
    // C++:  void setTruncatePrunedTree(bool val)
    //

    // C++:  void setMinSampleCount(int val)
    private static native void setMinSampleCount_0(long nativeObj, int val);


    //
    // C++:  void setUse1SERule(bool val)
    //

    // C++:  void setPriors(Mat val)
    private static native void setPriors_0(long nativeObj, long val_nativeObj);


    //
    // C++:  void setUseSurrogates(bool val)
    //

    // C++:  void setRegressionAccuracy(float val)
    private static native void setRegressionAccuracy_0(long nativeObj, float val);

    // C++:  void setTruncatePrunedTree(bool val)
    private static native void setTruncatePrunedTree_0(long nativeObj, boolean val);

    // C++:  void setUse1SERule(bool val)
    private static native void setUse1SERule_0(long nativeObj, boolean val);

    // C++:  void setUseSurrogates(bool val)
    private static native void setUseSurrogates_0(long nativeObj, boolean val);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: DTrees::getPriors()
    public Mat getPriors() {

        return new Mat(getPriors_0(nativeObj));
    }

    //javadoc: DTrees::setPriors(val)
    public void setPriors(Mat val) {

        setPriors_0(nativeObj, val.nativeObj);

    }

    //javadoc: DTrees::getTruncatePrunedTree()
    public boolean getTruncatePrunedTree() {

        return getTruncatePrunedTree_0(nativeObj);
    }

    //javadoc: DTrees::setTruncatePrunedTree(val)
    public void setTruncatePrunedTree(boolean val) {

        setTruncatePrunedTree_0(nativeObj, val);

    }

    //javadoc: DTrees::getUse1SERule()
    public boolean getUse1SERule() {

        return getUse1SERule_0(nativeObj);
    }

    //javadoc: DTrees::setUse1SERule(val)
    public void setUse1SERule(boolean val) {

        setUse1SERule_0(nativeObj, val);

    }

    //javadoc: DTrees::getUseSurrogates()
    public boolean getUseSurrogates() {

        return getUseSurrogates_0(nativeObj);
    }

    //javadoc: DTrees::setUseSurrogates(val)
    public void setUseSurrogates(boolean val) {

        setUseSurrogates_0(nativeObj, val);

    }

    //javadoc: DTrees::getRegressionAccuracy()
    public float getRegressionAccuracy() {

        return getRegressionAccuracy_0(nativeObj);
    }

    //javadoc: DTrees::setRegressionAccuracy(val)
    public void setRegressionAccuracy(float val) {

        setRegressionAccuracy_0(nativeObj, val);

    }

    //javadoc: DTrees::getCVFolds()
    public int getCVFolds() {

        return getCVFolds_0(nativeObj);
    }

    //javadoc: DTrees::setCVFolds(val)
    public void setCVFolds(int val) {

        setCVFolds_0(nativeObj, val);

    }

    //javadoc: DTrees::getMaxCategories()
    public int getMaxCategories() {

        return getMaxCategories_0(nativeObj);
    }

    //javadoc: DTrees::setMaxCategories(val)
    public void setMaxCategories(int val) {

        setMaxCategories_0(nativeObj, val);

    }

    //javadoc: DTrees::getMaxDepth()
    public int getMaxDepth() {

        return getMaxDepth_0(nativeObj);
    }

    //javadoc: DTrees::setMaxDepth(val)
    public void setMaxDepth(int val) {

        setMaxDepth_0(nativeObj, val);

    }

    //javadoc: DTrees::getMinSampleCount()
    public int getMinSampleCount() {

        return getMinSampleCount_0(nativeObj);
    }

    //javadoc: DTrees::setMinSampleCount(val)
    public void setMinSampleCount(int val) {

        setMinSampleCount_0(nativeObj, val);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
