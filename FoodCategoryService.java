package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FoodCategoryEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.FoodCategoryVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.FoodCategoryView;


/**
 * 食品分类
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:42
 */
public interface FoodCategoryService extends IService<FoodCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FoodCategoryVO> selectListVO(Wrapper<FoodCategoryEntity> wrapper);
   	
   	FoodCategoryVO selectVO(@Param("ew") Wrapper<FoodCategoryEntity> wrapper);
   	
   	List<FoodCategoryView> selectListView(Wrapper<FoodCategoryEntity> wrapper);
   	
   	FoodCategoryView selectView(@Param("ew") Wrapper<FoodCategoryEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FoodCategoryEntity> wrapper);

   	

}

