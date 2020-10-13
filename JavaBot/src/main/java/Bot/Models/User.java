package Bot.Models;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String location;
    public Boolean isSubscribed;
    public int userId;
    public int state;
    public float lat;
    public float lon;

    public User(int userId, String name, String location, Boolean isSubscribed) {
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.isSubscribed = isSubscribed;
        this.state = 0;
        this.lat = 0;
        this.lon = 0;
    }

    public User(String[] dataFromCsv) {
        this.userId = Integer.parseInt(dataFromCsv[0]);
        this.name = dataFromCsv[1];
        this.location = !dataFromCsv[2].equals("null") ? dataFromCsv[2] : null;
        this.isSubscribed = Boolean.parseBoolean(dataFromCsv[3]);
        this.state = Integer.parseInt(dataFromCsv[4]);
        this.lat = Float.parseFloat(dataFromCsv[5]);
        this.lon = Float.parseFloat(dataFromCsv[6]);
    }

    @Override
    public String toString() {
        return Integer.toString(userId)
                + "," + name
                + "," + location
                + "," + Boolean.toString(isSubscribed)
                + "," + Integer.toString(state)
                + "," + Float.toString(lat)
                + "," + Float.toString(lon);
    }
}
