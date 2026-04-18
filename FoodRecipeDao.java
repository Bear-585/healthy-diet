package com.dao;

import com.entity.FoodRecipeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FoodRecipeVO;
import com.entity.view.FoodRecipeView;


/**
 * 美食食谱
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface FoodRecipeDao extends BaseMapper<FoodRecipeEntity> {
	
	List<FoodRecipeVO> selectListVO(@Param("ew") Wrapper<FoodRecipeEntity> wrapper);
	
	FoodRecipeVO selectVO(@Param("ew") Wrapper<FoodRecipeEntity> wrapper);
	
	List<FoodRecipeView> selectListView(@Param("ew") Wrapper<FoodRecipeEntity> wrapper);

	List<FoodRecipeView> selectListView(Pagination page,@Param("ew") Wrapper<FoodRecipeEntity> wrapper);

	
	FoodRecipeView selectView(@Param("ew") Wrapper<FoodRecipeEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<FoodRecipeEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<FoodRecipeEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<FoodRecipeEntity> wrapper);



}
