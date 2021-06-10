package com.example.project_trip;

public class Transfer {
    String trans(String str) {

        str = str.replaceAll("&gt;", "> ")
                .replaceAll("&#xD;", " ")
                .replaceAll("&lt;", " <")
                .replaceAll("&lt;;", " <")
                .replaceAll("&amp;", "&");;



        return str;

    }
}
