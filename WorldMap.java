public class WorldMap {

    // takes seven standard inputs (usually from redirection from a file)
    public static void main(String[] args) {

        // width and height inputs are taken
        int width = StdIn.readInt();
        int height = StdIn.readInt();

        // canvas size, x, and y-scale are set depending on width and height values
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.enableDoubleBuffering();

        // will run until no more inputs are available
        while (!StdIn.isEmpty()) {

            // reads the name and the number of vertices
            String name = StdIn.readString();
            int vertices = StdIn.readInt();

            // creates arrays for the x-cords and y-cords
            double[] xcord = new double[vertices];
            double[] ycord = new double[vertices];

            // iterates through the arrays to set values through standard inputs
            for (int i = 0; i < vertices; i++) {

                xcord[i] = StdIn.readDouble();
                ycord[i] = StdIn.readDouble();

            }

            // draws the image
            StdDraw.polygon(xcord, ycord);

        }

        StdDraw.show();

    }
}
