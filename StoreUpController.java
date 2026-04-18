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

import com.entity.StoreUpEntity;
import com.entity.view.StoreUpView;

import com.service.StoreUpService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 收藏表
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@RestController
@RequestMapping("/storeUp")
public class StoreUpController {
    @Autowired
    private StoreUpService storeUpService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,StoreUpEntity storeUp,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            storeUp.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        //设置查询条件
        EntityWrapper<StoreUpEntity> ew = new EntityWrapper<StoreUpEntity>();


        //查询结果
		PageUtils page = storeUpService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeUp), params), params));
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
    public R list(@RequestParam Map<String, Object> params,StoreUpEntity storeUp,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<StoreUpEntity> ew = new EntityWrapper<StoreUpEntity>();

        //查询结果
		PageUtils page = storeUpService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeUp), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( StoreUpEntity storeUp){
       	EntityWrapper<StoreUpEntity> ew = new EntityWrapper<StoreUpEntity>();
      	ew.allEq(MPUtil.allEQMapPre( storeUp, "storeUp"));
        return R.ok().put("data", storeUpService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(StoreUpEntity storeUp){
        EntityWrapper< StoreUpEntity> ew = new EntityWrapper< StoreUpEntity>();
 		ew.allEq(MPUtil.allEQMapPre( storeUp, "storeUp"));
		StoreUpView storeUpView =  storeUpService.selectView(ew);
		return R.ok("查询收藏表成功").put("data", storeUpView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        StoreUpEntity storeUp = storeUpService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(storeUp,deSens);
        return R.ok().put("data", storeUp);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        StoreUpEntity storeUp = storeUpService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(storeUp,deSens);
        return R.ok().put("data", storeUp);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody StoreUpEntity storeUp, HttpServletRequest request){
        //ValidatorUtils.validateEntity(storeUp);
        if(storeUp.getUserid()==null){
            storeUp.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        storeUpService.insert(storeUp);
        return R.ok().put("data",storeUp.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody StoreUpEntity storeUp, HttpServletRequest request){
        //ValidatorUtils.validateEntity(storeUp);
        storeUpService.insert(storeUp);
        return R.ok().put("data",storeUp.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        StoreUpEntity storeUp = storeUpService.selectOne(new EntityWrapper<StoreUpEntity>().eq("", username));
        return R.ok().put("data", storeUp);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody StoreUpEntity storeUp, HttpServletRequest request){
        //ValidatorUtils.validateEntity(storeUp);
        //全部更新
        storeUpService.updateById(storeUp);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        storeUpService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,StoreUpEntity storeUp, HttpServletRequest request,String pre){
        EntityWrapper<StoreUpEntity> ew = new EntityWrapper<StoreUpEntity>();
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

		PageUtils page = storeUpService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, storeUp), params), params));
        return R.ok().put("data", page);
    }









}
