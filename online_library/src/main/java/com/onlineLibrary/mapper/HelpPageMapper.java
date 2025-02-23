package com.onlineLibrary.mapper;

import com.github.pagehelper.Page;
import com.onlineLibrary.DTO.RequestPageQueryDTO;
import com.onlineLibrary.VO.RequestManagementVO;
import com.onlineLibrary.annotation.AutoFill;
import com.onlineLibrary.entity.ContactForm;
import com.onlineLibrary.entity.RequestForm;
import com.onlineLibrary.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HelpPageMapper {

    /**
     * 新增购书请求
     * @param reqForm
     */
    @AutoFill(value = OperationType.INSERT)
    @Insert("INSERT INTO request_form (title, author, isbn, publisher, publishYear, reason,status,create_time,create_user,update_time,update_user) " +
            "VALUES (#{title}, #{author}, #{isbn}, #{publisher}, #{publishYear}, #{reason},#{status},#{createTime},#{createUser},#{updateTime},#{updateUser})")
    void addRequestForm(RequestForm reqForm);

    /**
     * 新增建议
     * @param contactForm
     */
    @Insert("INSERT INTO contact_form ( email,subject,message,create_time,create_user) " +
            "VALUES (#{email}, #{subject}, #{message},#{createTime},#{createUser})")
    void addContactForm(ContactForm contactForm);

    /**
     * 条件查询request并分页展示
     * @param requestPageQueryDTO
     * @return
     */
    Page<RequestManagementVO> select02(RequestPageQueryDTO requestPageQueryDTO);

    /**
     * 请求同意/驳回
     * @param status
     */
    @Update("update request_form set status = #{status} where id = #{id}")
    void updateRequestStatus(Integer status,Integer id);

    /**
     * 我的请求
     * @param requestPageQueryDTO
     * @return
     */
    Page<RequestManagementVO> select03(RequestPageQueryDTO requestPageQueryDTO);
}
