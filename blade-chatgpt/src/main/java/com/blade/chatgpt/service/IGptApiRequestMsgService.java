package com.blade.chatgpt.service;

import java.util.List;
import com.blade.chatgpt.domain.GptApiRequestMsg;

/**
 * GPT API 请求信息Service接口
 * 
 * @author blade
 * @date 2023-03-12
 */
public interface IGptApiRequestMsgService 
{
    /**
     * 查询GPT API 请求信息
     * 
     * @param id GPT API 请求信息主键
     * @return GPT API 请求信息
     */
    public GptApiRequestMsg selectGptApiRequestMsgById(String id);

    /**
     * 查询GPT API 请求信息列表
     * 
     * @param gptApiRequestMsg GPT API 请求信息
     * @return GPT API 请求信息集合
     */
    public List<GptApiRequestMsg> selectGptApiRequestMsgList(GptApiRequestMsg gptApiRequestMsg);

    /**
     * 新增GPT API 请求信息
     * 
     * @param gptApiRequestMsg GPT API 请求信息
     * @return 结果
     */
    public int insertGptApiRequestMsg(GptApiRequestMsg gptApiRequestMsg);

    /**
     * 修改GPT API 请求信息
     * 
     * @param gptApiRequestMsg GPT API 请求信息
     * @return 结果
     */
    public int updateGptApiRequestMsg(GptApiRequestMsg gptApiRequestMsg);

    /**
     * 批量删除GPT API 请求信息
     * 
     * @param ids 需要删除的GPT API 请求信息主键集合
     * @return 结果
     */
    public int deleteGptApiRequestMsgByIds(String[] ids);

    /**
     * 删除GPT API 请求信息信息
     * 
     * @param id GPT API 请求信息主键
     * @return 结果
     */
    public int deleteGptApiRequestMsgById(String id);
}
