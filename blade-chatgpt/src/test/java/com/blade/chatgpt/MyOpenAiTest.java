package com.blade.chatgpt;

import com.alibaba.fastjson2.JSON;
import com.blade.chatgpt.OpenAiClient;
import com.blade.chatgpt.OpenAiStreamClient;
import com.blade.chatgpt.entity.chat.ChatCompletion;
import com.blade.chatgpt.entity.chat.ChatCompletionResponse;
import com.blade.chatgpt.entity.chat.Message;
import com.blade.chatgpt.entity.completions.Completion;
import com.blade.chatgpt.entity.completions.CompletionResponse;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

/**
 * TODO:
 *
 * @author blade
 * 2023/3/11 13:06
 */
public class MyOpenAiTest {

    private OpenAiClient client;

    @Before
    public void before() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10809));
        client = OpenAiClient.builder()
                .connectTimeout(50)
                .readTimeout(50)
                .writeTimeout(50)
                .apiKey("sk-**********")
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build();
    }

    // 最新版本的模型 gpt-3.5
    @Test
    public void chat() {
        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content("帮我翻译成英文: 天气晴朗，阳光明媚，是个春游的好日子！").build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = client.chatCompletion(chatCompletion);
        System.out.println(JSON.toJSONString(chatCompletionResponse));
        /*chatCompletionResponse.getChoices().forEach(e -> {
            System.out.println(e.getMessage());
        });*/
    }
}
