
package com.se;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    UserDataManager userDataManager;
    NavigationManager navigationManager;
    RestarauntManager manager = new RestarauntManager();

    public static void main(String[] args) {
        App a = new App();
        a.startApp();
    }

    public void startApp(){
        userDataManager = new UserDataManager();
        navigationManager = new NavigationManager(userDataManager, manager);
        userDataManager.loadAllUsers();
        navigationManager.start();
    }
}

