public class SoundWaveVisualizer {

    // SoundWaveVisualizer takes two command-line inputs
    // one is the filename that is played
    // second is the number of groups each visualizer will represent
    public static void main(String[] args) {

        String fileName = args[0];
        int groups = Integer.parseInt(args[1]);

        // sets canvas size, x-scale, y-scale, and pen color
        StdDraw.setCanvasSize(1000, 100);
        StdDraw.setXscale(-1.0, groups);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);

        // makes given file into an array
        double[] wave = StdAudio.read(fileName);
        // makes an array of the max values from each group
        double[] maxNum = new double[groups];
        // finds the number of sound waves per group for the audio file
        int numWaves = (wave.length / groups);
        int counter = 0;

        // iterates through the number of groups
        for (int i = 0; i < groups; i++) {

            // creates a max value
            double max = 0;

            // iterates through all the waves in each group
            for (int j = 0; j < numWaves; j++) {

                // checks if new value in the group is larger than the previous values
                if (Math.abs(wave[counter]) > max) {

                    // sets the new max value
                    max = Math.abs(wave[counter]);

                }

                // adds one to counter
                counter++;
            }

            // adds the max value to the array of max values
            maxNum[i] = max;

        }

        int countertwo = 0;

        // iterates through the maxNum array to draw out the max values
        for (int i = 0; i < groups; i++) {

            StdDraw.line(i, maxNum[i], i, -maxNum[i]);

            // plays the corresponding audio with the given group
            for (int j = 0; j < numWaves; j++) {

                StdAudio.play(wave[countertwo]);
                countertwo++;
            }

        }

    }
}
