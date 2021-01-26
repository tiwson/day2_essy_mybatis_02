package com.tiwson.mybatis.test;

import com.tiwson.mybatis.bean.User;
import com.tiwson.mybatis.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestMybatis {

    InputStream in;
    SqlSession sqlSession;
    IUserDao dao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = sessionFactory.openSession();
        dao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception {

        List<User> users = dao.findAll();
        for(User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("小六");
        user.setSex("女");
        user.setAddress("北京市");
        user.setBirthday(new Date());

        dao.addUser(user);
        sqlSession.commit();
        System.out.println("添加用户成功");
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("小七");
        user.setSex("女");
        user.setAddress("上海市");
        user.setBirthday(new Date());
        user.setId(44);

        dao.updateUser(user);
        sqlSession.commit();
        System.out.println("修改用户成功");
    }

    @Test
    public void testDeleteUser() {
        dao.deleteUser(45);
        sqlSession.commit();
        System.out.println("删除用户成功");
    }

    @Test
    public void testFindUser() throws Exception {

        User user = dao.findUser(43);
        System.out.println(user);

    }

    /**
     * 根据用户名模糊查询
     * @throws Exception
     */
    @Test
    public void testFindUserByUsername() throws Exception {

        List<User> users = dao.findUserByUsername("%老%");
        for(User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindTotalUser() throws Exception {

        int total = dao.findTotalUser();
        System.out.println(total);
    }
}
