package com.wenl.pojo;

import com.wenl.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * Jpa操作步骤
     *      1.加载配置文件创建工厂（实体类管理工厂）对象
     *              根据持久化单元名称创建实体类管理器工厂Persistence.createEntityManagerFactory("持久化单元名称");
     *      2.通过实体管理类工厂获取实体管理器
     *              factory.createEntityManager();
     *                  内部维护数据库信息
     *                  维护缓存信息
     *                  维护所有实体管理器对象
     *                  在创建EntityManagerFactory过程中根据配置创建数据库表，创建过程比较浪费资源，
     *                          EntityManagerFactory是一个线程安全对象
     *                          创建一个公用对象即可，静态代码块，单例模式
     *      3.获取事务对象  开启事务
     *              EntityManager对象： 实体类管理器
     *                                     getTransaction() 创建事务对象
     *                                     persist: 保存
     *                                     merge : 更新
     *                                     remove : 删除
     *                                     find/getRefrence : 根据id查询
     *              EntityTransaction对象： 事务对象
     *                                      begin ：开启事务
     *                                      commit: 提交事务
     *                                      rollback: 事务回滚
     *      4.完成增删改查操作
     *      5.提交事务（回滚事务）
     *      6.释放资源
     */

    @Test
    public void testSave(){

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");

        EntityManager en = factory.createEntityManager();
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务
        Customer customer = new Customer();
        customer.setCustName("wenl");
        customer.setCustPhone("123456789");
        en.persist(customer);
        et.commit();

        en.close();
        factory.close();
    }


    @Test
    public void testSave2(){

        EntityManager en = JpaUtils.getEntityManager();
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务
        Customer customer = new Customer();
        customer.setCustName("wenl1");
        customer.setCustPhone("123");
        en.persist(customer);
        et.commit();
        en.close();
    }

    @Test
    public void testFind(){

        EntityManager en = JpaUtils.getEntityManager();
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务
        Customer customer = en.find(Customer.class, 2l); //find()立即加载
        System.out.println(customer.toString());
        et.commit();
        en.close();
    }

    @Test
    public void testFind2(){

        EntityManager en = JpaUtils.getEntityManager();
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务
        Customer customer = en.getReference(Customer.class, 2l); //reference 懒加载
        System.out.println(customer.toString());
        et.commit();
        en.close();
    }

    @Test
    public void testRemove(){
        EntityManager en = JpaUtils.getEntityManager();
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务

        //查询表获取对象
        Customer customer = en.find(Customer.class, 2l);
        //删除
        en.remove(customer); //根据实体类对象删除

        et.commit();
        en.close();
    }

    @Test
    public void testUpdate(){
        EntityManager en = JpaUtils.getEntityManager();
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务

        //查询表获取对象
        Customer customer = en.find(Customer.class, 3l);

        customer.setCustPhone("123213");
        //跟新操作
        en.merge(customer);

        et.commit();
        en.close();
    }
}
