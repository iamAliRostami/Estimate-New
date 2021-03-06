//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

// C++: class SVM
//javadoc: SVM

public class SVM extends StatModel {

    public static final int
            C_SVC = 100,
            NU_SVC = 101,
            ONE_CLASS = 102,
            EPS_SVR = 103,
            NU_SVR = 104,
            CUSTOM = -1,
            LINEAR = 0,
            POLY = 1,
            RBF = 2,
            SIGMOID = 3,
            CHI2 = 4,
            INTER = 5,
            C = 0,
            GAMMA = 1,
            P = 2,
            NU = 3,
            COEF = 4,
            DEGREE = 5;

    protected SVM(long addr) {
        super(addr);
    }

    // internal usage only
    public static SVM __fromPtr__(long addr) {
        return new SVM(addr);
    }


    //
    // C++:  Mat getClassWeights()
    //

    //javadoc: SVM::getDefaultGridPtr(param_id)
    public static ParamGrid getDefaultGridPtr(int param_id) {

        return ParamGrid.__fromPtr__(getDefaultGridPtr_0(param_id));
    }


    //
    // C++:  Mat getSupportVectors()
    //

    //javadoc: SVM::create()
    public static SVM create() {

        return SVM.__fromPtr__(create_0());
    }


    //
    // C++:  Mat getUncompressedSupportVectors()
    //

    //javadoc: SVM::load(filepath)
    public static SVM load(String filepath) {

        return SVM.__fromPtr__(load_0(filepath));
    }


    //
    // C++: static Ptr_ParamGrid getDefaultGridPtr(int param_id)
    //

    // C++:  Mat getClassWeights()
    private static native long getClassWeights_0(long nativeObj);


    //
    // C++: static Ptr_SVM create()
    //

    // C++:  Mat getSupportVectors()
    private static native long getSupportVectors_0(long nativeObj);


    //
    // C++: static Ptr_SVM load(String filepath)
    //

    // C++:  Mat getUncompressedSupportVectors()
    private static native long getUncompressedSupportVectors_0(long nativeObj);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    // C++: static Ptr_ParamGrid getDefaultGridPtr(int param_id)
    private static native long getDefaultGridPtr_0(int param_id);


    //
    // C++:  bool trainAuto(Mat samples, int layout, Mat responses, int kFold = 10, Ptr_ParamGrid Cgrid = SVM::getDefaultGridPtr(SVM::C), Ptr_ParamGrid gammaGrid = SVM::getDefaultGridPtr(SVM::GAMMA), Ptr_ParamGrid pGrid = SVM::getDefaultGridPtr(SVM::P), Ptr_ParamGrid nuGrid = SVM::getDefaultGridPtr(SVM::NU), Ptr_ParamGrid coeffGrid = SVM::getDefaultGridPtr(SVM::COEF), Ptr_ParamGrid degreeGrid = SVM::getDefaultGridPtr(SVM::DEGREE), bool balanced = false)
    //

    // C++: static Ptr_SVM create()
    private static native long create_0();

    // C++: static Ptr_SVM load(String filepath)
    private static native long load_0(String filepath);


    //
    // C++:  double getC()
    //

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  double getCoef0()
    //

    // C++:  bool trainAuto(Mat samples, int layout, Mat responses, int kFold = 10, Ptr_ParamGrid Cgrid = SVM::getDefaultGridPtr(SVM::C), Ptr_ParamGrid gammaGrid = SVM::getDefaultGridPtr(SVM::GAMMA), Ptr_ParamGrid pGrid = SVM::getDefaultGridPtr(SVM::P), Ptr_ParamGrid nuGrid = SVM::getDefaultGridPtr(SVM::NU), Ptr_ParamGrid coeffGrid = SVM::getDefaultGridPtr(SVM::COEF), Ptr_ParamGrid degreeGrid = SVM::getDefaultGridPtr(SVM::DEGREE), bool balanced = false)
    private static native boolean trainAuto_0(long nativeObj, long samples_nativeObj, int layout, long responses_nativeObj, int kFold, long Cgrid_nativeObj, long gammaGrid_nativeObj, long pGrid_nativeObj, long nuGrid_nativeObj, long coeffGrid_nativeObj, long degreeGrid_nativeObj, boolean balanced);


    //
    // C++:  double getDecisionFunction(int i, Mat& alpha, Mat& svidx)
    //

    private static native boolean trainAuto_1(long nativeObj, long samples_nativeObj, int layout, long responses_nativeObj);


    //
    // C++:  double getDegree()
    //

    // C++:  double getC()
    private static native double getC_0(long nativeObj);


    //
    // C++:  double getGamma()
    //

    // C++:  double getCoef0()
    private static native double getCoef0_0(long nativeObj);


    //
    // C++:  double getNu()
    //

    // C++:  double getDecisionFunction(int i, Mat& alpha, Mat& svidx)
    private static native double getDecisionFunction_0(long nativeObj, int i, long alpha_nativeObj, long svidx_nativeObj);


    //
    // C++:  double getP()
    //

    // C++:  double getDegree()
    private static native double getDegree_0(long nativeObj);


    //
    // C++:  int getKernelType()
    //

    // C++:  double getGamma()
    private static native double getGamma_0(long nativeObj);


    //
    // C++:  int getType()
    //

    // C++:  double getNu()
    private static native double getNu_0(long nativeObj);


    //
    // C++:  void setC(double val)
    //

    // C++:  double getP()
    private static native double getP_0(long nativeObj);


    //
    // C++:  void setClassWeights(Mat val)
    //

    // C++:  int getKernelType()
    private static native int getKernelType_0(long nativeObj);


    //
    // C++:  void setCoef0(double val)
    //

