package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DietRecordEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DietRecordVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DietRecordView;


/**
 * 饮食记录
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface DietRecordService extends IService<DietRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DietRecordVO> selectListVO(Wrapper<DietRecordEntity> wrapper);
   	
   	DietRecordVO selectVO(@Param("ew") Wrapper<DietRecordEntity> wrapper);
   	
   	List<DietRecordView> selectListView(Wrapper<DietRecordEntity> wrapper);
   	
   	DietRecordView selectView(@Param("ew") Wrapper<DietRecordEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DietRecordEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<DietRecordEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<DietRecordEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<DietRecordEntity> wrapper);



}

