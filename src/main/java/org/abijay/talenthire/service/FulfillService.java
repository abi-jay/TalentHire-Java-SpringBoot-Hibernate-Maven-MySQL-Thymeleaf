/**
 *
 * * Filename: FullfillService.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.dto.RequestDto;

import java.util.List;

public interface FulfillService {
    List<FulfillDto> findAllFulfills();
    void deleteClient(Long clientId);
    List<FulfillDto> searchClientsByName(String query);
    List<FulfillDto> findFulfillsByTalent();
}
