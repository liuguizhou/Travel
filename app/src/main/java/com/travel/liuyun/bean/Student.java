package com.travel.liuyun.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liuguizhou on 2017/3/17.
 */
@Entity
public class Student {
    //主键 依次递增
    @Id(autoincrement = true)
    private Long id;
    //学生姓名
    private String name;
    //学生年龄
    private int age;
    //学生性别
    private String sex;
    //
    private String telephone;
    //学生住址
    private String familyAddress;
    @Generated(hash = 506066451)
    public Student(Long id, String name, int age, String sex, String telephone,
            String familyAddress) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.telephone = telephone;
        this.familyAddress = familyAddress;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getFamilyAddress() {
        return this.familyAddress;
    }
    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }


}
