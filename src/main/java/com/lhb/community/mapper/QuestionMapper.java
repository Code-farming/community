package com.lhb.community.mapper;

import com.lhb.community.model.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {

    void create(Question question);

    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    Integer count();

}
