package com.odk.apps.baseutil.request.countdown;

import com.odk.base.vo.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * CountDownDateAddRequest
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CountDownDateAddRequest extends BaseRequest {

    @Serial
    private static final long serialVersionUID = -3157599013414851473L;

    @NotBlank(message = "bookId不能为空")
    private String bookId;

    @Size(min = 1, max = 10, message = "desc长度必须在1-10之间")
    private String description;

    @Size(min = 10, max = 10, message = "targetDate长度必须在10-10之间")
    private String targetDate;
}
