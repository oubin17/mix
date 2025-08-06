package com.odk.apps.baseapi.inter.verificationcode;


import com.odk.apps.baseutil.entity.VerificationCodeEntity;
import com.odk.apps.baseutil.request.VerificationCodeRequest;
import com.odk.base.vo.response.ServiceResponse;

/**
 * VerificationCodeApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/4/29
 */
public interface VerificationCodeApi {

    /**
     * 生成验证码
     *
     * @param codeRequest
     * @return
     */
    ServiceResponse<VerificationCodeEntity> generateCode(VerificationCodeRequest codeRequest);

    /**
     * 验证码验证
     *
     * @param codeRequest
     * @return
     */
    ServiceResponse<Boolean> compare(VerificationCodeRequest codeRequest);

}
