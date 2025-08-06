package org.example.view;

public class Menu {
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";
    public static void showMainMenu() {
        System.out.println(GREEN + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(GREEN + "║         LIBRARY MANAGER MENU         ║" + RESET);
        System.out.println(GREEN + "╠══════════════════════════════════════╣" + RESET);
        System.out.println(GREEN + "║     1) Add a Book                    ║" + RESET);
        System.out.println(GREEN + "║     2) Search Book by Title          ║" + RESET);
        System.out.println(GREEN + "║     3) Search Book by Author         ║" + RESET);
        System.out.println(GREEN + "║     5) List All Books & Authors      ║" + RESET);
        System.out.println(GREEN + "║     6) Lend Book to Student          ║" + RESET);
        System.out.println(GREEN + "║     7) List Books by USN             ║" + RESET);
        System.out.println(GREEN + "║     8) Exit                          ║" + RESET);
        System.out.println(GREEN + "╚══════════════════════════════════════╝" + RESET);
        System.out.print(GREEN + ">> Please enter your choice: " + RESET);
    }
}