public class EchoFilter {
    // This program takes three command-line inputs
    // One input takes filename another takes the delay
    // The last takes the factor of the increase or decrease in the volume of the echo
    public static void main(String[] args) {

        String fileName = args[0];
        int delay = Integer.parseInt(args[1]);
        double factor = Double.parseDouble(args[2]);
        double[] audio = StdAudio.read(fileName);
        double[] newAudio = new double[audio.length + delay];

        // This for-loop goes through the newAudio array
        for (int i = 0; i < newAudio.length; i++) {

            // sees if "i" is longer than the length of the original audio array
            if (i < audio.length) {
                // checks if audio[i] before the delay is applicable
                // newAudio is = to the audio array at that "i" position
                if (i < delay) {

                    newAudio[i] = audio[i];

                }
                // Uses superposition formula for echo filter
                else {

                    newAudio[i] = audio[i] + (factor * audio[i - delay]);

                }
            }
            // once "i" is larger than the length of the audio array
            else {

                if (i >= delay) {

                    newAudio[i] = factor * audio[i - delay];

                }
                // checks if audio[i] is empty, if empty newAudio[i] = 0
                else {

                    newAudio[i] = 0.0;

                }
            }
        }

        // plays newAudio
        StdAudio.play(newAudio);

    }
}
