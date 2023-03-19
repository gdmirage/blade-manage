package com.blade.chatgpt.service.impl;

import java.util.List;

import com.blade.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blade.chatgpt.mapper.WebUserMapper;
import com.blade.chatgpt.domain.WebUser;
import com.blade.chatgpt.service.IWebUserService;

/**
 * web用户Service业务层处理
 *
 * @author blade
 * @date 2023-03-18
 */
@Service
public class WebUserServiceImpl implements IWebUserService {
    @Autowired
    private WebUserMapper webUserMapper;

    /**
     * 查询web用户
     *
     * @param id web用户主键
     * @return web用户
     */
    @Override
    public WebUser selectWebUserById(String id) {
        return webUserMapper.selectWebUserById(id);
    }

    /**
     * 查询web用户列表
     *
     * @param webUser web用户
     * @return web用户
     */
    @Override
    public List<WebUser> selectWebUserList(WebUser webUser) {
        return webUserMapper.selectWebUserList(webUser);
    }

    /**
     * 新增web用户
     *
     * @param webUser web用户
     * @return 结果
     */
    @Override
    public int insertWebUser(WebUser webUser) {
        webUser.setCreateTime(DateUtils.getNowDate());
        return webUserMapper.insertWebUser(webUser);
    }

    /**
     * 修改web用户
     *
     * @param webUser web用户
     * @return 结果
     */
    @Override
    public int updateWebUser(WebUser webUser) {
        return webUserMapper.updateWebUser(webUser);
    }

    /**
     * 批量删除web用户
     *
     * @param ids 需要删除的web用户主键
     * @return 结果
     */
    @Override
    public int deleteWebUserByIds(String[] ids) {
        return webUserMapper.deleteWebUserByIds(ids);
    }

    /**
     * 删除web用户信息
     *
     * @param id web用户主键
     * @return 结果
     */
    @Override
    public int deleteWebUserById(String id) {
        return webUserMapper.deleteWebUserById(id);
    }
}
