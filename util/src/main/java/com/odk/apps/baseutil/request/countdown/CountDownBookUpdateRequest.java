package com.odk.apps.baseutil.request.countdown;

import com.odk.base.vo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * CountDownBookUpdateRequest
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CountDownBookUpdateRequest extends BaseRequest {

    @Serial
    private static final long serialVersionUID = 5955747852258027334L;

    private String bookId;

    private String bookName;

    private String bookIcon;
}
