package com.odk.apps.basedomain.repository.couontdown;

import com.odk.apps.basedomain.model.countdown.CountDownDateDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CountDownDateRepository
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
public interface CountDownDateRepository extends JpaRepository<CountDownDateDO, String> {


    List<CountDownDateDO> findByBookId(String bookId);
}
