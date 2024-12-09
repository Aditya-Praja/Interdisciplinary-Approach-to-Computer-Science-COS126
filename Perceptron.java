public class Perceptron {

    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    // length of weight vector
    private int n;
    // creates double array instance var to be used as a weight vector
    private double[] perc;

    // creates a weight vector initialized to all 0 of a given length
    public Perceptron(int n) {
        this.n = n;
        perc = new double[n];
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return this.n;
    }

    // Returns the weighted sum of the weight vector and x.
    public double weightedSum(double[] x) {
        if (this.n != x.length) {
            throw new IllegalArgumentException("lengths of two vectors are not equal");
        }
        double sum = 0.0;
        for (int i = 0; i < this.n; i++) {
            sum += x[i] * this.perc[i];
        }
        return sum;
    }

    // Predicts the binary label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        int sign;
        if (this.weightedSum(x) <= 0) {
            sign = -1;
        }
        else {
            sign = 1;
        }
        return sign;
    }

    // Trains this perceptron on the binary labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int binaryLabel) {
        if (binaryLabel != predict(x)) {
            for (int i = 0; i < this.n; i++) {
                this.perc[i] += binaryLabel * x[i];
            }
        }
    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        String par1 = "(";
        String in = "";
        for (int i = 0; i < this.n - 1; i++) {
            in = in + "" + this.perc[i] + ", ";
        }
        String las = perc[n - 1] + ")";
        return par1 + in + las;
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        Perceptron first = new Perceptron(4);
        System.out.println("length = " + first.numberOfInputs());
        double[] per = { 1, 2, 3, 4 };
        System.out.println("Sum = " + first.weightedSum(per));
        System.out.println("prediction = " + first.predict(per));
        first.train(per, 1);
        System.out.println("final = " + first.toString());
    }
}
