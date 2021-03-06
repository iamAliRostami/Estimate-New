//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.video;

// C++: class DualTVL1OpticalFlow
//javadoc: DualTVL1OpticalFlow

public class DualTVL1OpticalFlow extends DenseOpticalFlow {

    protected DualTVL1OpticalFlow(long addr) {
        super(addr);
    }

    // internal usage only
    public static DualTVL1OpticalFlow __fromPtr__(long addr) {
        return new DualTVL1OpticalFlow(addr);
    }

    //
    // C++: static Ptr_DualTVL1OpticalFlow create(double tau = 0.25, double lambda = 0.15, double theta = 0.3, int nscales = 5, int warps = 5, double epsilon = 0.01, int innnerIterations = 30, int outerIterations = 10, double scaleStep = 0.8, double gamma = 0.0, int medianFiltering = 5, bool useInitialFlow = false)
    //

    //javadoc: DualTVL1OpticalFlow::create(tau, lambda, theta, nscales, warps, epsilon, innnerIterations, outerIterations, scaleStep, gamma, medianFiltering, useInitialFlow)
    public static DualTVL1OpticalFlow create(double tau, double lambda, double theta, int nscales, int warps, double epsilon, int innnerIterations, int outerIterations, double scaleStep, double gamma, int medianFiltering, boolean useInitialFlow) {

        return DualTVL1OpticalFlow.__fromPtr__(create_0(tau, lambda, theta, nscales, warps, epsilon, innnerIterations, outerIterations, scaleStep, gamma, medianFiltering, useInitialFlow));
    }

    //javadoc: DualTVL1OpticalFlow::create()
    public static DualTVL1OpticalFlow create() {

        return DualTVL1OpticalFlow.__fromPtr__(create_1());
    }


    //
    // C++:  bool getUseInitialFlow()
    //

    // C++: static Ptr_DualTVL1OpticalFlow create(double tau = 0.25, double lambda = 0.15, double theta = 0.3, int nscales = 5, int warps = 5, double epsilon = 0.01, int innnerIterations = 30, int outerIterations = 10, double scaleStep = 0.8, double gamma = 0.0, int medianFiltering = 5, bool useInitialFlow = false)
    private static native long create_0(double tau, double lambda, double theta, int nscales, int warps, double epsilon, int innnerIterations, int outerIterations, double scaleStep, double gamma, int medianFiltering, boolean useInitialFlow);


    //
    // C++:  double getEpsilon()
    //

    private static native long create_1();


    //
    // C++:  double getGamma()
    //

    // C++:  bool getUseInitialFlow()
    private static native boolean getUseInitialFlow_0(long nativeObj);


    //
    // C++:  double getLambda()
    //

    // C++:  double getEpsilon()
    private static native double getEpsilon_0(long nativeObj);


    //
    // C++:  double getScaleStep()
    //

    // C++:  double getGamma()
    private static native double getGamma_0(long nativeObj);


    //
    // C++:  double getTau()
    //

    // C++:  double getLambda()
    private static native double getLambda_0(long nativeObj);


    //
    // C++:  double getTheta()
    //

    // C++:  double getScaleStep()
    private static native double getScaleStep_0(long nativeObj);


    //
    // C++:  int getInnerIterations()
    //

    // C++:  double getTau()
    private static native double getTau_0(long nativeObj);


    //
    // C++:  int getMedianFiltering()
    //

    // C++:  double getTheta()
    private static native double getTheta_0(long nativeObj);


    //
    // C++:  int getOuterIterations()
    //

    // C++:  int getInnerIterations()
    private static native int getInnerIterations_0(long nativeObj);


    //
    // C++:  int getScalesNumber()
    //

    // C++:  int getMedianFiltering()
    private static native int getMedianFiltering_0(long nativeObj);


    //
    // C++:  int getWarpingsNumber()
    //

    // C++:  int getOuterIterations()
    private static native int getOuterIterations_0(long nativeObj);


    //
    // C++:  void setEpsilon(double val)
    //

    // C++:  int getScalesNumber()
    private static native int getScalesNumber_0(long nativeObj);


    //
    // C++:  void setGamma(double val)
    //

