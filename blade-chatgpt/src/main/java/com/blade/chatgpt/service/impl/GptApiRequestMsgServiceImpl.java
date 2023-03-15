package com.blade.chatgpt.service.impl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import com.blade.chatgpt.OpenAiClient;
import com.blade.chatgpt.domain.GptAccount;
import com.blade.chatgpt.entity.chat.ChatChoice;
import com.blade.chatgpt.entity.chat.ChatCompletion;
import com.blade.chatgpt.entity.chat.ChatCompletionResponse;
import com.blade.chatgpt.entity.chat.Message;
import com.blade.chatgpt.model.TranslateRequest;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.blade.chatgpt.mapper.GptApiRequestMsgMapper;
import com.blade.chatgpt.domain.GptApiRequestMsg;
import com.blade.chatgpt.service.IGptApiRequestMsgService;
import sun.rmi.runtime.Log;

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

    public GptApiRequestMsgServiceImpl(GptApiRequestMsgMapper gptApiRequestMsgMapper, IGptAccountService gptAccountService) {
        this.gptApiRequestMsgMapper = gptApiRequestMsgMapper;
        this.gptAccountService = gptAccountService;
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

    public String translate(TranslateRequest request) {
        GptAccount gptAccount = this.gptAccountService.getOneGptAccount();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809));
        OpenAiClient client = OpenAiClient.builder()
                .connectTimeout(50)
                .readTimeout(50)
                .writeTimeout(50)
                .apiKey(gptAccount.getKey())
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build();
        String content = String.format("帮我翻译成%s: %s", request.getTargetLanguage(), request.getContent());
        Message message = Message.builder().role(Message.Role.USER).content(content).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();

        log.info("开始请求open ai");
        ChatCompletionResponse chatCompletionResponse = client.chatCompletion(chatCompletion);
        log.info("完成请求open ai");
        StringBuilder returnContent = new StringBuilder();
        for (ChatChoice chatChoice : chatCompletionResponse.getChoices()) {
            returnContent.append(chatChoice.getMessage().getContent());
        }

        return returnContent.toString();
    }
}
