package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;

import cn.hutool.core.util.StrUtil;
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
import com.utils.UserBasedCollaborativeFiltering;
import com.algorithm.recommend.RecommendAlgorithmFactory;

import com.entity.FoodRecipeEntity;
import com.entity.view.FoodRecipeView;

import com.service.FoodRecipeService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreUpService;
import com.entity.StoreUpEntity;

/**
 * 美食食谱
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@RestController
@RequestMapping("/foodRecipe")
public class FoodRecipeController {
    @Autowired
    private FoodRecipeService foodRecipeService;

    @Autowired
    private StoreUpService storeUpService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FoodRecipeEntity foodRecipe,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();


        //查询结果
		PageUtils page = foodRecipeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipe), params), params));
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
    public R list(@RequestParam Map<String, Object> params,FoodRecipeEntity foodRecipe,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date lastClickTimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date lastClickTimeend,
                @RequestParam(required = false) Double clickNumstart,
                @RequestParam(required = false) Double clickNumend,
                @RequestParam(required = false) Double discussNumstart,
                @RequestParam(required = false) Double discussNumend,
                @RequestParam(required = false) Double storeUpNumstart,
                @RequestParam(required = false) Double storeUpNumend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
//        if(lastClickTimestart!=null) ew.ge("lastClickTime", lastClickTimestart);
//        if(lastClickTimeend!=null) ew.le("lastClickTime", lastClickTimeend);
        if(clickNumstart!=null) ew.ge("click_num", clickNumstart);
        if(clickNumend!=null) ew.le("click_num", clickNumend);
        if(discussNumstart!=null) ew.ge("discuss_num", discussNumstart);
        if(discussNumend!=null) ew.le("discuss_num", discussNumend);
        if(storeUpNumstart!=null) ew.ge("store_up_num", storeUpNumstart);
        if(storeUpNumend!=null) ew.le("store_up_num", storeUpNumend);

        //查询结果
		PageUtils page = foodRecipeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipe), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FoodRecipeEntity foodRecipe){
       	EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( foodRecipe, "foodRecipe"));
        return R.ok().put("data", foodRecipeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FoodRecipeEntity foodRecipe){
        EntityWrapper< FoodRecipeEntity> ew = new EntityWrapper< FoodRecipeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( foodRecipe, "foodRecipe"));
		FoodRecipeView foodRecipeView =  foodRecipeService.selectView(ew);
		return R.ok("查询美食食谱成功").put("data", foodRecipeView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FoodRecipeEntity foodRecipe = foodRecipeService.selectById(id);
        if(null==foodRecipe.getClickNum()){
            foodRecipe.setClickNum(0);
        }
		foodRecipe.setClickNum(foodRecipe.getClickNum()+1);
		foodRecipeService.updateById(foodRecipe);
        foodRecipe = foodRecipeService.selectView(new EntityWrapper<FoodRecipeEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(foodRecipe,deSens);
        return R.ok().put("data", foodRecipe);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FoodRecipeEntity foodRecipe = foodRecipeService.selectById(id);
        if(null==foodRecipe.getClickNum()){
            foodRecipe.setClickNum(0);
        }
		foodRecipe.setClickNum(foodRecipe.getClickNum()+1);
		foodRecipeService.updateById(foodRecipe);
        foodRecipe = foodRecipeService.selectView(new EntityWrapper<FoodRecipeEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(foodRecipe,deSens);
        return R.ok().put("data", foodRecipe);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FoodRecipeEntity foodRecipe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodRecipe);
        foodRecipeService.insert(foodRecipe);
        return R.ok().put("data",foodRecipe.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FoodRecipeEntity foodRecipe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodRecipe);
        foodRecipeService.insert(foodRecipe);
        return R.ok().put("data",foodRecipe.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FoodRecipeEntity foodRecipe, HttpServletRequest request){
        //ValidatorUtils.validateEntity(foodRecipe);
        //全部更新
        foodRecipeService.updateById(foodRecipe);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        foodRecipeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,FoodRecipeEntity foodRecipe, HttpServletRequest request,String pre){
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
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
		params.put("sort", "click_num");
        params.put("order", "desc");

		PageUtils page = foodRecipeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipe), params), params));
        return R.ok().put("data", page);
    }



    /**
     * 协同算法（基于用户的协同算法：购买、收藏、关注）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,FoodRecipeEntity foodRecipe, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        //用户行为数据
        List<RecommendAlgorithmFactory.UserBehavior> userBehaviors = new ArrayList<>();
        // 查询收藏/关注数据
        userBehaviors.addAll(storeUpService.selectList(new EntityWrapper<StoreUpEntity>().eq("type", 1 ).eq("table_name", "foodRecipe")).stream().map(storeUp -> {
            return new RecommendAlgorithmFactory.UserBehavior(storeUp.getUserid(), storeUp.getRefId());
        }).collect(Collectors.toList()));
        // 根据物品推荐用户
        // 用协 算法推荐物品id
        List<Long> recommendations = RecommendAlgorithmFactory.buildUserItemMatrix(userBehaviors).recommendItems(Long.parseLong(userId), limit);
        // 输出推荐结果
        System.out.println("Recommendations for " + userId + ":");
        recommendations.forEach(recommendation -> System.out.println(recommendation));
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();

        if(recommendations!=null && recommendations.size()>0) {
            ew.in("id", recommendations);
            ew.last("order by FIELD(id, "+String.join(",", recommendations.stream().map(String::valueOf).collect(Collectors.toList()))+")");
        }
        // 根据协同结果查询结果并返回
        PageUtils page = foodRecipeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipe), params), params));
        List<FoodRecipeEntity> pageList = (List<FoodRecipeEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<FoodRecipeEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(foodRecipeService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);
        return R.ok().put("data", page);
    }



    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        yColumnName = convertToUnderscoreStyle(yColumnName);
        xColumnName = convertToUnderscoreStyle(xColumnName);

        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_foodRecipe_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("method", func);
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? xColumnName : yColumnName, order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }

        //获取结果
        List<Map<String, Object>> result = foodRecipeService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            Map<String, Object> tempMap = new HashMap<>(m);
            m.clear();
            for(Map.Entry<String, Object> entry : tempMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof Date) {
                    value = sdf.format((Date)value);
                }
                if(key.contains("_")) {
                    key = StrUtil.toCamelCase(key);
                }
                m.put(key, value);
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request)  throws IOException {
        xColumnName = convertToUnderscoreStyle(xColumnName);

        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_foodRecipe_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        //构建查询统计条件
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? Arrays.asList(xColumnName) : Arrays.asList(yColumnNames), order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", convertToUnderscoreStyle(yColumnNames[i]));
            List<Map<String, Object>> result = foodRecipeService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                Map<String, Object> tempMap = new HashMap<>(m);
                m.clear();
                for(Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if(value instanceof Date) {
                        value = sdf.format((Date)value);
                    }
                    if(key.contains("_")) {
                        key = StrUtil.toCamelCase(key);
                    }
                    m.put(key, value);
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        yColumnName = convertToUnderscoreStyle(yColumnName);
        xColumnName = convertToUnderscoreStyle(xColumnName);

        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_foodRecipe_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        params.put("method", func);
        //构建查询统计条件
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? xColumnName : yColumnName, order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<Map<String, Object>> result = foodRecipeService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            Map<String, Object> tempMap = new HashMap<>(m);
            m.clear();
            for(Map.Entry<String, Object> entry : tempMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof Date) {
                    value = sdf.format((Date)value);
                }
                if(key.contains("_")) {
                    key = StrUtil.toCamelCase(key);
                }
                m.put(key, value);
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType, @RequestParam String yColumnNameMul, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request) throws IOException {
        xColumnName = convertToUnderscoreStyle(xColumnName);

        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_foodRecipe_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
        String order = request.getParameter("order");
        if (StringUtils.isNotBlank(order)) {
            String orderType = request.getParameter("orderType");
            if (null != orderType) {
                ew.orderBy(true, orderType.equals("x") ? Arrays.asList(xColumnName) : Arrays.asList(yColumnNames), order.equals("desc"));
            }
        }
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", convertToUnderscoreStyle(yColumnNames[i]));
            List<Map<String, Object>> result = foodRecipeService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                Map<String, Object> tempMap = new HashMap<>(m);
                m.clear();
                for(Map.Entry<String, Object> entry : tempMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if(value instanceof Date) {
                        value = sdf.format((Date)value);
                    }
                    if(key.contains("_")) {
                        key = StrUtil.toCamelCase(key);
                    }
                    m.put(key, value);
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, HttpServletRequest request) throws IOException {
        columnName = convertToUnderscoreStyle(columnName);

        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_foodRecipe_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
        if(StringUtils.isNotBlank(conditionColumn)&&StringUtils.isNotBlank(conditionValue))
        {
            String[] conditionColumns = conditionColumn.split(";");
            String[] conditionValues = conditionValue.split(";");

            for (int i = 0; i < conditionColumns.length; i++) {
                String column = conditionColumns[i];
                String value = conditionValues[i];

                // 处理范围查询：如果列名包含逗号，表示是范围查询
                if (column.contains(",")) {
                    String[] rangeColumns = column.split(",");
                    String[] rangeValues = value.split(",");

                    if (rangeColumns.length == 2 && rangeValues.length == 2) {
                        // 第一个列名使用 >= 条件
                        ew.ge(rangeColumns[0], rangeValues[0]);
                        // 第二个列名使用 <= 条件
                        ew.le(rangeColumns[1], rangeValues[1]);
                    }
                } else {
                    // 普通等值查询
                    ew.eq(column, value);
                }
            }
        }
        List<Map<String, Object>> result = foodRecipeService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            Map<String, Object> tempMap = new HashMap<>(m);
            m.clear();
            for(Map.Entry<String, Object> entry : tempMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(value instanceof Date) {
                    value = sdf.format((Date)value);
                }
                if(key.contains("_")) {
                    key = StrUtil.toCamelCase(key);
                }
                m.put(key, value);
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,FoodRecipeEntity foodRecipe, HttpServletRequest request){
        EntityWrapper<FoodRecipeEntity> ew = new EntityWrapper<FoodRecipeEntity>();
        int count = foodRecipeService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, foodRecipe), params), params));
        return R.ok().put("data", count);
    }



    /**
     * 将驼峰命名转换为下划线命名
     * @param camelCase 驼峰命名字符串
     * @return 下划线命名字符串
     */
    private String convertToUnderscoreStyle(String camelCase) {
        if (camelCase == null || camelCase.isEmpty()) {
            return camelCase;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    result.append('_');
                }
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
