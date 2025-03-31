package com.onlineLibrary.service;

import com.onlineLibrary.DTO.PublicationDTO;
import com.onlineLibrary.result.PageResult;

import java.util.List;

public interface PublicationPageService {

    void savePublication(PublicationDTO publicationDTO);

    /**
     * 查询期刊
     * @param publicationDTO
     * @return
     */
    PageResult pageQuery(PublicationDTO publicationDTO);

}
