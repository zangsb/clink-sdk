package com.tinet.clink.cc.model;

import java.util.List;

public class QueueWithAgentActionModel {

	/**
	 * 队列号
	 */
	private String qno;

	/**
	 * 队列名
	 */
	private String name;

	/**
	 * 空闲座席数
	 */
	private Long idle;

	/**
	 * 座席总数（登录 + 未登录）
	 */
	private Integer total;

	/**
	 * 座席列表
	 */
	private List<QueueWithAgentActionClientModel> clientLists;

	public String getQno() {
		return qno;
	}

	public void setQno(String qno) {
		this.qno = qno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdle() {
		return idle;
	}

	public void setIdle(Long idle) {
		this.idle = idle;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<QueueWithAgentActionClientModel> getClientLists() {
		return clientLists;
	}

	public void setClientLists(List<QueueWithAgentActionClientModel> clientLists) {
		this.clientLists = clientLists;
	}
}
