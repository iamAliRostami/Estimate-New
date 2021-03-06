//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

// C++: class ANN_MLP
//javadoc: ANN_MLP

public class ANN_MLP extends StatModel {

    public static final int
            BACKPROP = 0,
            RPROP = 1,
            ANNEAL = 2,
            IDENTITY = 0,
            SIGMOID_SYM = 1,
            GAUSSIAN = 2,
            RELU = 3,
            LEAKYRELU = 4,
            UPDATE_WEIGHTS = 1,
            NO_INPUT_SCALE = 2,
            NO_OUTPUT_SCALE = 4;

    protected ANN_MLP(long addr) {
        super(addr);
    }

    // internal usage only
    public static ANN_MLP __fromPtr__(long addr) {
        return new ANN_MLP(addr);
    }


    //
    // C++:  Mat getLayerSizes()
    //

    //javadoc: ANN_MLP::create()
    public static ANN_MLP create() {

        return ANN_MLP.__fromPtr__(create_0());
    }


    //
    // C++:  Mat getWeights(int layerIdx)
    //

    //javadoc: ANN_MLP::load(filepath)
    public static ANN_MLP load(String filepath) {

        return ANN_MLP.__fromPtr__(load_0(filepath));
    }


    //
    // C++: static Ptr_ANN_MLP create()
    //

    // C++:  Mat getLayerSizes()
    private static native long getLayerSizes_0(long nativeObj);


    //
    // C++: static Ptr_ANN_MLP load(String filepath)
    //

    // C++:  Mat getWeights(int layerIdx)
    private static native long getWeights_0(long nativeObj, int layerIdx);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    // C++: static Ptr_ANN_MLP create()
    private static native long create_0();


    //
    // C++:  double getAnnealCoolingRatio()
    //

    // C++: static Ptr_ANN_MLP load(String filepath)
    private static native long load_0(String filepath);


    //
    // C++:  double getAnnealFinalT()
    //

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  double getAnnealInitialT()
    //

    // C++:  double getAnnealCoolingRatio()
    private static native double getAnnealCoolingRatio_0(long nativeObj);


    //
    // C++:  double getBackpropMomentumScale()
    //

    // C++:  double getAnnealFinalT()
    private static native double getAnnealFinalT_0(long nativeObj);


    //
    // C++:  double getBackpropWeightScale()
    //

    // C++:  double getAnnealInitialT()
    private static native double getAnnealInitialT_0(long nativeObj);


    //
    // C++:  double getRpropDW0()
    //

    // C++:  double getBackpropMomentumScale()
    private static native double getBackpropMomentumScale_0(long nativeObj);


    //
    // C++:  double getRpropDWMax()
    //

    // C++:  double getBackpropWeightScale()
    private static native double getBackpropWeightScale_0(long nativeObj);


    //
    // C++:  double getRpropDWMin()
    //

    // C++:  double getRpropDW0()
    private static native double getRpropDW0_0(long nativeObj);


    //
    // C++:  double getRpropDWMinus()
    //

    // C++:  double getRpropDWMax()
    private static native double getRpropDWMax_0(long nativeObj);


    //
    // C++:  double getRpropDWPlus()
    //

    // C++:  double getRpropDWMin()
    private static native double getRpropDWMin_0(long nativeObj);


    //
    // C++:  int getAnnealItePerStep()
    //

    // C++:  double getRpropDWMinus()
    private static native double getRpropDWMinus_0(long nativeObj);


    //
    // C++:  int getTrainMethod()
    //

    // C++:  double getRpropDWPlus()
    private static native double getRpropDWPlus_0(long nativeObj);


    //
    // C++:  void setActivationFunction(int type, double param1 = 0, double param2 = 0)
    //

    // C++:  int getAnnealItePerStep()
    private static native int getAnnealItePerStep_0(long nativeObj);

    // C++:  int getTrainMethod()
    private static native int getTrainMethod_0(long nativeObj);


    //
    // C++:  void setAnnealCoolingRatio(double val)
    //

