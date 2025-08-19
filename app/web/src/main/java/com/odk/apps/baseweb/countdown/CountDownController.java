package com.odk.apps.baseweb.countdown;

import com.odk.apps.baseapi.inter.countdown.CountDownApi;
import com.odk.apps.baseutil.request.countdown.CountDownBookAddRequest;
import com.odk.apps.baseutil.request.countdown.CountDownDateAddRequest;
import com.odk.apps.baseutil.response.countdown.CountDownBookListResponse;
import com.odk.apps.baseutil.response.countdown.CountDownDateListResponse;
import com.odk.apps.baseweb.interceptor.tenantid.SupportTenantId;
import com.odk.base.vo.response.ServiceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CountDownController
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@SupportTenantId("ODK-COUNTDOWN")
@RestController
@RequestMapping("/countdown")
public class CountDownController {

    private CountDownApi countDownApi;

    @PostMapping("/book/add")
    ServiceResponse<String> addBook(@RequestBody @Valid CountDownBookAddRequest addRequest) {
        return countDownApi.addBook(addRequest);
    }

    @GetMapping("/book/list")
    ServiceResponse<CountDownBookListResponse> bookList() {
        return countDownApi.bookList();
    }

    @PostMapping("/date/add")
    ServiceResponse<Boolean> addDate(@RequestBody @Valid CountDownDateAddRequest addRequest) {
        return countDownApi.addDate(addRequest);
    }

    @GetMapping("/date/list")
    ServiceResponse<CountDownDateListResponse> dateList(@RequestParam(required = false) String bookId) {
        return countDownApi.dateList(bookId);
    }

    @Autowired
    public void setCountDownApi(CountDownApi countDownApi) {
        this.countDownApi = countDownApi;
    }
}
