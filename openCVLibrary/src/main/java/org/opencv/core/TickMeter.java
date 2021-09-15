//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.core;


// C++: class TickMeter
//javadoc: TickMeter

public class TickMeter {

    protected final long nativeObj;

    protected TickMeter(long addr) {
        nativeObj = addr;
    }

    //javadoc: TickMeter::TickMeter()
    public TickMeter() {

        nativeObj = TickMeter_0();

    }

    // internal usage only
    public static TickMeter __fromPtr__(long addr) {
        return new TickMeter(addr);
    }

    //
    // C++:   TickMeter()
    //

    // C++:   TickMeter()
    private static native long TickMeter_0();


    //
    // C++:  double getTimeMicro()
    //

    // C++:  double getTimeMicro()
    private static native double getTimeMicro_0(long nativeObj);


    //
    // C++:  double getTimeMilli()
    //

    // C++:  double getTimeMilli()
    private static native double getTimeMilli_0(long nativeObj);


    //
    // C++:  double getTimeSec()
    //

    // C++:  double getTimeSec()
    private static native double getTimeSec_0(long nativeObj);


    //
    // C++:  int64 getCounter()
    //

    // C++:  int64 getCounter()
    private static native long getCounter_0(long nativeObj);


    //
    // C++:  int64 getTimeTicks()
    //

    // C++:  int64 getTimeTicks()
    private static native long getTimeTicks_0(long nativeObj);


    //
    // C++:  void reset()
    //

    // C++:  void reset()
    private static native void reset_0(long nativeObj);


    //
    // C++:  void start()
    //

    // C++:  void start()
    private static native void start_0(long nativeObj);


    //
    // C++:  void stop()
    //

    // C++:  void stop()
    private static native void stop_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    public long getNativeObjAddr() {
        return nativeObj;
    }

    //javadoc: TickMeter::getTimeMicro()
    public double getTimeMicro() {

        return getTimeMicro_0(nativeObj);
    }

    //javadoc: TickMeter::getTimeMilli()
    public double getTimeMilli() {

        return getTimeMilli_0(nativeObj);
    }

    //javadoc: TickMeter::getTimeSec()
    public double getTimeSec() {

        return getTimeSec_0(nativeObj);
    }

    //javadoc: TickMeter::getCounter()
    public long getCounter() {

        return getCounter_0(nativeObj);
    }

    //javadoc: TickMeter::getTimeTicks()
    public long getTimeTicks() {

        return getTimeTicks_0(nativeObj);
    }

    //javadoc: TickMeter::reset()
    public void reset() {

        reset_0(nativeObj);

    }

    //javadoc: TickMeter::start()
    public void start() {

        start_0(nativeObj);

    }

    //javadoc: TickMeter::stop()
    public void stop() {

        stop_0(nativeObj);

    }

    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }

}
