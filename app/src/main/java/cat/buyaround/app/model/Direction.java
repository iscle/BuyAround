package cat.buyaround.app.model;

public class Direction {
    private String address;
    private String city;
    private String province;
    private int postalCode;
    private double latitude;
    private double longitude;

    public Direction(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
