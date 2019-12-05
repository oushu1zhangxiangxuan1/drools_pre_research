package com.sample;

public class Person {
    Integer age;
    String name;

    public Person(){}

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer id){
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Person(Integer age, String name){
        this.age = age;
        this.name = name;
    }
}