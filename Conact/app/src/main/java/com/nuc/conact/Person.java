package com.nuc.conact;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String tel;
    private int imgId;

    public Person() {
    }

    public Person(String name, String tel, int imgId) {
        this.name = name;
        this.tel = tel;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
