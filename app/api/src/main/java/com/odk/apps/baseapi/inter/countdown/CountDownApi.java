package com.odk.apps.baseapi.inter.countdown;

import com.odk.apps.baseutil.request.countdown.CountDownBookAddRequest;
import com.odk.apps.baseutil.request.countdown.CountDownDateAddRequest;
import com.odk.apps.baseutil.response.countdown.CountDownBookListResponse;
import com.odk.apps.baseutil.response.countdown.CountDownDateListResponse;
import com.odk.base.vo.response.ServiceResponse;

/**
 * CountDownApi
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
public interface CountDownApi {

    ServiceResponse<String> addBook(CountDownBookAddRequest addRequest);

    ServiceResponse<CountDownBookListResponse> bookList();

    ServiceResponse<Boolean> addDate(CountDownDateAddRequest addRequest);

    ServiceResponse<CountDownDateListResponse> dateList(String bookId);

}
