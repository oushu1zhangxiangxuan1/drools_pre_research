package com.sample;

public class Message{
    String id;
    String name;

    public Message(){}

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Message(String id, String name){
        this.id = id;
        this.name = name;
    }
}