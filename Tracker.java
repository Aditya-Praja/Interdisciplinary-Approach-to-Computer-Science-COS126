public class Tracker {

    // instance variable for the trackers name
    private String namer;
    // instance variable for trackers latitude
    private double latitude;
    // instance variable for trackers longitude
    private double longitude;
    // instance variable for trackers lost mode status
    private boolean lostmode;

    // Creates a new Tracker with the given name and initial location.
    // By default, a new Tracker's lost mode is disabled.
    public Tracker(String name, double initialLatitude, double initialLongitude) {
        namer = name;
        latitude = initialLatitude;
        longitude = initialLongitude;
        lostmode = false;
    }

    // Is this tracker in lost mode?
    public boolean isInLostMode() {
        return lostmode;
    }

    // Enables lost mode on this tracker.
    public void enableLostMode() {
        lostmode = true;
    }

    // Disables lost mode on this tracker.
    public void disableLostMode() {
        lostmode = false;
    }

    // Moves this tracker to the new location.
    public void move(double newLatitude, double newLongitude) {
        latitude = newLatitude;
        longitude = newLongitude;
    }

    // Returns the great circle distance between the two trackers.
    public double distanceTo(Tracker other) {
        // First part of the Haverson equation
        double first = Math.pow(Math.sin(Math.toRadians(
                ((other.latitude) - latitude) / 2)), 2);
        // Second part of the Haverson equation
        double second = Math.cos(Math.toRadians(latitude)) *
                Math.cos(Math.toRadians(other.latitude));
        // Third part of the Haverson equation
        double third = Math.pow(Math.sin(Math.toRadians(
                ((other.longitude) - longitude) / 2)), 2);
        // Haverson equation
        double distance = (2 * 6371.0 * Math.asin(Math.sqrt(first + second *
                third)));
        return distance;
    }

    // Returns a string representation of this tracker.
    public String toString() {
        return namer + " (" + latitude + ", " + longitude + ")";
    }

    // Unit tests the Tracker data type.
    public static void main(String[] args) {
        String name = args[0];
        double lat = Double.parseDouble(args[1]);
        double lon = Double.parseDouble(args[2]);
        // creates a Tracker object
        Tracker track1 = new Tracker(name, lat, lon);

        StdOut.println("This new trackers (track1) lost mode is: "
                               + track1.isInLostMode());
        track1.enableLostMode();
        StdOut.println("Now track 1 is " + track1.isInLostMode());
        track1.disableLostMode();
        StdOut.println("Now track 1 is " + track1.isInLostMode());
        // Creates a new tracker object with swaped lon and lat
        Tracker track2 = new Tracker(name, lon, lat);
        track2.move(lon, lat);
        StdOut.println("The distance between track1 and its "
                               + "inverse lat" + " and lon values is: "
                               + track1.distanceTo(track2));
    }

}
