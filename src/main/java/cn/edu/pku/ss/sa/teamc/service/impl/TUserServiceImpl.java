package cn.edu.pku.ss.sa.teamc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.pku.ss.sa.teamc.dao.model.TUser;
import cn.edu.pku.ss.sa.teamc.dao.model.TUserExample;
import cn.edu.pku.ss.sa.teamc.dao.sqlmapper.TUserMapper;
import cn.edu.pku.ss.sa.teamc.service.TUserService;
import cn.edu.pku.ss.sa.teamc.util.DigestUtil;
/**
 * 
 * @author xuyuanming
 *
 * 2016年4月25日下午5:40:24
 */
@Service("TUserService")
public class TUserServiceImpl implements TUserService {

	private static final String salt = "xuyuanming";
	
	@Resource
	private TUserMapper dao;
	
	public TUser getUserByName(String username) {
		// TODO Auto-generated method stub
		TUserExample example = new TUserExample();
		example.createCriteria().andNameEqualTo(username);
		List<TUser> list = dao.selectByExample(example);
		
		return (null == list || list.size() <=0) ? null: list.get(0);
	}

	@Override
	public TUser getUserByNameAndPassword(String username, String password) {
		TUserExample example = new TUserExample();
		example.createCriteria().andNameEqualTo(username)
		.andPasswordEqualTo(DigestUtil.hashToMD5Hex(salt + password).toUpperCase());
		
		List<TUser> list = dao.selectByExample(example);
		
		return (null == list || list.size() <=0) ? null: list.get(0);
	}

}
