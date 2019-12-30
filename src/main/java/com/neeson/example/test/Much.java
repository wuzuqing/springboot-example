//package com.neeson.example.test;
//
//
//import org.apache.ibatis.annotations.Many;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//public class Much {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    private String name;
//
//    @ManyToMany
//    @JoinTable(
//            name = "much_more",
//            joinColumns= {@JoinColumn(name = "many_id", referencedColumnName = "id")},
//            inverseJoinColumns= {@JoinColumn(name = "much_id", referencedColumnName = "id")})
//    private List<Many> manys;
//
//    public Integer getId() {
//        return id;
//    }
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
//    public List<Many> getManys() {
//        return manys;
//    }
//
//    public void setManys(List<Many> manys) {
//        this.manys = manys;
//    }
//
//
//}