package com.learn.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.dao.UserMapper;
import com.learn.model.User;
import com.learn.model.auditCourse;
import com.learn.utils.JWT;

@Service
public class userServ {
	@Autowired
	private UserMapper userdao;
	
	//������ѯ�û�
	public List<User> selectByUseridList(List<Integer> listid){
		List<User> userlist = userdao.selectByUseridList(listid);
		return userlist;
	}
	
	//ͨ���˺ź������ѯ�û�
	public User login(User user){
		User getUser = userdao.selectByNameAndPsd(user);
		return getUser;
	}
	 //��ѯ���н�ɫΪrole�Ľ�ɫ��roleΪ��teacher��student��admin
	public List<User> selectUsersByRole(String role){
		User user = new User();
		user.setRole(role);
		List<User> techerlist = userdao.queryByPage(user);
		return techerlist;
	}
	
	//��ѯ�û���Ϣ
	public List<User> selectUser(User user){
		List<User> ulist = userdao.queryByPage(user);
		return ulist;
	}
	
	//��ѯ�û���Ϣ===��ҳ
	public Map<String,Object> selectUser(User user,int page, int limit){
		Map<String,Object> map = new HashMap<String,Object>();
		PageHelper.startPage(page,limit);
		List<User> ulist = userdao.queryByPage(user);
		PageInfo<User> pageinfo = new PageInfo<>(ulist);
		long total = pageinfo.getTotal();
		map.put("userlist", ulist);
		map.put("total",total);
		return map;
	}
		
	//�����û���Ϣ
	public boolean updateUserInfo(User user) {
		int i = userdao.updateByPrimaryKeySelective(user);
		if(i>0) {
			return true;
		}
		return false;
	}	
		
	//����û���Ϣ
	public boolean addUserInfo(User user) {
		int i = userdao.insertSelective(user);
		if(i>0) {
			return true;
		}
		return false;
	}
	
	public Map<String,Object> selectUserNumByRole(String role) {
		Map<String,Object> map = userdao.selectUserNumByRole(role);
		return map;
	}
}
