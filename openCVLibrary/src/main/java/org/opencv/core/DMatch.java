package org.opencv.core;

//C++: class DMatch

/**
 * Structure for matching: query descriptor index, train descriptor index, train
 * image index and distance between descriptors.
 */
public class DMatch {

    /**
     * Query descriptor index.
     */
    public final int queryIdx;
    /**
     * Train descriptor index.
     */
    public final int trainIdx;
    /**
     * Train image index.
     */
    public final int imgIdx;

    // javadoc: DMatch::distance
    public final float distance;

    // javadoc: DMatch::DMatch()
    public DMatch() {
        this(-1, -1, Float.MAX_VALUE);
    }

    // javadoc: DMatch::DMatch(_queryIdx, _trainIdx, _distance)
    public DMatch(int _queryIdx, int _trainIdx, float _distance) {
        queryIdx = _queryIdx;
        trainIdx = _trainIdx;
        imgIdx = -1;
        distance = _distance;
    }

    // javadoc: DMatch::DMatch(_queryIdx, _trainIdx, _imgIdx, _distance)
    public DMatch(int _queryIdx, int _trainIdx, int _imgIdx, float _distance) {
        queryIdx = _queryIdx;
        trainIdx = _trainIdx;
        imgIdx = _imgIdx;
        distance = _distance;
    }

    public boolean lessThan(DMatch it) {
        return distance < it.distance;
    }

    @Override
    public String toString() {
        return "DMatch [queryIdx=" + queryIdx + ", trainIdx=" + trainIdx
                + ", imgIdx=" + imgIdx + ", distance=" + distance + "]";
    }

}
