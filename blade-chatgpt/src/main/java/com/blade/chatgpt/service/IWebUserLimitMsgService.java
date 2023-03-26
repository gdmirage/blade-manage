package com.blade.chatgpt.service;

import com.blade.chatgpt.domain.WebUserLimitMsg;

import java.util.List;

/**
 * web用户使用限制信息Service接口
 *
 * @author Blade
 * @date 2023-03-25
 */
public interface IWebUserLimitMsgService {
    /**
     * 查询web用户使用限制信息
     *
     * @param id web用户使用限制信息主键
     * @return web用户使用限制信息
     */
    WebUserLimitMsg selectWebUserLimitMsgById(String id);

    /**
     * 查询web用户使用限制信息列表
     *
     * @param webUserLimitMsg web用户使用限制信息
     * @return web用户使用限制信息集合
     */
    List<WebUserLimitMsg> selectWebUserLimitMsgList(WebUserLimitMsg webUserLimitMsg);

    /**
     * 新增web用户使用限制信息
     *
     * @param webUserLimitMsg web用户使用限制信息
     * @return 结果
     */
    int insertWebUserLimitMsg(WebUserLimitMsg webUserLimitMsg);

    /**
     * 修改web用户使用限制信息
     *
     * @param webUserLimitMsg web用户使用限制信息
     * @return 结果
     */
    int updateWebUserLimitMsg(WebUserLimitMsg webUserLimitMsg);

    /**
     * 批量删除web用户使用限制信息
     *
     * @param ids 需要删除的web用户使用限制信息主键集合
     * @return 结果
     */
    int deleteWebUserLimitMsgByIds(String[] ids);

    /**
     * 删除web用户使用限制信息信息
     *
     * @param id web用户使用限制信息主键
     * @return 结果
     */
    int deleteWebUserLimitMsgById(String id);

    /**
     * 根据用户账号查询限制信息
     *
     * @param userAccount 用户账号
     * @return {@link WebUserLimitMsg}
     */
    WebUserLimitMsg selectLimitMsgByUserAccount(String userAccount);
}
