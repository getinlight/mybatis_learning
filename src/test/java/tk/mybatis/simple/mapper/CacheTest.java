package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

public class CacheTest extends BaseMapperTest {

    @Test
    public void testL1Cache() {
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user1 = userMapper.selectById(1L);
            user1.setUserName("New Name");
            SysUser user2 = userMapper.selectById(1L);
            Assert.assertEquals("New Name", user2.getUserName());
            Assert.assertEquals(user1, user2);
            //这里查询了一次 一级缓存体现
        } finally {
            sqlSession.close();
        }
        System.out.println("开启新的sql session");
        sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user2 = userMapper.selectById(1L);
            Assert.assertNotEquals("New Name", user2.getUserName());
            Assert.assertNotEquals(user1, user2);
            userMapper.deleteUserById(2L);
            SysUser user3 = userMapper.selectById(1L);
            Assert.assertNotEquals(user2, user3);
            //任何insert update delete 操作都会清空一级缓存
        } finally {
            sqlSession.close();
        }
    }

}
