package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatClientLoginResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 客服登录
 *
 * @author wangpw
 * @date 2021年5月14日
 */
/**
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated
public class  ChatClientLoginRequest extends AbstractRequestModel<ChatClientLoginResponse> {

    public ChatClientLoginRequest() {
        super(PathEnum.ChatClientLogin.value(), HttpMethodType.POST);
    }

    /**
     * 座席工号
     */
    private String cno;
    /**
     * 会话上限
     */
    private Integer chatLimitNumber;
    /**
     * 座席登录状态
     */
    private Integer chatLoginStatus;
    /**
     * 座席置忙类型
     */
    private Integer pauseType;
    /**
     * 座席置忙状态描述
     */
    private String pauseDescription;

    @Override
    public Class<ChatClientLoginResponse> getResponseClass() {
        return ChatClientLoginResponse.class;
    }

    public void setCno(String cno) {
        putQueryParameter("cno", cno);
        this.cno = cno;
    }

    public void setChatLimitNumber(Integer chatLimitNumber) {
        putQueryParameter("chatLimitNumber", chatLimitNumber);
        this.chatLimitNumber = chatLimitNumber;
    }

    public void setChatLoginStatus(Integer chatLoginStatus) {
        putQueryParameter("chatLoginStatus", chatLoginStatus);
        this.chatLoginStatus = chatLoginStatus;
    }

    public void setPauseType(Integer pauseType) {
        putQueryParameter("pauseType", pauseType);
        this.pauseType = pauseType;
    }

    public void setPauseDescription(String pauseDescription) {
        putQueryParameter("pauseDescription", pauseDescription);
        this.pauseDescription = pauseDescription;
    }

    public String getCno() {
        return cno;
    }

    public Integer getChatLimitNumber() {
        return chatLimitNumber;
    }

    public Integer getChatLoginStatus() {
        return chatLoginStatus;
    }

    public Integer getPauseType() {
        return pauseType;
    }

    public String getPauseDescription() {
        return pauseDescription;
    }

    @Override
    public String toString() {
        return "ChatClientLoginRequest{" +
                "cno='" + cno + '\'' +
                ", chatLimitNumber=" + chatLimitNumber +
                ", chatLoginStatus=" + chatLoginStatus +
                ", pauseType=" + pauseType +
                ", pauseDescription='" + pauseDescription + '\'' +
                "} " + super.toString();
    }
}
