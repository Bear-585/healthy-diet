package com.dao;

import com.entity.FoodRecipeCommentEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FoodRecipeCommentVO;
import com.entity.view.FoodRecipeCommentView;


/**
 * 美食食谱评论
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:44
 */
public interface FoodRecipeCommentDao extends BaseMapper<FoodRecipeCommentEntity> {
	
	List<FoodRecipeCommentVO> selectListVO(@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);
	
	FoodRecipeCommentVO selectVO(@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);
	
	List<FoodRecipeCommentView> selectListView(@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);

	List<FoodRecipeCommentView> selectListView(Pagination page,@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);

	
	FoodRecipeCommentView selectView(@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);
	

}
