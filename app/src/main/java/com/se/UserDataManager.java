package com.se;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Loads and saves user data from/in CSV file */

public class UserDataManager {

    final Map<String, User> users = new HashMap<>();
    private final String fileName = "userdata.csv";

    public void saveUser(User user) {
        users.put(user.email, user);
        writeUserDataToDisk(users);
    }

    public Map<String, User> loadAllUsers() {
        users.clear();
        users.putAll(readUserDataFromDisk());
        return users;

    }

    public int authenticateUser(String email, String name, String number) {
        if (!checkUser(email)) return -1; // error user doesn't exist
        if (name.equals(users.get(email).name))return 0;

        return 1;
    }


    public boolean checkUser(String email) {
        return users.containsKey(email);
    }

    
    private void writeUserDataToDisk(Map<String, User> users) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry<String, User> entry : users.entrySet()) {
                User user = entry.getValue();
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    
    private Map<String, User> readUserDataFromDisk() {

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Map<String, User> users = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String email = data[0];
                String name = data[1];
                String number = data[2];

                users.put(email, new User(email,name,number));
            }
        } 
        
          catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
          catch (IOException e) {
            e.printStackTrace();
        } 
        
          catch (ArrayIndexOutOfBoundsException e) {
        	System.out.println(e.toString());
          }
        
          finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;

    }

}
