package com.tinet.clink.cc.request.monitor;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.monitor.AgentStatusDetailResponse;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;


import java.util.Objects;

/**
 * 查询座席状态详情
 *
 * @author yinzk
 * @date 202/2/9
 **/
public class AgentStatusDetailRequest extends AbstractRequestModel<AgentStatusDetailResponse> {

    /**
     * 队列号数组
     */
    private String cno;

    public AgentStatusDetailRequest() {
        super(PathEnum.AgentStatusDetail.value(), HttpMethodType.GET);
    }

    @Override
    public Class<AgentStatusDetailResponse> getResponseClass() {
        return AgentStatusDetailResponse.class;
    }


    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
        if (Objects.nonNull(cno)) {
            putQueryParameter("cno", cno);
        }
    }
}
