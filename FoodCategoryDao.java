package com.dao;

import com.entity.FoodCategoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FoodCategoryVO;
import com.entity.view.FoodCategoryView;


/**
 * 食品分类
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:42
 */
public interface FoodCategoryDao extends BaseMapper<FoodCategoryEntity> {
	
	List<FoodCategoryVO> selectListVO(@Param("ew") Wrapper<FoodCategoryEntity> wrapper);
	
	FoodCategoryVO selectVO(@Param("ew") Wrapper<FoodCategoryEntity> wrapper);
	
	List<FoodCategoryView> selectListView(@Param("ew") Wrapper<FoodCategoryEntity> wrapper);

	List<FoodCategoryView> selectListView(Pagination page,@Param("ew") Wrapper<FoodCategoryEntity> wrapper);

	
	FoodCategoryView selectView(@Param("ew") Wrapper<FoodCategoryEntity> wrapper);
	

}
