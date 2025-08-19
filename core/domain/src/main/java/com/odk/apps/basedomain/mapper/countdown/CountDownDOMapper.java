package com.odk.apps.basedomain.mapper.countdown;

import com.odk.apps.basedomain.model.countdown.CountDownBookDO;
import com.odk.apps.basedomain.model.countdown.CountDownDateDO;
import com.odk.apps.baseutil.dto.countdown.CountDownBookDTO;
import com.odk.apps.baseutil.dto.countdown.CountDownDateDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * CountDownMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Mapper(componentModel = "spring")
public interface CountDownDOMapper {
    CountDownBookDO toDO(CountDownBookDTO countDownBookDTO);

    CountDownBookDTO toDTO(CountDownBookDO countDownBookDO);

    List<CountDownBookDTO> toDTO(List<CountDownBookDO> countDownBookDO);


    CountDownDateDO toDO(CountDownDateDTO countDownDateDTO);

    List<CountDownDateDTO> toDateDTO(List<CountDownDateDO> countDownDateDO);


}
