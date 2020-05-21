package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.carouselMapper;
import com.learn.model.carousel;
import com.learn.model.courseInfo;

@Service
public class carouselServ {
	@Autowired
	private carouselMapper carouseldao;
	
	public boolean addCarousel(carousel carou) {
		int i = carouseldao.insertSelective(carou);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean delectCarousel(Integer id) {
		int i = carouseldao.deleteByPrimaryKey(id);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public boolean updateCarousel(carousel carou) {
		int i = carouseldao.updateByPrimaryKeySelective(carou);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String, Object> selectCarousel(carousel carou,int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<carousel> list = carouseldao.queryByPage(carou);
		PageInfo<carousel> pageinfo = new PageInfo<>(list);
		long total = pageinfo.getTotal();
		map.put("carousellist", list);
		map.put("total", total);
		return map;
	}
	
	public List<carousel> selectCarousel(carousel carou) {
		List<carousel> list = carouseldao.queryByPage(carou);
		return list;
	}
}
