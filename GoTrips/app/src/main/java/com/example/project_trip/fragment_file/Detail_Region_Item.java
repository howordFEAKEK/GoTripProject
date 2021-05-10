package com.example.project_trip.fragment_file;

public class Detail_Region_Item {

    // 세부지역 리스트 아이템입니다.

    private String Name;

    public Detail_Region_Item() {

    }

    public Detail_Region_Item(String name){
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
