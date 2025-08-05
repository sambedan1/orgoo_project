package com.tuespotsolutions.service;
import com.tuespotsolutions.entity.Hr;
import com.tuespotsolutions.models.HrRequest;
import com.tuespotsolutions.models.HrResponse;

import java.util.List;

public interface HrService {
    HrResponse saveHr(HrRequest hrRequest);
    HrResponse getHrById(Long id);
    List<HrResponse> getAllHrs();
    HrResponse updateHr(Long id,HrRequest hrRequest);
    void deleteHr(Long id);
}
