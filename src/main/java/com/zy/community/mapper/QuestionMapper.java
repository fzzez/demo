package com.zy.community.mapper;

import com.zy.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,tag,creator) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{tag},#{creator})")
    void insert(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{id} limit #{offset}, #{size}")
    List<Question> listById(@Param("id") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question where creator = #{id}")
    Integer countByUserId(@Param("id") Integer userId);
}
