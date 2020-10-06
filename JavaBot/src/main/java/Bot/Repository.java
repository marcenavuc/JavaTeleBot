package Bot;

import Bot.Models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Repository {
    private final String pathToDB = "JavaBot/src/main/resources/users.csv";
    private final String header = "Id,Name,Location,Subscribed";
    private HashMap<Integer, User> users;

    public Repository() {
        try {
            users = getUsers();
        }
        catch (Exception e) {
            System.out.println(System.getProperty("user.dir"));
            e.printStackTrace();
        }
    }

    private HashMap<Integer, User> getUsers() throws IOException {
        HashMap<Integer, User> users = new HashMap<Integer, User>();
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToDB));
        String row = csvReader.readLine();
        row = csvReader.readLine();
        while (row != null) {
            String[] arguments = row.split(",");
            User user = new User(arguments);
            users.put(user.userId, user);
            row = csvReader.readLine();
        }
        csvReader.close();

        return users;
    }

    public void addUser(User user) throws IOException {
        addUserToCSV(user);
        users.put(user.userId, user);
    }

    private void addUserToCSV(User user) throws IOException {
        FileWriter csvWriter = new FileWriter(pathToDB, true);
        csvWriter.write(user.toString() + '\n');
        csvWriter.flush();
        csvWriter.close();
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public void updateUser(User user) throws IOException {
        User currentUser = users.get(user.userId);
        if (currentUser != null) {
            updateUsersToCSV();
            users.put(user.userId, user);
        }
    }

    private void updateUsersToCSV() throws IOException {
        FileWriter csvWriter = new FileWriter(pathToDB);
        csvWriter.write(header + '\n');
        for (User user: users.values()) {
            csvWriter.write(user.toString() + '\n');
        }

        csvWriter.flush();
        csvWriter.close();
    }
}

