package com.library.mapper;

import com.library.pojo.UserWechat;
import com.library.pojo.UserWechatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWechatMapper {
    int countByExample(UserWechatExample example);

    int deleteByExample(UserWechatExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(UserWechat record);

    int insertSelective(UserWechat record);

    List<UserWechat> selectByExample(UserWechatExample example);

    UserWechat selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") UserWechat record, @Param("example") UserWechatExample example);

    int updateByExample(@Param("record") UserWechat record, @Param("example") UserWechatExample example);

    int updateByPrimaryKeySelective(UserWechat record);

    int updateByPrimaryKey(UserWechat record);
}