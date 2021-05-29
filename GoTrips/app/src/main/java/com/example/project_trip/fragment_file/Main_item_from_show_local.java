package com.example.project_trip.fragment_file;

public class Main_item_from_show_local {

    public String tour_title = null; // 관광지명
    public String tour_location = null; // 관광지 지역(00도 00시)

    private String local_title;

    public Main_item_from_show_local(){

    }

    public Main_item_from_show_local(String name){

        local_title = name;
    }




    public String getLocal_title() {
        return local_title;
    }

    public void setLocal_title(String local_title) {
        this.local_title = local_title;
    }
}

