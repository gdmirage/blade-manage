package com.blade.chatgpt.service.impl;

import java.util.Date;
import java.util.List;

import com.blade.chatgpt.domain.WebUserLimitMsg;
import com.blade.chatgpt.model.TranslateRequest;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.chatgpt.service.IWebUserLimitMsgService;
import com.blade.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.blade.chatgpt.mapper.GptApiRequestMsgMapper;
import com.blade.chatgpt.domain.GptApiRequestMsg;
import com.blade.chatgpt.service.IGptApiRequestMsgService;

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

    public String translate(TranslateRequest request) {

        this.validateLimitMsg(request.getUserAccount());

        /*GptAccount gptAccount = this.gptAccountService.getOneGptAccount();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809));
        OpenAiClient client = OpenAiClient.builder()
                .connectTimeout(50)
                .readTimeout(50)
                .writeTimeout(50)
                .apiKey(gptAccount.getKey())
//                .proxy(proxy)
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
        */

        return "这是个测试";
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
