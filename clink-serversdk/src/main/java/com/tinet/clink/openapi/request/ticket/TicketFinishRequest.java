package com.tinet.clink.openapi.request.ticket;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.ticket.TicketFinishResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

import java.util.Objects;

/**
 * 工单 完成
 *
 * @Author DengJie
 * @Date 2022/04/21
 */
public class TicketFinishRequest extends AbstractRequestModel<TicketFinishResponse> {

    public TicketFinishRequest() {
        super(PathEnum.FinishTicket.value(), HttpMethodType.POST);
    }

    /**
     * 操作人id
     */
    private Integer operatorId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 工单id
     */
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if (Objects.nonNull(id)) {
            putQueryParameter("id", id);
        }
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取响应对象
     *
     * @return 响应对象
     */
    @Override
    public Class<TicketFinishResponse> getResponseClass() {
        return TicketFinishResponse.class;
    }
}
