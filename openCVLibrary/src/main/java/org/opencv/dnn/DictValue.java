//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.dnn;

// C++: class DictValue
//javadoc: DictValue

public class DictValue {

    protected final long nativeObj;

    protected DictValue(long addr) {
        nativeObj = addr;
    }

    //javadoc: DictValue::DictValue(s)
    public DictValue(String s) {

        nativeObj = DictValue_0(s);

    }

    //javadoc: DictValue::DictValue(p)
    public DictValue(double p) {

        nativeObj = DictValue_1(p);

    }

    //
    // C++:   DictValue(String s)
    //

    //javadoc: DictValue::DictValue(i)
    public DictValue(int i) {

        nativeObj = DictValue_2(i);

    }


    //
    // C++:   DictValue(double p)
    //

    // internal usage only
    public static DictValue __fromPtr__(long addr) {
        return new DictValue(addr);
    }


    //
    // C++:   DictValue(int i)
    //

    // C++:   DictValue(String s)
    private static native long DictValue_0(String s);


    //
    // C++:  String getStringValue(int idx = -1)
    //

    // C++:   DictValue(double p)
    private static native long DictValue_1(double p);

    // C++:   DictValue(int i)
    private static native long DictValue_2(int i);


    //
    // C++:  bool isInt()
    //

    // C++:  String getStringValue(int idx = -1)
    private static native String getStringValue_0(long nativeObj, int idx);


    //
    // C++:  bool isReal()
    //

    private static native String getStringValue_1(long nativeObj);


    //
    // C++:  bool isString()
    //

    // C++:  bool isInt()
    private static native boolean isInt_0(long nativeObj);


    //
    // C++:  double getRealValue(int idx = -1)
    //

    // C++:  bool isReal()
    private static native boolean isReal_0(long nativeObj);

    // C++:  bool isString()
    private static native boolean isString_0(long nativeObj);


    //
    // C++:  int getIntValue(int idx = -1)
    //

    // C++:  double getRealValue(int idx = -1)
    private static native double getRealValue_0(long nativeObj, int idx);

    private static native double getRealValue_1(long nativeObj);

    // C++:  int getIntValue(int idx = -1)
    private static native int getIntValue_0(long nativeObj, int idx);

    private static native int getIntValue_1(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: DictValue::getStringValue(idx)
    public String getStringValue(int idx) {

        return getStringValue_0(nativeObj, idx);
    }

    //javadoc: DictValue::getStringValue()
    public String getStringValue() {

        return getStringValue_1(nativeObj);
    }

    //javadoc: DictValue::isInt()
    public boolean isInt() {

        return isInt_0(nativeObj);
    }

    //javadoc: DictValue::isReal()
    public boolean isReal() {

        return isReal_0(nativeObj);
    }

    //javadoc: DictValue::isString()
    public boolean isString() {

        return isString_0(nativeObj);
    }

    //javadoc: DictValue::getRealValue(idx)
    public double getRealValue(int idx) {

        return getRealValue_0(nativeObj, idx);
    }

    //javadoc: DictValue::getRealValue()
    public double getRealValue() {

        return getRealValue_1(nativeObj);
    }

    //javadoc: DictValue::getIntValue(idx)
    public int getIntValue(int idx) {

        return getIntValue_0(nativeObj, idx);
    }

    //javadoc: DictValue::getIntValue()
    public int getIntValue() {

        return getIntValue_1(nativeObj);
    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
