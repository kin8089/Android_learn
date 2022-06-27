package com.cookandroid.conn;

public class PostItem { //필드 선언
    private String versions_weather;
    private String versions_img;
    private String versions_content;
    private String versions_date;
    private String versions_title;
    private String versions_todayme;
    private String versions_tomorrowme;
    private int versions_id;

    //여기서부턴 생성자
    public PostItem(){}
    //매개변수 개수대로
    public PostItem(
            String diary_title,String diary_content) {
        this.versions_content = diary_content;
        this.versions_title = diary_title;
    }

    //getter와 setter 설정
    public int getversions_id() {
        return versions_id;
    }

    public int setversions_id(int diary_id) {
        this.versions_id = diary_id;
        return diary_id;
    }

    public String getWeather() {
        return versions_weather;
    }

    public String setWeather(String diary_weather) {
        this.versions_weather = diary_weather;
        return diary_weather;
    }

    public String getImage() {
        return versions_img;
    }

    public String setImage(String diary_img) {
        this.versions_img = diary_img;
        return diary_img;
    }

    public String getTitle() {
        return versions_title;
    }

    public String setTitle(String diary_title) {
        this.versions_title = diary_title;
        return diary_title;
    }
    public String getContent() {
        return versions_content;
    }

    public String setContent(String diary_content) {

        this.versions_content = diary_content;
        return diary_content;
    }

    public String getDate() {
        return versions_date;
    }

    public String setDate(String diary_date) {
        this.versions_date = diary_date;
        return diary_date;
    }
    public String getTodayme() {
        return versions_todayme;
    }

    public String setTodayme(String diary_todayme) {
        this.versions_todayme = diary_todayme;
        return diary_todayme;
    }

    public String getTomorrowme() {
        return versions_tomorrowme;
    }

    public String setTomorrowme(String diary_tomorrowme) {
        this.versions_tomorrowme = diary_tomorrowme;
        return diary_tomorrowme;
    }

}