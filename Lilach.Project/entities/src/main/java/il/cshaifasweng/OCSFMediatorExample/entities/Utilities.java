package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.Date;

public class Utilities {
    public boolean check_Valid_String(String text){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) < 'a' || (text.charAt(i) > 'z' && text.charAt(i) < 'A') ||text.charAt(i) > 'Z'){
                return false;
            }
        }
        return true;
    }

    public boolean checkValidDate(Date from, Date to){
        if(from.after(to)){ // momken to.after(from)
            return false;
        }
        return true;
    }

    public boolean check_Validate_Price(String price){
        if(price.charAt(0) == '-'){
            return false;
        }
        for(int i = 0; i < price.length(); i++){
            if(price.charAt(i) > '0' || price.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }

}
