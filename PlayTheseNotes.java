public class PlayTheseNotes {

    // Reads a sequence of notes from the given file and returns a
    // Note array based on the given instrument
    // private int notes;

    public static Note[] read(String filename, String instrument) {

        // creates a new input object to read file
        In file = new In(filename);
        // reads the number of notes in the file
        int size = file.readInt();
        Note[] scale = new Note[size];
        for (int i = 0; i < size; i++) {
            scale[i] = new Note(file.readInt(), instrument);
            // reads the duration in the file but is unused
            // only used to get to the next int
            double duration = file.readDouble();
        }

        return scale;
    }

    // Takes three command-line arguments (a file name, an instrument name,
    // a delta value for transpose), reads a sequence of notes from the file
    // and plays the musical notes, according to the given instrument on
    // standard audio
    public static void main(String[] args) {
        String filename = args[0];
        String instrument = args[1];
        int delta = Integer.parseInt(args[2]);
        // creates an array with the notes from the files in it
        Note[] play = read(filename, instrument);
        // finds the number of names in the array
        int notes = play.length;
        for (int i = 0; i < notes; i++) {
            // creates a transposed array
            Note real = play[i].transpose(delta);
            // plays the notes
            real.play();
            // prints the notes
            StdOut.print(real.name() +
                                 real.octave() + " ");
        }
    }
}
