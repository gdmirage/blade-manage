package com.blade.chatgpt.entity.embeddings;

import com.blade.chatgpt.entity.common.Usage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：
 *
 * @author https:www.unfbx.com
 *  2023-02-15
 */
@Data
public class EmbeddingResponse {

    private String object;
    private List<Item> data;
    private String model;
    private Usage usage;
}
