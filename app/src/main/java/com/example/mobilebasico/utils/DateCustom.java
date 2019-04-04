package com.example.mobilebasico.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCustom {

    public static String dataAtual() {

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;

    }

    public static Date ConvertToDate(String dataValue) {

        Date dateFormated = null;
        try {
            dateFormated = new SimpleDateFormat("dd/MM/yyyy").parse(dataValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateFormated;

    }
}
