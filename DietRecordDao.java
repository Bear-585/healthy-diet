package com.dao;

import com.entity.DietRecordEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DietRecordVO;
import com.entity.view.DietRecordView;


/**
 * 饮食记录
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface DietRecordDao extends BaseMapper<DietRecordEntity> {
	
	List<DietRecordVO> selectListVO(@Param("ew") Wrapper<DietRecordEntity> wrapper);
	
	DietRecordVO selectVO(@Param("ew") Wrapper<DietRecordEntity> wrapper);
	
	List<DietRecordView> selectListView(@Param("ew") Wrapper<DietRecordEntity> wrapper);

	List<DietRecordView> selectListView(Pagination page,@Param("ew") Wrapper<DietRecordEntity> wrapper);

	
	DietRecordView selectView(@Param("ew") Wrapper<DietRecordEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<DietRecordEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<DietRecordEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<DietRecordEntity> wrapper);



}
