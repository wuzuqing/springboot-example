//package com.neeson.example.test;
//
//import org.apache.ibatis.annotations.Many;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class One {
//    @Id
//    @GeneratedValue
//    private Integer id;
//    private String name;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "one")
//    @JoinColumn(name = "many_id")
//    private List<Many> manys;
//}