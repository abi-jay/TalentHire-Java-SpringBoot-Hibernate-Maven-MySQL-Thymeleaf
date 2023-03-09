package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.entity.Fulfill;

import java.util.List;

public interface RequestService {
    void createRequest(String talentUrl, RequestDto requestDto);

    List<RequestDto> findAllRequests();

    Fulfill fulfillRequest(Long requestId);
}
