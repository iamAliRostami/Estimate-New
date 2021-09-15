//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

// C++: class SVMSGD
//javadoc: SVMSGD

public class SVMSGD extends StatModel {

    public static final int
            SGD = 0,
            ASGD = 1,
            SOFT_MARGIN = 0,
            HARD_MARGIN = 1;

    protected SVMSGD(long addr) {
        super(addr);
    }

    // internal usage only
    public static SVMSGD __fromPtr__(long addr) {
        return new SVMSGD(addr);
    }


    //
    // C++:  Mat getWeights()
    //

    //javadoc: SVMSGD::create()
    public static SVMSGD create() {

        return SVMSGD.__fromPtr__(create_0());
    }


    //
    // C++: static Ptr_SVMSGD create()
    //

    //javadoc: SVMSGD::load(filepath, nodeName)
    public static SVMSGD load(String filepath, String nodeName) {

        return SVMSGD.__fromPtr__(load_0(filepath, nodeName));
    }


    //
    // C++: static Ptr_SVMSGD load(String filepath, String nodeName = String())
    //

    //javadoc: SVMSGD::load(filepath)
    public static SVMSGD load(String filepath) {

        return SVMSGD.__fromPtr__(load_1(filepath));
    }

    // C++:  Mat getWeights()
    private static native long getWeights_0(long nativeObj);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    // C++: static Ptr_SVMSGD create()
    private static native long create_0();


    //
    // C++:  float getInitialStepSize()
    //

    // C++: static Ptr_SVMSGD load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);


    //
    // C++:  float getMarginRegularization()
    //

    private static native long load_1(String filepath);


    //
    // C++:  float getShift()
    //

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  float getStepDecreasingPower()
    //

    // C++:  float getInitialStepSize()
    private static native float getInitialStepSize_0(long nativeObj);


    //
    // C++:  int getMarginType()
    //

    // C++:  float getMarginRegularization()
    private static native float getMarginRegularization_0(long nativeObj);


    //
    // C++:  int getSvmsgdType()
    //

    // C++:  float getShift()
    private static native float getShift_0(long nativeObj);


    //
    // C++:  void setInitialStepSize(float InitialStepSize)
    //

    // C++:  float getStepDecreasingPower()
    private static native float getStepDecreasingPower_0(long nativeObj);


    //
    // C++:  void setMarginRegularization(float marginRegularization)
    //

    // C++:  int getMarginType()
    private static native int getMarginType_0(long nativeObj);


    //
    // C++:  void setMarginType(int marginType)
    //

    // C++:  int getSvmsgdType()
    private static native int getSvmsgdType_0(long nativeObj);


    //
    // C++:  void setOptimalParameters(int svmsgdType = SVMSGD::ASGD, int marginType = SVMSGD::SOFT_MARGIN)
    //

    // C++:  void setInitialStepSize(float InitialStepSize)
    private static native void setInitialStepSize_0(long nativeObj, float InitialStepSize);

    // C++:  void setMarginRegularization(float marginRegularization)
    private static native void setMarginRegularization_0(long nativeObj, float marginRegularization);


    //
    // C++:  void setStepDecreasingPower(float stepDecreasingPower)
    //

    // C++:  void setMarginType(int marginType)
    private static native void setMarginType_0(long nativeObj, int marginType);


    //
    // C++:  void setSvmsgdType(int svmsgdType)
    //

    // C++:  void setOptimalParameters(int svmsgdType = SVMSGD::ASGD, int marginType = SVMSGD::SOFT_MARGIN)
    private static native void setOptimalParameters_0(long nativeObj, int svmsgdType, int marginType);


    //
    // C++:  void setTermCriteria(TermCriteria val)
    //

    private static native void setOptimalParameters_1(long nativeObj);

    // C++:  void setStepDecreasingPower(float stepDecreasingPower)
    private static native void setStepDecreasingPower_0(long nativeObj, float stepDecreasingPower);

    // C++:  void setSvmsgdType(int svmsgdType)
    private static native void setSvmsgdType_0(long nativeObj, int svmsgdType);

    // C++:  void setTermCriteria(TermCriteria val)
    private static native void setTermCriteria_0(long nativeObj, int val_type, int val_maxCount, double val_epsilon);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: SVMSGD::getWeights()
    public Mat getWeights() {

        return new Mat(getWeights_0(nativeObj));
    }

    //javadoc: SVMSGD::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: SVMSGD::setTermCriteria(val)
    public void setTermCriteria(TermCriteria val) {

        setTermCriteria_0(nativeObj, val.type, val.maxCount, val.epsilon);

    }

    //javadoc: SVMSGD::getInitialStepSize()
    public float getInitialStepSize() {

        return getInitialStepSize_0(nativeObj);
    }

    //javadoc: SVMSGD::setInitialStepSize(InitialStepSize)
    public void setInitialStepSize(float InitialStepSize) {

        setInitialStepSize_0(nativeObj, InitialStepSize);

    }

    //javadoc: SVMSGD::getMarginRegularization()
    public float getMarginRegularization() {

        return getMarginRegularization_0(nativeObj);
    }

    //javadoc: SVMSGD::setMarginRegularization(marginRegularization)
    public void setMarginRegularization(float marginRegularization) {

        setMarginRegularization_0(nativeObj, marginRegularization);

    }

    //javadoc: SVMSGD::getShift()
    public float getShift() {

        return getShift_0(nativeObj);
    }

    //javadoc: SVMSGD::getStepDecreasingPower()
    public float getStepDecreasingPower() {

        return getStepDecreasingPower_0(nativeObj);
    }

    //javadoc: SVMSGD::setStepDecreasingPower(stepDecreasingPower)
    public void setStepDecreasingPower(float stepDecreasingPower) {

        setStepDecreasingPower_0(nativeObj, stepDecreasingPower);

    }

    //javadoc: SVMSGD::getMarginType()
    public int getMarginType() {

        return getMarginType_0(nativeObj);
    }

    //javadoc: SVMSGD::setMarginType(marginType)
    public void setMarginType(int marginType) {

        setMarginType_0(nativeObj, marginType);

    }

    //javadoc: SVMSGD::getSvmsgdType()
    public int getSvmsgdType() {

        return getSvmsgdType_0(nativeObj);
    }

    //javadoc: SVMSGD::setSvmsgdType(svmsgdType)
    public void setSvmsgdType(int svmsgdType) {

        setSvmsgdType_0(nativeObj, svmsgdType);

    }

    //javadoc: SVMSGD::setOptimalParameters(svmsgdType, marginType)
    public void setOptimalParameters(int svmsgdType, int marginType) {

        setOptimalParameters_0(nativeObj, svmsgdType, marginType);

    }

    //javadoc: SVMSGD::setOptimalParameters()
    public void setOptimalParameters() {

        setOptimalParameters_1(nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
