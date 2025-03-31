package com.onlineLibrary.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {

    private Integer id;
    private String author;
    private String doi;
    private String title;
    private int citedCount;
    private Year publicationYear;
    //页码
    private int page;
    //每页显示记录数
    private int pageSize;

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }
}
