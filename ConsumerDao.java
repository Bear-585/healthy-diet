package com.dao;

import com.entity.ConsumerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ConsumerVO;
import com.entity.view.ConsumerView;


/**
 * 用户
 * 
 * @author 
 * @email 
 * @date 2026-02-13 19:37:42
 */
public interface ConsumerDao extends BaseMapper<ConsumerEntity> {
	
	List<ConsumerVO> selectListVO(@Param("ew") Wrapper<ConsumerEntity> wrapper);
	
	ConsumerVO selectVO(@Param("ew") Wrapper<ConsumerEntity> wrapper);
	
	List<ConsumerView> selectListView(@Param("ew") Wrapper<ConsumerEntity> wrapper);

	List<ConsumerView> selectListView(Pagination page,@Param("ew") Wrapper<ConsumerEntity> wrapper);

	
	ConsumerView selectView(@Param("ew") Wrapper<ConsumerEntity> wrapper);
	

}
