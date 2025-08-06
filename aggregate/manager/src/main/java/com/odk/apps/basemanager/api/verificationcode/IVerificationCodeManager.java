package com.odk.apps.basemanager.api.verificationcode;


import com.odk.apps.baseutil.dto.verificationcode.VerificationCodeDTO;
import com.odk.apps.baseutil.entity.VerificationCodeEntity;

/**
 * IVerificationCodeManager
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/1
 */
public interface IVerificationCodeManager {

    VerificationCodeEntity generate(VerificationCodeDTO verificationCodeDTO);

    boolean compareAndIncr(VerificationCodeDTO verificationCodeDTO);
}
