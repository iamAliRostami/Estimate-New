//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;

import java.util.List;

// C++: class TrainData
//javadoc: TrainData

public class TrainData {

    protected final long nativeObj;

    protected TrainData(long addr) {
        nativeObj = addr;
    }

    // internal usage only
    public static TrainData __fromPtr__(long addr) {
        return new TrainData(addr);
    }

    //javadoc: TrainData::getSubVector(vec, idx)
    public static Mat getSubVector(Mat vec, Mat idx) {

        return new Mat(getSubVector_0(vec.nativeObj, idx.nativeObj));
    }

    //
    // C++:  Mat getCatMap()
    //

    //javadoc: TrainData::create(samples, layout, responses, varIdx, sampleIdx, sampleWeights, varType)
    public static TrainData create(Mat samples, int layout, Mat responses, Mat varIdx, Mat sampleIdx, Mat sampleWeights, Mat varType) {

        return TrainData.__fromPtr__(create_0(samples.nativeObj, layout, responses.nativeObj, varIdx.nativeObj, sampleIdx.nativeObj, sampleWeights.nativeObj, varType.nativeObj));
    }


    //
    // C++:  Mat getCatOfs()
    //

    //javadoc: TrainData::create(samples, layout, responses)
    public static TrainData create(Mat samples, int layout, Mat responses) {

        return TrainData.__fromPtr__(create_1(samples.nativeObj, layout, responses.nativeObj));
    }


    //
    // C++:  Mat getClassLabels()
    //

    // C++:  Mat getCatMap()
    private static native long getCatMap_0(long nativeObj);


    //
    // C++:  Mat getDefaultSubstValues()
    //

    // C++:  Mat getCatOfs()
    private static native long getCatOfs_0(long nativeObj);


    //
    // C++:  Mat getMissing()
    //

    // C++:  Mat getClassLabels()
    private static native long getClassLabels_0(long nativeObj);


    //
    // C++:  Mat getNormCatResponses()
    //

    // C++:  Mat getDefaultSubstValues()
    private static native long getDefaultSubstValues_0(long nativeObj);


    //
    // C++:  Mat getResponses()
    //

    // C++:  Mat getMissing()
    private static native long getMissing_0(long nativeObj);


    //
    // C++:  Mat getSampleWeights()
    //

    // C++:  Mat getNormCatResponses()
    private static native long getNormCatResponses_0(long nativeObj);


    //
    // C++:  Mat getSamples()
    //

    // C++:  Mat getResponses()
    private static native long getResponses_0(long nativeObj);


    //
    // C++: static Mat getSubVector(Mat vec, Mat idx)
    //

    // C++:  Mat getSampleWeights()
    private static native long getSampleWeights_0(long nativeObj);


    //
    // C++:  Mat getTestNormCatResponses()
    //

    // C++:  Mat getSamples()
    private static native long getSamples_0(long nativeObj);


    //
    // C++:  Mat getTestResponses()
    //

    // C++: static Mat getSubVector(Mat vec, Mat idx)
    private static native long getSubVector_0(long vec_nativeObj, long idx_nativeObj);


    //
    // C++:  Mat getTestSampleIdx()
    //

    // C++:  Mat getTestNormCatResponses()
    private static native long getTestNormCatResponses_0(long nativeObj);


    //
    // C++:  Mat getTestSampleWeights()
    //

    // C++:  Mat getTestResponses()
    private static native long getTestResponses_0(long nativeObj);


    //
    // C++:  Mat getTestSamples()
    //

    // C++:  Mat getTestSampleIdx()
    private static native long getTestSampleIdx_0(long nativeObj);


    //
    // C++:  Mat getTrainNormCatResponses()
    //

    // C++:  Mat getTestSampleWeights()
    private static native long getTestSampleWeights_0(long nativeObj);


    //
    // C++:  Mat getTrainResponses()
    //

    // C++:  Mat getTestSamples()
    private static native long getTestSamples_0(long nativeObj);


    //
    // C++:  Mat getTrainSampleIdx()
    //

    // C++:  Mat getTrainNormCatResponses()
    private static native long getTrainNormCatResponses_0(long nativeObj);


    //
    // C++:  Mat getTrainSampleWeights()
    //

    // C++:  Mat getTrainResponses()
    private static native long getTrainResponses_0(long nativeObj);


    //
    // C++:  Mat getTrainSamples(int layout = ROW_SAMPLE, bool compressSamples = true, bool compressVars = true)
    //

    // C++:  Mat getTrainSampleIdx()
    private static native long getTrainSampleIdx_0(long nativeObj);

    // C++:  Mat getTrainSampleWeights()
    private static native long getTrainSampleWeights_0(long nativeObj);


    //
    // C++:  Mat getVarIdx()
    //

    // C++:  Mat getTrainSamples(int layout = ROW_SAMPLE, bool compressSamples = true, bool compressVars = true)
    private static native long getTrainSamples_0(long nativeObj, int layout, boolean compressSamples, boolean compressVars);


    //
    // C++:  Mat getVarSymbolFlags()
    //

    private static native long getTrainSamples_1(long nativeObj);


