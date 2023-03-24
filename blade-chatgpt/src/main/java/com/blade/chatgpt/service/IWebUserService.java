package com.blade.chatgpt.service;

import com.blade.chatgpt.domain.WebUser;

import java.util.List;

/**
 * web用户Service接口
 *
 * @author blade
 * @date 2023-03-18
 */
public interface IWebUserService {
    /**
     * 查询web用户
     *
     * @param id web用户主键
     * @return web用户
     */
    WebUser selectWebUserById(String id);

    /**
     * 查询web用户列表
     *
     * @param webUser web用户
     * @return web用户集合
     */
    List<WebUser> selectWebUserList(WebUser webUser);

    /**
     * 新增web用户
     *
     * @param webUser web用户
     * @return 结果
     */
    int insertWebUser(WebUser webUser);

    /**
     * 修改web用户
     *
     * @param webUser web用户
     * @return 结果
     */
    int updateWebUser(WebUser webUser);

    /**
     * 批量删除web用户
     *
     * @param ids 需要删除的web用户主键集合
     * @return 结果
     */
    int deleteWebUserByIds(String[] ids);

    /**
     * 删除web用户信息
     *
     * @param id web用户主键
     * @return 结果
     */
    int deleteWebUserById(String id);

    /**
     * 注册web用户
     *
     * @param webUser 注册对象
     * @return token
     */
    String registerWebUser(WebUser webUser);

    /**
     * 登录web用户
     *
     * @param webUser 登录对象
     * @return token
     */
    String loginWebUser(WebUser webUser);
}
