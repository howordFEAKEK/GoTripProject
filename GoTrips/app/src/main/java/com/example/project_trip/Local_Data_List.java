package com.example.project_trip;

import com.example.project_trip.fragment_file.Main_item;
import com.example.project_trip.fragment_file.Main_item_from_show_local;

import java.util.ArrayList;
import java.util.List;

public class Local_Data_List {
    public static String username = "TUS77T";

    public static String sett_sido ;
    public static String sett_gungo ;

    public static List<Local_title_item> local_data = new ArrayList<>(); // 어디에서 쓰는지 모르겠음

    //위치탭에서 관광지명, 관광지역, 시도, 군구 를 가지고 있기 (위치탭용 데이터 모음)
    public static List<Main_item_from_show_local> local_tour_data_list = new ArrayList<>();

    //메인탭에서 관광지명, 관광지역, 시도, 군구 를 가지고 있기 (메인탭용 데이터 모음)
    public static List<Main_item_from_show_local> main_tour_data_list = new ArrayList<>();

    //추천탭에서 관광지명, 관광지역, 시도, 군구 를 가지고 있기 (추천탭용 데이터 모음) ---> 나중에 작업하기
    public static List<Main_item_from_show_local> cusmaid_tour_data_list = new ArrayList<>();

    //메인 탭에서 주간,  작성자, 작성일자, 리뷰명 을 가지고 있다
    public static List<Main_item> main_review_week_list = new ArrayList<>();

    //메인 탭에서 월간,  작성자, 작성일자, 리뷰명 을 가지고 있다
    public static List<Main_item> main_review_month_list = new ArrayList<>();

}
