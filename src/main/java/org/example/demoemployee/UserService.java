package org.example.demoemployee;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final List<User> userList;
    private final List<String> usernameList;
    private final Map<String, String> emailByUsernameMap;

    public UserService() {
        this.userList = new ArrayList<>();
        this.usernameList = new ArrayList<>();
        this.emailByUsernameMap = new HashMap<>();
        loadUsersFromFile();
    }

    public void addUser(User user) {
        userList.add(user);
        usernameList.add(user.getUsername());
        emailByUsernameMap.put(user.getUsername(), user.getEmail());
        saveUsersToFile();
    }

    public void removeUser(String username) {
        int index = usernameList.indexOf(username);
        if (index != -1) {
            usernameList.remove(index);
            emailByUsernameMap.remove(username);
            userList.remove(index);
            saveUsersToFile();
        } else {
            System.out.println("User not found.");
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<String> getUsernameList() {
        return usernameList;
    }

    public String getEmailByUsername(String username) {
        return emailByUsernameMap.get(username);
    }

    private void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
            for (User user : userList) {
                writer.println(user.getUsername() + "," + user.getEmail() + "," + user.getPersonalId() + "," + user.getAge() + "," + user.getGender());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromFile() {
        try {
            File file = new File("users.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) { // Make sure all fields are present
                        String username = parts[0];
                        String email = parts[1];
                        String id = parts[2];
                        String age = parts[3];
                        String gender = parts[4];
                        User user = new User.Builder()
                                .username(username)
                                .email(email)
                                .id(id)
                                .age(age)
                                .gender(gender)
                                .build();
                        userList.add(user);
                        usernameList.add(username);
                        emailByUsernameMap.put(username, email);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
