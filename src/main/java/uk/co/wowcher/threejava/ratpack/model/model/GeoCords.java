package uk.co.wowcher.threejava.ratpack.model.model;

public class GeoCords {

    public final double lat;
    public final double lon;
    public final int distance;

    public GeoCords(double lat, double lon, int distance) {
        this.lat = lat;
        this.lon = lon;
        this.distance = distance;
    }
}
