package com.blade.chatgpt.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.blade.common.annotation.Excel;
import com.blade.common.core.domain.BaseEntity;

/**
 * GPT API 请求信息对象 gpt_api_request_msg
 * 
 * @author blade
 * @date 2023-03-16
 */
public class GptApiRequestMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 修改人 */
    private String modifier;

    /** 修改时间 */
    private Date modifyTime;

    /** 删除标识， 1：yes， 2：no */
    private String isDelete;

    /** 版本，用于乐观锁 */
    private Long version;

    /** GPT账号的key */
    @Excel(name = "GPT账号的key")
    private String accountKey;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userAccount;

    /** 请求内容 */
    private String requestContent;

    /** 返回内容 */
    private String responseContent;

    /** API请求参数 */
    private String apiRequest;

    /** API返回结果 */
    private String apiResponse;

    /** API返回结果代码 */
    private String apiResultCode;

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
    public void setAccountKey(String accountKey) 
    {
        this.accountKey = accountKey;
    }

    public String getAccountKey() 
    {
        return accountKey;
    }
    public void setUserAccount(String userAccount) 
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount() 
    {
        return userAccount;
    }
    public void setRequestContent(String requestContent) 
    {
        this.requestContent = requestContent;
    }

    public String getRequestContent() 
    {
        return requestContent;
    }
    public void setResponseContent(String responseContent) 
    {
        this.responseContent = responseContent;
    }

    public String getResponseContent() 
    {
        return responseContent;
    }
    public void setApiRequest(String apiRequest) 
    {
        this.apiRequest = apiRequest;
    }

    public String getApiRequest() 
    {
        return apiRequest;
    }
    public void setApiResponse(String apiResponse) 
    {
        this.apiResponse = apiResponse;
    }

    public String getApiResponse() 
    {
        return apiResponse;
    }
    public void setApiResultCode(String apiResultCode) 
    {
        this.apiResultCode = apiResultCode;
    }

    public String getApiResultCode() 
    {
        return apiResultCode;
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
            .append("accountKey", getAccountKey())
            .append("userAccount", getUserAccount())
            .append("requestContent", getRequestContent())
            .append("responseContent", getResponseContent())
            .append("apiRequest", getApiRequest())
            .append("apiResponse", getApiResponse())
            .append("apiResultCode", getApiResultCode())
            .toString();
    }
}
