/**
 * 2次方程式の解を計算する
 *
 * a x^2 + b x + c = 0
 *
 *  x1 = (- b - sqrt(b^2 - 4 a c) ) / (2 a)
 *  x2 = (- b + sqrt(b^2 - 4 a c) ) / (2 a)   # this is NG
 *
 * a x^2 + b x + c = a (x - x1) (x - x2) = a x^2 - a (x1 + x2) x + a x1 x2
 *
 *  x2 = c / (a x1)  # this is ok
 *
 */
class QuadEq {
    public static double [] quadEq(double a, double b, double c) throws NonQuadraticException, NoRealAnswerException {
        if(a == 0) throw new NonQuadraticException();
        double d = b * b - 4 * a * c;
        if(d < 0) throw new NoRealAnswerException();
        double e = Math.sqrt(d);
        double x1 = (- b - e) / (2 * a);
        double x2 = c / (a * x1);
        return new double [] {x1, x2};
    }
    public static void main(String [] args) {
        try {
            quadEq(0, 1, 2);  // this is a linear eq.
        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            quadEq(1,0,2);    // this has no real answer
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

class NonQuadraticException extends Exception {
    NonQuadraticException() {
        super("a must be non-zero");
    }
}

class NoRealAnswerException extends Exception {
    NoRealAnswerException() {
        super("No real answer exists");
    }
}
