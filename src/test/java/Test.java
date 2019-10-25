import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;

public class Test {


    @org.junit.Test
    public void test1() {
        System.out.println("test1");
              //1、获取SecurityManager工厂，此处使用Ini配置文件初始化
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2. 得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zs", "1213");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            //e.printStackTrace();
        }


        System.out.println(subject.isAuthenticated());

        //6、退出
        subject.logout();
    }

    @org.junit.Test
    public void test2() {
        System.out.println("test2");
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");

        //2. 得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("ls", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }


        System.out.println(subject.isAuthenticated());

        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasRole("role2"));
        System.out.println(subject.hasRole("role3"));

//        try {
//            subject.checkRole("role1");
//        } catch (AuthorizationException e) {
//            //e.printStackTrace();
//            System.out.println("11111111111");
//        }
//        try {
//            subject.checkRole("role2");
//
//        } catch (AuthorizationException e) {
//            //e.printStackTrace();
//            System.out.println("2222");
//        }
//        try {
//            subject.checkRole("role3");
//        } catch (AuthorizationException e) {
//            //e.printStackTrace();
//            System.out.println("3333");
//        }
//        try {
//            subject.checkRoles("role1", "role2", "role3");
//            //xxxx
//        } catch (AuthorizationException e) {
//           // e.printStackTrace();
//            System.out.println("444");
//        }


        //6、退出
        subject.logout();
    }


    @org.junit.Test
    public void test3() {
        System.out.println("test3");
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");

        //2. 得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("ww", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }



        try {
            //subject.checkPermission("user:create");
            subject.checkPermission("user:delete");
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }


        //6、退出
        subject.logout();
    }
}
