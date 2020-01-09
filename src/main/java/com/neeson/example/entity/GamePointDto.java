package com.neeson.example.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Entity
@Data
@Table(name = "game_point")
public class GamePointDto implements Serializable {


    /**
     *   width_height_dpi
     */
    @Id
    private String flag;

    private String totalPoints;

    private String firstPoints;
    private String secondPoints;
    private String threePoints;
    private String fourPoints;

}
