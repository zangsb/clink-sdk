package com.tinet.clink.kb.request;


import com.tinet.clink.kb.PathEnum;
import com.tinet.clink.core.request.AbstractRequestModel;
import com.tinet.clink.core.utils.HttpMethodType;
import com.tinet.clink.kb.response.StandardQuestionResponse;

/**
 * 创建标准问请求
 *
 * @author feizq
 * @date 2022/06/15
 **/
public class CreateStandardQuestionRequest extends AbstractRequestModel<StandardQuestionResponse> {
    /**
     * 机器人ID
     */
    private String botId;
    /**
     * 标准问问题
     */
    private String title;
    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 标签ID集合
     */
    private String[] tagIdList;

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
        if (botId != null) {
            putBodyParameter("botId", botId);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        if (title != null) {
            putBodyParameter("title", title);
        }
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        if (categoryId != null) {
            putBodyParameter("categoryId", categoryId);
        }
    }

    public String[] getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(String[] tagIdList) {
        this.tagIdList = tagIdList;
        if (tagIdList != null) {
            putBodyParameter("tagIdList", tagIdList);
        }
    }

    public CreateStandardQuestionRequest() {
        super(PathEnum.CreateStandardQuestion.value(), HttpMethodType.POST);
    }

    @Override
    public Class<StandardQuestionResponse> getResponseClass() {
        return StandardQuestionResponse.class;
    }
}
