package com.wxw.dao;

import com.wxw.bean.Departmet;
import com.wxw.bean.DepartmetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    long countByExample(DepartmetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int deleteByExample(DepartmetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int deleteByPrimaryKey(Integer deptId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int insert(Departmet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int insertSelective(Departmet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    List<Departmet> selectByExample(DepartmetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    Departmet selectByPrimaryKey(Integer deptId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int updateByExampleSelective(@Param("record") Departmet record, @Param("example") DepartmetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int updateByExample(@Param("record") Departmet record, @Param("example") DepartmetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int updateByPrimaryKeySelective(Departmet record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Tue Jan 14 12:28:18 CST 2020
     */
    int updateByPrimaryKey(Departmet record);
}