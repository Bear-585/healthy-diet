package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FoodRecipeEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.FoodRecipeVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.FoodRecipeView;


/**
 * 美食食谱
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface FoodRecipeService extends IService<FoodRecipeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FoodRecipeVO> selectListVO(Wrapper<FoodRecipeEntity> wrapper);
   	
   	FoodRecipeVO selectVO(@Param("ew") Wrapper<FoodRecipeEntity> wrapper);
   	
   	List<FoodRecipeView> selectListView(Wrapper<FoodRecipeEntity> wrapper);
   	
   	FoodRecipeView selectView(@Param("ew") Wrapper<FoodRecipeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FoodRecipeEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<FoodRecipeEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<FoodRecipeEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<FoodRecipeEntity> wrapper);



}

