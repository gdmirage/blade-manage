package com.blade.chatgpt.service;

import java.util.List;
import com.blade.chatgpt.domain.GptAccount;

/**
 * GPT 账号Service接口
 * 
 * @author blade
 * @date 2023-03-12
 */
public interface IGptAccountService 
{
    /**
     * 查询GPT 账号
     * 
     * @param id GPT 账号主键
     * @return GPT 账号
     */
    public GptAccount selectGptAccountById(String id);

    /**
     * 查询GPT 账号列表
     * 
     * @param gptAccount GPT 账号
     * @return GPT 账号集合
     */
    public List<GptAccount> selectGptAccountList(GptAccount gptAccount);

    /**
     * 新增GPT 账号
     * 
     * @param gptAccount GPT 账号
     * @return 结果
     */
    public int insertGptAccount(GptAccount gptAccount);

    /**
     * 修改GPT 账号
     * 
     * @param gptAccount GPT 账号
     * @return 结果
     */
    public int updateGptAccount(GptAccount gptAccount);

    /**
     * 批量删除GPT 账号
     * 
     * @param ids 需要删除的GPT 账号主键集合
     * @return 结果
     */
    public int deleteGptAccountByIds(String[] ids);

    /**
     * 删除GPT 账号信息
     * 
     * @param id GPT 账号主键
     * @return 结果
     */
    public int deleteGptAccountById(String id);
}
