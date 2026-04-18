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

import com.entity.FoodRecipeCommentEntity;
import com.entity.view.FoodRecipeCommentView;

import com.service.FoodRecipeCommentService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 美食食谱评论
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-13 19:37:44
 */
@RestController
@RequestMapping("/foodRecipeComment")
public class FoodRecipeCommentController {
    @Autowired
    private FoodRecipeCommentService foodRecipeCommentService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FoodRecipeCommentEntity foodRecipeComment,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FoodRecipeCommentEntity> ew = new EntityWrapper<FoodRecipeCommentEntity>();


        //查询结果
		PageUtils page = foodRecipeCommentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipeComment), params), params));
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
    public R list(@RequestParam Map<String, Object> params,FoodRecipeCommentEntity foodRecipeComment,
                @RequestParam(required = false) Double thumbsUpNumstart,
                @RequestParam(required = false) Double thumbsUpNumend,
                @RequestParam(required = false) Double crazilyNumstart,
                @RequestParam(required = false) Double crazilyNumend,
                @RequestParam(required = false) Double isTopstart,
                @RequestParam(required = false) Double isTopend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FoodRecipeCommentEntity> ew = new EntityWrapper<FoodRecipeCommentEntity>();
        if(thumbsUpNumstart!=null) ew.ge("thumbs_up_num", thumbsUpNumstart);
        if(thumbsUpNumend!=null) ew.le("thumbs_up_num", thumbsUpNumend);
        if(crazilyNumstart!=null) ew.ge("crazily_num", crazilyNumstart);
        if(crazilyNumend!=null) ew.le("crazily_num", crazilyNumend);
        if(isTopstart!=null) ew.ge("is_top", isTopstart);
        if(isTopend!=null) ew.le("is_top", isTopend);

        //查询结果
		PageUtils page = foodRecipeCommentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipeComment), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FoodRecipeCommentEntity foodRecipeComment){
       	EntityWrapper<FoodRecipeCommentEntity> ew = new EntityWrapper<FoodRecipeCommentEntity>();
      	ew.allEq(MPUtil.allEQMapPre( foodRecipeComment, "foodRecipeComment"));
        return R.ok().put("data", foodRecipeCommentService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FoodRecipeCommentEntity foodRecipeComment){
        EntityWrapper< FoodRecipeCommentEntity> ew = new EntityWrapper< FoodRecipeCommentEntity>();
 		ew.allEq(MPUtil.allEQMapPre( foodRecipeComment, "foodRecipeComment"));
		FoodRecipeCommentView foodRecipeCommentView =  foodRecipeCommentService.selectView(ew);
		return R.ok("查询美食食谱评论成功").put("data", foodRecipeCommentView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FoodRecipeCommentEntity foodRecipeComment = foodRecipeCommentService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(foodRecipeComment,deSens);
        return R.ok().put("data", foodRecipeComment);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FoodRecipeCommentEntity foodRecipeComment = foodRecipeCommentService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(foodRecipeComment,deSens);
        return R.ok().put("data", foodRecipeComment);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodRecipeCommentEntity foodRecipeComment, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodRecipeComment);
        foodRecipeCommentService.insert(foodRecipeComment);
        return R.ok().put("data",foodRecipeComment.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FoodRecipeCommentEntity foodRecipeComment, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodRecipeComment);
        foodRecipeCommentService.insert(foodRecipeComment);
        return R.ok().put("data",foodRecipeComment.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        FoodRecipeCommentEntity foodRecipeComment = foodRecipeCommentService.selectOne(new EntityWrapper<FoodRecipeCommentEntity>().eq("", username));
        return R.ok().put("data", foodRecipeComment);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody FoodRecipeCommentEntity foodRecipeComment, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodRecipeComment);
        //全部更新
        foodRecipeCommentService.updateById(foodRecipeComment);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        foodRecipeCommentService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,FoodRecipeCommentEntity foodRecipeComment, HttpServletRequest request,String pre){
        EntityWrapper<FoodRecipeCommentEntity> ew = new EntityWrapper<FoodRecipeCommentEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "last_click_time");
        params.put("order", "desc");

		PageUtils page = foodRecipeCommentService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipeComment), params), params));
        return R.ok().put("data", page);
    }









}
