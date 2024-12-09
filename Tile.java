import java.awt.Color;

public class Tile {

    // Returns a cols-by-rows tiling of the specified image.
    public static Picture tile(Picture picture, int cols, int rows) {

        int w = picture.width();
        int h = picture.height();
        Picture tiles = new Picture(w * cols, h * rows);

        // iterates through the columns of tiles
        for (int c = 0; c < cols; c++) {
            // iterates through the rows of tiles
            for (int r = 0; r < rows; r++) {
                // iterates through the columns of the pixels of each tile
                for (int i = 0; i < w; i++) {
                    // iterates through the rows of the pixels of each tile
                    for (int j = 0; j < h; j++) {
                        Color rgb = picture.get(i, j);
                        // calculates the position of each pixel
                        tiles.set(i + w * c, j + r * h, rgb);
                    }
                }
            }
        }

        return tiles;
    }

    // Takes three command-line arguments (the name of the image, the number
    // of columns, and the number of rows), and displays a rectangular tiling
    // of the image with the specified number of columns and rows.
    public static void main(String[] args) {

        String name = args[0];
        int c = Integer.parseInt(args[1]);
        int r = Integer.parseInt(args[2]);

        Picture one = new Picture(name);
        Picture show = tile(one, c, r);

        show.show();
    }
}
