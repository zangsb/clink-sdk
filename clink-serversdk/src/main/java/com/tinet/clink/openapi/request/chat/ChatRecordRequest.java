package com.tinet.clink.openapi.request.chat;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.chat.ChatRecordResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * @author Wangyl
 * @date 2020/7/29
 */
public class ChatRecordRequest extends AbstractRequestModel<ChatRecordResponse> {

    private String scrollId;
    private String mainUniqueId;
    private String date;
    private Integer limit;

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
        if (scrollId != null) {
            putQueryParameter("scrollId", scrollId);
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        if (date != null) {
            putQueryParameter("date", date);
        }
    }

    public void setMainUniqueId(String mainUniqueId) {
        this.mainUniqueId = mainUniqueId;
        if (mainUniqueId != null) {
            putQueryParameter("mainUniqueId", mainUniqueId);
        }
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
        if (limit != null) {
            putQueryParameter("limit", limit);
        }
    }


    public ChatRecordRequest() {
        super(PathEnum.ChatRecord.value(), HttpMethodType.GET);
    }


    @Override
    public Class<ChatRecordResponse> getResponseClass() {
        return ChatRecordResponse.class;
    }
}
