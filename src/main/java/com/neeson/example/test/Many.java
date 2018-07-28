//package com.neeson.example.test;
//
//import org.apache.ibatis.annotations.One;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Many {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    private String name;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "one_id")
//    private One one;
//
//    public Integer getId() {
//        return id;
//    }
//
//
//    @ManyToMany(mappedBy="manys")
//    private List<Much> muchs;
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Much> getMuchs() {
//        return muchs;
//    }
//
//    public void setMuchs(List<Much> muchs) {
//        this.muchs = muchs;
//    }
//
//    public One getOne() {
//        return one;
//    }
//
//    public void setOne(One one) {
//        this.one = one;
//    }
//}