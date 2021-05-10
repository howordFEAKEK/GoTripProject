package com.example.project_trip.Main_API_DB_GPSFile;

public class Transfer {
    String trans(String str) {

        str = str.replaceAll("&gt;", "> ")
                .replaceAll("&#xD;", " ")
                .replaceAll("&lt;", " <")
                .replaceAll("&lt;;", " <");


        return str;

    }
}
