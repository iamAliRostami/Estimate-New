//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.features2d;

// C++: class BFMatcher
//javadoc: BFMatcher

public class BFMatcher extends DescriptorMatcher {

    protected BFMatcher(long addr) {
        super(addr);
    }

    //javadoc: BFMatcher::BFMatcher(normType, crossCheck)
    public BFMatcher(int normType, boolean crossCheck) {

        super(BFMatcher_0(normType, crossCheck));

    }

    //
    // C++:   BFMatcher(int normType = NORM_L2, bool crossCheck = false)
    //

    //javadoc: BFMatcher::BFMatcher()
    public BFMatcher() {

        super(BFMatcher_1());

    }

    // internal usage only
    public static BFMatcher __fromPtr__(long addr) {
        return new BFMatcher(addr);
    }


    //
    // C++: static Ptr_BFMatcher create(int normType = NORM_L2, bool crossCheck = false)
    //

    //javadoc: BFMatcher::create(normType, crossCheck)
    public static BFMatcher create(int normType, boolean crossCheck) {

        return BFMatcher.__fromPtr__(create_0(normType, crossCheck));
    }

    //javadoc: BFMatcher::create()
    public static BFMatcher create() {

        return BFMatcher.__fromPtr__(create_1());
    }

    // C++:   BFMatcher(int normType = NORM_L2, bool crossCheck = false)
    private static native long BFMatcher_0(int normType, boolean crossCheck);

    private static native long BFMatcher_1();

    // C++: static Ptr_BFMatcher create(int normType = NORM_L2, bool crossCheck = false)
    private static native long create_0(int normType, boolean crossCheck);

    private static native long create_1();

    // native support for java finalize()
    private static native void delete(long nativeObj);

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
