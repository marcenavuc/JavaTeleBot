package Bot.Models;

import java.io.Serializable;

public class User implements Serializable {
    public int userId;
    public long chatId;
    public String name;
    public String location;
    public Boolean isSubscribed;
    public States state;
    public float lat;
    public float lon;

    public User(int userId, long chatId, String name, String location, Boolean isSubscribed) {
        this.userId = userId;
        this.chatId = chatId;
        this.name = name;
        this.location = location;
        this.isSubscribed = isSubscribed;
        this.state = States.DEFAULT;
        this.lat = Float.MAX_VALUE;
        this.lon = Float.MAX_VALUE;
    }

    public User(String[] dataFromCsv) {
        this.userId = Integer.parseInt(dataFromCsv[0]);
        this.chatId = Long.parseLong(dataFromCsv[1]);
        this.name = dataFromCsv[2];
        this.location = !dataFromCsv[3].equals("null") ? dataFromCsv[3] : null;
        this.isSubscribed = Boolean.parseBoolean(dataFromCsv[4]);
        this.state = States.values()[Integer.parseInt(dataFromCsv[5])];
        this.lat = Float.parseFloat(dataFromCsv[6]);
        this.lon = Float.parseFloat(dataFromCsv[7]);
    }

    @Override
    public String toString() {
        return userId
                + "," + chatId
                + "," + name
                + "," + location
                + "," + isSubscribed
                + "," + state.ordinal()
                + "," + lat
                + "," + lon;
    }

    public boolean isLatLonChanged() {
        return lat != Float.MAX_VALUE && lon != Float.MAX_VALUE;
    }

    public void setLatLonToDefault() {
        lat = Float.MAX_VALUE;
        lon = Float.MAX_VALUE;
    }
}

