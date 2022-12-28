package com.DoyouEat.DYEat.service.menu;


import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Review;
import com.DoyouEat.DYEat.repository.menu.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(DYE_Review dyeReview) {
        commentRepository.save(dyeReview);

    }

    public DYE_Review findOne(Long id) {
        return commentRepository.findOne(id);
    }

    public List<DYE_Review> findAll(Long id){
        return commentRepository.FindAll(id);
    }

}
