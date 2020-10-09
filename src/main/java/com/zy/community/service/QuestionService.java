package com.zy.community.service;

import com.zy.community.dto.PaginationDTO;
import com.zy.community.dto.QuestionDTO;
import com.zy.community.mapper.QuestionMapper;
import com.zy.community.mapper.UserMapper;
import com.zy.community.model.Question;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size){
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if (totalCount%size==0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if (page<1)
            page =1;
        if (page>totalPage)
            page = totalPage;
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size*(page-1);
        List<Question> questionList = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            Integer creator = question.getCreator();
            questionDTO.setUser(userMapper.findById(creator));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount%size==0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if (page<1)
            page =1;
        if (page>totalPage)
            page = totalPage;
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size*(page-1);
        List<Question> questionList = questionMapper.listById(userId, offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            Integer creator = question.getCreator();
            questionDTO.setUser(userMapper.findById(creator));
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }
}
