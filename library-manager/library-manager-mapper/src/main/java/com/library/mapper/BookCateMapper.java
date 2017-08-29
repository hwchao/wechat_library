package com.library.mapper;

import com.library.pojo.BookCate;
import com.library.pojo.BookCateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookCateMapper {
    int countByExample(BookCateExample example);

    int deleteByExample(BookCateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookCate record);

    int insertSelective(BookCate record);

    List<BookCate> selectByExample(BookCateExample example);

    BookCate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookCate record, @Param("example") BookCateExample example);

    int updateByExample(@Param("record") BookCate record, @Param("example") BookCateExample example);

    int updateByPrimaryKeySelective(BookCate record);

    int updateByPrimaryKey(BookCate record);
}