import java.awt.Color;

public class ImageClassifier {

    // instance variable for the width of the images in test cases
    private int width;
    // instance variable for the height of the images in test cases
    private int height;
    // instances variable for the number of classes in the test cases
    private int classes;
    // instance variable for the names of the classes in the test cases
    private String[] names;
    // instance variable for a MultiPerceptron object that is used to train
    // and predict
    private MultiPerceptron z;

    // Uses the provided configuration file to create an
    // ImageClassifier object.
    public ImageClassifier(String configFile) {
        In config = new In(configFile);
        width = config.readInt();
        height = config.readInt();
        classes = config.readInt();
        // Creates a MultiPerceptron object set to all zeros
        z = new MultiPerceptron(classes, width * height);
        names = new String[classes];
        for (int i = 0; i < classes; i++) {
            names[i] = config.readString();
        }
    }

    // Creates a feature vector (1D array) from the given picture.
    public double[] extractFeatures(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        if (w != width || h != height) {
            throw new IllegalArgumentException("Dimensions of input file "
                                                       + "are not correct.");
        }
        // creates array of the rgb values of each pixel
        double[] feat = new double[w * h];
        int count = 0;
        // Change loop order to row-major (row first, then column)
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Color color = picture.get(c, r);
                feat[count] = color.getBlue();
                count++;
            }
        }
        return feat;
    }


    // Trains the perceptron on the given training data file.
    public void trainClassifier(String trainFile) {
        In n = new In(trainFile);
        // trains the Classifier with all images in a train file
        while (!n.isEmpty()) {
            Picture test = new Picture(n.readString());
            z.trainMulti(extractFeatures(test), n.readInt());
        }
    }

    // Returns the name of the class for the given class label.
    public String classNameOf(int classLabel) {
        if (classLabel < 0 || classLabel >= names.length) {
            throw new IllegalArgumentException(
                    "Index " + classLabel +
                            " out of bounds for length " + names.length);
        }
        return this.names[classLabel];
    }

    // Returns the predicted class for the given picture.
    public int classifyImage(Picture picture) {
        // creates a double array with the features from the picture object
        double[] features = extractFeatures(picture);
        return z.predictMulti(features);
    }

    // Returns the error rate on the given testing data file.
    // Also prints the misclassified examples - see specification.
    public double testClassifier(String testFile) {
        In n = new In(testFile);
        // numerator = times prediction doesn't equal the actual class label
        int top = 0;
        // denominator = total number of images
        int bottom = 0;
        // test the classifier with all images from test file
        while (!n.isEmpty()) {
            bottom++;
            String x = n.readString();
            int y = n.readInt();
            Picture test = new Picture(x);
            int pred = classifyImage(test);
            // checks to see if predication equals the class label
            if (pred != y) {
                top++;
                System.out.println(x + ", label = " + classNameOf(y) + ", "
                                           + "predict = " + classNameOf(pred));
            }
        }
        return (double) top / bottom;
    }

    // Tests this class using a configuration file, training file and test file.
    // See below.
    public static void main(String[] args) {
        ImageClassifier classifier = new ImageClassifier(args[0]);
        classifier.trainClassifier(args[1]);
        double testErrorRate = classifier.testClassifier(args[2]);
        System.out.println("test error rate = " + testErrorRate);
    }
}
