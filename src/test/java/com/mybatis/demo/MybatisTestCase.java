package com.mybatis.demo;

import com.mybatis.demo.entity.SysUser;
import com.mybatis.demo.mapper.SysUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author wudi
 * @version 1.0
 * @Description
 * @date 2019/12/8 3:36 PM
 */
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisTestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisTestCase.class);

    private static SqlSessionFactory factory;

    @BeforeClass
    public static void beforeClass() throws IOException {
        String resource = "mybatis.xml";
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testLevel1Cache(){

        /**
         * 一级缓存默认开启，默认范围 scope = LocalCacheScope.SESSION
         */
        try (SqlSession sqlSession = factory.openSession();SqlSession sqlSession1 = factory.openSession()){
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.selectByPrimaryKey(17);
            LOGGER.info("user = [{}]",user);
            user = sqlSession1.getMapper(SysUserMapper.class).selectByPrimaryKey(17);
            LOGGER.info("user = [{}]",user);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
    }


    /**
     * 二级缓存
     */
    @Test
    public void testLevel2Cache(){
        try {

        }catch (Exception e){

        }
    }


    /**
     * 事务
     */
    @Test
    public void testTransactional(){
        try (SqlSession sqlSession = factory.openSession()){
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.selectByPrimaryKey(17);
            LOGGER.info("user = [{}]",user);
            user.setPhone("13146057077");
            mapper.updateByPrimaryKeySelective(user);
            sqlSession.commit();
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    /**
     * 测试sql预编译
     */
    @Test
    public void testSqlPrecompile(){
        try (SqlSession sqlSession = factory.openSession()){
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.findByLoginname("zero");
            sqlSession.commit();
            LOGGER.info("user = [{}]",user);
            LOGGER.info("user = [{}]",mapper.findByLoginname("zero"));
            LOGGER.info("user = [{}]",mapper.findByLoginname("zero"));
        }
    }

    @Test
    public void testMapperProxy(){
        MybatisMapperProxy proxy = new MybatisMapperProxy();
        SysUserMapper mapper = proxy.newInstance(SysUserMapper.class);
        SysUser user = mapper.selectByPrimaryKey(17);
        System.out.println(user);
    }

}
