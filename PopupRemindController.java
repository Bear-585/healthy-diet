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

import com.entity.PopupRemindEntity;
import com.entity.view.PopupRemindView;

import com.service.PopupRemindService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 弹窗提醒
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@RestController
@RequestMapping("/popupRemind")
public class PopupRemindController {
    @Autowired
    private PopupRemindService popupRemindService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PopupRemindEntity popupRemind,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindTimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindTimeend,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            popupRemind.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        //设置查询条件
        EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
        if(remindTimestart!=null) ew.ge("remind_time", remindTimestart);
        if(remindTimeend!=null) ew.le("remind_time", remindTimeend);


        //查询结果
		PageUtils page = popupRemindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupRemind), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    @RequestMapping("/message/list")
    public R messageList(@RequestParam Map<String, Object> params,PopupRemindEntity popupRemind,HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
        Wrapper wrapper = MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupRemind), params), params);
        wrapper.andNew().eq("type", "全局");
        if (!request.getSession().getAttribute("role").toString().equals("管理员")) {
            wrapper.or().eq("userid", (Long) request.getSession().getAttribute("userId"));
        }
        return R.ok().put("data", popupRemindService.queryPage(params, wrapper));
    }

    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PopupRemindEntity popupRemind,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindTimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindTimeend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
        if(remindTimestart!=null) ew.ge("remind_time", remindTimestart);
        if(remindTimeend!=null) ew.le("remind_time", remindTimeend);

        //查询结果
		PageUtils page = popupRemindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupRemind), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PopupRemindEntity popupRemind){
       	EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
      	ew.allEq(MPUtil.allEQMapPre( popupRemind, "popupRemind"));
        return R.ok().put("data", popupRemindService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PopupRemindEntity popupRemind){
        EntityWrapper< PopupRemindEntity> ew = new EntityWrapper< PopupRemindEntity>();
 		ew.allEq(MPUtil.allEQMapPre( popupRemind, "popupRemind"));
		PopupRemindView popupRemindView =  popupRemindService.selectView(ew);
		return R.ok("查询弹窗提醒成功").put("data", popupRemindView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PopupRemindEntity popupRemind = popupRemindService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(popupRemind,deSens);
        return R.ok().put("data", popupRemind);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PopupRemindEntity popupRemind = popupRemindService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(popupRemind,deSens);
        return R.ok().put("data", popupRemind);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PopupRemindEntity popupRemind, HttpServletRequest request){
        //ValidatorUtils.validateEntity(popupRemind);
        if(popupRemind.getUserid()==null){
            popupRemind.setUserid((Long) request.getSession().getAttribute("userId"));
        }
        popupRemindService.insert(popupRemind);
        return R.ok().put("data",popupRemind.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PopupRemindEntity popupRemind, HttpServletRequest request){
        //ValidatorUtils.validateEntity(popupRemind);
        popupRemindService.insert(popupRemind);
        return R.ok().put("data",popupRemind.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        PopupRemindEntity popupRemind = popupRemindService.selectOne(new EntityWrapper<PopupRemindEntity>().eq("", username));
        return R.ok().put("data", popupRemind);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody PopupRemindEntity popupRemind, HttpServletRequest request){
        //ValidatorUtils.validateEntity(popupRemind);
        //全部更新
        popupRemindService.updateById(popupRemind);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        popupRemindService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PopupRemindEntity popupRemind, HttpServletRequest request,String pre){
        EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
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

		PageUtils page = popupRemindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupRemind), params), params));
        return R.ok().put("data", page);
    }









}
