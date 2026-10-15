public class Complex {
    private double re;
    private double im;

    public Complex() {
        this.re = 0;
        this.im = 0;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public String toString() {
        if (im == 0) {
            return String.valueOf(re);
        }
        if (re == 0) {
            return String.valueOf(im) + "i";
        }
        if (im < 0) {
            return String.valueOf(re) + " - " + String.valueOf(-im) + "i";
        }
        return String.valueOf(re) + " + " + String.valueOf(im) + "i";
    }

    public Complex plus(Complex other) {
        double newRe = this.re + other.re;
        double newIm = this.im + other.im;
        return new Complex(newRe, newIm);
    }

    public Complex minus(Complex other) {
        double newRe = this.re - other.re;
        double newIm = this.im - other.im;
        return new Complex(newRe, newIm);
    }

    public Complex mult(Complex other) {
        double newRe = this.re * other.re - this.im * other.im;
        double newIm = this.re * other.im + this.im * other.re;
        return new Complex(newRe, newIm);
    }

    public Complex divide(Complex other) {
        if (other.re * other.re + other.im * other.im == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        double newRe = (this.re * other.re + this.im * other.im) / (other.re * other.re + other.im * other.im);
        double newIm = (this.im * other.re - this.re * other.im) / (other.re * other.re + other.im * other.im);
        return new Complex(newRe, newIm);
    }
}
