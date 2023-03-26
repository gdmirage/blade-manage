package com.blade.chatgpt.service.impl;

import com.alibaba.fastjson2.JSON;
import com.blade.chatgpt.OpenAiClient;
import com.blade.chatgpt.domain.GptAccount;
import com.blade.chatgpt.domain.GptApiRequestMsg;
import com.blade.chatgpt.domain.WebUserLimitMsg;
import com.blade.chatgpt.entity.chat.ChatChoice;
import com.blade.chatgpt.entity.chat.ChatCompletion;
import com.blade.chatgpt.entity.chat.ChatCompletionResponse;
import com.blade.chatgpt.entity.chat.Message;
import com.blade.chatgpt.mapper.GptApiRequestMsgMapper;
import com.blade.chatgpt.model.TranslateRequest;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.chatgpt.service.IGptApiRequestMsgService;
import com.blade.chatgpt.service.IWebUserLimitMsgService;
import com.blade.common.constant.UserConstants;
import com.blade.common.utils.DateUtils;
import com.blade.common.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * GPT API 请求信息Service业务层处理
 *
 * @author blade
 * @date 2023-03-12
 */
@Service
@Slf4j
public class GptApiRequestMsgServiceImpl implements IGptApiRequestMsgService {

    private final GptApiRequestMsgMapper gptApiRequestMsgMapper;
    private final IGptAccountService gptAccountService;
    private final IWebUserLimitMsgService webUserLimitMsgService;

    public GptApiRequestMsgServiceImpl(GptApiRequestMsgMapper gptApiRequestMsgMapper, IGptAccountService gptAccountService,
                                       IWebUserLimitMsgService webUserLimitMsgService) {
        this.gptApiRequestMsgMapper = gptApiRequestMsgMapper;
        this.gptAccountService = gptAccountService;
        this.webUserLimitMsgService = webUserLimitMsgService;
    }

    /**
     * 查询GPT API 请求信息
     *
     * @param id GPT API 请求信息主键
     * @return GPT API 请求信息
     */
    @Override
    public GptApiRequestMsg selectGptApiRequestMsgById(String id) {
        return gptApiRequestMsgMapper.selectGptApiRequestMsgById(id);
    }

    /**
     * 查询GPT API 请求信息列表
     *
     * @param gptApiRequestMsg GPT API 请求信息
     * @return GPT API 请求信息
     */
    @Override
    public List<GptApiRequestMsg> selectGptApiRequestMsgList(GptApiRequestMsg gptApiRequestMsg) {
        return gptApiRequestMsgMapper.selectGptApiRequestMsgList(gptApiRequestMsg);
    }

    /**
     * 新增GPT API 请求信息
     *
     * @param gptApiRequestMsg GPT API 请求信息
     * @return 结果
     */
    @Override
    public int insertGptApiRequestMsg(GptApiRequestMsg gptApiRequestMsg) {
        gptApiRequestMsg.setCreateTime(DateUtils.getNowDate());
        return gptApiRequestMsgMapper.insertGptApiRequestMsg(gptApiRequestMsg);
    }

    /**
     * 修改GPT API 请求信息
     *
     * @param gptApiRequestMsg GPT API 请求信息
     * @return 结果
     */
    @Override
    public int updateGptApiRequestMsg(GptApiRequestMsg gptApiRequestMsg) {
        return gptApiRequestMsgMapper.updateGptApiRequestMsg(gptApiRequestMsg);
    }

    /**
     * 批量删除GPT API 请求信息
     *
     * @param ids 需要删除的GPT API 请求信息主键
     * @return 结果
     */
    @Override
    public int deleteGptApiRequestMsgByIds(String[] ids) {
        return gptApiRequestMsgMapper.deleteGptApiRequestMsgByIds(ids);
    }

    /**
     * 删除GPT API 请求信息信息
     *
     * @param id GPT API 请求信息主键
     * @return 结果
     */
    @Override
    public int deleteGptApiRequestMsgById(String id) {
        return gptApiRequestMsgMapper.deleteGptApiRequestMsgById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String translate(TranslateRequest request) {

        this.validateLimitMsg(request.getUserAccount());

        GptAccount gptAccount = this.gptAccountService.getOneGptAccount();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809));
        OpenAiClient client = OpenAiClient.builder()
                .connectTimeout(500)
                .readTimeout(500)
                .writeTimeout(500)
                .apiKey(gptAccount.getGptKey())
//                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build();
        String content = String.format("帮我翻译成%s: %s", request.getTargetLanguage(), request.getContent());
        Message message = Message.builder().role(Message.Role.USER).content(content).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();

        log.info("开始请求open ai");
        String result = "{\"id\":\"chatcmpl-6yG0fnfFktSR4dBPS0CYdiaJr6wrk\",\"object\":\"chat.completion\",\"created\":1679819241,\"model\":\"gpt-3.5-turbo-0301\",\"usage\":{\"prompt_tokens\":24,\"completion_tokens\":2,\"total_tokens\":26},\"choices\":[{\"message\":{\"role\":\"assistant\",\"content\":\"Sky!\"},\"finish_reason\":\"stop\",\"index\":0}]}";
        ChatCompletionResponse chatCompletionResponse = JSON.parseObject(result, ChatCompletionResponse.class);
//        ChatCompletionResponse chatCompletionResponse = client.chatCompletion(chatCompletion);
        log.info("完成请求open ai");
        StringBuilder returnContent = new StringBuilder();
        for (ChatChoice chatChoice : chatCompletionResponse.getChoices()) {
            returnContent.append(chatChoice.getMessage().getContent());
        }

        GptApiRequestMsg gptApiRequestMsg = new GptApiRequestMsg();
        gptApiRequestMsg.setId(IdUtils.fastUUID());
        gptApiRequestMsg.setCreateTime(DateUtils.getNowDate());
        gptApiRequestMsg.setCreator(UserConstants.SYS_USER);
        gptApiRequestMsg.setModifyTime(DateUtils.getNowDate());
        gptApiRequestMsg.setModifier(UserConstants.SYS_USER);
        gptApiRequestMsg.setRequestContent(content);
        gptApiRequestMsg.setApiRequest(JSON.toJSONString(chatCompletion));
        gptApiRequestMsg.setApiResponse(JSON.toJSONString(chatCompletionResponse));
        gptApiRequestMsg.setResponseContent(returnContent.toString());
        gptApiRequestMsg.setUserAccount(request.getUserAccount());
        gptApiRequestMsg.setAccountKey(gptAccount.getGptKey());
        this.gptApiRequestMsgMapper.insertGptApiRequestMsg(gptApiRequestMsg);

        gptAccount.setUsedToken(gptAccount.getUsedToken() + chatCompletionResponse.getUsage().getTotalTokens());
        this.gptAccountService.updateGptAccount(gptAccount);

        return returnContent.toString();


//        return "这是个测试";
    }

    private boolean validateLimitMsg(String userAccount) {
        WebUserLimitMsg limitMsg = this.webUserLimitMsgService.selectLimitMsgByUserAccount(userAccount);
        if (null == limitMsg) {
            throw new RuntimeException("用户无额度，请联系客服！");
        }

        if (null == limitMsg.getAvailableEndTime() || limitMsg.getAvailableEndTime().before(new Date())) {
            throw new RuntimeException("用户无额度，请联系客服！");
        }

        return false;
    }
}
