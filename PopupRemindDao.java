package com.dao;

import com.entity.PopupRemindEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.PopupRemindVO;
import com.entity.view.PopupRemindView;


/**
 * 弹窗提醒
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:43
 */
public interface PopupRemindDao extends BaseMapper<PopupRemindEntity> {
	
	List<PopupRemindVO> selectListVO(@Param("ew") Wrapper<PopupRemindEntity> wrapper);
	
	PopupRemindVO selectVO(@Param("ew") Wrapper<PopupRemindEntity> wrapper);
	
	List<PopupRemindView> selectListView(@Param("ew") Wrapper<PopupRemindEntity> wrapper);

	List<PopupRemindView> selectListView(Pagination page,@Param("ew") Wrapper<PopupRemindEntity> wrapper);

	
	PopupRemindView selectView(@Param("ew") Wrapper<PopupRemindEntity> wrapper);
	

}
