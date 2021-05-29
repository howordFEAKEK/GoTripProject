package com.example.project_trip.fragment_file;

public class Main_item {

    public String review_index_writername = null; // 작성자
    public String review_index_date = null; // 작성일자
    public String review_index_title = null; // 리뷰 제목

    private String List;

    public Main_item(){

    }

    public Main_item(String list){
        List = list;
    }

    public String getList() {

        return List;
    }

    public void setList(String list) {

        List = list;
    }

}

