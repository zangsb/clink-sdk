package com.tinet.clink.openapi.request.kb;

import com.tinet.clink.openapi.PathEnum;
import com.tinet.clink.openapi.request.AbstractRequestModel;
import com.tinet.clink.openapi.response.kb.DeleteStandardQuestionResponse;
import com.tinet.clink.openapi.utils.HttpMethodType;

/**
 * 删除标准问请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  DeleteStandardQuestionRequest extends AbstractRequestModel<DeleteStandardQuestionResponse> {

    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准问id数组
     */
    private Integer[] ids;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
        if (ids != null) {
            putBodyParameter("ids", ids);
        }
    }

    public DeleteStandardQuestionRequest() {
        super(PathEnum.DeleteStandardQuestion.value(), HttpMethodType.POST);
    }

    @Override
    public Class<DeleteStandardQuestionResponse> getResponseClass() {
        return DeleteStandardQuestionResponse.class;
    }
}
