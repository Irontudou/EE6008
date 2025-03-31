package com.onlineLibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {

    private Integer id;
    private String author;
    private String doi;
    private String title;
    private int citedCount;
    private Year publicationYear;

}