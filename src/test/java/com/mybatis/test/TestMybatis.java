package com.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mybatis.bean.User;

public class TestMybatis {

	@Test
	public void testMybatis() throws IOException {
		
		String resource = "mybatisConfig.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session = ssf.openSession();
		
		List<User> us = session.selectList("com.mybatis.bean.User.allList");
		User u = session.selectOne("com.mybatis.bean.User.getOne", 1);
		session.commit();
		session.close();
		for(int i = 0; i < us.size(); i++) {
			System.out.println(us.get(i).toString());
		}
		System.out.println(u.toString());
	}
	
	@Test
	public void testInsert() {
		
		String resource = "mybatisConfig.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = ssf.openSession();
		
		User user = new User();
		user.setUserName("咯啊运费");
		user.setAge("22");
		user.setSalary(123);
		
		int i = session.insert("com.mybatis.bean.User.insertUser", user);
		session.commit();
		System.out.println(i);
	}
	
	@Test
	public void testUpdate() {
		
		String resource = "mybatisConfig.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch(IOException e) {}
		
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		
		SqlSession session = ssf.openSession();
		
		User user = session.selectOne("com.mybatis.bean.User.getOne", 1);
		user.setUserName("飞飞");
		int i = session.update("com.mybatis.bean.User.updateUser", user);
		session.commit();
		
		System.out.println(i);
		
	}
}
