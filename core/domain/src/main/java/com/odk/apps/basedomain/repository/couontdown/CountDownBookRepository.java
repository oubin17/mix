package com.odk.apps.basedomain.repository.couontdown;

import com.odk.apps.basedomain.model.countdown.CountDownBookDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CountDownBookRepository
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
public interface CountDownBookRepository extends JpaRepository<CountDownBookDO, String> {

}
