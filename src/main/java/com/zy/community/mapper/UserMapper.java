package com.zy.community.mapper;

import com.zy.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Insert("insert into user (id,name,account_id,token,gmt_create,gmt_modified) values (#{id},#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
