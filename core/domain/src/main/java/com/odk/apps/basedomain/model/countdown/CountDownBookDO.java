package com.odk.apps.basedomain.model.countdown;

import com.odk.base.dos.BaseDO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

/**
 * CountDownBookDO
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_countdown_book")
@EntityListeners(AuditingEntityListener.class)
public class CountDownBookDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = -3359732858039067269L;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_icon")
    private String bookIcon;
}
