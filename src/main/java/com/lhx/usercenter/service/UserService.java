package com.lhx.usercenter.service;

import com.lhx.usercenter.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Neveremoer
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-04-08 14:54:08
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    Long UserRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request      cookie
     * @return 用户脱敏信息
     */
    User UserLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 搜索用户
     *
     * @param userAccount
     * @param request
     * @return
     */
    List<User> searchUsers(String userAccount, HttpServletRequest request);

    /**
     * 删除用户
     *
     * @param id
     * @param request
     * @return
     */


    /**
     * 获取当前用户
     *
     * @param request
     * @return
     */
    User getCurrentUser(HttpServletRequest request);

    Integer userLogout(HttpServletRequest request);


    Boolean deleteUser(Long userId,HttpServletRequest request);
}
