package com.blade.chatgpt.mapper;

import com.blade.chatgpt.domain.WebUser;

import java.util.List;

/**
 * web用户Mapper接口
 *
 * @author blade
 * @date 2023-03-18
 */
public interface WebUserMapper {
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
     * 删除web用户
     *
     * @param id web用户主键
     * @return 结果
     */
    int deleteWebUserById(String id);

    /**
     * 批量删除web用户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWebUserByIds(String[] ids);

    /**
     * 根据用户账号查询web用户
     *
     * @param userAccount 用户账号
     * @return {@link WebUser}
     */
    WebUser selectUserByUserAccount(String userAccount);

    /**
     * 根据邮箱查询web用户
     *
     * @param email 邮箱
     * @return {@link WebUser}
     */
    WebUser selectUserByEmail(String email);
}
