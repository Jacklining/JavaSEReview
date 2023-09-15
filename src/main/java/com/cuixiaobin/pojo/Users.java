package com.cuixiaobin.pojo;

import java.time.LocalDate;


public class Users {

    private Integer id;
    private String name;
    private Integer age;
    private LocalDate birthday;



    public static void eat(String s){
        System.out.println(s);
    }

    public void Ieat(String s){
        System.out.println(s);
    }


    public Users() {
    }

    public Users(Integer id, String name, Integer age, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        return "Users{id = " + id + ", name = " + name + ", age = " + age + ", birthday = " + birthday + "}";
    }
}
