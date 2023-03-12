package com.blade.chatgpt.service.impl;

import java.util.List;
import com.blade.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GptApiRequestMsgServiceImpl implements IGptApiRequestMsgService 
{
    @Autowired
    private GptApiRequestMsgMapper gptApiRequestMsgMapper;

    /**
     * 查询GPT API 请求信息
     * 
     * @param id GPT API 请求信息主键
     * @return GPT API 请求信息
     */
    @Override
    public GptApiRequestMsg selectGptApiRequestMsgById(String id)
    {
        return gptApiRequestMsgMapper.selectGptApiRequestMsgById(id);
    }

    /**
     * 查询GPT API 请求信息列表
     * 
     * @param gptApiRequestMsg GPT API 请求信息
     * @return GPT API 请求信息
     */
    @Override
    public List<GptApiRequestMsg> selectGptApiRequestMsgList(GptApiRequestMsg gptApiRequestMsg)
    {
        return gptApiRequestMsgMapper.selectGptApiRequestMsgList(gptApiRequestMsg);
    }

    /**
     * 新增GPT API 请求信息
     * 
     * @param gptApiRequestMsg GPT API 请求信息
     * @return 结果
     */
    @Override
    public int insertGptApiRequestMsg(GptApiRequestMsg gptApiRequestMsg)
    {
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
    public int updateGptApiRequestMsg(GptApiRequestMsg gptApiRequestMsg)
    {
        return gptApiRequestMsgMapper.updateGptApiRequestMsg(gptApiRequestMsg);
    }

    /**
     * 批量删除GPT API 请求信息
     * 
     * @param ids 需要删除的GPT API 请求信息主键
     * @return 结果
     */
    @Override
    public int deleteGptApiRequestMsgByIds(String[] ids)
    {
        return gptApiRequestMsgMapper.deleteGptApiRequestMsgByIds(ids);
    }

    /**
     * 删除GPT API 请求信息信息
     * 
     * @param id GPT API 请求信息主键
     * @return 结果
     */
    @Override
    public int deleteGptApiRequestMsgById(String id)
    {
        return gptApiRequestMsgMapper.deleteGptApiRequestMsgById(id);
    }
}
