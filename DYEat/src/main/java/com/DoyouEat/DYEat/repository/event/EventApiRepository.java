package com.DoyouEat.DYEat.repository.event;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventApiRepository extends JpaRepository<DYE_Event,Long> {
}
