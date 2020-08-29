package com.neeson.example.entity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "book")
public class BookDto {
    /**
     * 小说Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小说名
     */
    private String name;

    /**
     * 下载网站
     */
    private String channel;

    /**
     * 目录网址
     */
    private String catalogUrl;

}

/**
 * import java.util.ArrayList;
 * import java.util.Date;
 * import java.util.List;
 * <p>
 * import org.litepal.crud.LitePalSupport;
 * <p>
 * public class Album extends LitePalSupport {
 * <p>
 * private long id;
 * <p>
 * //    @Column(ignore = false, unique = false, nullable = false, defaultValue = "888")
 * private int sales;
 * <p>
 * //    @Column(nullable = false)
 * private String name;
 * <p>
 * //    @Column(ignore = false, nullable = false)
 * private String publisher;
 * <p>
 * //    @Column(nullable = false, ignore = false)
 * private double price;
 * <p>
 * //    @Column(unique = true, ignore = false)
 * private String serial;
 * <p>
 * //    @Column(ignore = false, nullable = false, defaultValue = "100")
 * private Date release;
 * <p>
 * private Singer singer;
 * <p>
 * private List<Song> songs = new ArrayList<Song>();
 * <p>
 * public long getId() {
 * return id;
 * }
 * <p>
 * public void setId(long id) {
 * this.id = id;
 * }
 * <p>
 * public String getName() {
 * return name;
 * }
 * <p>
 * public void setName(String name) {
 * this.name = name;
 * }
 * <p>
 * public String getPublisher() {
 * return publisher;
 * }
 * <p>
 * public void setPublisher(String publisher) {
 * this.publisher = publisher;
 * }
 * <p>
 * public double getPrice() {
 * return price;
 * }
 * <p>
 * public void setPrice(double price) {
 * this.price = price;
 * }
 * <p>
 * public Date getRelease() {
 * return release;
 * }
 * <p>
 * public void setRelease(Date release) {
 * this.release = release;
 * }
 * <p>
 * public Singer getSinger() {
 * return singer;
 * }
 * <p>
 * public void setSinger(Singer singer) {
 * this.singer = singer;
 * }
 * <p>
 * public List<Song> getSongs() {
 * return songs;
 * }
 * <p>
 * public void setSongs(List<Song> songs) {
 * this.songs = songs;
 * }
 * <p>
 * public String getSerial() {
 * return serial;
 * }
 * <p>
 * public void setSerial(String serial) {
 * this.serial = serial;
 * }
 * <p>
 * public int getSales() {
 * return sales;
 * }
 * <p>
 * public void setSales(int sales) {
 * this.sales = sales;
 * }
 * <p>
 * }
 */
