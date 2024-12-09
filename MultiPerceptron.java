public class MultiPerceptron {

    private Perceptron[] multiperc;
    private int classes;
    private int inputs;

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    public MultiPerceptron(int m, int n) {
        classes = m;
        inputs = n;
        multiperc = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            multiperc[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return classes;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return inputs;
    }

    // Returns the predicted class label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double max = multiperc[0].weightedSum(x);
        int num = 0;
        double weights;
        for (int i = 0; i < classes; i++) {
            weights = multiperc[i].weightedSum(x);
            if (weights > max) {
                max = weights;
                num = i;
            }
        }

        return num;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int classLabel) {
        for (int i = 0; i < classes; i++) {
            if (classLabel == i) {
                multiperc[i].train(x, 1);
            }
            else {
                multiperc[i].train(x, -1);
            }
        }
    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        String par1 = "(";
        String in = "";
        for (int i = 0; i < classes - 1; i++) {
            in += multiperc[i].toString() + ", ";
        }
        String las = multiperc[classes - 1] + ")";
        return par1 + in + las;
    }

    // Tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        MultiPerceptron mult = new MultiPerceptron(2, 2);
        double[] test = { 2.0, 3.0 };
        System.out.println("classes = " + mult.numberOfClasses());
        System.out.println("inputs = " + mult.numberOfInputs());
        System.out.println("prediction = " + mult.predictMulti(test));
        mult.trainMulti(test, 1);
        System.out.println("final = " + mult.toString());


    }
}
