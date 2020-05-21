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
	
	//批量查询用户
	public List<User> selectByUseridList(List<Integer> listid){
		List<User> userlist = userdao.selectByUseridList(listid);
		return userlist;
	}
	
	//通过账号和密码查询用户
	public User login(User user){
		User getUser = userdao.selectByNameAndPsd(user);
		return getUser;
	}
	 //查询所有角色为role的角色，role为：teacher、student、admin
	public List<User> selectUsersByRole(String role){
		User user = new User();
		user.setRole(role);
		List<User> techerlist = userdao.queryByPage(user);
		return techerlist;
	}
	
	//查询用户信息
	public List<User> selectUser(User user){
		List<User> ulist = userdao.queryByPage(user);
		return ulist;
	}
	
	//查询用户信息===分页
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
		
	//更新用户信息
	public boolean updateUserInfo(User user) {
		int i = userdao.updateByPrimaryKeySelective(user);
		if(i>0) {
			return true;
		}
		return false;
	}	
		
	//添加用户信息
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
