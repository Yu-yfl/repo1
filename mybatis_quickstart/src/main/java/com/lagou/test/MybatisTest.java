package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest  {
  @Test
    public void mybatisQuickStart() throws IOException {
      // 加载核心配置文件
      InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
      // 获取SqlSession会话对象
      SqlSession sqlSession = sqlSessionFactory.openSession();
      // 执行sql
      List<User> list = sqlSession.selectList("UserMapper.findAll");
      for (User user : list) {
          System.out.println(user);
      }
        // 释放资源
      sqlSession.close();
    }

  @Test
  public void testSave() throws Exception {
    // 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
    // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
    // 执行sql
        User user = new User();
        user.setUsername("自动提交事务");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("上海");
        sqlSession.insert("UserMapper.save", user);
    // DML语句，手动提交事务
        //sqlSession.commit();
    // 释放资源
        sqlSession.close();
  }
    @Test
    public void testUpdate() throws Exception {
        // 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        User user = new User();
        user.setId(4);
        user.setUsername("luce");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("广州");
        sqlSession.update("UserMapper.updateUser", user);
        // DML语句，手动提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testDelete() throws Exception {
        // 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sqlUser
        sqlSession.delete("UserMapper.deleteUser", 6);
        // DML语句，手动提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

}
