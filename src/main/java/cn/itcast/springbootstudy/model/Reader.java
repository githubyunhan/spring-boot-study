package cn.itcast.springbootstudy.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Reader implements Serializable {
    private static final long serialVersionUID = 6531119231312452181L;

    private String name;
    private int age;

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