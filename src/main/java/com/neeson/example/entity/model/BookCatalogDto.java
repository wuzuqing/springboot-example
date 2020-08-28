package com.neeson.example.entity.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "book_catalog")
public class BookCatalogDto {
    @Id
    private Long id;

    private Long bookId;
    private Integer catalogIndex;
    private String title;
    private String path;
    @Column( name = "book_content" ,length = 10000)
    private String content;
}

/**
 * import java.util.ArrayList;
 * import java.util.Date;
 * import java.util.List;
 *
 * import org.litepal.crud.LitePalSupport;
 *
 * public class Album extends LitePalSupport {
 *
 * 	private long id;
 *
 * //    @Column(ignore = false, unique = false, nullable = false, defaultValue = "888")
 *     private int sales;
 *
 * //    @Column(nullable = false)
 * 	private String name;
 *
 * //    @Column(ignore = false, nullable = false)
 * 	private String publisher;
 *
 * //    @Column(nullable = false, ignore = false)
 * 	private double price;
 *
 * //    @Column(unique = true, ignore = false)
 *     private String serial;
 *
 * //    @Column(ignore = false, nullable = false, defaultValue = "100")
 * 	private Date release;
 *
 * 	private Singer singer;
 *
 * 	private List<Song> songs = new ArrayList<Song>();
 *
 * 	public long getId() {
 * 		return id;
 *        }
 *
 * 	public void setId(long id) {
 * 		this.id = id;
 *    }
 *
 * 	public String getName() {
 * 		return name;
 *    }
 *
 * 	public void setName(String name) {
 * 		this.name = name;
 *    }
 *
 * 	public String getPublisher() {
 * 		return publisher;
 *    }
 *
 * 	public void setPublisher(String publisher) {
 * 		this.publisher = publisher;
 *    }
 *
 * 	public double getPrice() {
 * 		return price;
 *    }
 *
 * 	public void setPrice(double price) {
 * 		this.price = price;
 *    }
 *
 * 	public Date getRelease() {
 * 		return release;
 *    }
 *
 * 	public void setRelease(Date release) {
 * 		this.release = release;
 *    }
 *
 * 	public Singer getSinger() {
 * 		return singer;
 *    }
 *
 * 	public void setSinger(Singer singer) {
 * 		this.singer = singer;
 *    }
 *
 * 	public List<Song> getSongs() {
 * 		return songs;
 *    }
 *
 * 	public void setSongs(List<Song> songs) {
 * 		this.songs = songs;
 *    }
 *
 *     public String getSerial() {
 *         return serial;
 *     }
 *
 *     public void setSerial(String serial) {
 *         this.serial = serial;
 *     }
 *
 *     public int getSales() {
 *         return sales;
 *     }
 *
 *     public void setSales(int sales) {
 *         this.sales = sales;
 *     }
 *
 * }
 */
