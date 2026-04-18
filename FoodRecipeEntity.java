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
 * 美食食谱
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@TableName("food_recipe")
public class FoodRecipeEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public FoodRecipeEntity() {
		
	}
	
	public FoodRecipeEntity(T t) {
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
	 * 食品名称
	 */
					
	private String foodName;
	
	/**
	 * 食品封面
	 */
					
	private String foodCover;
	
	/**
	 * 食品分类
	 */
					
	private String foodCategory;
	
	/**
	 * 热量
	 */
					
	private String calories;
	
	/**
	 * 蛋白质
	 */
					
	private String protein;
	
	/**
	 * 脂肪量
	 */
					
	private String fat;
	
	/**
	 * 营养价值
	 */
					
	private String nutritionalValue;
	
	/**
	 * 所需食材
	 */
					
	private String requiredIngredients;
	
	/**
	 * 美食教学
	 */
					
	private String cookingGuide;
	
	/**
	 * 制作方法
	 */
					
	private String preparationMethod;
	
	/**
	 * 菜品详情
	 */
					
	private String dishDetails;
	
	/**
	 * 最近点击时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date lastClickTime;
	
	/**
	 * 点击次数
	 */
					
	private Integer clickNum;
	
	/**
	 * 评论数
	 */
					
	private Integer discussNum;
	
	/**
	 * 收藏数
	 */
					
	private Integer storeUpNum;
	
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
	 * 设置：食品名称
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	/**
	 * 获取：食品名称
	 */
	public String getFoodName() {
		return foodName;
	}
	/**
	 * 设置：食品封面
	 */
	public void setFoodCover(String foodCover) {
		this.foodCover = foodCover;
	}
	/**
	 * 获取：食品封面
	 */
	public String getFoodCover() {
		return foodCover;
	}
	/**
	 * 设置：食品分类
	 */
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	/**
	 * 获取：食品分类
	 */
	public String getFoodCategory() {
		return foodCategory;
	}
	/**
	 * 设置：热量
	 */
	public void setCalories(String calories) {
		this.calories = calories;
	}
	/**
	 * 获取：热量
	 */
	public String getCalories() {
		return calories;
	}
	/**
	 * 设置：蛋白质
	 */
	public void setProtein(String protein) {
		this.protein = protein;
	}
	/**
	 * 获取：蛋白质
	 */
	public String getProtein() {
		return protein;
	}
	/**
	 * 设置：脂肪量
	 */
	public void setFat(String fat) {
		this.fat = fat;
	}
	/**
	 * 获取：脂肪量
	 */
	public String getFat() {
		return fat;
	}
	/**
	 * 设置：营养价值
	 */
	public void setNutritionalValue(String nutritionalValue) {
		this.nutritionalValue = nutritionalValue;
	}
	/**
	 * 获取：营养价值
	 */
	public String getNutritionalValue() {
		return nutritionalValue;
	}
	/**
	 * 设置：所需食材
	 */
	public void setRequiredIngredients(String requiredIngredients) {
		this.requiredIngredients = requiredIngredients;
	}
	/**
	 * 获取：所需食材
	 */
	public String getRequiredIngredients() {
		return requiredIngredients;
	}
	/**
	 * 设置：美食教学
	 */
	public void setCookingGuide(String cookingGuide) {
		this.cookingGuide = cookingGuide;
	}
	/**
	 * 获取：美食教学
	 */
	public String getCookingGuide() {
		return cookingGuide;
	}
	/**
	 * 设置：制作方法
	 */
	public void setPreparationMethod(String preparationMethod) {
		this.preparationMethod = preparationMethod;
	}
	/**
	 * 获取：制作方法
	 */
	public String getPreparationMethod() {
		return preparationMethod;
	}
	/**
	 * 设置：菜品详情
	 */
	public void setDishDetails(String dishDetails) {
		this.dishDetails = dishDetails;
	}
	/**
	 * 获取：菜品详情
	 */
	public String getDishDetails() {
		return dishDetails;
	}
	/**
	 * 设置：最近点击时间
	 */
	public void setLastClickTime(Date lastClickTime) {
		this.lastClickTime = lastClickTime;
	}
	/**
	 * 获取：最近点击时间
	 */
	public Date getLastClickTime() {
		return lastClickTime;
	}
	/**
	 * 设置：点击次数
	 */
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	/**
	 * 获取：点击次数
	 */
	public Integer getClickNum() {
		return clickNum;
	}
	/**
	 * 设置：评论数
	 */
	public void setDiscussNum(Integer discussNum) {
		this.discussNum = discussNum;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussNum() {
		return discussNum;
	}
	/**
	 * 设置：收藏数
	 */
	public void setStoreUpNum(Integer storeUpNum) {
		this.storeUpNum = storeUpNum;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreUpNum() {
		return storeUpNum;
	}
}
