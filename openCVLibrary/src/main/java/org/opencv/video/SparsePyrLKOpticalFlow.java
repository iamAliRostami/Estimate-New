//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.video;

import org.opencv.core.Size;
import org.opencv.core.TermCriteria;

// C++: class SparsePyrLKOpticalFlow
//javadoc: SparsePyrLKOpticalFlow

public class SparsePyrLKOpticalFlow extends SparseOpticalFlow {

    protected SparsePyrLKOpticalFlow(long addr) {
        super(addr);
    }

    // internal usage only
    public static SparsePyrLKOpticalFlow __fromPtr__(long addr) {
        return new SparsePyrLKOpticalFlow(addr);
    }

    //
    // C++: static Ptr_SparsePyrLKOpticalFlow create(Size winSize = Size(21, 21), int maxLevel = 3, TermCriteria crit = TermCriteria(TermCriteria::COUNT+TermCriteria::EPS, 30, 0.01), int flags = 0, double minEigThreshold = 1e-4)
    //

    //javadoc: SparsePyrLKOpticalFlow::create(winSize, maxLevel, crit, flags, minEigThreshold)
    public static SparsePyrLKOpticalFlow create(Size winSize, int maxLevel, TermCriteria crit, int flags, double minEigThreshold) {

        return SparsePyrLKOpticalFlow.__fromPtr__(create_0(winSize.width, winSize.height, maxLevel, crit.type, crit.maxCount, crit.epsilon, flags, minEigThreshold));
    }

    //javadoc: SparsePyrLKOpticalFlow::create()
    public static SparsePyrLKOpticalFlow create() {

        return SparsePyrLKOpticalFlow.__fromPtr__(create_1());
    }


    //
    // C++:  Size getWinSize()
    //

    // C++: static Ptr_SparsePyrLKOpticalFlow create(Size winSize = Size(21, 21), int maxLevel = 3, TermCriteria crit = TermCriteria(TermCriteria::COUNT+TermCriteria::EPS, 30, 0.01), int flags = 0, double minEigThreshold = 1e-4)
    private static native long create_0(double winSize_width, double winSize_height, int maxLevel, int crit_type, int crit_maxCount, double crit_epsilon, int flags, double minEigThreshold);


    //
    // C++:  TermCriteria getTermCriteria()
    //

    private static native long create_1();


    //
    // C++:  double getMinEigThreshold()
    //

    // C++:  Size getWinSize()
    private static native double[] getWinSize_0(long nativeObj);


    //
    // C++:  int getFlags()
    //

    // C++:  TermCriteria getTermCriteria()
    private static native double[] getTermCriteria_0(long nativeObj);


    //
    // C++:  int getMaxLevel()
    //

    // C++:  double getMinEigThreshold()
    private static native double getMinEigThreshold_0(long nativeObj);


    //
    // C++:  void setFlags(int flags)
    //

    // C++:  int getFlags()
    private static native int getFlags_0(long nativeObj);


    //
    // C++:  void setMaxLevel(int maxLevel)
    //

    // C++:  int getMaxLevel()
    private static native int getMaxLevel_0(long nativeObj);


    //
    // C++:  void setMinEigThreshold(double minEigThreshold)
    //

    // C++:  void setFlags(int flags)
    private static native void setFlags_0(long nativeObj, int flags);


    //
    // C++:  void setTermCriteria(TermCriteria crit)
    //

    // C++:  void setMaxLevel(int maxLevel)
    private static native void setMaxLevel_0(long nativeObj, int maxLevel);


    //
    // C++:  void setWinSize(Size winSize)
    //

    // C++:  void setMinEigThreshold(double minEigThreshold)
    private static native void setMinEigThreshold_0(long nativeObj, double minEigThreshold);

    // C++:  void setTermCriteria(TermCriteria crit)
    private static native void setTermCriteria_0(long nativeObj, int crit_type, int crit_maxCount, double crit_epsilon);

    // C++:  void setWinSize(Size winSize)
    private static native void setWinSize_0(long nativeObj, double winSize_width, double winSize_height);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: SparsePyrLKOpticalFlow::getWinSize()
    public Size getWinSize() {

        return new Size(getWinSize_0(nativeObj));
    }

    //javadoc: SparsePyrLKOpticalFlow::setWinSize(winSize)
    public void setWinSize(Size winSize) {

        setWinSize_0(nativeObj, winSize.width, winSize.height);

    }

    //javadoc: SparsePyrLKOpticalFlow::getTermCriteria()
    public TermCriteria getTermCriteria() {

        return new TermCriteria(getTermCriteria_0(nativeObj));
    }

    //javadoc: SparsePyrLKOpticalFlow::setTermCriteria(crit)
    public void setTermCriteria(TermCriteria crit) {

        setTermCriteria_0(nativeObj, crit.type, crit.maxCount, crit.epsilon);

    }

    //javadoc: SparsePyrLKOpticalFlow::getMinEigThreshold()
    public double getMinEigThreshold() {

        return getMinEigThreshold_0(nativeObj);
    }

    //javadoc: SparsePyrLKOpticalFlow::setMinEigThreshold(minEigThreshold)
    public void setMinEigThreshold(double minEigThreshold) {

        setMinEigThreshold_0(nativeObj, minEigThreshold);

    }

    //javadoc: SparsePyrLKOpticalFlow::getFlags()
    public int getFlags() {

        return getFlags_0(nativeObj);
    }

    //javadoc: SparsePyrLKOpticalFlow::setFlags(flags)
    public void setFlags(int flags) {

        setFlags_0(nativeObj, flags);

    }

    //javadoc: SparsePyrLKOpticalFlow::getMaxLevel()
    public int getMaxLevel() {

        return getMaxLevel_0(nativeObj);
    }

    //javadoc: SparsePyrLKOpticalFlow::setMaxLevel(maxLevel)
    public void setMaxLevel(int maxLevel) {

        setMaxLevel_0(nativeObj, maxLevel);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
