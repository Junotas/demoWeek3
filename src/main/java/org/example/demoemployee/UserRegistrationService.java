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
            System.out.println("Welcome to User Registration!(Admin Edition)");

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
        }
    }

    public void registerUsers(Scanner scanner) {
        boolean addMoreUsers = true;
        while (addMoreUsers) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            if (userService.getUsernameList().contains(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                continue;
            }

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            if (userService.getEmailByUsername(email) != null) {
                System.out.println("Email already exists. Please choose a different email.");
                continue;
            }

            System.out.print("Enter your ID (Personnummer): ");
            String id = scanner.nextLine();

            if (userService.getUserList().stream().anyMatch(user -> user.getPersonalId().equals(id))) {
                System.out.println("ID already exists. Please choose a different ID.");
                continue;
            }

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

        if (userService.getUserList().isEmpty()) {
            System.out.println("There are no users to remove!");
            return;
        }

        System.out.print("Enter the username of the user to remove: ");
        String usernameToRemove = scanner.nextLine();
        userService.removeUser(usernameToRemove);
        System.out.println("User removed successfully.");
    }

    private void viewUsers() {

        if(userService.getUserList().isEmpty()) {
            System.out.println("There are no users to view!");
            return;
        }

        System.out.println("All Users:");
        for (User u : userService.getUserList()) {
            System.out.println("Username: " + u.getUsername());
            System.out.println("Email: " + u.getEmail());
            System.out.println("ID: " + u.getPersonalId());
            System.out.println("Age: " + u.getAge());
            System.out.println("Gender: " + u.getGender());
            System.out.println("----------------------");
        }
    }
}