    // C++:  int getType()
    private static native int getType_0(long nativeObj);


    //
    // C++:  void setDegree(double val)
    //

    // C++:  void setC(double val)
    private static native void setC_0(long nativeObj, double val);


    //
    // C++:  void setGamma(double val)
    //

    // C++:  void setClassWeights(Mat val)
    private static native void setClassWeights_0(long nativeObj, long val_nativeObj);


    //
    // C++:  void setKernel(int kernelType)
    //

    // C++:  void setCoef0(double val)
    private static native void setCoef0_0(long nativeObj, double val);


    //
    // C++:  void setNu(double val)
    //

    // C++:  void setDegree(double val)
    private static native void setDegree_0(long nativeObj, double val);


    //
    // C++:  void setP(double val)
    //

    // C++:  void setGamma(double val)
    private static native void setGamma_0(long nativeObj, double val);


    //
    // C++:  void setTermCriteria(TermCriteria val)
    //

    // C++:  void setKernel(int kernelType)
    private static native void setKernel_0(long nativeObj, int kernelType);


    //
    // C++:  void setType(int val)
    //

    // C++:  void setNu(double val)
    private static native void setNu_0(long nativeObj, double val);

    // C++:  void setP(double val)
    private static native void setP_0(long nativeObj, double val);

    // C++:  void setTermCriteria(TermCriteria val)
    private static native void setTermCriteria_0(long nativeObj, int val_type, int val_maxCount, double val_epsilon);

    // C++:  void setType(int val)
    private static native void setType_0(long nativeObj, int val);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: SVM::getClassWeights()
    public Mat getClassWeights() {

        return new Mat(getClassWeights_0(nativeObj));
    }

    //javadoc: SVM::setClassWeights(val)
    public void setClassWeights(Mat val) {

        setClassWeights_0(nativeObj, val.nativeObj);

    }

    //javadoc: SVM::getSupportVectors()
    public Mat getSupportVectors() {

        return new Mat(getSupportVectors_0(nativeObj));
    }

    //javadoc: SVM::getUncompressedSupportVectors()
    public Mat getUncompressedSupportVectors() {

        return new Mat(getUncompressedSupportVectors_0(nativeObj));
    }

    //javadoc: SVM::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: SVM::setTermCriteria(val)
    public void setTermCriteria(TermCriteria val) {

        setTermCriteria_0(nativeObj, val.type, val.maxCount, val.epsilon);

    }

    //javadoc: SVM::trainAuto(samples, layout, responses, kFold, Cgrid, gammaGrid, pGrid, nuGrid, coeffGrid, degreeGrid, balanced)
    public boolean trainAuto(Mat samples, int layout, Mat responses, int kFold, ParamGrid Cgrid, ParamGrid gammaGrid, ParamGrid pGrid, ParamGrid nuGrid, ParamGrid coeffGrid, ParamGrid degreeGrid, boolean balanced) {

        return trainAuto_0(nativeObj, samples.nativeObj, layout, responses.nativeObj, kFold, Cgrid.getNativeObjAddr(), gammaGrid.getNativeObjAddr(), pGrid.getNativeObjAddr(), nuGrid.getNativeObjAddr(), coeffGrid.getNativeObjAddr(), degreeGrid.getNativeObjAddr(), balanced);
    }

    //javadoc: SVM::trainAuto(samples, layout, responses)
    public boolean trainAuto(Mat samples, int layout, Mat responses) {

        return trainAuto_1(nativeObj, samples.nativeObj, layout, responses.nativeObj);
    }

    //javadoc: SVM::getC()
    public double getC() {

        return getC_0(nativeObj);
    }

    //javadoc: SVM::setC(val)
    public void setC(double val) {

        setC_0(nativeObj, val);

    }

    //javadoc: SVM::getCoef0()
    public double getCoef0() {

        return getCoef0_0(nativeObj);
    }

    //javadoc: SVM::setCoef0(val)
    public void setCoef0(double val) {

        setCoef0_0(nativeObj, val);

    }

    //javadoc: SVM::getDecisionFunction(i, alpha, svidx)
    public double getDecisionFunction(int i, Mat alpha, Mat svidx) {

        return getDecisionFunction_0(nativeObj, i, alpha.nativeObj, svidx.nativeObj);
    }

    //javadoc: SVM::getDegree()
    public double getDegree() {

        return getDegree_0(nativeObj);
    }

    //javadoc: SVM::setDegree(val)
    public void setDegree(double val) {

        setDegree_0(nativeObj, val);

    }

    //javadoc: SVM::getGamma()
    public double getGamma() {

        return getGamma_0(nativeObj);
    }

    //javadoc: SVM::setGamma(val)
    public void setGamma(double val) {

        setGamma_0(nativeObj, val);

    }

    //javadoc: SVM::getNu()
    public double getNu() {

        return getNu_0(nativeObj);
    }

    //javadoc: SVM::setNu(val)
    public void setNu(double val) {

        setNu_0(nativeObj, val);

    }

    //javadoc: SVM::getP()
    public double getP() {

        return getP_0(nativeObj);
    }

    //javadoc: SVM::setP(val)
    public void setP(double val) {

        setP_0(nativeObj, val);

    }

    //javadoc: SVM::getKernelType()
    public int getKernelType() {

        return getKernelType_0(nativeObj);
    }

    //javadoc: SVM::getType()
    public int getType() {

        return getType_0(nativeObj);
    }

    //javadoc: SVM::setType(val)
    public void setType(int val) {

        setType_0(nativeObj, val);

    }

    //javadoc: SVM::setKernel(kernelType)
    public void setKernel(int kernelType) {

        setKernel_0(nativeObj, kernelType);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
