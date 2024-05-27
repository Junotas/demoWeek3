package org.example.demoemployee;

import java.util.Scanner;

public class UserRegistrationService {

    private final UserService userService;

    public UserRegistrationService(UserService userService) {
        this.userService = userService;
    }

    public void performUserActions(Scanner scanner) {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Welcome to User Registration!");

            System.out.print("Do you want to add, delete, view users, or exit? (add/delete/view/exit): ");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "add":
                    registerUsers(scanner);
                    break;
                case "delete":
                    removeUser(scanner);
                    break;
                case "view":
                    viewUsers();
                    break;
                case "exit":
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid action. Please choose 'add', 'delete', 'view', or 'exit'.");
            }

            displayUserInformation();
        }
    }

    public void registerUsers(Scanner scanner) {
        boolean addMoreUsers = true;
        while (addMoreUsers) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter your age: ");
            String age = scanner.nextLine();

            System.out.print("Enter your gender: ");
            String gender = scanner.nextLine();

            User user = new User.Builder()
                    .username(username)
                    .email(email)
                    .id(id)
                    .age(age)
                    .gender(gender)
                    .build();

            userService.addUser(user);

            System.out.println("User registration successful!");

            System.out.print("Do you want to add another user? (yes/no): ");
            String response = scanner.nextLine();
            addMoreUsers = response.equalsIgnoreCase("yes");
        }
    }

    private void removeUser(Scanner scanner) {
        System.out.print("Enter the username of the user to remove: ");
        String usernameToRemove = scanner.nextLine();
        userService.removeUser(usernameToRemove);
        System.out.println("User removed successfully.");
    }

    private void viewUsers() {
        System.out.println("All Users:");
        for (User u : userService.getUserList()) {
            System.out.println("Username: " + u.getUsername());
            System.out.println("Email: " + u.getEmail());
            System.out.println("ID: " + u.getId());
            System.out.println("Age: " + u.getAge());
            System.out.println("Gender: " + u.getGender());
            System.out.println("----------------------");
        }
    }

    private void displayUserInformation() {
        System.out.println("Users added:");
        for (User u : userService.getUserList()) {
            System.out.println("Username: " + u.getUsername());
            System.out.println("Email: " + u.getEmail());
            System.out.println("ID: " + u.getId());
            System.out.println("Age: " + u.getAge());
            System.out.println("Gender: " + u.getGender());
            System.out.println("----------------------");
        }

        System.out.println("All entered usernames and emails:");
        for (String username : userService.getUsernameList()) {
            System.out.println("Username: " + username + ", Email: " + userService.getEmailByUsername(username));
        }
    }
}
