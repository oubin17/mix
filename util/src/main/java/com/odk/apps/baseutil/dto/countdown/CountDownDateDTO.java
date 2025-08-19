package com.odk.apps.baseutil.dto.countdown;

import lombok.Data;

/**
 * CountDownDateDTO
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Data
public class CountDownDateDTO {

    private String id;

    private String bookId;

    private String description;

    private String targetDate;

    /**
     * 剩余天数，过去时间：负数
     * 将来时间：正数
     */
    private Integer leftDays;

}
