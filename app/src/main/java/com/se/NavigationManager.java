package com.se;

import com.se.screens.*;

import java.io.IOException;

public class NavigationManager {

    UserDataManager userDataManager;

    FirstScreen firstScreen;
    LoginScreen loginScreen;
    SignupScreen signupScreen;
    HomeScreen homeScreen;
    BookingScreen bookingScreen;
    ViewBookingsScreen viewBookingsScreen;
    CancelBookingScreen cancelBookingScreen;
    ChangeBookingScreen changeBookingScreen;
    
    Language lang;

    public NavigationManager(UserDataManager userDataManager, RestarauntManager manager) {
        this.userDataManager = userDataManager;

        firstScreen = new FirstScreen();
        homeScreen = new HomeScreen();
        loginScreen = new LoginScreen(userDataManager);
        signupScreen = new SignupScreen(userDataManager);
        bookingScreen = new BookingScreen(manager);
        viewBookingsScreen = new ViewBookingsScreen(manager);
        cancelBookingScreen = new CancelBookingScreen(manager);
        changeBookingScreen = new ChangeBookingScreen(manager);

    }

    public static void clrscr() {
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                Runtime.getRuntime().exec("clear");
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    public void start() {
        clrscr();
        String result = firstScreen.showQuestions();
        if (result.equalsIgnoreCase("y") || result.equalsIgnoreCase("yes") || result.equalsIgnoreCase("ja") || result.equalsIgnoreCase("j")) {
            result = loginScreen.showQuestions();
        } else {
            result = signupScreen.showQuestions();
        }
        if (!result.equals("okay")) {
            start();
        }
        clrscr();
        loop:
        while (true) {
            result = homeScreen.showQuestions();
            clrscr();
            switch (result) {
                case "1":
                    bookingScreen.showQuestions();
                    break;
                case "2":
                    viewBookingsScreen.showQuestions();
                    break;
                case "3":
                    cancelBookingScreen.showQuestions();
                    break;
                case "4":
                    changeBookingScreen.showQuestions();
                    break;
                case "5":
                	System.out.println(lang.option.equals("1") ? "Logout!":"Ausloggen" );
                	start();
                    break loop;
                case "6":
                    System.out.println(lang.option.equals("1") ? "Bye!":"Wiedersehen");
                    break loop;
                default:
                    System.out.println(lang.option.equals("1") ? "Invalid option" : "Ung?ltige Option");
            }
            clrscr();
        }
    }


}
