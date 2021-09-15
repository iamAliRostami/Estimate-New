//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.utils.Converters;

import java.util.List;

// C++: class EM
//javadoc: EM

public class EM extends StatModel {

    public static final int
            COV_MAT_SPHERICAL = 0,
            COV_MAT_DIAGONAL = 1,
            COV_MAT_GENERIC = 2,
            COV_MAT_DEFAULT = COV_MAT_DIAGONAL,
            DEFAULT_NCLUSTERS = 5,
            DEFAULT_MAX_ITERS = 100,
            START_E_STEP = 1,
            START_M_STEP = 2,
            START_AUTO_STEP = 0;

    protected EM(long addr) {
        super(addr);
    }

    // internal usage only
    public static EM __fromPtr__(long addr) {
        return new EM(addr);
    }


    //
    // C++:  Mat getMeans()
    //

    //javadoc: EM::create()
    public static EM create() {

        return EM.__fromPtr__(create_0());
    }


    //
    // C++:  Mat getWeights()
    //

    //javadoc: EM::load(filepath, nodeName)
    public static EM load(String filepath, String nodeName) {

        return EM.__fromPtr__(load_0(filepath, nodeName));
    }


    //
    // C++: static Ptr_EM create()
    //

    //javadoc: EM::load(filepath)
    public static EM load(String filepath) {

        return EM.__fromPtr__(load_1(filepath));
    }


    //
    // C++: static Ptr_EM load(String filepath, String nodeName = String())
    //

    // C++:  Mat getMeans()
    private static native long getMeans_0(long nativeObj);

    // C++:  Mat getWeights()
    private static native long getWeights_0(long nativeObj);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    // C++: static Ptr_EM create()
    private static native long create_0();


    //
    // C++:  Vec2d predict2(Mat sample, Mat& probs)
    //

    // C++: static Ptr_EM load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);


    //
    // C++:  bool trainE(Mat samples, Mat means0, Mat covs0 = Mat(), Mat weights0 = Mat(), Mat& logLikelihoods = Mat(), Mat& labels = Mat(), Mat& probs = Mat())
    //

    private static native long load_1(String filepath);

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  bool trainEM(Mat samples, Mat& logLikelihoods = Mat(), Mat& labels = Mat(), Mat& probs = Mat())
    //

    // C++:  Vec2d predict2(Mat sample, Mat& probs)
    private static native double[] predict2_0(long nativeObj, long sample_nativeObj, long probs_nativeObj);

    // C++:  bool trainE(Mat samples, Mat means0, Mat covs0 = Mat(), Mat weights0 = Mat(), Mat& logLikelihoods = Mat(), Mat& labels = Mat(), Mat& probs = Mat())
    private static native boolean trainE_0(long nativeObj, long samples_nativeObj, long means0_nativeObj, long covs0_nativeObj, long weights0_nativeObj, long logLikelihoods_nativeObj, long labels_nativeObj, long probs_nativeObj);


    //
    // C++:  bool trainM(Mat samples, Mat probs0, Mat& logLikelihoods = Mat(), Mat& labels = Mat(), Mat& probs = Mat())
    //

    private static native boolean trainE_1(long nativeObj, long samples_nativeObj, long means0_nativeObj);

    // C++:  bool trainEM(Mat samples, Mat& logLikelihoods = Mat(), Mat& labels = Mat(), Mat& probs = Mat())
    private static native boolean trainEM_0(long nativeObj, long samples_nativeObj, long logLikelihoods_nativeObj, long labels_nativeObj, long probs_nativeObj);


    //
    // C++:  float predict(Mat samples, Mat& results = Mat(), int flags = 0)
    //

    private static native boolean trainEM_1(long nativeObj, long samples_nativeObj);

    // C++:  bool trainM(Mat samples, Mat probs0, Mat& logLikelihoods = Mat(), Mat& labels = Mat(), Mat& probs = Mat())
    private static native boolean trainM_0(long nativeObj, long samples_nativeObj, long probs0_nativeObj, long logLikelihoods_nativeObj, long labels_nativeObj, long probs_nativeObj);


    //
    // C++:  int getClustersNumber()
    //

    private static native boolean trainM_1(long nativeObj, long samples_nativeObj, long probs0_nativeObj);


    //
    // C++:  int getCovarianceMatrixType()
    //

    // C++:  float predict(Mat samples, Mat& results = Mat(), int flags = 0)
    private static native float predict_0(long nativeObj, long samples_nativeObj, long results_nativeObj, int flags);


    //
    // C++:  void getCovs(vector_Mat& covs)
    //

    private static native float predict_1(long nativeObj, long samples_nativeObj);


    //
    // C++:  void setClustersNumber(int val)
    //

