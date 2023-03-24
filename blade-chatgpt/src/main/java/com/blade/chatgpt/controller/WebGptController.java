package com.blade.chatgpt.controller;

import com.blade.chatgpt.domain.WebUser;
import com.blade.chatgpt.model.TranslateRequest;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.chatgpt.service.IGptApiRequestMsgService;
import com.blade.chatgpt.service.IWebUserService;
import com.blade.common.core.controller.BaseController;
import com.blade.common.core.domain.AjaxResult;
import com.blade.common.core.redis.RedisCache;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * GPT 账号Controller
 *
 * @author blade
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/gptapi")
public class WebGptController extends BaseController {
    private final IGptAccountService gptAccountService;
    private final IGptApiRequestMsgService gptApiRequestMsgService;
    private final IWebUserService webUserService;
    private final RedisCache redisCache;

    public WebGptController(IGptAccountService gptAccountService, IGptApiRequestMsgService gptApiRequestMsgService,
                            IWebUserService webUserService, RedisCache redisCache) {
        this.gptAccountService = gptAccountService;
        this.gptApiRequestMsgService = gptApiRequestMsgService;
        this.webUserService = webUserService;
        this.redisCache = redisCache;
    }

    private boolean matchToken(String token) {
        String userAccount = StringUtils.split(token, "_")[0];
        String exitToken = this.redisCache.getCacheObject("login_token:" + userAccount);
        if (Objects.equals(token, exitToken)) {
            return true;
        }
        return false;
    }

    @PostMapping("/translate")
    public AjaxResult translate(@RequestBody @Valid TranslateRequest translateRequest, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            return error("用户未登录,请登录后再试");
        }

        if (!matchToken(token)) {
            return error("用户登录失效，请重新登录");
        }

//        return success("接口调试中");
        return success(this.gptApiRequestMsgService.translate(translateRequest));
    }

    /**
     * 注册web用户
     */
    @PostMapping("/webuser/register")
    public AjaxResult register(@RequestBody WebUser webUser) {
        return success(this.webUserService.registerWebUser(webUser));
    }

    /**
     * 注册web用户
     */
    @PostMapping("/webuser/login")
    public AjaxResult login(@RequestBody WebUser webUser) {
        return success(this.webUserService.loginWebUser(webUser));
    }
}
