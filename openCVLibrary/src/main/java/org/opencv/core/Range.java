package org.opencv.core;

//javadoc:Range
public class Range {

    public int start, end;

    public Range(int s, int e) {
        this.start = s;
        this.end = e;
    }

    public Range() {
        this(0, 0);
    }

    public Range(double[] vals) {
        set(vals);
    }

    public static Range all() {
        return new Range(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void set(double[] vals) {
        if (vals != null) {
            start = vals.length > 0 ? (int) vals[0] : 0;
            end = vals.length > 1 ? (int) vals[1] : 0;
        } else {
            start = 0;
            end = 0;
        }

    }

    public int size() {
        return empty() ? 0 : end - start;
    }

    public boolean empty() {
        return end <= start;
    }

    public Range intersection(Range r1) {
        Range r = new Range(Math.max(r1.start, this.start), Math.min(r1.end, this.end));
        r.end = Math.max(r.end, r.start);
        return r;
    }

    public Range shift(int delta) {
        return new Range(start + delta, end + delta);
    }

    public Range clone() {
        return new Range(start, end);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.hashCode(start);
        result = prime * result + Double.hashCode(end);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Range it)) return false;
        return start == it.start && end == it.end;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + ")";
    }
}
