package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.utils.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.FoodCategoryEntity;
import com.entity.view.FoodCategoryView;

import com.service.FoodCategoryService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 食品分类
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-13 19:37:42
 */
@RestController
@RequestMapping("/foodCategory")
public class FoodCategoryController {
    @Autowired
    private FoodCategoryService foodCategoryService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FoodCategoryEntity foodCategory,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FoodCategoryEntity> ew = new EntityWrapper<FoodCategoryEntity>();


        //查询结果
		PageUtils page = foodCategoryService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodCategory), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FoodCategoryEntity foodCategory,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FoodCategoryEntity> ew = new EntityWrapper<FoodCategoryEntity>();

        //查询结果
		PageUtils page = foodCategoryService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodCategory), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FoodCategoryEntity foodCategory){
       	EntityWrapper<FoodCategoryEntity> ew = new EntityWrapper<FoodCategoryEntity>();
      	ew.allEq(MPUtil.allEQMapPre( foodCategory, "foodCategory"));
        return R.ok().put("data", foodCategoryService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FoodCategoryEntity foodCategory){
        EntityWrapper< FoodCategoryEntity> ew = new EntityWrapper< FoodCategoryEntity>();
 		ew.allEq(MPUtil.allEQMapPre( foodCategory, "foodCategory"));
		FoodCategoryView foodCategoryView =  foodCategoryService.selectView(ew);
		return R.ok("查询食品分类成功").put("data", foodCategoryView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FoodCategoryEntity foodCategory = foodCategoryService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(foodCategory,deSens);
        return R.ok().put("data", foodCategory);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FoodCategoryEntity foodCategory = foodCategoryService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(foodCategory,deSens);
        return R.ok().put("data", foodCategory);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodCategoryEntity foodCategory, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(foodCategoryService.selectCount(new EntityWrapper<FoodCategoryEntity>().eq("food_category", foodCategory.getFoodCategory()))>0) {
            return R.error("食品分类已存在");
        }
        //ValidatorUtils.validateEntity(foodCategory);
        foodCategoryService.insert(foodCategory);
        return R.ok().put("data",foodCategory.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FoodCategoryEntity foodCategory, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(foodCategoryService.selectCount(new EntityWrapper<FoodCategoryEntity>().eq("food_category", foodCategory.getFoodCategory()))>0) {
            return R.error("食品分类已存在");
        }
        //ValidatorUtils.validateEntity(foodCategory);
        foodCategoryService.insert(foodCategory);
        return R.ok().put("data",foodCategory.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FoodCategoryEntity foodCategory, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodCategory);
        //验证字段唯一性，否则返回错误信息
        if(foodCategoryService.selectCount(new EntityWrapper<FoodCategoryEntity>().ne("id", foodCategory.getId()).eq("food_category", foodCategory.getFoodCategory()))>0) {
            return R.error("食品分类已存在");
        }
        //全部更新
        foodCategoryService.updateById(foodCategory);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        foodCategoryService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
