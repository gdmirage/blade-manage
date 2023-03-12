package com.blade.chatgpt.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blade.common.annotation.Excel;
import com.blade.common.core.domain.BaseEntity;

/**
 * GPT 账号对象 gpt_account
 * 
 * @author blade
 * @date 2023-03-12
 */
public class GptAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 创建人 */
    private String creator;

    /** 修改人 */
    private String modifier;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** 删除标识， 1: yes, 2: no */
    private String isDelete;

    /** 版本，用于乐观锁 */
    private Long version;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 邮箱登录密码 */
    private String emailPassword;

    /** GPT密码 */
    private String gptPassword;

    /** GPT账号的key */
    @Excel(name = "GPT账号的key")
    private String key;

    /** 总共的token */
    private Long totalToken;

    /** 已经使用的token */
    private Long usedToken;

    /** 启用状态， 1: yes, 2: no */
    @Excel(name = "启用状态， 1: yes, 2: no")
    private String enabled;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setModifier(String modifier) 
    {
        this.modifier = modifier;
    }

    public String getModifier() 
    {
        return modifier;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setVersion(Long version) 
    {
        this.version = version;
    }

    public Long getVersion() 
    {
        return version;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setEmailPassword(String emailPassword) 
    {
        this.emailPassword = emailPassword;
    }

    public String getEmailPassword() 
    {
        return emailPassword;
    }
    public void setGptPassword(String gptPassword) 
    {
        this.gptPassword = gptPassword;
    }

    public String getGptPassword() 
    {
        return gptPassword;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
    }
    public void setTotalToken(Long totalToken) 
    {
        this.totalToken = totalToken;
    }

    public Long getTotalToken() 
    {
        return totalToken;
    }
    public void setUsedToken(Long usedToken) 
    {
        this.usedToken = usedToken;
    }

    public Long getUsedToken() 
    {
        return usedToken;
    }
    public void setEnabled(String enabled) 
    {
        this.enabled = enabled;
    }

    public String getEnabled() 
    {
        return enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("creator", getCreator())
            .append("createTime", getCreateTime())
            .append("modifier", getModifier())
            .append("modifyTime", getModifyTime())
            .append("isDelete", getIsDelete())
            .append("version", getVersion())
            .append("account", getAccount())
            .append("emailPassword", getEmailPassword())
            .append("gptPassword", getGptPassword())
            .append("key", getKey())
            .append("totalToken", getTotalToken())
            .append("usedToken", getUsedToken())
            .append("enabled", getEnabled())
            .toString();
    }
}