    // C++:  int getClustersNumber()
    private static native int getClustersNumber_0(long nativeObj);


    //
    // C++:  void setCovarianceMatrixType(int val)
    //

    // C++:  int getCovarianceMatrixType()
    private static native int getCovarianceMatrixType_0(long nativeObj);


    //
    // C++:  void setTermCriteria(TermCriteria val)
    //

    // C++:  void getCovs(vector_Mat& covs)
    private static native void getCovs_0(long nativeObj, long covs_mat_nativeObj);

    // C++:  void setClustersNumber(int val)
    private static native void setClustersNumber_0(long nativeObj, int val);

    // C++:  void setCovarianceMatrixType(int val)
    private static native void setCovarianceMatrixType_0(long nativeObj, int val);

    // C++:  void setTermCriteria(TermCriteria val)
    private static native void setTermCriteria_0(long nativeObj, int val_type, int val_maxCount, double val_epsilon);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: EM::getMeans()
    public Mat getMeans() {

        return new Mat(getMeans_0(nativeObj));
    }

    //javadoc: EM::getWeights()
    public Mat getWeights() {

        return new Mat(getWeights_0(nativeObj));
    }

    //javadoc: EM::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: EM::setTermCriteria(val)
    public void setTermCriteria(TermCriteria val) {

        setTermCriteria_0(nativeObj, val.type, val.maxCount, val.epsilon);

    }

    //javadoc: EM::predict2(sample, probs)
    public double[] predict2(Mat sample, Mat probs) {

        return predict2_0(nativeObj, sample.nativeObj, probs.nativeObj);
    }

    //javadoc: EM::trainE(samples, means0, covs0, weights0, logLikelihoods, labels, probs)
    public boolean trainE(Mat samples, Mat means0, Mat covs0, Mat weights0, Mat logLikelihoods, Mat labels, Mat probs) {

        return trainE_0(nativeObj, samples.nativeObj, means0.nativeObj, covs0.nativeObj, weights0.nativeObj, logLikelihoods.nativeObj, labels.nativeObj, probs.nativeObj);
    }

    //javadoc: EM::trainE(samples, means0)
    public boolean trainE(Mat samples, Mat means0) {

        return trainE_1(nativeObj, samples.nativeObj, means0.nativeObj);
    }

    //javadoc: EM::trainEM(samples, logLikelihoods, labels, probs)
    public boolean trainEM(Mat samples, Mat logLikelihoods, Mat labels, Mat probs) {

        return trainEM_0(nativeObj, samples.nativeObj, logLikelihoods.nativeObj, labels.nativeObj, probs.nativeObj);
    }

    //javadoc: EM::trainEM(samples)
    public boolean trainEM(Mat samples) {

        return trainEM_1(nativeObj, samples.nativeObj);
    }

    //javadoc: EM::trainM(samples, probs0, logLikelihoods, labels, probs)
    public boolean trainM(Mat samples, Mat probs0, Mat logLikelihoods, Mat labels, Mat probs) {

        return trainM_0(nativeObj, samples.nativeObj, probs0.nativeObj, logLikelihoods.nativeObj, labels.nativeObj, probs.nativeObj);
    }

    //javadoc: EM::trainM(samples, probs0)
    public boolean trainM(Mat samples, Mat probs0) {

        return trainM_1(nativeObj, samples.nativeObj, probs0.nativeObj);
    }

    //javadoc: EM::predict(samples, results, flags)
    public float predict(Mat samples, Mat results, int flags) {

        return predict_0(nativeObj, samples.nativeObj, results.nativeObj, flags);
    }

    //javadoc: EM::predict(samples)
    public float predict(Mat samples) {

        return predict_1(nativeObj, samples.nativeObj);
    }

    //javadoc: EM::getClustersNumber()
    public int getClustersNumber() {

        return getClustersNumber_0(nativeObj);
    }

    //javadoc: EM::setClustersNumber(val)
    public void setClustersNumber(int val) {

        setClustersNumber_0(nativeObj, val);

    }

    //javadoc: EM::getCovarianceMatrixType()
    public int getCovarianceMatrixType() {

        return getCovarianceMatrixType_0(nativeObj);
    }

    //javadoc: EM::setCovarianceMatrixType(val)
    public void setCovarianceMatrixType(int val) {

        setCovarianceMatrixType_0(nativeObj, val);

    }

    //javadoc: EM::getCovs(covs)
    public void getCovs(List<Mat> covs) {
        Mat covs_mat = new Mat();
        getCovs_0(nativeObj, covs_mat.nativeObj);
        Converters.Mat_to_vector_Mat(covs_mat, covs);
        covs_mat.release();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
