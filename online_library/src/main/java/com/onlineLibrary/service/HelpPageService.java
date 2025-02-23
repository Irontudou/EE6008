package com.onlineLibrary.service;

import com.onlineLibrary.DTO.ContactDTO;
import com.onlineLibrary.DTO.RequestFormDTO;
import com.onlineLibrary.DTO.RequestPageQueryDTO;
import com.onlineLibrary.result.PageResult;

public interface HelpPageService {
    /**
     * 新增购书请求
     * @param requestFormDTO
     */
    void saveRequestForm(RequestFormDTO requestFormDTO);

    /**
     * 建议
     * @param contactDTO
     */
    void saveContactForm(ContactDTO contactDTO);

    /**
     * 条件查询request并分页展示
     * @param requestPageQueryDTO
     * @return
     */
    PageResult pageQuery(RequestPageQueryDTO requestPageQueryDTO);


    /**
     *
     * @param status
     */
    void updateStatus(Integer status,Integer id);

    /**
     * 我的请求
     * @param requestPageQueryDTO
     * @return
     */
    PageResult queryMyRequest(RequestPageQueryDTO requestPageQueryDTO);
}
