//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;

// C++: class NormalBayesClassifier
//javadoc: NormalBayesClassifier

public class NormalBayesClassifier extends StatModel {

    protected NormalBayesClassifier(long addr) {
        super(addr);
    }

    // internal usage only
    public static NormalBayesClassifier __fromPtr__(long addr) {
        return new NormalBayesClassifier(addr);
    }

    //
    // C++: static Ptr_NormalBayesClassifier create()
    //

    //javadoc: NormalBayesClassifier::create()
    public static NormalBayesClassifier create() {

        return NormalBayesClassifier.__fromPtr__(create_0());
    }


    //
    // C++: static Ptr_NormalBayesClassifier load(String filepath, String nodeName = String())
    //

    //javadoc: NormalBayesClassifier::load(filepath, nodeName)
    public static NormalBayesClassifier load(String filepath, String nodeName) {

        return NormalBayesClassifier.__fromPtr__(load_0(filepath, nodeName));
    }

    //javadoc: NormalBayesClassifier::load(filepath)
    public static NormalBayesClassifier load(String filepath) {

        return NormalBayesClassifier.__fromPtr__(load_1(filepath));
    }


    //
    // C++:  float predictProb(Mat inputs, Mat& outputs, Mat& outputProbs, int flags = 0)
    //

    // C++: static Ptr_NormalBayesClassifier create()
    private static native long create_0();

    // C++: static Ptr_NormalBayesClassifier load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);

    private static native long load_1(String filepath);

    // C++:  float predictProb(Mat inputs, Mat& outputs, Mat& outputProbs, int flags = 0)
    private static native float predictProb_0(long nativeObj, long inputs_nativeObj, long outputs_nativeObj, long outputProbs_nativeObj, int flags);

    private static native float predictProb_1(long nativeObj, long inputs_nativeObj, long outputs_nativeObj, long outputProbs_nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: NormalBayesClassifier::predictProb(inputs, outputs, outputProbs, flags)
    public float predictProb(Mat inputs, Mat outputs, Mat outputProbs, int flags) {

        return predictProb_0(nativeObj, inputs.nativeObj, outputs.nativeObj, outputProbs.nativeObj, flags);
    }

    //javadoc: NormalBayesClassifier::predictProb(inputs, outputs, outputProbs)
    public float predictProb(Mat inputs, Mat outputs, Mat outputProbs) {

        return predictProb_1(nativeObj, inputs.nativeObj, outputs.nativeObj, outputProbs.nativeObj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
