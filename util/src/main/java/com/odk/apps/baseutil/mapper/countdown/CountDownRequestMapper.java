package com.odk.apps.baseutil.mapper.countdown;

import com.odk.apps.baseutil.dto.countdown.CountDownBookDTO;
import com.odk.apps.baseutil.dto.countdown.CountDownDateDTO;
import com.odk.apps.baseutil.request.countdown.CountDownBookAddRequest;
import com.odk.apps.baseutil.request.countdown.CountDownDateAddRequest;
import org.mapstruct.Mapper;

/**
 * CountDownMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Mapper(componentModel = "spring")
public interface CountDownRequestMapper {

    CountDownBookDTO toDTO(CountDownBookAddRequest countDownBookRequest);

    CountDownDateDTO toDTO(CountDownDateAddRequest countDownBookRequest);
}
