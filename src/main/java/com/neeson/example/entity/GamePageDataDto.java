package com.neeson.example.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Data
@Table(name = "game_page_data")
public class GamePageDataDto implements Serializable {


    /**
     *   width_height_dpi
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String flag;

    private String page;

    private String data;

}