    // C++:  void setActivationFunction(int type, double param1 = 0, double param2 = 0)
    private static native void setActivationFunction_0(long nativeObj, int type, double param1, double param2);


    //
    // C++:  void setAnnealFinalT(double val)
    //

    private static native void setActivationFunction_1(long nativeObj, int type);


    //
    // C++:  void setAnnealInitialT(double val)
    //

    // C++:  void setAnnealCoolingRatio(double val)
    private static native void setAnnealCoolingRatio_0(long nativeObj, double val);


    //
    // C++:  void setAnnealItePerStep(int val)
    //

    // C++:  void setAnnealFinalT(double val)
    private static native void setAnnealFinalT_0(long nativeObj, double val);


    //
    // C++:  void setBackpropMomentumScale(double val)
    //

    // C++:  void setAnnealInitialT(double val)
    private static native void setAnnealInitialT_0(long nativeObj, double val);


    //
    // C++:  void setBackpropWeightScale(double val)
    //

    // C++:  void setAnnealItePerStep(int val)
    private static native void setAnnealItePerStep_0(long nativeObj, int val);


    //
    // C++:  void setLayerSizes(Mat _layer_sizes)
    //

    // C++:  void setBackpropMomentumScale(double val)
    private static native void setBackpropMomentumScale_0(long nativeObj, double val);


    //
    // C++:  void setRpropDW0(double val)
    //

    // C++:  void setBackpropWeightScale(double val)
    private static native void setBackpropWeightScale_0(long nativeObj, double val);


    //
    // C++:  void setRpropDWMax(double val)
    //

    // C++:  void setLayerSizes(Mat _layer_sizes)
    private static native void setLayerSizes_0(long nativeObj, long _layer_sizes_nativeObj);


    //
    // C++:  void setRpropDWMin(double val)
    //

    // C++:  void setRpropDW0(double val)
    private static native void setRpropDW0_0(long nativeObj, double val);


    //
    // C++:  void setRpropDWMinus(double val)
    //

    // C++:  void setRpropDWMax(double val)
    private static native void setRpropDWMax_0(long nativeObj, double val);


    //
    // C++:  void setRpropDWPlus(double val)
    //

    // C++:  void setRpropDWMin(double val)
    private static native void setRpropDWMin_0(long nativeObj, double val);


    //
    // C++:  void setTermCriteria(TermCriteria val)
    //

    // C++:  void setRpropDWMinus(double val)
    private static native void setRpropDWMinus_0(long nativeObj, double val);


    //
    // C++:  void setTrainMethod(int method, double param1 = 0, double param2 = 0)
    //

    // C++:  void setRpropDWPlus(double val)
    private static native void setRpropDWPlus_0(long nativeObj, double val);

    // C++:  void setTermCriteria(TermCriteria val)
    private static native void setTermCriteria_0(long nativeObj, int val_type, int val_maxCount, double val_epsilon);

    // C++:  void setTrainMethod(int method, double param1 = 0, double param2 = 0)
    private static native void setTrainMethod_0(long nativeObj, int method, double param1, double param2);

    private static native void setTrainMethod_1(long nativeObj, int method);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: ANN_MLP::getLayerSizes()
    public Mat getLayerSizes() {

        return new Mat(getLayerSizes_0(nativeObj));
    }

    //javadoc: ANN_MLP::setLayerSizes(_layer_sizes)
    public void setLayerSizes(Mat _layer_sizes) {

        setLayerSizes_0(nativeObj, _layer_sizes.nativeObj);

    }

    //javadoc: ANN_MLP::getWeights(layerIdx)
    public Mat getWeights(int layerIdx) {

        return new Mat(getWeights_0(nativeObj, layerIdx));
    }

    //javadoc: ANN_MLP::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: ANN_MLP::setTermCriteria(val)
    public void setTermCriteria(TermCriteria val) {

        setTermCriteria_0(nativeObj, val.type, val.maxCount, val.epsilon);

    }

