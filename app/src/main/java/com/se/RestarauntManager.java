package com.se;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.se.screens.Screen;

public class RestarauntManager {

	int seats = 30;
	
	final Map<String, Booking> calendar = new HashMap<>();
	public List<Booking> bookingList = new ArrayList<Booking>();
	
    private final String fileName = "bookedDates.csv";
    
    public void saveBooking (Booking book) {
    	bookingList.add(book);
    	calendar.put(book.id, book);
        writeBookToDisk(calendar);
    }
    
    public void updateBooking (Booking book) {
       	calendar.put(book.id, book);
       	bookingList = new ArrayList<Booking>(calendar.values());
        writeBookToDisk(calendar);
    }

  


    public Map<String, Booking> loadAllBooked() {
    	calendar.clear();
    	calendar.putAll(readBookFromDisk()); 
    	bookingList = new ArrayList<Booking>(calendar.values());
        return calendar;

    }
	       
   
    private void writeBookToDisk(Map<String, Booking> calendar) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry<String, Booking> entry : calendar.entrySet()) {
                Booking book = entry.getValue();
                bw.write(book.toString());
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

    
    private Map<String, Booking> readBookFromDisk() {

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Map<String, Booking> calendar = new HashMap<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];
                String email = data[1];
                String time = data[2];
                String date = data[3];
                String noOfPeople = data[4];

                calendar.put(id, new Booking(id, email,time, date, noOfPeople));
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
        return calendar;
    }

	public boolean checkAvailibility(String noOfPeople) {

		int total = Integer.parseInt(noOfPeople);
	

		for (Booking b : bookingList) {

			total += Integer.parseInt(b.noOfPeople);

		}
		int seatsavailable = seats - total;

		return seatsavailable>=0;


	}
	
	
	public boolean checkTimeAvailibility(String date, String time) {
		
	
		for (Booking b : bookingList) {
			if (Objects.equals(date, b.date) && Objects.equals(time, b.time)) {				
				return false;
			}
		}
		return true;
	}

	
}
