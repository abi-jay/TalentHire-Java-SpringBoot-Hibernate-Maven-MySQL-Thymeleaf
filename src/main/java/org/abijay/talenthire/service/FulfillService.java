package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.dto.RequestDto;

import java.util.List;

public interface FulfillService {
    List<FulfillDto> findAllFulfills();

    void deleteClient(Long clientId);

    List<FulfillDto> searchClientsByName(String query);
}
