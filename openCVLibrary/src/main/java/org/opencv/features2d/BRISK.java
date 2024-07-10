//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.features2d;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;

// C++: class BRISK
//javadoc: BRISK

public class BRISK extends Feature2D {

    protected BRISK(long addr) {
        super(addr);
    }

    // internal usage only
    public static BRISK __fromPtr__(long addr) {
        return new BRISK(addr);
    }

    //
    // C++: static Ptr_BRISK create(int thresh, int octaves, vector_float radiusList, vector_int numberList, float dMax = 5.85f, float dMin = 8.2f, vector_int indexChange = std::vector<int>())
    //

    //javadoc: BRISK::create(thresh, octaves, radiusList, numberList, dMax, dMin, indexChange)
    public static BRISK create(int thresh, int octaves, MatOfFloat radiusList, MatOfInt numberList, float dMax, float dMin, MatOfInt indexChange) {

        return BRISK.__fromPtr__(create_0(thresh, octaves, radiusList.nativeObj, numberList.nativeObj, dMax, dMin, indexChange.nativeObj));
    }

    //javadoc: BRISK::create(thresh, octaves, radiusList, numberList)
    public static BRISK create(int thresh, int octaves, MatOfFloat radiusList, MatOfInt numberList) {

        return BRISK.__fromPtr__(create_1(thresh, octaves, radiusList.nativeObj, numberList.nativeObj));
    }


    //
    // C++: static Ptr_BRISK create(int thresh = 30, int octaves = 3, float patternScale = 1.0f)
    //

    //javadoc: BRISK::create(thresh, octaves, patternScale)
    public static BRISK create(int thresh, int octaves, float patternScale) {

        return BRISK.__fromPtr__(create_2(thresh, octaves, patternScale));
    }

    //javadoc: BRISK::create()
    public static BRISK create() {

        return BRISK.__fromPtr__(create_3());
    }


    //
    // C++: static Ptr_BRISK create(vector_float radiusList, vector_int numberList, float dMax = 5.85f, float dMin = 8.2f, vector_int indexChange = std::vector<int>())
    //

    //javadoc: BRISK::create(radiusList, numberList, dMax, dMin, indexChange)
    public static BRISK create(MatOfFloat radiusList, MatOfInt numberList, float dMax, float dMin, MatOfInt indexChange) {

        return BRISK.__fromPtr__(create_4(radiusList.nativeObj, numberList.nativeObj, dMax, dMin, indexChange.nativeObj));
    }

    //javadoc: BRISK::create(radiusList, numberList)
    public static BRISK create(MatOfFloat radiusList, MatOfInt numberList) {

        return BRISK.__fromPtr__(create_5(radiusList.nativeObj, numberList.nativeObj));
    }


    //
    // C++:  String getDefaultName()
    //

    // C++: static Ptr_BRISK create(int thresh, int octaves, vector_float radiusList, vector_int numberList, float dMax = 5.85f, float dMin = 8.2f, vector_int indexChange = std::vector<int>())
    private static native long create_0(int thresh, int octaves, long radiusList_mat_nativeObj, long numberList_mat_nativeObj, float dMax, float dMin, long indexChange_mat_nativeObj);

    private static native long create_1(int thresh, int octaves, long radiusList_mat_nativeObj, long numberList_mat_nativeObj);

    // C++: static Ptr_BRISK create(int thresh = 30, int octaves = 3, float patternScale = 1.0f)
    private static native long create_2(int thresh, int octaves, float patternScale);

    private static native long create_3();

    // C++: static Ptr_BRISK create(vector_float radiusList, vector_int numberList, float dMax = 5.85f, float dMin = 8.2f, vector_int indexChange = std::vector<int>())
    private static native long create_4(long radiusList_mat_nativeObj, long numberList_mat_nativeObj, float dMax, float dMin, long indexChange_mat_nativeObj);

    private static native long create_5(long radiusList_mat_nativeObj, long numberList_mat_nativeObj);

    // C++:  String getDefaultName()
    private static native String getDefaultName_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: BRISK::getDefaultName()
    public String getDefaultName() {

        return getDefaultName_0(nativeObj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
