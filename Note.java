public class Note {
    // private instance variable representing the midi value
    private int midi;

    // private instance variable representing the name of the note
    private String name;

    // constant value for num of notes in an octave.
    int NOTES_IN_AN_OCTAVE = 12;

    // Creates a new Note corresponding to the MIDI number and instrument.
    public Note(int midiNumber, String instrumentName) {
        midi = midiNumber;
        name = instrumentName;
    }

    // Returns the MIDI number.
    public int midi() {
        return midi;
    }

    // Returns the frequency of this note.
    public double frequency() {
        return 440.0 * Math.pow(2, (midi - 69.0) / NOTES_IN_AN_OCTAVE);
    }

    // Plays this note to standard audio using an associated WAV file.
    public void play() {
        StdAudio.play(name + "/" + name + midi + ".wav");
    }

    // Returns the name of this note (such as C or A#).
    public String name() {
        // finds the octave
        int octave = (midi / NOTES_IN_AN_OCTAVE);

        // finds the position 0-12 within the octaves midi values
        int position = midi - (octave * NOTES_IN_AN_OCTAVE);
        // each note corresponding to a position within an octave
        String[] notes = {
                "C", "C#", "D", "D#", "E", "F",
                "F#", "G", "G#", "A", "A#", "B"
        };

        // finds the note given the position value
        String nameFinal = notes[position];

        return nameFinal;

    }

    // Returns the octave of this note.
    public int octave() {
        // each set of octaves has 12 midi values, and starts at -1.
        int row = (midi / NOTES_IN_AN_OCTAVE) - 1;

        return row;
    }

    // Returns a new Note that is transposed delta halftones.
    public Note transpose(int delta) {
        // creates a new midi value transposed delta half-steps
        int newNote = midi + delta;

        // creates a new note value given the transposed Midi
        Note x = new Note(newNote, name);

        return x;
    }

    // Returns a string representation of this note.
    public String toString() {
        return midi + " " + name() + octave() + " (" + name + ")";
    }

    // main function that runs all of the established methods
    public static void main(String[] args) {
        // sets the command line arguments to an int and a string respectively
        int midi = Integer.parseInt(args[0]);
        String name = args[1];

        // establish a note with provided argument values
        Note first = new Note(midi, name);

        // transposes the note
        Note second = first.transpose(10);

        // runs every created method for the note and prints them out
        StdOut.println(first.toString());
        StdOut.println("Octave: " + first.octave());
        StdOut.println("Midi Value: " + first.midi());
        StdOut.println("Frequency: " + first.frequency());
        StdOut.println("Name: " + first.name());
        StdOut.println("Transposed by Delta 10: " + second);
        first.play();
    }
}