    // C++:  int getWarpingsNumber()
    private static native int getWarpingsNumber_0(long nativeObj);


    //
    // C++:  void setInnerIterations(int val)
    //

    // C++:  void setEpsilon(double val)
    private static native void setEpsilon_0(long nativeObj, double val);


    //
    // C++:  void setLambda(double val)
    //

    // C++:  void setGamma(double val)
    private static native void setGamma_0(long nativeObj, double val);


    //
    // C++:  void setMedianFiltering(int val)
    //

    // C++:  void setInnerIterations(int val)
    private static native void setInnerIterations_0(long nativeObj, int val);


    //
    // C++:  void setOuterIterations(int val)
    //

    // C++:  void setLambda(double val)
    private static native void setLambda_0(long nativeObj, double val);


    //
    // C++:  void setScaleStep(double val)
    //

    // C++:  void setMedianFiltering(int val)
    private static native void setMedianFiltering_0(long nativeObj, int val);


    //
    // C++:  void setScalesNumber(int val)
    //

    // C++:  void setOuterIterations(int val)
    private static native void setOuterIterations_0(long nativeObj, int val);


    //
    // C++:  void setTau(double val)
    //

    // C++:  void setScaleStep(double val)
    private static native void setScaleStep_0(long nativeObj, double val);


    //
    // C++:  void setTheta(double val)
    //

    // C++:  void setScalesNumber(int val)
    private static native void setScalesNumber_0(long nativeObj, int val);


    //
    // C++:  void setUseInitialFlow(bool val)
    //

    // C++:  void setTau(double val)
    private static native void setTau_0(long nativeObj, double val);


    //
    // C++:  void setWarpingsNumber(int val)
    //

    // C++:  void setTheta(double val)
    private static native void setTheta_0(long nativeObj, double val);

    // C++:  void setUseInitialFlow(bool val)
    private static native void setUseInitialFlow_0(long nativeObj, boolean val);

    // C++:  void setWarpingsNumber(int val)
    private static native void setWarpingsNumber_0(long nativeObj, int val);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: DualTVL1OpticalFlow::getUseInitialFlow()
    public boolean getUseInitialFlow() {

        return getUseInitialFlow_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setUseInitialFlow(val)
    public void setUseInitialFlow(boolean val) {

        setUseInitialFlow_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getEpsilon()
    public double getEpsilon() {

        return getEpsilon_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setEpsilon(val)
    public void setEpsilon(double val) {

        setEpsilon_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getGamma()
    public double getGamma() {

        return getGamma_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setGamma(val)
    public void setGamma(double val) {

        setGamma_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getLambda()
    public double getLambda() {

        return getLambda_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setLambda(val)
    public void setLambda(double val) {

        setLambda_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getScaleStep()
    public double getScaleStep() {

        return getScaleStep_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setScaleStep(val)
    public void setScaleStep(double val) {

        setScaleStep_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getTau()
    public double getTau() {

        return getTau_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setTau(val)
    public void setTau(double val) {

        setTau_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getTheta()
    public double getTheta() {

        return getTheta_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setTheta(val)
    public void setTheta(double val) {

        setTheta_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getInnerIterations()
    public int getInnerIterations() {

        return getInnerIterations_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setInnerIterations(val)
    public void setInnerIterations(int val) {

        setInnerIterations_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getMedianFiltering()
    public int getMedianFiltering() {

        return getMedianFiltering_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setMedianFiltering(val)
    public void setMedianFiltering(int val) {

        setMedianFiltering_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getOuterIterations()
    public int getOuterIterations() {

        return getOuterIterations_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setOuterIterations(val)
    public void setOuterIterations(int val) {

        setOuterIterations_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getScalesNumber()
    public int getScalesNumber() {

        return getScalesNumber_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setScalesNumber(val)
    public void setScalesNumber(int val) {

        setScalesNumber_0(nativeObj, val);

    }

    //javadoc: DualTVL1OpticalFlow::getWarpingsNumber()
    public int getWarpingsNumber() {

        return getWarpingsNumber_0(nativeObj);
    }

    //javadoc: DualTVL1OpticalFlow::setWarpingsNumber(val)
    public void setWarpingsNumber(int val) {

        setWarpingsNumber_0(nativeObj, val);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
