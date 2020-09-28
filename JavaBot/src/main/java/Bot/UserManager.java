package Bot;

import Bot.Models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/// Написать штуку которая сохраняет состояние
public class UserManager {
    private final String pathToDB = "JavaBot/src/main/resources/users.csv";
    private final String header = "Id,Name,Location,Subscribed";
    private List<User> users;

    public UserManager() {
        try {
            users = getUsers();
//            System.out.println(users);
        }
        catch (Exception e) {
            System.out.println(System.getProperty("user.dir"));
            e.printStackTrace();
        }
    }

    private List<User> getUsers() throws IOException {
        List<User> users = new ArrayList<User>();
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToDB));
        String row = csvReader.readLine();
        // Типа пропускем первую строку
        row = csvReader.readLine();
        while (row != null) {
            String[] data = row.split(",");
            users.add(new User(data));
            row = csvReader.readLine();
        }
        csvReader.close();

        return users;
    }

    public void addUser(User user) {
        users.add(user);
        addUserToCSV(user);
    }

    private void addUserToCSV(User user) {
        try{
            FileWriter csvWriter = new FileWriter(pathToDB, true);
            csvWriter.write(user.toString() + '\n');
            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void deleteUser(int userId) throws IOException {
//        FileWriter csvWriter = new FileWriter(pathToDB);
//    }

    public User getUser(int userId) {
        for (User user: users) {
            if (user.userId == userId) {
                return user;
            }
        }

        return null;
    }

    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userId == user.userId)
                users.set(i, user);
        }

        updateUsersToCSV();
    }

    private void updateUsersToCSV() {
        try{
            FileWriter csvWriter = new FileWriter(pathToDB);
            csvWriter.write(header + '\n');
            for (User user: users) {
                csvWriter.write(user.toString() + '\n');
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

