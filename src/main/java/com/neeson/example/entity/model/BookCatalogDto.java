package com.neeson.example.entity.model;

import javax.persistence.*;

@Entity
@Table(name = "book_catalog")
public class BookCatalogDto {

    public BookCatalogDto() {
    }

    public BookCatalogDto(String title, String path, int index) {
        this.title = title;
        this.path = path;
        this.catalogIndex = index;
    }

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private Integer catalogIndex;
    private String title;
    private String path;

    @Lob
    @Column(columnDefinition="TEXT",name = "book_content")
    private String content;
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getCatalogIndex() {
        return catalogIndex;
    }

    public void setCatalogIndex(Integer catalogIndex) {
        this.catalogIndex = catalogIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
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