    //
    // C++:  Mat getVarType()
    //

    // C++:  Mat getVarIdx()
    private static native long getVarIdx_0(long nativeObj);


    //
    // C++: static Ptr_TrainData create(Mat samples, int layout, Mat responses, Mat varIdx = Mat(), Mat sampleIdx = Mat(), Mat sampleWeights = Mat(), Mat varType = Mat())
    //

    // C++:  Mat getVarSymbolFlags()
    private static native long getVarSymbolFlags_0(long nativeObj);

    // C++:  Mat getVarType()
    private static native long getVarType_0(long nativeObj);


    //
    // C++:  int getCatCount(int vi)
    //

    // C++: static Ptr_TrainData create(Mat samples, int layout, Mat responses, Mat varIdx = Mat(), Mat sampleIdx = Mat(), Mat sampleWeights = Mat(), Mat varType = Mat())
    private static native long create_0(long samples_nativeObj, int layout, long responses_nativeObj, long varIdx_nativeObj, long sampleIdx_nativeObj, long sampleWeights_nativeObj, long varType_nativeObj);


    //
    // C++:  int getLayout()
    //

    private static native long create_1(long samples_nativeObj, int layout, long responses_nativeObj);


    //
    // C++:  int getNAllVars()
    //

    // C++:  int getCatCount(int vi)
    private static native int getCatCount_0(long nativeObj, int vi);


    //
    // C++:  int getNSamples()
    //

    // C++:  int getLayout()
    private static native int getLayout_0(long nativeObj);


    //
    // C++:  int getNTestSamples()
    //

    // C++:  int getNAllVars()
    private static native int getNAllVars_0(long nativeObj);


    //
    // C++:  int getNTrainSamples()
    //

    // C++:  int getNSamples()
    private static native int getNSamples_0(long nativeObj);


    //
    // C++:  int getNVars()
    //

    // C++:  int getNTestSamples()
    private static native int getNTestSamples_0(long nativeObj);


    //
    // C++:  int getResponseType()
    //

    // C++:  int getNTrainSamples()
    private static native int getNTrainSamples_0(long nativeObj);


    //
    // C++:  void getNames(vector_String names)
    //

    // C++:  int getNVars()
    private static native int getNVars_0(long nativeObj);


    //
    // C++:  void getSample(Mat varIdx, int sidx, float* buf)
    //

    // C++:  int getResponseType()
    private static native int getResponseType_0(long nativeObj);


    //
    // C++:  void getValues(int vi, Mat sidx, float* values)
    //

    // C++:  void getNames(vector_String names)
    private static native void getNames_0(long nativeObj, List<String> names);


    //
    // C++:  void setTrainTestSplit(int count, bool shuffle = true)
    //

    // C++:  void getSample(Mat varIdx, int sidx, float* buf)
    private static native void getSample_0(long nativeObj, long varIdx_nativeObj, int sidx, float buf);

    // C++:  void getValues(int vi, Mat sidx, float* values)
    private static native void getValues_0(long nativeObj, int vi, long sidx_nativeObj, float values);


    //
    // C++:  void setTrainTestSplitRatio(double ratio, bool shuffle = true)
    //

    // C++:  void setTrainTestSplit(int count, bool shuffle = true)
    private static native void setTrainTestSplit_0(long nativeObj, int count, boolean shuffle);

    private static native void setTrainTestSplit_1(long nativeObj, int count);


    //
    // C++:  void shuffleTrainTest()
    //

    // C++:  void setTrainTestSplitRatio(double ratio, bool shuffle = true)
    private static native void setTrainTestSplitRatio_0(long nativeObj, double ratio, boolean shuffle);

    private static native void setTrainTestSplitRatio_1(long nativeObj, double ratio);

    // C++:  void shuffleTrainTest()
    private static native void shuffleTrainTest_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: TrainData::getCatMap()
    public Mat getCatMap() {

        return new Mat(getCatMap_0(nativeObj));
    }

    //javadoc: TrainData::getCatOfs()
    public Mat getCatOfs() {

        return new Mat(getCatOfs_0(nativeObj));
    }

    //javadoc: TrainData::getClassLabels()
    public Mat getClassLabels() {

        return new Mat(getClassLabels_0(nativeObj));
    }

    //javadoc: TrainData::getDefaultSubstValues()
    public Mat getDefaultSubstValues() {

        return new Mat(getDefaultSubstValues_0(nativeObj));
    }

    //javadoc: TrainData::getMissing()
    public Mat getMissing() {

        return new Mat(getMissing_0(nativeObj));
    }

    //javadoc: TrainData::getNormCatResponses()
    public Mat getNormCatResponses() {

        return new Mat(getNormCatResponses_0(nativeObj));
    }

    //javadoc: TrainData::getResponses()
    public Mat getResponses() {

        return new Mat(getResponses_0(nativeObj));
    }

    //javadoc: TrainData::getSampleWeights()
    public Mat getSampleWeights() {

        return new Mat(getSampleWeights_0(nativeObj));
    }

    //javadoc: TrainData::getSamples()
    public Mat getSamples() {

        return new Mat(getSamples_0(nativeObj));
    }

