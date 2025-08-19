package com.odk.apps.baseutil.response.countdown;

import com.odk.apps.baseutil.dto.countdown.CountDownBookDTO;
import lombok.Data;

import java.util.List;

/**
 * CountDownBookListResponse
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Data
public class CountDownBookListResponse {

    private List<CountDownBookDTO> bookDTOList;
}
