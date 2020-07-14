package service;

import com.bosssoft.hr.train.web.exception.BusinessException;
import com.bosssoft.hr.train.web.pojo.Query;
import com.bosssoft.hr.train.web.pojo.User;
import com.bosssoft.hr.train.web.service.UserService;
import com.bosssoft.hr.train.web.service.UserServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        userService = null;
    }

    @Test
    public void save() {
        User user = new User(11111, "jim");
        Assert.assertEquals(true, userService.save(user));
        //删除防止之后测试重复
        Assert.assertEquals(true,userService.remove(user));
        //测试没有id 和 名字的参数
        User user2 = new User();
        Assert.assertEquals(true, userService.save(user2));

        // 如果需要测试为空等继续构建 可以验证的 User
    }

    /**
     * 测试插入重复  这里会出错但是暂时没什么办法
     */
    @Test(expected = BusinessException.class)
    public void saveSameId() {
        User user = new User(111, "jim");
        Assert.assertEquals(false, userService.save(user));
    }


    @Test
    public void remove() {
        //存在 原始user 先添加进去
        User user0 = new User(11111, "jim");
        Assert.assertEquals(true, userService.save(user0));
        User user = new User(11111, "jim");
        Assert.assertEquals(true, userService.remove(user));

        User user2 = new User(1111, "jim");
        Assert.assertEquals(false, userService.remove(user));
    }

    /**
     * 依据界面可能开展的测试编写测试用例  例如某一些字段为空等，实际就是依据
     * 项目需求的测试用例来扩展测试用例
     */
    @Test
    public void update() {
        User user = new User(1, "张三2", "zhangsan", "123456");
        Assert.assertEquals(true, userService.update(user));

        User user2 = new User(-1, "张三2", "zhangsan", "123456");
        Assert.assertEquals(false, userService.update(user2));
    }

    @Test
    public void queryByCondition() {
        //按照名字模糊查询
        User user = new User(-1, "");
        Assert.assertEquals(true, userService.queryByCondition(new Query(user.getName())).size() > 0);

    }

    /**
     * 测试正确账号和错误账号
     */
    @Test
    public void authentication() {
        //测试正确
        User user = new User("zhangsan", "123456");
        Assert.assertEquals(true, userService.authentication(user));

        //测试错误账号
        User user2 = new User("zhangwu", "123456");
        Assert.assertEquals(false, userService.authentication(user2));

    }
}