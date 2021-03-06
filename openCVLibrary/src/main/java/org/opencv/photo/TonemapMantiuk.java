//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.photo;

// C++: class TonemapMantiuk
//javadoc: TonemapMantiuk

public class TonemapMantiuk extends Tonemap {

    protected TonemapMantiuk(long addr) {
        super(addr);
    }

    // internal usage only
    public static TonemapMantiuk __fromPtr__(long addr) {
        return new TonemapMantiuk(addr);
    }

    //
    // C++:  float getSaturation()
    //

    // C++:  float getSaturation()
    private static native float getSaturation_0(long nativeObj);


    //
    // C++:  float getScale()
    //

    // C++:  float getScale()
    private static native float getScale_0(long nativeObj);


    //
    // C++:  void setSaturation(float saturation)
    //

    // C++:  void setSaturation(float saturation)
    private static native void setSaturation_0(long nativeObj, float saturation);


    //
    // C++:  void setScale(float scale)
    //

    // C++:  void setScale(float scale)
    private static native void setScale_0(long nativeObj, float scale);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: TonemapMantiuk::getSaturation()
    public float getSaturation() {

        return getSaturation_0(nativeObj);
    }

    //javadoc: TonemapMantiuk::setSaturation(saturation)
    public void setSaturation(float saturation) {

        setSaturation_0(nativeObj, saturation);

    }

    //javadoc: TonemapMantiuk::getScale()
    public float getScale() {

        return getScale_0(nativeObj);
    }

    //javadoc: TonemapMantiuk::setScale(scale)
    public void setScale(float scale) {

        setScale_0(nativeObj, scale);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
