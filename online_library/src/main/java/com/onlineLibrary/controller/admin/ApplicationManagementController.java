package com.onlineLibrary.controller.admin;


import com.onlineLibrary.DTO.BooksDTO;
import com.onlineLibrary.DTO.RequestPageQueryDTO;
import com.onlineLibrary.mapper.HelpPageMapper;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.HelpPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")

public class ApplicationManagementController {

    @Autowired
    private HelpPageService helpPageService;

    /**
     * 条件查询request并分页展示
     * @param requestPageQueryDTO
     * @return
     */
    @GetMapping("/request")
    @CrossOrigin()
    public Result<PageResult> pageQuery(RequestPageQueryDTO requestPageQueryDTO) {
        PageResult pageResult = helpPageService.pageQuery(requestPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 同意/驳回请求
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/request/update")
    @CrossOrigin()
    public Result updateRequestStatus(Integer status,Integer id) {
        helpPageService.updateStatus(status,id);
        return Result.success();
    }
}
