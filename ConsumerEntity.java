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
 * 用户
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-02-13 19:37:42
 */
@TableName("consumer")
public class ConsumerEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ConsumerEntity() {
		
	}
	
	public ConsumerEntity(T t) {
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
    @TableId
	private Long id;
	/**
	 * 账号
	 */
					
	private String account;
	
	/**
	 * 密码
	 */
					
	private String password;
	
	/**
	 * 姓名
	 */
					
	private String fullName;
	
	/**
	 * 性别
	 */
					
	private String gender;
	
	/**
	 * 手机
	 */
					
	private String phone;
	
	/**
	 * 身高/m
	 */
					
	private String height;
	
	/**
	 * 体重/kg
	 */
					
	private String weight;
	
	/**
	 * BMI
	 */
					
	private String bmi;
	
	/**
	 * 头像
	 */
					
	private String profile;
	
	/**
	 * 微信openid
	 */
					
	private String openid;
	
	/**
	 * 微信昵称
	 */
					
	private String nickname;
	
	/**
	 * 微信头像
	 */
					
	private String avatar;
	
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
	 * 设置：账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：账号
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：姓名
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * 获取：姓名
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：手机
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：身高/m
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * 获取：身高/m
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * 设置：体重/kg
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重/kg
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * 设置：BMI
	 */
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	/**
	 * 获取：BMI
	 */
	public String getBmi() {
		return bmi;
	}
	/**
	 * 设置：头像
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * 获取：头像
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * 设置：微信openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：微信openid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：微信昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：微信昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：微信头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：微信头像
	 */
	public String getAvatar() {
		return avatar;
	}
}
