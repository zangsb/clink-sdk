package com.tinet.clink.ticket;

/**
 * @author wangll
 * @date 2019/2/19
 */
public enum PathEnum {
    //查询工单记录
    ListTicket("ticket/list_ticket"),

    // 获取工单详情
    GetTicketDetail("ticket/get_ticket_detail"),

    ListTicketCategory("ticket/list_ticket_category"),

    ListTicketWorkflow("ticket/list_ticket_workflow"),

    SaveTicket("ticket/save_ticket"),
    // 更新工单-人工
    UpdateTicket("ticket/update_ticket"),
    // 更新工单-预制流
    UpdateTicketPrefabricated("ticket/update_ticket_prefabricated"),
    // 获取表单详情
    GetFormDetail("ticket/get_form_detail"),
    // 获取表单详情
    GetSysFormDetail("ticket/get_sys_form_detail"),
    // 流转工单
    FlowTicket("ticket/flow_ticket"),
    // 保存工单
    StoreTicket("ticket/store_ticket"),
    // 评论工单
    CommentTicket("ticket/comment_ticket"),
    // 关闭工单
    CloseTicket("ticket/close_ticket"),
    // 完成工单
    FinishTicket("ticket/finish_ticket"),
    // 获取工单文件 url
    GetTicketFileUrl("ticket/get_ticket_file_url"),
    // 根据外部参数或ID查询工单插件
    GetTicketPlugin("ticket/get_ticket_plugin"),
    // 获取工单字段列表
    ListTicketFields("ticket/list_ticket_fields"),
    // 更新工单字段选项值
    updateTicketFieldProperty("ticket/update_ticket_field_property"),
    // 工单整体统计报表
    StatTicket("ticket/stat_ticket"),
    // 座席情况统计报表
    StatTicketByClient("ticket/stat_ticket_client"),
    // 座席组情况统计报表
    StatTicketByQueue("ticket/stat_ticket_queue"),
    ;
    private String value;

    PathEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
