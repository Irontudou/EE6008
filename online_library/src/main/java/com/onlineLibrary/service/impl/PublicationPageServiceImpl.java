package com.onlineLibrary.service.impl;

import com.onlineLibrary.DTO.PublicationDTO;
import com.onlineLibrary.entity.Publication;
import com.onlineLibrary.mapper.PublicationPageMapper;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.service.HomePageService;
import com.onlineLibrary.service.PublicationPageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationPageServiceImpl implements PublicationPageService {
    @Autowired
    PublicationPageMapper publicationPageMapper;
    @Override
    @Transactional
    public void savePublication(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        BeanUtils.copyProperties(publicationDTO, publication);
        publicationPageMapper.insert(publication);
    }

    @Override
    public PageResult pageQuery(PublicationDTO publicationDTO) {
        return null;
    }
}
