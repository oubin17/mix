package com.odk.apps.baseutil.response.countdown;

import com.odk.apps.baseutil.dto.countdown.CountDownDateDTO;
import lombok.Data;

import java.util.List;

/**
 * CountDownDateListResponse
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Data
public class CountDownDateListResponse {

    private List<CountDownDateDTO> dateDTOList;
}
