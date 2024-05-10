package com.easy.tour.Tour_View.utils;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class ParserDateTimeUtils {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String format(String date) {
       if (date != null) {
           try {
               LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
               return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss"));
           } catch (Exception ex) {
               ex.printStackTrace();
           }
       }
        return null;
    }
}
