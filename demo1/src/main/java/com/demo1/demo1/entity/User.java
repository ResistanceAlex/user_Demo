package com.demo1.demo1.entity;

public class User {

    //id
    private int id;

    //姓名
    public String name;

    //性别
    public String gender;

    //年龄
    public int age;

    //其他
    public String other;

    public User() { }

    public User(String name, String gender, int age, String other) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", other='" + other + '\'' +
                '}';
    }
}
