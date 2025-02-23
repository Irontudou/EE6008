package com.onlineLibrary.controller;


import com.onlineLibrary.DTO.ContactDTO;
import com.onlineLibrary.DTO.RequestFormDTO;
import com.onlineLibrary.DTO.RequestPageQueryDTO;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.HelpPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/help")
public class HelpPageController {

    @Autowired
    private HelpPageService helpPageService;

    /**
     * 新增购书请求
     * @param requestFormDTO
     * @return
     */
    @PostMapping("/request")
    @CrossOrigin()
    public Result saveRequestForm(@RequestBody RequestFormDTO requestFormDTO) {
        helpPageService.saveRequestForm(requestFormDTO);
        return Result.success();
    }

    /**
     * 新增联系
     * @param contactDTO
     * @return
     */
    @PostMapping("/contact")
    @CrossOrigin()
    public Result saveContactForm(@RequestBody ContactDTO contactDTO) {
        helpPageService.saveContactForm(contactDTO);
        return Result.success();
    }


    /**
     * 我的请求
     * @param requestPageQueryDTO
     * @return
     */
    @GetMapping("/myrequest")
    @CrossOrigin()
    public Result<PageResult> pageQuery(RequestPageQueryDTO requestPageQueryDTO) {
        PageResult pageResult = helpPageService.queryMyRequest(requestPageQueryDTO);
        return Result.success(pageResult);
    }




}
