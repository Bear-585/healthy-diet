package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.StoreUpEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.StoreUpVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.StoreUpView;


/**
 * 收藏表
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface StoreUpService extends IService<StoreUpEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<StoreUpVO> selectListVO(Wrapper<StoreUpEntity> wrapper);
   	
   	StoreUpVO selectVO(@Param("ew") Wrapper<StoreUpEntity> wrapper);
   	
   	List<StoreUpView> selectListView(Wrapper<StoreUpEntity> wrapper);
   	
   	StoreUpView selectView(@Param("ew") Wrapper<StoreUpEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<StoreUpEntity> wrapper);

   	

}

