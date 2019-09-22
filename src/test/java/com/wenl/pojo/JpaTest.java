package com.wenl.pojo;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * Jpa操作步骤
     *      1.加载配置文件创建工厂（实体类管理工厂）对象
     *      2.通过实体管理类工厂获取实体管理器
     *      3.获取事务对象  开启事务
     *      4.完成增删改查操作
     *      5.提交事务（回滚事务）
     *      6.释放资源
     */

    @Test
    public void testSave(){
        //1
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2
        EntityManager en = factory.createEntityManager();
        //3
        EntityTransaction et = en.getTransaction();//获取事务对象
        et.begin();//开启事务
        //4
        Customer customer = new Customer();
        customer.setCustName("wenl");
        customer.setCustPhone("123456789");
        //保存
        en.persist(customer);
        //5
        et.commit();
        //6
        en.close();
        factory.close();
    }

}
