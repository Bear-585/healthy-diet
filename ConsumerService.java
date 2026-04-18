package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ConsumerEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ConsumerVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ConsumerView;


/**
 * 用户
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:42
 */
public interface ConsumerService extends IService<ConsumerEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ConsumerVO> selectListVO(Wrapper<ConsumerEntity> wrapper);
   	
   	ConsumerVO selectVO(@Param("ew") Wrapper<ConsumerEntity> wrapper);
   	
   	List<ConsumerView> selectListView(Wrapper<ConsumerEntity> wrapper);
   	
   	ConsumerView selectView(@Param("ew") Wrapper<ConsumerEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ConsumerEntity> wrapper);

   	

}

