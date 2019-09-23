package com.wenl.pojo;

import javax.persistence.*;

/**
 * 客户实体类
 *      配置映射关系
 *             1.实体类与表的关系
 *                  @Entity 声明实体类
 *                  @Table（name= “数据库表名”）  配置实体类和表的映射关系
 *             2.实体类中属性和表中字段的映射关系
 *
 */
@Entity
@Table (name = "Customer")
public class Customer {

    /**
     * @Id  声明主键的配置
     * @GeneratedValue
     *          strategy：
     *             √     GenerationType.IDENTITY : 自增  *底层数据库必须支持自动增长方式 mysql  √
     *             √     GenerationType.SEQUENCE : 序列  *底层数据库必须支持序列 oracle        √
     *                  GenerationType.TABLE    : jpa提供的机制，通过一张数据库表形式帮组完成自增
     *                  GenerationType.AUTO    ：程序自动选择主键生成策略
     * @Column
     *             name:
     *                  "数据库字段名"
     */
    @Id//声明主键的配置
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custId")
    private  Long custId;//客户主键

    @Column(name = "custName")
    private String custName;//客户名称


    @Column(name = "custPhone")
    private String custPhone;//客户电话

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}
