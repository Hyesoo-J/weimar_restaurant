#### How to run
Type `gradlew run` in terminal to run the app.  
If you get _'permission denied'_ error then run `chmod +x gradlew` in terminal once before the first command. 

---

## [ Restaurant table reservation service ] - Origin code

* Make a reservation
1. Create a user account - register your name / email / phone number to reserve a table → _No name input function_
2. Date - Year / Month / Day  
3. Reservation time for restaurant - 10 am to 10 pm → _Not implemented yet_
4. Number of reservable seats (number of people) - At least 1 person (enter a number greater than 1) → _Not implemented yet_

* Cancellation  
1. Reservation cancellation function - Users can log in to their account to cancel the reservation  

* Notice  
1. Notice if there are no seats available at the desired date and time - "Not Available" / "Search for another date and time"→ _Not implemented yet_
2. When reservation is complete - "Reservation has been completed for date/time/person"  
3. When canceling a reservation - "Cancellation is confirmed"  
