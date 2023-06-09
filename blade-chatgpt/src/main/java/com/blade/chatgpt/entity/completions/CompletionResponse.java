package com.blade.chatgpt.entity.completions;

import com.blade.chatgpt.entity.common.Choice;
import com.blade.chatgpt.entity.common.OpenAiResponse;
import com.blade.chatgpt.entity.common.Usage;
import lombok.Data;
/**
 * 描述： 答案类
 *
 * @author https:www.unfbx.com
 *  2023-02-11
 */
@Data
public class CompletionResponse extends OpenAiResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;
}
