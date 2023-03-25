package com.blade.chatgpt.service.impl;

import com.blade.chatgpt.domain.WebUser;
import com.blade.chatgpt.mapper.WebUserMapper;
import com.blade.chatgpt.service.IWebUserService;
import com.blade.common.constant.UserConstants;
import com.blade.common.core.redis.RedisCache;
import com.blade.common.utils.DateUtils;
import com.blade.common.utils.sign.MD5Util;
import com.blade.common.utils.uuid.IdUtils;
import com.blade.common.utils.uuid.UUID;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * web用户Service业务层处理
 *
 * @author blade
 * @date 2023-03-18
 */
@Service
public class WebUserServiceImpl implements IWebUserService {
    private final WebUserMapper webUserMapper;
    private final RedisCache redisCache;

    public WebUserServiceImpl(WebUserMapper webUserMapper, RedisCache redisCache) {
        this.webUserMapper = webUserMapper;
        this.redisCache = redisCache;
    }

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

    @Override
    public String registerWebUser(WebUser webUser) {
        WebUser exit = this.webUserMapper.selectWebUserById(webUser.getUserAccount());

        if (null == exit) {
            exit = this.webUserMapper.selectUserByEmail(webUser.getEmail());
        }

        if (null != exit) {
            throw new RuntimeException("用户账号或者邮箱已经存在");
        }

        webUser.setPassword(MD5Util.encode32ToUpperCase(webUser.getPassword()));
        webUser.setId(IdUtils.simpleUUID());
        webUser.setCreateTime(DateUtils.getNowDate());
        webUser.setCreator(UserConstants.SYS_USER);
        webUser.setModifier(UserConstants.SYS_USER);
        webUser.setModifyTime(DateUtils.getNowDate());
        webUserMapper.insertWebUser(webUser);

        String login_key = this.createTokenKey(webUser.getUserAccount());
        String token = this.createToken(webUser.getUserAccount());
        this.redisCache.setCacheObject(login_key, token);
        return token;
    }

    @Override
    public String loginWebUser(WebUser webUser) {
        WebUser exit = this.webUserMapper.selectUserByUserAccount(webUser.getUserAccount());

        if (null == exit) {
            exit = this.webUserMapper.selectUserByEmail(webUser.getEmail());
        }

        if (null == exit) {
            throw new RuntimeException("用户不存在");
        }

        if (!Objects.equals(MD5Util.encode32ToUpperCase(webUser.getPassword()), exit.getPassword())) {
                throw new RuntimeException("密码不正确");
        }

        exit.setModifier(UserConstants.SYS_USER);
        exit.setModifyTime(DateUtils.getNowDate());
        exit.setLoginDate(DateUtils.getNowDate());
        webUserMapper.updateWebUser(exit);

        String login_key = this.createTokenKey(exit.getUserAccount());
        String token = this.createToken(exit.getUserAccount());
        this.redisCache.setCacheObject(login_key, token);
        return token;
    }

    private String createToken(String userAccount) {
        return userAccount + "_" + UUID.fastUUID();
    }

    private String createTokenKey(String userAccount) {
        return "login_token:" + userAccount;
    }
}
