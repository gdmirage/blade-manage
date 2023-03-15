package com.blade.chatgpt.controller;

import com.blade.chatgpt.OpenAiClient;
import com.blade.chatgpt.domain.GptAccount;
import com.blade.chatgpt.model.TranslateRequest;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.chatgpt.service.IGptApiRequestMsgService;
import com.blade.common.annotation.Log;
import com.blade.common.core.controller.BaseController;
import com.blade.common.core.domain.AjaxResult;
import com.blade.common.core.page.TableDataInfo;
import com.blade.common.enums.BusinessType;
import com.blade.common.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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

    public WebGptController(IGptAccountService gptAccountService, IGptApiRequestMsgService gptApiRequestMsgService) {
        this.gptAccountService = gptAccountService;
        this.gptApiRequestMsgService = gptApiRequestMsgService;
    }

    @PostMapping("/translate")
    public AjaxResult translate(@RequestBody @Valid TranslateRequest request) {
        return success(this.gptApiRequestMsgService.translate(request));
    }
}
