package com.tinet.clink.openapi.model;

/** 
 * 由于SDK版本升级，当前类已过期，请使用v3.0新版SDK,详见官网文档
 */
@Deprecated  
public class  QueueWithAgentActionClientModel {

	/**
	 * 座席号
	 */
	private String cno;

	/**
	 * 座席名
	 */
	private String name;

	/**
	 * 当前状态
	 */
	private String status;

	/**
	 * 当前状态持续时长
	 */
	private Long duration;

	/**
	 * 座席当前状态
	 */
	private String code;

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
