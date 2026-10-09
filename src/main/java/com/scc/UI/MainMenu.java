package com.scc.UI;
import com.scc.dao.UserDao;
import com.scc.model.User;
import java.util.Scanner;

public class MainMenu {
    private final Scanner sc = new Scanner(System.in);
    private final UserDao userDao = new UserDao();

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("| STUDENT RECORDS MANAGEMENT |");
            System.out.println("==============================");
            System.out.println("| 1.) LOGIN                  |");
            System.out.println("| 2.) REGISTER               |");
            System.out.println("| 3.) EXIT                   |");
            System.out.println("==============================");
            System.out.print("Choose an option: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter Username: ");
                    String loginUsername = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    User loggedInUser = userDao.loginUser(loginUsername, loginPassword);

                    if (loggedInUser != null) {
                        System.out.println("\nLOGIN SUCCESSFUL!");
                        System.out.println("Welcome, " + loggedInUser.Username() + "!");
                        System.out.println("Role: " + loggedInUser.Roles());
                        System.out.println("Status: " + loggedInUser.Status());
                    } else {
                        System.out.println("INVALID USERNAME OR PASSWORD!");
                    }
                    break;

                case "2":
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    System.out.println("Select role:");
                    System.out.println("1. TEACHER");
                    System.out.println("2. STUDENT");
                    System.out.print("Enter choice: ");

                    String roleChoice = sc.nextLine();
                    String role;

                    switch (roleChoice) {
                        case "1":
                            role = "TEACHER";
                            break;
                        case "2":
                            role = "STUDENT";
                            break;
                        default:
                            role = null;
                    }

                    if (role != null) {
                        userDao.registerUser(username, password, role);
                    } else {
                        System.out.println("INVALID ROLE SELECTION!");
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Exiting Student Records Management System...");
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1, 2, or 3.");
            }
        }
        sc.close();
    }
}