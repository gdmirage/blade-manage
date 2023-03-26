package com.blade.chatgpt.service.impl;

import com.blade.chatgpt.domain.GptAccount;
import com.blade.chatgpt.mapper.GptAccountMapper;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * GPT 账号Service业务层处理
 *
 * @author blade
 * @date 2023-03-26
 */
@Service
public class GptAccountServiceImpl implements IGptAccountService {
    @Autowired
    private GptAccountMapper gptAccountMapper;

    /**
     * 查询GPT 账号
     *
     * @param id GPT 账号主键
     * @return GPT 账号
     */
    @Override
    public GptAccount selectGptAccountById(String id) {
        return gptAccountMapper.selectGptAccountById(id);
    }

    /**
     * 查询GPT 账号列表
     *
     * @param gptAccount GPT 账号
     * @return GPT 账号
     */
    @Override
    public List<GptAccount> selectGptAccountList(GptAccount gptAccount) {
        return gptAccountMapper.selectGptAccountList(gptAccount);
    }

    /**
     * 新增GPT 账号
     *
     * @param gptAccount GPT 账号
     * @return 结果
     */
    @Override
    public int insertGptAccount(GptAccount gptAccount) {
        gptAccount.setCreateTime(DateUtils.getNowDate());
        return gptAccountMapper.insertGptAccount(gptAccount);
    }

    /**
     * 修改GPT 账号
     *
     * @param gptAccount GPT 账号
     * @return 结果
     */
    @Override
    public int updateGptAccount(GptAccount gptAccount) {
        return gptAccountMapper.updateGptAccount(gptAccount);
    }

    /**
     * 批量删除GPT 账号
     *
     * @param ids 需要删除的GPT 账号主键
     * @return 结果
     */
    @Override
    public int deleteGptAccountByIds(String[] ids) {
        return gptAccountMapper.deleteGptAccountByIds(ids);
    }

    /**
     * 删除GPT 账号信息
     *
     * @param id GPT 账号主键
     * @return 结果
     */
    @Override
    public int deleteGptAccountById(String id) {
        return gptAccountMapper.deleteGptAccountById(id);
    }

    @Override
    public GptAccount getOneGptAccount() {
        GptAccount gptAccount = this.gptAccountMapper.selectGptAccountById("7647dc07-0a31-484f-aba8-47e63d01528b");
        return gptAccount;
    }
}
