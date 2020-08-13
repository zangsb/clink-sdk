package com.tinet.clink.openapi.response.chat;

import com.tinet.clink.openapi.model.ChatComment;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatCommentResponse extends ResponseModel {
    List<ChatComment> records;
    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    /**
     * 游标 id
     */
    private String scrollId;
    public List<ChatComment> getRecords() {
        return records;
    }

    public void setChatInfos(List<ChatComment> chatComments) {
        this.records = chatComments;
    }
}
