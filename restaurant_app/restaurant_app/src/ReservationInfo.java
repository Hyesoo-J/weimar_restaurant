import java.util.Scanner;
/**
 *
 * This class details the data part of each node.
 * Each node in the Reservation linked list shall
 * have this same set of data; which contains all
 * the details for each reservation.
 * 
 */
public class ReservationInfo
{
    String rName;
    String rEmail;
    String rDate;
    String rTime;

    public ReservationInfo(String rName, String rEmail, String rDate, String rTime)
    {
        this.rName = rName;
        this.rEmail = rEmail;
        this.rDate = rDate;
        this.rTime = rTime;
    }
    
    //Getters
    public String getrName(){ return rName;   }
    public String getrEmail(){ return rEmail; }
    public String getrDate(){ return rDate;   }
    public String getrTime(){ return rTime;   }
    
    //Setters
    public void setrName(String rName){ this.rName    = rName;   }
    public void setrEmail(String rEmail){ this.rName    = rEmail;   }
    public void setrDate(String rDate){ this.rDate    = rDate;   }
    public void setrTime(String rTime){ this.rTime    = rTime;   }    
}
