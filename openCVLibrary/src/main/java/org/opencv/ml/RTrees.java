//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

// C++: class RTrees
//javadoc: RTrees

public class RTrees extends DTrees {

    protected RTrees(long addr) {
        super(addr);
    }

    // internal usage only
    public static RTrees __fromPtr__(long addr) {
        return new RTrees(addr);
    }

    //
    // C++:  Mat getVarImportance()
    //

    //javadoc: RTrees::create()
    public static RTrees create() {

        return RTrees.__fromPtr__(create_0());
    }


    //
    // C++: static Ptr_RTrees create()
    //

    //javadoc: RTrees::load(filepath, nodeName)
    public static RTrees load(String filepath, String nodeName) {

        return RTrees.__fromPtr__(load_0(filepath, nodeName));
    }


    //
    // C++: static Ptr_RTrees load(String filepath, String nodeName = String())
    //

    //javadoc: RTrees::load(filepath)
    public static RTrees load(String filepath) {

        return RTrees.__fromPtr__(load_1(filepath));
    }

    // C++:  Mat getVarImportance()
    private static native long getVarImportance_0(long nativeObj);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    // C++: static Ptr_RTrees create()
    private static native long create_0();


    //
    // C++:  bool getCalculateVarImportance()
    //

    // C++: static Ptr_RTrees load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);


    //
    // C++:  int getActiveVarCount()
    //

    private static native long load_1(String filepath);


    //
    // C++:  void getVotes(Mat samples, Mat& results, int flags)
    //

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  void setActiveVarCount(int val)
    //

    // C++:  bool getCalculateVarImportance()
    private static native boolean getCalculateVarImportance_0(long nativeObj);


    //
    // C++:  void setCalculateVarImportance(bool val)
    //

    // C++:  int getActiveVarCount()
    private static native int getActiveVarCount_0(long nativeObj);


    //
    // C++:  void setTermCriteria(TermCriteria val)
    //

    // C++:  void getVotes(Mat samples, Mat& results, int flags)
    private static native void getVotes_0(long nativeObj, long samples_nativeObj, long results_nativeObj, int flags);

    // C++:  void setActiveVarCount(int val)
    private static native void setActiveVarCount_0(long nativeObj, int val);

    // C++:  void setCalculateVarImportance(bool val)
    private static native void setCalculateVarImportance_0(long nativeObj, boolean val);

    // C++:  void setTermCriteria(TermCriteria val)
    private static native void setTermCriteria_0(long nativeObj, int val_type, int val_maxCount, double val_epsilon);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: RTrees::getVarImportance()
    public Mat getVarImportance() {

        return new Mat(getVarImportance_0(nativeObj));
    }

    //javadoc: RTrees::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: RTrees::setTermCriteria(val)
    public void setTermCriteria(TermCriteria val) {

        setTermCriteria_0(nativeObj, val.type, val.maxCount, val.epsilon);

    }

    //javadoc: RTrees::getCalculateVarImportance()
    public boolean getCalculateVarImportance() {

        return getCalculateVarImportance_0(nativeObj);
    }

    //javadoc: RTrees::setCalculateVarImportance(val)
    public void setCalculateVarImportance(boolean val) {

        setCalculateVarImportance_0(nativeObj, val);

    }

    //javadoc: RTrees::getActiveVarCount()
    public int getActiveVarCount() {

        return getActiveVarCount_0(nativeObj);
    }

    //javadoc: RTrees::setActiveVarCount(val)
    public void setActiveVarCount(int val) {

        setActiveVarCount_0(nativeObj, val);

    }

    //javadoc: RTrees::getVotes(samples, results, flags)
    public void getVotes(Mat samples, Mat results, int flags) {

        getVotes_0(nativeObj, samples.nativeObj, results.nativeObj, flags);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
