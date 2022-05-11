
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


    // Only for testing purpose. Delete this later on...
    private static void testUserDataManager() {
        UserDataManager manager = new UserDataManager();
        manager.loadAllUsers();
        for (User u : manager.users.values()) {
            System.out.println(u.toString());
        }
        manager.saveUser(new User("abc", "123"));
        manager.saveUser(new User("xyf", "123456"));
    }


    // Only for testing purpose. Delete this later on...
    private static void testQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is your name", Question.Format.TXT));
        questions.add(new Question("What is your age", Question.Format.NUM));
        questions.add(new Question("Do you like pizza?", Question.Format.YN));

        Scanner sc = new Scanner(System.in);

        for (Question q : questions) {
            System.out.println(q.text);

            while (true) {
                String ans = sc.nextLine();
                if (q.isValidAnswer(ans)) {
                    break;
                } else {
                    System.out.println("Invalid answer :(");
                }

            }

        }

        System.out.println("done!");
    }

}
