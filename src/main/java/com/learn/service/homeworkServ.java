package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.homeworkCommitMapper;
import com.learn.dao.homeworkInfoMapper;
import com.learn.model.fileInfo;
import com.learn.model.homeworkCommit;
import com.learn.model.homeworkCommitKey;
import com.learn.model.homeworkInfo;

@Service
public class homeworkServ {
	@Autowired
	private	homeworkInfoMapper homeworkdao;
	@Autowired
	private homeworkCommitMapper commithomeworkdao;
	/**
	 * �����ҵ
	 *Title: addHomework
	 *Description: 
	 *2020��3��30������2:43:43
	 * @param homework
	 * @return
	 */
	public boolean addHomework(homeworkInfo homework) {
		int i = homeworkdao.insertSelective(homework);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * ��ѯ��ҵ
	 *Title: selectHomework
	 *Description: 
	 *2020��3��30������2:46:31
	 * @param homework
	 * @return
	 */
	public List<homeworkInfo> selectHomework(homeworkInfo homework){
		List<homeworkInfo> hlist = homeworkdao.queryByPage(homework);
		return hlist;
	}
	
	/**
	 * ��ѯ��ҵ==��ҳ��ѯ
	 *Title: selectHomework
	 *Description: 
	 *2020��3��30������2:46:31
	 * @param homework
	 * @return
	 */
	public Map<String, Object> selectHomework(homeworkInfo homework, int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<homeworkInfo> hlist = homeworkdao.queryByPage(homework);
		PageInfo<homeworkInfo> pageinfo = new PageInfo<>(hlist);
		long total = pageinfo.getTotal();
		map.put("homeworklist", hlist);
		map.put("total", total);
		return map;
	}
	
	/**
	 * �޸���ҵ
	 *Title: updateHomework
	 *Description: 
	 *2020��3��30������2:58:39
	 * @param homework
	 * @return
	 */
	public boolean updateHomework(homeworkInfo homework){
		int i = homeworkdao.updateByPrimaryKeySelective(homework);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * ɾ����ҵ
	 *Title: delectHomework
	 *Description: 
	 *2020��3��30������3:00:04
	 * @param wid
	 * @return
	 */
	public boolean delectHomework(Integer wid){
		int i = homeworkdao.deleteByPrimaryKey(wid);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * �ύ��ҵ
	 *Title: commitHomework
	 *Description: 
	 *2020��3��30������3:27:29
	 * @param commit
	 * @return
	 */
	public boolean commitHomework(homeworkCommit commit) {
		int i = commithomeworkdao.insertSelective(commit);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * �޸��ύ����ҵ
	 *Title: updateCommitHomework
	 *Description: 
	 *2020��3��30������3:27:42
	 * @param commit
	 * @return
	 */
	public boolean updateCommitHomework(homeworkCommit commit) {
		
		int i = commithomeworkdao.updateByPrimaryKeySelective(commit);
		if(i>0) {
			return true;
		}
		return false;
	}
	/**
	 * ��ѯ�����ύ����ҵ
	 *Title: selectCommitHomework
	 *Description: 
	 *2020��3��30������3:27:58
	 * @param commit
	 * @return
	 */
	public List<homeworkCommit> selectCommitHomework(homeworkCommit commit){
		List<homeworkCommit> hclist = commithomeworkdao.queryByPage(commit);
		return hclist;
	}
	/**
	 * ��ʦ��ѯ�ύ����ҵ����ҳ��ѯ
	 *Title: selectCommitHomework
	 *Description: 
	 *2020��3��30������3:33:14
	 * @param commit
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String, Object> selectCommitHomework(homeworkCommit commit,int page, int limit){
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(page, limit);
		List<homeworkCommit> hclist = commithomeworkdao.queryByPage(commit);
		PageInfo<homeworkCommit> pageinfo = new PageInfo<>(hclist);
		long total = pageinfo.getTotal();
		map.put("commitlist", hclist);
		map.put("total", total);
		return map;
	}
	
	public Map<String, Object> getHomeworkScore(Integer userid,Integer courseid){
		Map<String,Object> map = new HashMap<>();
		map.put("userid", userid);
		map.put("courseid", courseid);
		Map<String, Object> map2 = commithomeworkdao.selectHomeworkScore(map);
		return map2;
	}
}
