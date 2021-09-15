//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

// C++: class LogisticRegression
//javadoc: LogisticRegression

public class LogisticRegression extends StatModel {

    public static final int
            REG_DISABLE = -1,
            REG_L1 = 0,
            REG_L2 = 1,
            BATCH = 0,
            MINI_BATCH = 1;

    protected LogisticRegression(long addr) {
        super(addr);
    }

    // internal usage only
    public static LogisticRegression __fromPtr__(long addr) {
        return new LogisticRegression(addr);
    }


    //
    // C++:  Mat get_learnt_thetas()
    //

    //javadoc: LogisticRegression::create()
    public static LogisticRegression create() {

        return LogisticRegression.__fromPtr__(create_0());
    }


    //
    // C++: static Ptr_LogisticRegression create()
    //

    //javadoc: LogisticRegression::load(filepath, nodeName)
    public static LogisticRegression load(String filepath, String nodeName) {

        return LogisticRegression.__fromPtr__(load_0(filepath, nodeName));
    }


    //
    // C++: static Ptr_LogisticRegression load(String filepath, String nodeName = String())
    //

    //javadoc: LogisticRegression::load(filepath)
    public static LogisticRegression load(String filepath) {

        return LogisticRegression.__fromPtr__(load_1(filepath));
    }

    // C++:  Mat get_learnt_thetas()
    private static native long get_learnt_thetas_0(long nativeObj);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    // C++: static Ptr_LogisticRegression create()
    private static native long create_0();


    //
    // C++:  double getLearningRate()
    //

    // C++: static Ptr_LogisticRegression load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);


    //
    // C++:  float predict(Mat samples, Mat& results = Mat(), int flags = 0)
    //

    private static native long load_1(String filepath);

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  int getIterations()
    //

    // C++:  double getLearningRate()
    private static native double getLearningRate_0(long nativeObj);


    //
    // C++:  int getMiniBatchSize()
    //

    // C++:  float predict(Mat samples, Mat& results = Mat(), int flags = 0)
    private static native float predict_0(long nativeObj, long samples_nativeObj, long results_nativeObj, int flags);


    //
    // C++:  int getRegularization()
    //

    private static native float predict_1(long nativeObj, long samples_nativeObj);


    //
    // C++:  int getTrainMethod()
    //

    // C++:  int getIterations()
    private static native int getIterations_0(long nativeObj);


    //
    // C++:  void setIterations(int val)
    //

    // C++:  int getMiniBatchSize()
    private static native int getMiniBatchSize_0(long nativeObj);


    //
    // C++:  void setLearningRate(double val)
    //

    // C++:  int getRegularization()
    private static native int getRegularization_0(long nativeObj);


    //
    // C++:  void setMiniBatchSize(int val)
    //

    // C++:  int getTrainMethod()
    private static native int getTrainMethod_0(long nativeObj);


    //
    // C++:  void setRegularization(int val)
    //

    // C++:  void setIterations(int val)
    private static native void setIterations_0(long nativeObj, int val);


    //
    // C++:  void setTermCriteria(TermCriteria val)
    //

    // C++:  void setLearningRate(double val)
    private static native void setLearningRate_0(long nativeObj, double val);


    //
    // C++:  void setTrainMethod(int val)
    //

    // C++:  void setMiniBatchSize(int val)
    private static native void setMiniBatchSize_0(long nativeObj, int val);

    // C++:  void setRegularization(int val)
    private static native void setRegularization_0(long nativeObj, int val);

    // C++:  void setTermCriteria(TermCriteria val)
    private static native void setTermCriteria_0(long nativeObj, int val_type, int val_maxCount, double val_epsilon);

    // C++:  void setTrainMethod(int val)
    private static native void setTrainMethod_0(long nativeObj, int val);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: LogisticRegression::get_learnt_thetas()
    public Mat get_learnt_thetas() {

        return new Mat(get_learnt_thetas_0(nativeObj));
    }

    //javadoc: LogisticRegression::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: LogisticRegression::setTermCriteria(val)
    public void setTermCriteria(TermCriteria val) {

        setTermCriteria_0(nativeObj, val.type, val.maxCount, val.epsilon);

    }

    //javadoc: LogisticRegression::getLearningRate()
    public double getLearningRate() {

        return getLearningRate_0(nativeObj);
    }

    //javadoc: LogisticRegression::setLearningRate(val)
    public void setLearningRate(double val) {

        setLearningRate_0(nativeObj, val);

    }

    //javadoc: LogisticRegression::predict(samples, results, flags)
    public float predict(Mat samples, Mat results, int flags) {

        return predict_0(nativeObj, samples.nativeObj, results.nativeObj, flags);
    }

    //javadoc: LogisticRegression::predict(samples)
    public float predict(Mat samples) {

        return predict_1(nativeObj, samples.nativeObj);
    }

    //javadoc: LogisticRegression::getIterations()
    public int getIterations() {

        return getIterations_0(nativeObj);
    }

    //javadoc: LogisticRegression::setIterations(val)
    public void setIterations(int val) {

        setIterations_0(nativeObj, val);

    }

    //javadoc: LogisticRegression::getMiniBatchSize()
    public int getMiniBatchSize() {

        return getMiniBatchSize_0(nativeObj);
    }

    //javadoc: LogisticRegression::setMiniBatchSize(val)
    public void setMiniBatchSize(int val) {

        setMiniBatchSize_0(nativeObj, val);

    }

    //javadoc: LogisticRegression::getRegularization()
    public int getRegularization() {

        return getRegularization_0(nativeObj);
    }

    //javadoc: LogisticRegression::setRegularization(val)
    public void setRegularization(int val) {

        setRegularization_0(nativeObj, val);

    }

    //javadoc: LogisticRegression::getTrainMethod()
    public int getTrainMethod() {

        return getTrainMethod_0(nativeObj);
    }

    //javadoc: LogisticRegression::setTrainMethod(val)
    public void setTrainMethod(int val) {

        setTrainMethod_0(nativeObj, val);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
