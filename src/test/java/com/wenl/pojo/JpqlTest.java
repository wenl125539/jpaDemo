package com.wenl.pojo;

import com.wenl.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 *  jpql查询
 *      em.createQuery(“jpql语句”);创建query对象
 */

public class JpqlTest {

    /**
     * 查询全部
     *          jpql: from 实体类
     *          sql : select * from tableName
     */
    @Test
    public void testFindAll(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String jpql = "from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        List<Customer> list = query.getResultList();//发送查询 并封装结果集
        for (Customer customer : list) {
            System.out.print(customer.toString());
            System.out.println("");
        }
        et.commit();
        em.close();
    }


    /**
     * 排序查询全部
     *      sql: select * from table order by id desc
     *      jpql : from table order by 实体类属性 desc
     */
    @Test
    public void testFindAllOrderById(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        List<Customer> list = query.getResultList();//发送查询 并封装结果集
        for (Customer customer : list) {
            System.out.print(customer.toString());
            System.out.println("");
        }
        et.commit();
        em.close();
    }


    /**
     * jpql 统计客户总数
     *      sql: select Count(id) from table
     *      jpql: select Count(属性) from 实体类
     */
    @Test
    public void testCount(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        Object result = query.getSingleResult();//发送查询 并封装结果集
        System.out.println(result);
        et.commit();
        em.close();
    }

    /**
     * 分页查询
     *      sql: select * from tableName limit ?,?
     *      jpql: 写查询的语句写出来就可以
     */
    @Test
    public void testPaged(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String jpql = "from Customer";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        //对分页参数赋值
        query.setFirstResult(0);//开始索引
        query.setMaxResults(1);//查询条数

        List<Customer> list = query.getResultList();//发送查询 并封装结果集
        for (Customer customer : list) {
            System.out.print(customer.toString());
            System.out.println("");
        }

        et.commit();
        em.close();
    }

    /**
     * 条件查询  模糊查询加 %
     *
     *         sql: select * from table where 字段 like ？
     *         jpql: from tableName where 属性 like ?
     */
    @Test
    public void testCondition(){
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String jpql = "from Customer where custName like ?0 ";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jpql的对象
        //对占位符赋值        占位符位置 1开始   ，取值
        query.setParameter(0,"wenl");

        List<Customer> list = query.getResultList();//发送查询 并封装结果集
        for (Customer customer : list) {
            System.out.print(customer.toString());
            System.out.println("");
        }

        et.commit();
        em.close();
    }
}
