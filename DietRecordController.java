package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

import cn.hutool.core.util.StrUtil;
import com.utils.*;
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
import com.annotation.IgnoreAuth;

import com.entity.DietRecordEntity;
import com.entity.view.DietRecordView;

import com.service.DietRecordService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import java.io.IOException;

/**
 * 饮食记录
 * 后端接口
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
@RestController
@RequestMapping("/dietRecord")
public class DietRecordController {
    @Autowired
    private DietRecordService dietRecordService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DietRecordEntity dietRecord,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date recordTimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date recordTimeend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("consumer")) {
			dietRecord.setAccount((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
        if(recordTimestart!=null) ew.ge("record_time", recordTimestart);
        if(recordTimeend!=null) ew.le("record_time", recordTimeend);


        //查询结果
		PageUtils page = dietRecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dietRecord), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DietRecordEntity dietRecord,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date mealTimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date mealTimeend,
                @RequestParam(required = false) Double caloriesstart,
                @RequestParam(required = false) Double caloriesend,
                @RequestParam(required = false) Double proteinstart,
                @RequestParam(required = false) Double proteinend,
                @RequestParam(required = false) Double fatIntakestart,
                @RequestParam(required = false) Double fatIntakeend,
                @RequestParam(required = false) Double carbohydrateIntakestart,
                @RequestParam(required = false) Double carbohydrateIntakeend,
                @RequestParam(required = false) Double dietaryFiberIntakestart,
                @RequestParam(required = false) Double dietaryFiberIntakeend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date recordTimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date recordTimeend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("consumer")) {
			dietRecord.setAccount((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
        if(mealTimestart!=null) ew.ge("meal_time", mealTimestart);
        if(mealTimeend!=null) ew.le("meal_time", mealTimeend);
        if(caloriesstart!=null) ew.ge("calories", caloriesstart);
        if(caloriesend!=null) ew.le("calories", caloriesend);
        if(proteinstart!=null) ew.ge("protein", proteinstart);
        if(proteinend!=null) ew.le("protein", proteinend);
        if(fatIntakestart!=null) ew.ge("fat_intake", fatIntakestart);
        if(fatIntakeend!=null) ew.le("fat_intake", fatIntakeend);
        if(carbohydrateIntakestart!=null) ew.ge("carbohydrate_intake", carbohydrateIntakestart);
        if(carbohydrateIntakeend!=null) ew.le("carbohydrate_intake", carbohydrateIntakeend);
        if(dietaryFiberIntakestart!=null) ew.ge("dietary_fiber_intake", dietaryFiberIntakestart);
        if(dietaryFiberIntakeend!=null) ew.le("dietary_fiber_intake", dietaryFiberIntakeend);
        if(recordTimestart!=null) ew.ge("record_time", recordTimestart);
        if(recordTimeend!=null) ew.le("record_time", recordTimeend);

        //查询结果
		PageUtils page = dietRecordService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dietRecord), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DietRecordEntity dietRecord){
       	EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dietRecord, "dietRecord"));
        return R.ok().put("data", dietRecordService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DietRecordEntity dietRecord){
        EntityWrapper< DietRecordEntity> ew = new EntityWrapper< DietRecordEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dietRecord, "dietRecord"));
		DietRecordView dietRecordView =  dietRecordService.selectView(ew);
		return R.ok("查询饮食记录成功").put("data", dietRecordView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DietRecordEntity dietRecord = dietRecordService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(dietRecord,deSens);
        return R.ok().put("data", dietRecord);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DietRecordEntity dietRecord = dietRecordService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(dietRecord,deSens);
        return R.ok().put("data", dietRecord);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DietRecordEntity dietRecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dietRecord);
        dietRecordService.insert(dietRecord);
        return R.ok().put("data",dietRecord.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DietRecordEntity dietRecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dietRecord);
    	dietRecord.setUserid((Long)request.getSession().getAttribute("userId"));
        dietRecordService.insert(dietRecord);
        return R.ok().put("data",dietRecord.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DietRecordEntity dietRecord, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dietRecord);
        //全部更新
        dietRecordService.updateById(dietRecord);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dietRecordService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }







    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @RequestParam(required = false) String conditionColumn, @RequestParam(required = false) String conditionValue, @RequestParam(required = false, defaultValue = "总和") String func, HttpServletRequest request) throws IOException {
        yColumnName = convertToUnderscoreStyle(yColumnName);
        xColumnName = convertToUnderscoreStyle(xColumnName);

        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_dietRecord_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("method", func);
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
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
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("consumer")) {
            ew.eq("account", (String)request.getSession().getAttribute("username"));
        }

        //获取结果
        List<Map<String, Object>> result = dietRecordService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_dietRecord_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        //构建查询统计条件
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
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

        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("consumer")) {
            ew.eq("account", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", convertToUnderscoreStyle(yColumnNames[i]));
            List<Map<String, Object>> result = dietRecordService.selectValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_dietRecord_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
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
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
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
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("consumer")) {
            ew.eq("account", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = dietRecordService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("value_dietRecord_" + xColumnName + "_" + String.join("_", yColumnNameMul.split(",")) + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
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
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("consumer")) {
            ew.eq("account", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", convertToUnderscoreStyle(yColumnNames[i]));
            List<Map<String, Object>> result = dietRecordService.selectTimeStatValue(params, ew);
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
        java.nio.file.Path path = java.nio.file.Paths.get("group_dietRecord_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
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
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("consumer")) {
            ew.eq("account", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = dietRecordService.selectGroup(params, ew);
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
    public R count(@RequestParam Map<String, Object> params,DietRecordEntity dietRecord, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("consumer")) {
            dietRecord.setAccount((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<DietRecordEntity> ew = new EntityWrapper<DietRecordEntity>();
        int count = dietRecordService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dietRecord), params), params));
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
