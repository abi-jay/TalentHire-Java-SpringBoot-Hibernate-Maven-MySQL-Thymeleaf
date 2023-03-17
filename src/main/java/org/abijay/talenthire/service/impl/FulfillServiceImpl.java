/**
 *
 * * Filename: FullfillServiceImpl.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.mapper.FulfillMapper;
import org.abijay.talenthire.mapper.RequestMapper;
import org.abijay.talenthire.repository.FulfillRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.FulfillService;
import org.abijay.talenthire.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FulfillServiceImpl implements FulfillService {
    private FulfillRepository fulfillRepository;
    private UserRepository userRepository;

    public FulfillServiceImpl(FulfillRepository fulfillRepository, UserRepository userRepository) {
        this.fulfillRepository = fulfillRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<FulfillDto> findAllFulfills() {
        List<Fulfill> fulfills = fulfillRepository.findAll();
        return fulfills.stream()
                        .map(FulfillMapper::mapToFulfillDto)
                                .collect(Collectors.toList());
    }

    @Override
    public void deleteClient(Long clientId) {
        fulfillRepository.deleteById(clientId);
    }

    @Override
    public List<FulfillDto> searchClientsByName(String query) {
        List<Fulfill> fulfills = fulfillRepository.searchClientsByName(query);
        return fulfills.stream()
                .map(FulfillMapper::mapToFulfillDto)
                .collect(Collectors.toList());

    }
    @Override
    public List<FulfillDto> findFulfillsByTalent() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Fulfill> fulfills = fulfillRepository.findFulfiillsByTalent(userId);
        return fulfills.stream()
                .map(FulfillMapper::mapToFulfillDto)
                .collect(Collectors.toList());
    }
}
