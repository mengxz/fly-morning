package com.bluesky.tech.reflect;

public class User extends Human implements UserInterface{
    private String name;
    private int age;
    public User(){}
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void test(){
        System.out.println(name+"的年龄是："+age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
