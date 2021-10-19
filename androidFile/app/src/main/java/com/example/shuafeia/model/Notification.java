package com.example.shuafeia.model;

public class Notification {


    String from="",message="";

    public Notification(){

    }

    public Notification(String from, String messaege) {
        this.from = from;
        this.message = messaege;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
