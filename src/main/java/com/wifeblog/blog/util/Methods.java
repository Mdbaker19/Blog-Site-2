package com.wifeblog.blog.util;

public class Methods {

    public String timeFormat(String timeStamp){
        String timeString = timeStamp.substring(0, 16);
        String time = timeString.substring(11, 13);
        int number = Integer.parseInt(time);
        if(number == 12) {
            return (timeString.substring(0, 11) + (number) + timeString.substring(13) + " pm");
        }
        if(number == 0) {
            return (timeString.substring(0, 11) + "12" + timeString.substring(13, 16) + " am");
        }
        if(number > 12) {
            return (timeString.substring(0, 11) + (number % 12) + timeString.substring(13, 16) + " pm");
        }
        return (timeString.substring(0, 11) + (number % 12) + timeString.substring(13, 16) + " am");
    }

}
