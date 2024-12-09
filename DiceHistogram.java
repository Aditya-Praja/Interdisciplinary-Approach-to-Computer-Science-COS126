public class DiceHistogram {

    // Takes two arguments one for the # of dice and one for # of rolls
    // Prints a histogram with each roll where "*" represents a roll
    public static void main(String[] args) {

        int numDice = Integer.parseInt(args[0]);
        int numRolls = Integer.parseInt(args[1]);
        int SIDES = 6;
        int[] histogram = new int[numDice * SIDES + 1];

        // runs two for-loops
        // first for loop iterates through all the rolls
        // second for loop iterates through all the dice
        for (int i = 0; i < numRolls; i++) {

            int x = 0;

            for (int j = 0; j < numDice; j++) {

                x += (int) (Math.random() * SIDES + 1);

            }

            // adds 1 each time a number is rolled to that numbers indices
            histogram[x]++;

        }

        // prints the histogram
        for (int i = numDice; i <= (numDice * SIDES); i++) {

            StdOut.printf("%3d:", i);

            // prints the asterisks each time a number was rolled
            for (int j = 0; j < histogram[i]; j++) {

                StdOut.print("*");

            }

            StdOut.println();

        }

    }
}
