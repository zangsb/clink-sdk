package com.tinet.clink.ticket.request;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketStoreResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 工单保存
 *
 * @author lize
 * @date: 2022/4/1
 **/
public class TicketStoreRequest extends AbstractRequestModel<TicketStoreResponse> {


    public TicketStoreRequest() {
        super(PathEnum.StoreTicket.value(), HttpMethodType.POST);
    }

    @Override
    public boolean isMultipartFormData() {
        return true;
    }

    @Override
    public Class<TicketStoreResponse> getResponseClass() {
        return TicketStoreResponse.class;
    }
}