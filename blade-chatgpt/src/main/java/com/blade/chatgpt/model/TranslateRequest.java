package com.blade.chatgpt.model;

import javax.validation.constraints.NotBlank;

/**
 * TODO:
 *
 * @author Blade
 * @date 2023/3/15 11:10
 */
public class TranslateRequest {
    @NotBlank(message = "翻译文本不能为空")
    private String content;
    private String targetLanguage;
    private String userAccount;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
