package com.dao;

import com.entity.StoreUpEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.StoreUpVO;
import com.entity.view.StoreUpView;


/**
 * 收藏表
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface StoreUpDao extends BaseMapper<StoreUpEntity> {
	
	List<StoreUpVO> selectListVO(@Param("ew") Wrapper<StoreUpEntity> wrapper);
	
	StoreUpVO selectVO(@Param("ew") Wrapper<StoreUpEntity> wrapper);
	
	List<StoreUpView> selectListView(@Param("ew") Wrapper<StoreUpEntity> wrapper);

	List<StoreUpView> selectListView(Pagination page,@Param("ew") Wrapper<StoreUpEntity> wrapper);

	
	StoreUpView selectView(@Param("ew") Wrapper<StoreUpEntity> wrapper);
	

}
