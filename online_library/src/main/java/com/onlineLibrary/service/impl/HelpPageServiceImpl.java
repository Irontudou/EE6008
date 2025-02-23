package com.onlineLibrary.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.onlineLibrary.DTO.ContactDTO;
import com.onlineLibrary.DTO.RequestFormDTO;
import com.onlineLibrary.DTO.RequestPageQueryDTO;
import com.onlineLibrary.VO.RequestManagementVO;
import com.onlineLibrary.VO.UserManagementVO;
import com.onlineLibrary.context.BaseContext;
import com.onlineLibrary.entity.ContactForm;
import com.onlineLibrary.entity.RequestForm;
import com.onlineLibrary.mapper.HelpPageMapper;
import com.onlineLibrary.mapper.HomePageMapper;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.service.HelpPageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpPageServiceImpl implements HelpPageService {


    @Autowired
    private HelpPageMapper helpPageMapper;

    /**
     * 新增购书请求
     * @param requestFormDTO
     */
    @Override
    public void saveRequestForm(RequestFormDTO requestFormDTO) {
        //
        RequestForm reqForm = new RequestForm();
        BeanUtils.copyProperties(requestFormDTO, reqForm);
        reqForm.setStatus(RequestForm.pending);
        //
        helpPageMapper.addRequestForm(reqForm);
    }

    /**
     * 联系我们
     * @param contactDTO
     */
    @Override
    public void saveContactForm(ContactDTO contactDTO) {

        ContactForm contactForm = new ContactForm();
        BeanUtils.copyProperties(contactDTO, contactForm);

        contactForm.setCreateTime(LocalDateTime.now());
        contactForm.setCreateUser(BaseContext.getCurrentId());

        helpPageMapper.addContactForm(contactForm);
    }


    /**
     * 条件查询request并分页展示
     * @param requestPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(RequestPageQueryDTO requestPageQueryDTO) {
        //设置分页参数
        PageHelper.startPage(requestPageQueryDTO.getPage(), requestPageQueryDTO.getPageSize());
        //调用Mapper查数据
        Page<RequestManagementVO> page = helpPageMapper.select02(requestPageQueryDTO);
        //数据封装
        long total = page.getTotal();
        List<RequestManagementVO> records = page.getResult();
        //返回
        return new PageResult(total, records);
    }

    /**
     * 同意/驳回请求
     * @param status
     * @param id
     */
    @Override
    public void updateStatus(Integer status,Integer id) {
        helpPageMapper.updateRequestStatus(status,id);
    }

    /**
     * 我的请求
     * @param requestPageQueryDTO
     * @return
     */
    @Override
    public PageResult queryMyRequest(RequestPageQueryDTO requestPageQueryDTO) {
        //设置分页参数
        PageHelper.startPage(requestPageQueryDTO.getPage(), requestPageQueryDTO.getPageSize());
        //调用Mapper查数据
        Integer userId = BaseContext.getCurrentId();
        requestPageQueryDTO.setCreateUser(userId);
        Page<RequestManagementVO> page = helpPageMapper.select03(requestPageQueryDTO);
        //数据封装
        long total = page.getTotal();
        List<RequestManagementVO> records = page.getResult();
        //返回
        return new PageResult(total, records);
    }
}
