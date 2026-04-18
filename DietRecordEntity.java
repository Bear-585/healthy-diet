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
 * 饮食记录
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@TableName("diet_record")
public class DietRecordEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DietRecordEntity() {
		
	}
	
	public DietRecordEntity(T t) {
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
	 * 用餐时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date mealTime;
	
	/**
	 * 食物名称
	 */
					
	private String foodName;
	
	/**
	 * 摄入热量/千卡
	 */
					
	private Integer calories;
	
	/**
	 * 摄入蛋白质/g
	 */
					
	private Integer protein;
	
	/**
	 * 摄入脂肪/g
	 */
					
	private Double fatIntake;
	
	/**
	 * 摄入碳水化合物/g
	 */
					
	private Double carbohydrateIntake;
	
	/**
	 * 摄入膳食纤维/g
	 */
					
	private Integer dietaryFiberIntake;
	
	/**
	 * 维生素
	 */
					
	private String vitamin;
	
	/**
	 * 记录时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date recordTime;
	
	/**
	 * 饮食分析
	 */
					
	private String dietAnalysis;
	
	/**
	 * 账号
	 */
					
	private String account;
	
	/**
	 * 姓名
	 */
					
	private String fullName;
	
	/**
	 * 图片
	 */
					
	private String picture;
	
	/**
	 * 用户id
	 */
					
	private Long userid;
	
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
	 * 设置：用餐时间
	 */
	public void setMealTime(Date mealTime) {
		this.mealTime = mealTime;
	}
	/**
	 * 获取：用餐时间
	 */
	public Date getMealTime() {
		return mealTime;
	}
	/**
	 * 设置：食物名称
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * 获取：食物名称
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * 设置：摄入热量/千卡
	 */
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	/**
	 * 获取：摄入热量/千卡
	 */
	public Integer getCalories() {
		return calories;
	}
	/**
	 * 设置：摄入蛋白质/g
	 */
	public void setProtein(Integer protein) {
		this.protein = protein;
	}
	/**
	 * 获取：摄入蛋白质/g
	 */
	public Integer getProtein() {
		return protein;
	}
	/**
	 * 设置：摄入脂肪/g
	 */
	public void setFatIntake(Double fatIntake) {
		this.fatIntake = fatIntake;
	}
	/**
	 * 获取：摄入脂肪/g
	 */
	public Double getFatIntake() {
		return fatIntake;
	}
	/**
	 * 设置：摄入碳水化合物/g
	 */
	public void setCarbohydrateIntake(Double carbohydrateIntake) {
		this.carbohydrateIntake = carbohydrateIntake;
	}
	/**
	 * 获取：摄入碳水化合物/g
	 */
	public Double getCarbohydrateIntake() {
		return carbohydrateIntake;
	}
	/**
	 * 设置：摄入膳食纤维/g
	 */
	public void setDietaryFiberIntake(Integer dietaryFiberIntake) {
		this.dietaryFiberIntake = dietaryFiberIntake;
	}
	/**
	 * 获取：摄入膳食纤维/g
	 */
	public Integer getDietaryFiberIntake() {
		return dietaryFiberIntake;
	}
	/**
	 * 设置：维生素
	 */
	public void setVitamin(String vitamin) {
		this.vitamin = vitamin;
	}
	/**
	 * 获取：维生素
	 */
	public String getVitamin() {
		return vitamin;
	}
	/**
	 * 设置：记录时间
	 */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	/**
	 * 获取：记录时间
	 */
	public Date getRecordTime() {
		return recordTime;
	}
	/**
	 * 设置：饮食分析
	 */
	public void setDietAnalysis(String dietAnalysis) {
		this.dietAnalysis = dietAnalysis;
	}
	/**
	 * 获取：饮食分析
	 */
	public String getDietAnalysis() {
		return dietAnalysis;
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
	 * 设置：图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：图片
	 */
	public String getPicture() {
		return picture;
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
}