    //javadoc: TrainData::getTestNormCatResponses()
    public Mat getTestNormCatResponses() {

        return new Mat(getTestNormCatResponses_0(nativeObj));
    }

    //javadoc: TrainData::getTestResponses()
    public Mat getTestResponses() {

        return new Mat(getTestResponses_0(nativeObj));
    }

    //javadoc: TrainData::getTestSampleIdx()
    public Mat getTestSampleIdx() {

        return new Mat(getTestSampleIdx_0(nativeObj));
    }

    //javadoc: TrainData::getTestSampleWeights()
    public Mat getTestSampleWeights() {

        return new Mat(getTestSampleWeights_0(nativeObj));
    }

    //javadoc: TrainData::getTestSamples()
    public Mat getTestSamples() {

        return new Mat(getTestSamples_0(nativeObj));
    }

    //javadoc: TrainData::getTrainNormCatResponses()
    public Mat getTrainNormCatResponses() {

        return new Mat(getTrainNormCatResponses_0(nativeObj));
    }

    //javadoc: TrainData::getTrainResponses()
    public Mat getTrainResponses() {

        return new Mat(getTrainResponses_0(nativeObj));
    }

    //javadoc: TrainData::getTrainSampleIdx()
    public Mat getTrainSampleIdx() {

        return new Mat(getTrainSampleIdx_0(nativeObj));
    }

    //javadoc: TrainData::getTrainSampleWeights()
    public Mat getTrainSampleWeights() {

        return new Mat(getTrainSampleWeights_0(nativeObj));
    }

    //javadoc: TrainData::getTrainSamples(layout, compressSamples, compressVars)
    public Mat getTrainSamples(int layout, boolean compressSamples, boolean compressVars) {

        return new Mat(getTrainSamples_0(nativeObj, layout, compressSamples, compressVars));
    }

    //javadoc: TrainData::getTrainSamples()
    public Mat getTrainSamples() {

        return new Mat(getTrainSamples_1(nativeObj));
    }

    //javadoc: TrainData::getVarIdx()
    public Mat getVarIdx() {

        return new Mat(getVarIdx_0(nativeObj));
    }

    //javadoc: TrainData::getVarSymbolFlags()
    public Mat getVarSymbolFlags() {

        return new Mat(getVarSymbolFlags_0(nativeObj));
    }

    //javadoc: TrainData::getVarType()
    public Mat getVarType() {

        return new Mat(getVarType_0(nativeObj));
    }

    //javadoc: TrainData::getCatCount(vi)
    public int getCatCount(int vi) {

        return getCatCount_0(nativeObj, vi);
    }

    //javadoc: TrainData::getLayout()
    public int getLayout() {

        return getLayout_0(nativeObj);
    }

    //javadoc: TrainData::getNAllVars()
    public int getNAllVars() {

        return getNAllVars_0(nativeObj);
    }

    //javadoc: TrainData::getNSamples()
    public int getNSamples() {

        return getNSamples_0(nativeObj);
    }

    //javadoc: TrainData::getNTestSamples()
    public int getNTestSamples() {

        return getNTestSamples_0(nativeObj);
    }

    //javadoc: TrainData::getNTrainSamples()
    public int getNTrainSamples() {

        return getNTrainSamples_0(nativeObj);
    }

    //javadoc: TrainData::getNVars()
    public int getNVars() {

        return getNVars_0(nativeObj);
    }

    //javadoc: TrainData::getResponseType()
    public int getResponseType() {

        return getResponseType_0(nativeObj);
    }

    //javadoc: TrainData::getNames(names)
    public void getNames(List<String> names) {

        getNames_0(nativeObj, names);

    }

    //javadoc: TrainData::getSample(varIdx, sidx, buf)
    public void getSample(Mat varIdx, int sidx, float buf) {

        getSample_0(nativeObj, varIdx.nativeObj, sidx, buf);

    }

    //javadoc: TrainData::getValues(vi, sidx, values)
    public void getValues(int vi, Mat sidx, float values) {

        getValues_0(nativeObj, vi, sidx.nativeObj, values);

    }

    //javadoc: TrainData::setTrainTestSplit(count, shuffle)
    public void setTrainTestSplit(int count, boolean shuffle) {

        setTrainTestSplit_0(nativeObj, count, shuffle);

    }

    //javadoc: TrainData::setTrainTestSplit(count)
    public void setTrainTestSplit(int count) {

        setTrainTestSplit_1(nativeObj, count);

    }

    //javadoc: TrainData::setTrainTestSplitRatio(ratio, shuffle)
    public void setTrainTestSplitRatio(double ratio, boolean shuffle) {

        setTrainTestSplitRatio_0(nativeObj, ratio, shuffle);

    }

    //javadoc: TrainData::setTrainTestSplitRatio(ratio)
    public void setTrainTestSplitRatio(double ratio) {

        setTrainTestSplitRatio_1(nativeObj, ratio);

    }

    //javadoc: TrainData::shuffleTrainTest()
    public void shuffleTrainTest() {

        shuffleTrainTest_0(nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
