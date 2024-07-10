package org.opencv.core;

import java.io.Serial;

public class CvException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CvException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "CvException [" + super.toString() + "]";
    }
}