    //javadoc: ANN_MLP::getAnnealCoolingRatio()
    public double getAnnealCoolingRatio() {

        return getAnnealCoolingRatio_0(nativeObj);
    }

    //javadoc: ANN_MLP::setAnnealCoolingRatio(val)
    public void setAnnealCoolingRatio(double val) {

        setAnnealCoolingRatio_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getAnnealFinalT()
    public double getAnnealFinalT() {

        return getAnnealFinalT_0(nativeObj);
    }

    //javadoc: ANN_MLP::setAnnealFinalT(val)
    public void setAnnealFinalT(double val) {

        setAnnealFinalT_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getAnnealInitialT()
    public double getAnnealInitialT() {

        return getAnnealInitialT_0(nativeObj);
    }

    //javadoc: ANN_MLP::setAnnealInitialT(val)
    public void setAnnealInitialT(double val) {

        setAnnealInitialT_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getBackpropMomentumScale()
    public double getBackpropMomentumScale() {

        return getBackpropMomentumScale_0(nativeObj);
    }

    //javadoc: ANN_MLP::setBackpropMomentumScale(val)
    public void setBackpropMomentumScale(double val) {

        setBackpropMomentumScale_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getBackpropWeightScale()
    public double getBackpropWeightScale() {

        return getBackpropWeightScale_0(nativeObj);
    }

    //javadoc: ANN_MLP::setBackpropWeightScale(val)
    public void setBackpropWeightScale(double val) {

        setBackpropWeightScale_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getRpropDW0()
    public double getRpropDW0() {

        return getRpropDW0_0(nativeObj);
    }

    //javadoc: ANN_MLP::setRpropDW0(val)
    public void setRpropDW0(double val) {

        setRpropDW0_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getRpropDWMax()
    public double getRpropDWMax() {

        return getRpropDWMax_0(nativeObj);
    }

    //javadoc: ANN_MLP::setRpropDWMax(val)
    public void setRpropDWMax(double val) {

        setRpropDWMax_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getRpropDWMin()
    public double getRpropDWMin() {

        return getRpropDWMin_0(nativeObj);
    }

    //javadoc: ANN_MLP::setRpropDWMin(val)
    public void setRpropDWMin(double val) {

        setRpropDWMin_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getRpropDWMinus()
    public double getRpropDWMinus() {

        return getRpropDWMinus_0(nativeObj);
    }

    //javadoc: ANN_MLP::setRpropDWMinus(val)
    public void setRpropDWMinus(double val) {

        setRpropDWMinus_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getRpropDWPlus()
    public double getRpropDWPlus() {

        return getRpropDWPlus_0(nativeObj);
    }

    //javadoc: ANN_MLP::setRpropDWPlus(val)
    public void setRpropDWPlus(double val) {

        setRpropDWPlus_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getAnnealItePerStep()
    public int getAnnealItePerStep() {

        return getAnnealItePerStep_0(nativeObj);
    }

    //javadoc: ANN_MLP::setAnnealItePerStep(val)
    public void setAnnealItePerStep(int val) {

        setAnnealItePerStep_0(nativeObj, val);

    }

    //javadoc: ANN_MLP::getTrainMethod()
    public int getTrainMethod() {

        return getTrainMethod_0(nativeObj);
    }

    //javadoc: ANN_MLP::setTrainMethod(method)
    public void setTrainMethod(int method) {

        setTrainMethod_1(nativeObj, method);

    }

    //javadoc: ANN_MLP::setActivationFunction(type, param1, param2)
    public void setActivationFunction(int type, double param1, double param2) {

        setActivationFunction_0(nativeObj, type, param1, param2);

    }

    //javadoc: ANN_MLP::setActivationFunction(type)
    public void setActivationFunction(int type) {

        setActivationFunction_1(nativeObj, type);

    }

    //javadoc: ANN_MLP::setTrainMethod(method, param1, param2)
    public void setTrainMethod(int method, double param1, double param2) {

        setTrainMethod_0(nativeObj, method, param1, param2);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
