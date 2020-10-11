package Bot.Models;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String location;
    public Boolean isSubscribed;
    public int userId;
    public int state;

    public User(int userId, String name, String location, Boolean isSubscribed) {
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.isSubscribed = isSubscribed;
        this.state = 0;
    }

    public User(String[] dataFromCsv) {
        this.userId = Integer.parseInt(dataFromCsv[0]);
        this.name = dataFromCsv[1];
        this.location = !dataFromCsv[2].equals("null") ? dataFromCsv[2] : null;
        this.isSubscribed = Boolean.parseBoolean(dataFromCsv[3]);
        this.state = Integer.parseInt(dataFromCsv[4]);
    }

    @Override
    public String toString() {
        return Integer.toString(userId)
                + "," + name
                + "," + location
                + "," + Boolean.toString(isSubscribed)
                + "," + Integer.toString(state);
    }
}
