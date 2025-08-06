package com.odk.apps.baseutil.mapper;

import com.odk.apps.baseutil.dto.verificationcode.VerificationCodeDTO;
import com.odk.apps.baseutil.enums.VerifySceneEnum;
import com.odk.apps.baseutil.request.VerificationCodeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * VerificationCodeMapper
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/29
 */
@Mapper(componentModel = "spring")
public interface VerificationCodeMapper {

    @Mapping(target = "verifyScene", source = "verifyScene", qualifiedByName = "mapStringToEnum")
    VerificationCodeDTO toDTO(VerificationCodeRequest verificationCodeRequest);

    @Named("mapStringToEnum")
    default VerifySceneEnum mapStringToEnum(String verifyScene) {
        if (verifyScene == null) {
            return null;
        }
        return VerifySceneEnum.getByCode(verifyScene);
    }
}
