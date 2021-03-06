//
// This file is auto-generated. Please don't modify it!
//
package org.opencv.dnn;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.List;

// C++: class Layer
//javadoc: Layer

public class Layer extends Algorithm {

    protected Layer(long addr) {
        super(addr);
    }

    // internal usage only
    public static Layer __fromPtr__(long addr) {
        return new Layer(addr);
    }

    //
    // C++:  vector_Mat finalize(vector_Mat inputs)
    //

    // C++:  vector_Mat finalize(vector_Mat inputs)
    private static native long finalize_0(long nativeObj, long inputs_mat_nativeObj);


    //
    // C++:  void finalize(vector_Mat inputs, vector_Mat& outputs)
    //

    // C++:  void finalize(vector_Mat inputs, vector_Mat& outputs)
    private static native void finalize_1(long nativeObj, long inputs_mat_nativeObj, long outputs_mat_nativeObj);


    //
    // C++:  void run(vector_Mat inputs, vector_Mat& outputs, vector_Mat& internals)
    //

    // C++:  void run(vector_Mat inputs, vector_Mat& outputs, vector_Mat& internals)
    private static native void run_0(long nativeObj, long inputs_mat_nativeObj, long outputs_mat_nativeObj, long internals_mat_nativeObj);


    //
    // C++: vector_Mat Layer::blobs
    //

    // C++: vector_Mat Layer::blobs
    private static native long get_blobs_0(long nativeObj);


    //
    // C++: void Layer::blobs
    //

    // C++: void Layer::blobs
    private static native void set_blobs_0(long nativeObj, long blobs_mat_nativeObj);


    //
    // C++: String Layer::name
    //

    // C++: String Layer::name
    private static native String get_name_0(long nativeObj);


    //
    // C++: String Layer::type
    //

    // C++: String Layer::type
    private static native String get_type_0(long nativeObj);


    //
    // C++: int Layer::preferableTarget
    //

    // C++: int Layer::preferableTarget
    private static native int get_preferableTarget_0(long nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

    //javadoc: Layer::finalize(inputs)
    public List<Mat> finalize(List<Mat> inputs) {
        Mat inputs_mat = Converters.vector_Mat_to_Mat(inputs);
        List<Mat> retVal = new ArrayList<>();
        Mat retValMat = new Mat(finalize_0(nativeObj, inputs_mat.nativeObj));
        Converters.Mat_to_vector_Mat(retValMat, retVal);
        return retVal;
    }

    //javadoc: Layer::finalize(inputs, outputs)
    public void finalize(List<Mat> inputs, List<Mat> outputs) {
        Mat inputs_mat = Converters.vector_Mat_to_Mat(inputs);
        Mat outputs_mat = new Mat();
        finalize_1(nativeObj, inputs_mat.nativeObj, outputs_mat.nativeObj);
        Converters.Mat_to_vector_Mat(outputs_mat, outputs);
        outputs_mat.release();
    }

    //javadoc: Layer::run(inputs, outputs, internals)
    public void run(List<Mat> inputs, List<Mat> outputs, List<Mat> internals) {
        Mat inputs_mat = Converters.vector_Mat_to_Mat(inputs);
        Mat outputs_mat = new Mat();
        Mat internals_mat = Converters.vector_Mat_to_Mat(internals);
        run_0(nativeObj, inputs_mat.nativeObj, outputs_mat.nativeObj, internals_mat.nativeObj);
        Converters.Mat_to_vector_Mat(outputs_mat, outputs);
        outputs_mat.release();
        Converters.Mat_to_vector_Mat(internals_mat, internals);
        internals_mat.release();
    }

    //javadoc: Layer::get_blobs()
    public List<Mat> get_blobs() {
        List<Mat> retVal = new ArrayList<>();
        Mat retValMat = new Mat(get_blobs_0(nativeObj));
        Converters.Mat_to_vector_Mat(retValMat, retVal);
        return retVal;
    }

    //javadoc: Layer::set_blobs(blobs)
    public void set_blobs(List<Mat> blobs) {
        Mat blobs_mat = Converters.vector_Mat_to_Mat(blobs);
        set_blobs_0(nativeObj, blobs_mat.nativeObj);

    }

    //javadoc: Layer::get_name()
    public String get_name() {

        return get_name_0(nativeObj);
    }

    //javadoc: Layer::get_type()
    public String get_type() {

        return get_type_0(nativeObj);
    }

    //javadoc: Layer::get_preferableTarget()
    public int get_preferableTarget() {

        return get_preferableTarget_0(nativeObj);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        delete(nativeObj);
    }

}
