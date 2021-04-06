package com.example.project_trip;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cutter {

    String apiCutter(String imp, String cut){
        Transfer t = new Transfer();

        String str = imp;
        String val ="";


        String start = cut+">";
        String end = "</"+cut;
        Pattern pattern = Pattern.compile(start+"(.*?)"+end);


        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {  // 일치하는 게 있다면
            System.out.println("ok");
            val += matcher.group(1)+"\n\n";
            if(matcher.group(1) ==  null)
                break;
        }

        val = t.trans(val);

        return val;
    }

}
