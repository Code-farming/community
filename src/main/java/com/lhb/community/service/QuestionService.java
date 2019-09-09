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
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount = questionMapper.count();
        paginationDTO.setPagination(totalcount, page, size);
        Integer offset = size * (paginationDTO.getPage() - 1);
        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOlist = new ArrayList<>();
        for (Question question : questionList) {
            Integer creator = question.getCreator();
            User user = userMapper.findById(creator);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOlist.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOlist);
        return paginationDTO;
    }

    public PaginationDTO listQuestionByID(Integer accountId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalcount = questionMapper.countByID(accountId);
        paginationDTO.setPagination(totalcount, page, size);
        Integer offset = size * (paginationDTO.getPage() - 1);
        List<Question> questionList = questionMapper.listQuestionByID(offset, size, accountId);
        List<QuestionDTO> questionDTOlist = new ArrayList<>();
        User user;
        user = userMapper.findById(accountId);
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOlist.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOlist);
        return paginationDTO;
    }
}
