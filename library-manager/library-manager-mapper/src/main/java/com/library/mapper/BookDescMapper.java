package com.library.mapper;

import com.library.pojo.BookDesc;
import com.library.pojo.BookDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookDescMapper {
    int countByExample(BookDescExample example);

    int deleteByExample(BookDescExample example);

    int deleteByPrimaryKey(Long bid);

    int insert(BookDesc record);

    int insertSelective(BookDesc record);

    List<BookDesc> selectByExampleWithBLOBs(BookDescExample example);

    List<BookDesc> selectByExample(BookDescExample example);

    BookDesc selectByPrimaryKey(Long bid);

    int updateByExampleSelective(@Param("record") BookDesc record, @Param("example") BookDescExample example);

    int updateByExampleWithBLOBs(@Param("record") BookDesc record, @Param("example") BookDescExample example);

    int updateByExample(@Param("record") BookDesc record, @Param("example") BookDescExample example);

    int updateByPrimaryKeySelective(BookDesc record);

    int updateByPrimaryKeyWithBLOBs(BookDesc record);
}