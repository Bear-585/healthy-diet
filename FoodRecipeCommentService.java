package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FoodRecipeCommentEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.FoodRecipeCommentVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.FoodRecipeCommentView;


/**
 * 美食食谱评论
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:44
 */
public interface FoodRecipeCommentService extends IService<FoodRecipeCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FoodRecipeCommentVO> selectListVO(Wrapper<FoodRecipeCommentEntity> wrapper);
   	
   	FoodRecipeCommentVO selectVO(@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);
   	
   	List<FoodRecipeCommentView> selectListView(Wrapper<FoodRecipeCommentEntity> wrapper);
   	
   	FoodRecipeCommentView selectView(@Param("ew") Wrapper<FoodRecipeCommentEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FoodRecipeCommentEntity> wrapper);

   	

}

