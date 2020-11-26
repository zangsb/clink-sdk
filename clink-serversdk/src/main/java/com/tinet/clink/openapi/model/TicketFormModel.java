package com.tinet.clink.openapi.model;


import java.util.List;

/**工单中的表单对象
 *
 * @author liuhy
 * @date: 2020/11/19
 **/
public class TicketFormModel {


    /**
     * 表单的id
     */
    private Integer id;

    /**
     * 表单名称
     */
    private String name;

    /**
     * 节点key
     */
    private String taskKey;

    /**
     * 节点名称
     */
    private String taskName;

    /**
     * 表单中的自定义字段集合
     */
    private List<Field> fields;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}