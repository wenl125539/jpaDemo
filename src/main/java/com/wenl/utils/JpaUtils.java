package com.wenl.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决创建实体类管理工厂时浪费资源问题
 *  公共的对象
 */
public class JpaUtils {
    private static EntityManagerFactory factory;

    static {
        //加载配置文件创建entityManagerFactory
         factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
