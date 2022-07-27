package com.tinet.clink.openapi.response.kb;

import com.tinet.clink.openapi.model.StandardQuestionModel;
import com.tinet.clink.openapi.response.ResponseModel;

import java.util.List;

/**
 * @author feizq
 * @date 2022/07/27
 **/
public class ListStandardQuestionResponse extends ResponseModel {

    private List<StandardQuestionModel> standardQuestions;

    public List<StandardQuestionModel> getStandardQuestions() {
        return standardQuestions;
    }

    public void setStandardQuestions(List<StandardQuestionModel> standardQuestions) {
        this.standardQuestions = standardQuestions;
    }
}
