package com.blade.chatgpt.service.impl;

import com.blade.chatgpt.domain.WebUserLimitMsg;
import com.blade.chatgpt.mapper.WebUserLimitMsgMapper;
import com.blade.chatgpt.service.IWebUserLimitMsgService;
import com.blade.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * web用户使用限制信息Service业务层处理
 *
 * @author Blade
 * @date 2023-03-25
 */
@Service
public class WebUserLimitMsgServiceImpl implements IWebUserLimitMsgService {
    @Autowired
    private WebUserLimitMsgMapper webUserLimitMsgMapper;

    /**
     * 查询web用户使用限制信息
     *
     * @param id web用户使用限制信息主键
     * @return web用户使用限制信息
     */
    @Override
    public WebUserLimitMsg selectWebUserLimitMsgById(String id) {
        return webUserLimitMsgMapper.selectWebUserLimitMsgById(id);
    }

    /**
     * 查询web用户使用限制信息列表
     *
     * @param webUserLimitMsg web用户使用限制信息
     * @return web用户使用限制信息
     */
    @Override
    public List<WebUserLimitMsg> selectWebUserLimitMsgList(WebUserLimitMsg webUserLimitMsg) {
        return webUserLimitMsgMapper.selectWebUserLimitMsgList(webUserLimitMsg);
    }

    /**
     * 新增web用户使用限制信息
     *
     * @param webUserLimitMsg web用户使用限制信息
     * @return 结果
     */
    @Override
    public int insertWebUserLimitMsg(WebUserLimitMsg webUserLimitMsg) {
        webUserLimitMsg.setCreateTime(DateUtils.getNowDate());
        return webUserLimitMsgMapper.insertWebUserLimitMsg(webUserLimitMsg);
    }

    /**
     * 修改web用户使用限制信息
     *
     * @param webUserLimitMsg web用户使用限制信息
     * @return 结果
     */
    @Override
    public int updateWebUserLimitMsg(WebUserLimitMsg webUserLimitMsg) {
        return webUserLimitMsgMapper.updateWebUserLimitMsg(webUserLimitMsg);
    }

    /**
     * 批量删除web用户使用限制信息
     *
     * @param ids 需要删除的web用户使用限制信息主键
     * @return 结果
     */
    @Override
    public int deleteWebUserLimitMsgByIds(String[] ids) {
        return webUserLimitMsgMapper.deleteWebUserLimitMsgByIds(ids);
    }

    /**
     * 删除web用户使用限制信息信息
     *
     * @param id web用户使用限制信息主键
     * @return 结果
     */
    @Override
    public int deleteWebUserLimitMsgById(String id) {
        return webUserLimitMsgMapper.deleteWebUserLimitMsgById(id);
    }

    @Override
    public WebUserLimitMsg selectLimitMsgByUserAccount(String userAccount) {
        return this.webUserLimitMsgMapper.selectLimitMsgByUserAccount(userAccount);
    }
}
