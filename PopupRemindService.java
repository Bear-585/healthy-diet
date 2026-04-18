package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PopupRemindEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PopupRemindVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PopupRemindView;


/**
 * 弹窗提醒
 *
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface PopupRemindService extends IService<PopupRemindEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PopupRemindVO> selectListVO(Wrapper<PopupRemindEntity> wrapper);
   	
   	PopupRemindVO selectVO(@Param("ew") Wrapper<PopupRemindEntity> wrapper);
   	
   	List<PopupRemindView> selectListView(Wrapper<PopupRemindEntity> wrapper);
   	
   	PopupRemindView selectView(@Param("ew") Wrapper<PopupRemindEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PopupRemindEntity> wrapper);

   	

}

