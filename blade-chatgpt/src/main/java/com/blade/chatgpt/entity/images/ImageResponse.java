package com.blade.chatgpt.entity.images;

import lombok.Data;

import java.util.List;

/**
 * 描述：
 *
 * @author https:www.unfbx.com
 *  2023-02-15
 */
@Data
public class ImageResponse {
    private long created;
    private List<Item> data;
}
