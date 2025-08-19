package com.odk.apps.baseutil.request.countdown;

import com.odk.base.vo.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * CountDownBookRequest
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CountDownBookAddRequest extends BaseRequest {

    @Serial
    private static final long serialVersionUID = 4776751430884430522L;

    @NotBlank(message = "bookName不能为空")
    @Size(min = 1, max = 10, message = "bookName长度必须在1-10之间")
    private String bookName;

    @NotBlank(message = "bookIcon不能为空")
    @Size(min = 1, max = 10, message = "bookIcon长度必须在1-10之间")
    private String bookIcon;
}
