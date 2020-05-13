package com.digitalacademy.domain;

public class Person {
    private String firstName;
    private String lastName;
    private String nickname;
    private Integer age;

//    Auto Generate Getter && Setter or other : command + N
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

//    Overloading in Java
    //    Overloaded Person(). This Person takes four parameters
    public Person(String firstName, String lastName, String nickname, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.age = age;
    }

    //    Overloaded Person(). This Person takes zero parameters
    public Person(){

    }
}
