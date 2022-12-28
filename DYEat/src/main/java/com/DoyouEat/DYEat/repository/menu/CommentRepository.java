package com.DoyouEat.DYEat.repository.menu;


import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(DYE_Review dyeReview) {
        em.persist(dyeReview);
    }

    public DYE_Review findOne(Long id) {
        return em.find(DYE_Review.class, id);
    }

    public List<DYE_Review> FindAll(Long id){
        return em.createQuery("select a from DYE_Review a",DYE_Review.class)
                .getResultList();
    }
}
