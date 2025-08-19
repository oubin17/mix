package com.odk.apps.baseservice.impl.countdown;

import com.odk.apps.baseapi.inter.countdown.CountDownApi;
import com.odk.apps.basedomain.mapper.countdown.CountDownDOMapper;
import com.odk.apps.basedomain.model.countdown.CountDownBookDO;
import com.odk.apps.basedomain.model.countdown.CountDownDateDO;
import com.odk.apps.basedomain.repository.couontdown.CountDownBookRepository;
import com.odk.apps.basedomain.repository.couontdown.CountDownDateRepository;
import com.odk.apps.baseservice.mix.AbstractApiImpl;
import com.odk.apps.baseutil.dto.countdown.CountDownBookDTO;
import com.odk.apps.baseutil.dto.countdown.CountDownDateDTO;
import com.odk.apps.baseutil.enums.BizScene;
import com.odk.apps.baseutil.mapper.countdown.CountDownRequestMapper;
import com.odk.apps.baseutil.request.countdown.CountDownBookAddRequest;
import com.odk.apps.baseutil.request.countdown.CountDownDateAddRequest;
import com.odk.apps.baseutil.response.countdown.CountDownBookListResponse;
import com.odk.apps.baseutil.response.countdown.CountDownDateListResponse;
import com.odk.base.exception.AssertUtil;
import com.odk.base.exception.BizErrorCode;
import com.odk.base.util.LocalDateUtil;
import com.odk.base.vo.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CountDownService
 *
 * @description:
 * @version: 1.0
 * @author: oubin on 2025/8/19
 */
@Slf4j
@Service
public class CountDownService  extends AbstractApiImpl implements CountDownApi {

    private CountDownRequestMapper countDownRequestMapper;

    private CountDownDOMapper countDownDOMapper;

    private CountDownBookRepository countDownBookRepository;

    private CountDownDateRepository countDownDateRepository;



    @Override
    public ServiceResponse<String> addBook(CountDownBookAddRequest addRequest) {
        return super.bizProcess(BizScene.COUNT_DOWN_BOOK_ADD, addRequest, new ApiCallBack<String, String>() {

            @Override
            protected Object convert(Object request) {
                return countDownRequestMapper.toDTO(addRequest);
            }

            @Override
            protected String doProcess(Object args) {
                CountDownBookDTO bookDTO = (CountDownBookDTO) args;
                CountDownBookDO bookDO = countDownDOMapper.toDO(bookDTO);
                return countDownBookRepository.save(bookDO).getId();
            }

            @Override
            protected String convertResult(String flag) {
                return flag;
            }

        });
    }

    @Override
    public ServiceResponse<CountDownBookListResponse> bookList() {
        return super.bizProcess(BizScene.COUNT_DOWN_BOOK_LIST, null, new ApiCallBack<List<CountDownBookDO>, CountDownBookListResponse>() {

            @Override
            protected List<CountDownBookDO> doProcess(Object args) {
                return countDownBookRepository.findAll();
            }

            @Override
            protected CountDownBookListResponse convertResult(List<CountDownBookDO> flag) {
                CountDownBookListResponse response = new CountDownBookListResponse();
                response.setBookDTOList(countDownDOMapper.toDTO(flag));
                return response;
            }

        });
    }

    @Override
    public ServiceResponse<Boolean> addDate(CountDownDateAddRequest addRequest) {
        return super.bizProcess(BizScene.COUNT_DOWN_DATE_ADD, addRequest, new ApiCallBack<Boolean, Boolean>() {

            @Override
            protected Object convert(Object request) {
                return countDownRequestMapper.toDTO(addRequest);
            }

            @Override
            protected Boolean doProcess(Object args) {
                CountDownDateDTO dateDTO = (CountDownDateDTO) args;
                CountDownDateDO bookDO = countDownDOMapper.toDO(dateDTO);
                AssertUtil.isTrue(countDownBookRepository.findById(bookDO.getBookId()).isPresent(), BizErrorCode.PARAM_ILLEGAL);
                countDownDateRepository.save(bookDO);
                return true;
            }

            @Override
            protected Boolean convertResult(Boolean flag) {
                return flag;
            }

        });
    }

    @Override
    public ServiceResponse<CountDownDateListResponse> dateList(String bookId) {
        return super.bizProcess(BizScene.COUNT_DOWN_DATE_LIST, bookId, new ApiCallBack<List<CountDownDateDO>, CountDownDateListResponse>() {

            @Override
            protected List<CountDownDateDO> doProcess(Object args) {
                if (StringUtils.isBlank(bookId)) {
                    return countDownDateRepository.findAll();
                } else {
                    AssertUtil.isTrue(countDownBookRepository.findById(bookId).isPresent(), BizErrorCode.PARAM_ILLEGAL);
                    return countDownDateRepository.findByBookId(bookId);
                }

            }

            @Override
            protected CountDownDateListResponse convertResult(List<CountDownDateDO> flag) {
                CountDownDateListResponse response = new CountDownDateListResponse();
                List<CountDownDateDTO> dateDTO = countDownDOMapper.toDateDTO(flag);
                dateDTO = dateDTO.stream().peek(date -> {
                    LocalDate targetDate = LocalDateUtil.parseStringToDate(date.getTargetDate(), "yyyy-MM-dd");
                    date.setLeftDays((int)LocalDateUtil.calculateDaysDifference(LocalDate.now(), targetDate));
                }).collect(Collectors.toList());

                response.setDateDTOList(dateDTO);
                return response;
            }

        });
    }

    @Autowired
    public void setCountDownMapper(CountDownRequestMapper countDownRequestMapper) {
        this.countDownRequestMapper = countDownRequestMapper;
    }

    @Autowired
    public void setCountDownDOMapper(CountDownDOMapper countDownDOMapper) {
        this.countDownDOMapper = countDownDOMapper;
    }

    @Autowired
    public void setCountDownBookRepository(CountDownBookRepository countDownBookRepository) {
        this.countDownBookRepository = countDownBookRepository;
    }

    @Autowired
    public void setCountDownDateRepository(CountDownDateRepository countDownDateRepository) {
        this.countDownDateRepository = countDownDateRepository;
    }
}
