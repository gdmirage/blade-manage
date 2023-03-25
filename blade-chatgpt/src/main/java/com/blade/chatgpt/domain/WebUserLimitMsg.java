package com.blade.chatgpt.domain;

import com.blade.common.annotation.Excel;
import com.blade.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * web用户使用限制信息对象 web_user_limit_msg
 *
 * @author Blade
 * @date 2023-03-25
 */
public class WebUserLimitMsg extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private String modifier;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /**
     * 删除标识， 1: yes, 2: no
     */
    private String isDelete;

    /**
     * 版本，用于乐观锁
     */
    private Long version;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private String userId;

    /**
     * 有效次数
     */
    @Excel(name = "有效次数")
    private Long availableNum;

    /**
     * 有效开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date availableStartTime;

    /**
     * 有效结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "有效结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date availableEndTime;

    /**
     * 启用状态， 1: yes, 2: no
     */
    @Excel(name = "启用状态， 1: yes, 2: no")
    private String enabled;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAvailableNum(Long availableNum) {
        this.availableNum = availableNum;
    }

    public Long getAvailableNum() {
        return availableNum;
    }

    public void setAvailableStartTime(Date availableStartTime) {
        this.availableStartTime = availableStartTime;
    }

    public Date getAvailableStartTime() {
        return availableStartTime;
    }

    public void setAvailableEndTime(Date availableEndTime) {
        this.availableEndTime = availableEndTime;
    }

    public Date getAvailableEndTime() {
        return availableEndTime;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("creator", getCreator())
                .append("createTime", getCreateTime())
                .append("modifier", getModifier())
                .append("modifyTime", getModifyTime())
                .append("isDelete", getIsDelete())
                .append("version", getVersion())
                .append("userId", getUserId())
                .append("availableNum", getAvailableNum())
                .append("availableStartTime", getAvailableStartTime())
                .append("availableEndTime", getAvailableEndTime())
                .append("enabled", getEnabled())
                .append("remark", getRemark())
                .toString();
    }
}
