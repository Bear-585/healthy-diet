package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 在线客服
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@TableName("chat")
public class ChatEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChatEntity() {
		
	}
	
	public ChatEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 用户id
	 */
					
	private Long userid;
	
	/**
	 * 管理员id
	 */
					
	private Long adminId;
	
	/**
	 * 提问
	 */
					
	private String ask;
	
	/**
	 * 回复
	 */
					
	private String reply;
	
	/**
	 * 是否回复
	 */
					
	private Integer isReply;
	
	/**
	 * 已读/未读(1:已读,0:未读)
	 */
					
	private Integer isRead;
	
	/**
	 * 用户名
	 */
					
	private String uname;
	
	/**
	 * 用户头像
	 */
					
	private String avatar;
	
	/**
	 * 内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)
	 */
					
	private Integer type;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
	/**
	 * 设置：管理员id
	 */
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	/**
	 * 获取：管理员id
	 */
	public Long getAdminId() {
		return adminId;
	}
	/**
	 * 设置：提问
	 */
	public void setAsk(String ask) {
		this.ask = ask;
	}
	/**
	 * 获取：提问
	 */
	public String getAsk() {
		return ask;
	}
	/**
	 * 设置：回复
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}
	/**
	 * 获取：回复
	 */
	public String getReply() {
		return reply;
	}
	/**
	 * 设置：是否回复
	 */
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	/**
	 * 获取：是否回复
	 */
	public Integer getIsReply() {
		return isReply;
	}
	/**
	 * 设置：已读/未读(1:已读,0:未读)
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：已读/未读(1:已读,0:未读)
	 */
	public Integer getIsRead() {
		return isRead;
	}
	/**
	 * 设置：用户名
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * 获取：用户名
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * 设置：用户头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：用户头像
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置：内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)
	 */
	public Integer getType() {
		return type;
	}
}
