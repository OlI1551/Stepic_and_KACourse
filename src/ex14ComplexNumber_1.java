import java.util.Objects;

public class ex14ComplexNumber_1 {
    private double re;
    private double im;

    public ex14ComplexNumber_1() {
    }

    public ex14ComplexNumber_1(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ex14ComplexNumber_1 that = (ex14ComplexNumber_1) o;
        return Double.compare(re, that.re) == 0 && Double.compare(im, that.im) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
}


