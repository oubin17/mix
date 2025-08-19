package com.odk.apps.basedomain.model.countdown;

import com.odk.base.dos.BaseDO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

/**
 * CountDownDateDO
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_countdown_date", indexes = {
        @Index(name = "idx_book_id", columnList = "book_id,tenant_id", unique = true)
})
@EntityListeners(AuditingEntityListener.class)
public class CountDownDateDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 5730285442140055691L;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "description")
    private String description;

    @Column(name = "target_date")
    private String targetDate;

}
