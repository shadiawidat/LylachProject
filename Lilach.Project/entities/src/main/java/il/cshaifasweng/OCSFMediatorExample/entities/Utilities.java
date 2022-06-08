package il.cshaifasweng.OCSFMediatorExample.entities;

import java.time.LocalDateTime;
import java.util.Date;

public class Utilities {

    public static boolean check_Validate_String(String text){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) < 'A' || (text.charAt(i) > 'Z' && text.charAt(i) < 'a') ||text.charAt(i) > 'z'){
                return false;
            }
        }
        return true;
    }
    public static boolean check_Validate_name(String text){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i)==' ')
                continue;
            if(text.charAt(i) < 'A' || (text.charAt(i) > 'Z' && text.charAt(i) < 'a') ||text.charAt(i) > 'z'){
                return false;
            }
        }
        return true;
    }
    public static boolean check_Validate_Address(String text){
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) <= '9' || (text.charAt(i) >= '0'))
                continue;
            if(text.charAt(i)==' ')
                continue;
            if(text.charAt(i) < 'A' || (text.charAt(i) > 'Z' && text.charAt(i) < 'a') ||text.charAt(i) > 'z'){
                return false;
            }
        }
        return true;
    }
    public static boolean checkValidDate(Date Then, Date Now){
        if(Now.after(Then)){ // momken to.after(from)
            return true;
        }
        return false;
    }
    public static boolean checkValidDatebeforequalnow(Date Then, Date Now){
        if(Now.after(Then)){ // momken to.after(from)
            return true;
        }else if(Now.equals(Then))
            return true;
        return false;
    }
    public static boolean checkAfterValidDate(Date Now, Date Then){
        if(Then.after(Now)||Then.equals(Now)){ // momken to.after(from)
            return true;
        }
        return false;
    }
    public static boolean check_Validate_Price(String price){
        boolean flag = false;
        if(price.length()==0){
            return false;
        }
        if(price.charAt(0) == '-'||price.charAt(0) == '.'){
            return false;
        }
        for(int i = 0; i < price.length(); i++){
            if(price.charAt(i) == '.') {
                if(flag)
                    return false;
                flag = true;
            }
            if(price.charAt(i) < '0' || price.charAt(i) > '9'){
                if(price.charAt(i) != '.')
                    return false;
            }
        }
        if(price.charAt(price.length()-1)=='.'){
            return false;
        }
        return true;
    }
    public static boolean check_Validate_Discount(String discount){
        boolean flag = false;

        if(discount.length()==0){
            return false;
        }
        if(discount.charAt(0) == '-'||discount.charAt(0) == '.'){
            return false;
        }
        for(int i = 0; i < discount.length(); i++){
            if(discount.charAt(i) == '.') {
                if(flag)
                    return false;
                flag = true;
            }
            if(discount.charAt(i) < '0' || discount.charAt(i) > '9'){
                if(discount.charAt(i) != '.')
                    return false;
            }
        }
        if(discount.charAt(discount.length()-1)=='.'){
            return false;
        }
        if(Double.parseDouble(discount)>100)
            return false;
        return true;
    }
    public static boolean check_Validate_Phone(String Phone){
        if(Phone.length()!=10||Phone.length()==0)
            return false;
        for(int i = 0; i < Phone.length(); i++){
            if(Phone.charAt(i) < '0' || Phone.charAt(i) > '9'){
                return false;
            }
        }
        if(Phone.charAt(0)!='0'||Phone.charAt(1)!='5')
            return false;
        return true;
    }

    public static boolean check_Validate_ID(String ID){
    if(ID.length()>10||ID.length()<9)
        return false;
        for(int i = 0; i < ID.length(); i++){
            if(ID.charAt(i) < '0' || ID.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }
    public static boolean check_Validate_Card(String Card){
        if(Card.length()!=16||Card.length()<1)
            return false;
        for(int i = 0; i < Card.length(); i++){
            if(Card.charAt(i) < '0' || Card.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }

    public static boolean check_Validate_Pass(String Password){
        if(Password.length()>10||Password.length()<1)
            return false;
        for(int i = 0; i < Password.length(); i++){
            if(Password.charAt(i) == ' '){
                return false;
            }
        }
        return true;
    }

    public static boolean check_Validate_Username(String User){
        if(User.length()>10||User.length()<1)
            return false;

        for(int i = 0; i < User.length(); i++){
            if(User.charAt(i) == ' ')
                return false;
            if(User.charAt(i)<'0'||User.charAt(i)>'z')
                return false;
            if(User.charAt(i)>'9'&& User.charAt(i)<'A')
                return false;
            if(User.charAt(i)>='A'&& User.charAt(i)<='Z')
                return false;
            if(User.charAt(i)>'Z'&& User.charAt(i)<'a')
                return false;
        }
        if(User.charAt(0)<='9'&&User.charAt(0)>='0')
            return false;
        return true;
    }

    public static boolean check_Validate_Amount(String amount){
        if(amount.equals("") || amount.equals(" ")){
            return false;
        }
        for(int i = 0; i < amount.length(); i++){
            if(amount.charAt(i) < '0' || amount.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }



}
