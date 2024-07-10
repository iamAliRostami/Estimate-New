//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

// C++: class ParamGrid
//javadoc: ParamGrid

public class ParamGrid {

    protected final long nativeObj;

    protected ParamGrid(long addr) {
        nativeObj = addr;
    }

    // internal usage only
    public static ParamGrid __fromPtr__(long addr) {
        return new ParamGrid(addr);
    }

    //javadoc: ParamGrid::create(minVal, maxVal, logstep)
    public static ParamGrid create(double minVal, double maxVal, double logstep) {

        return ParamGrid.__fromPtr__(create_0(minVal, maxVal, logstep));
    }

    //
    // C++: static Ptr_ParamGrid create(double minVal = 0., double maxVal = 0., double logstep = 1.)
    //

    //javadoc: ParamGrid::create()
    public static ParamGrid create() {

        return ParamGrid.__fromPtr__(create_1());
    }

    // C++: static Ptr_ParamGrid create(double minVal = 0., double maxVal = 0., double logstep = 1.)
    private static native long create_0(double minVal, double maxVal, double logstep);


    //
    // C++: double ParamGrid::minVal
    //

    private static native long create_1();


    //
    // C++: void ParamGrid::minVal
    //

    // C++: double ParamGrid::minVal
    private static native double get_minVal_0(long nativeObj);


    //
    // C++: double ParamGrid::maxVal
    //

    // C++: void ParamGrid::minVal
    private static native void set_minVal_0(long nativeObj, double minVal);


    //
    // C++: void ParamGrid::maxVal
    //

    // C++: double ParamGrid::maxVal
    private static native double get_maxVal_0(long nativeObj);


    //
    // C++: double ParamGrid::logStep
    //

    // C++: void ParamGrid::maxVal
    private static native void set_maxVal_0(long nativeObj, double maxVal);


    //
    // C++: void ParamGrid::logStep
    //

    // C++: double ParamGrid::logStep
    private static native double get_logStep_0(long nativeObj);

    // C++: void ParamGrid::logStep
    private static native void set_logStep_0(long nativeObj, double logStep);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: ParamGrid::get_minVal()
    public double get_minVal() {

        return get_minVal_0(nativeObj);
    }

    //javadoc: ParamGrid::set_minVal(minVal)
    public void set_minVal(double minVal) {

        set_minVal_0(nativeObj, minVal);

    }

    //javadoc: ParamGrid::get_maxVal()
    public double get_maxVal() {

        return get_maxVal_0(nativeObj);
    }

    //javadoc: ParamGrid::set_maxVal(maxVal)
    public void set_maxVal(double maxVal) {

        set_maxVal_0(nativeObj, maxVal);

    }

    //javadoc: ParamGrid::get_logStep()
    public double get_logStep() {

        return get_logStep_0(nativeObj);
    }

    //javadoc: ParamGrid::set_logStep(logStep)
    public void set_logStep(double logStep) {

        set_logStep_0(nativeObj, logStep);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
