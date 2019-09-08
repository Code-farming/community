package com.lhb.community.service;


import com.lhb.community.dto.PaginationDTO;
import com.lhb.community.dto.QuestionDTO;
import com.lhb.community.mapper.QuestionMapper;
import com.lhb.community.mapper.UserMapper;
import com.lhb.community.model.Question;
import com.lhb.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);

        List<Question> questionList = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOlist = new ArrayList<>();
        PaginationDTO paginationDTO=new PaginationDTO();
        for (Question question : questionList) {
            Integer creator = question.getCreator();
            User user = userMapper.findById(creator);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOlist.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOlist);
        Integer totalcount = questionMapper.count();
        paginationDTO.setPagination(totalcount,page,size);
        return paginationDTO;
    }

}
