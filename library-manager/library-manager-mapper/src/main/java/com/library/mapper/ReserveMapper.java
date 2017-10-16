package com.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.library.pojo.Reserve;
import com.library.pojo.ReserveExample;
import com.library.pojo.ReserveKey;


public interface ReserveMapper {
    int countByExample(ReserveExample example);

    int deleteByExample(ReserveExample example);

    int deleteByPrimaryKey(ReserveKey key);

    int insert(Reserve record);

    int insertSelective(Reserve record);

    List<Reserve> selectByExample(ReserveExample example);

    Reserve selectByPrimaryKey(ReserveKey key);

    int updateByExampleSelective(@Param("record") Reserve record, @Param("example") ReserveExample example);

    int updateByExample(@Param("record") Reserve record, @Param("example") ReserveExample example);

    int updateByPrimaryKeySelective(Reserve record);

    int updateByPrimaryKey(Reserve record);
}