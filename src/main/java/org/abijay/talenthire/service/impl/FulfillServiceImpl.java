package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.mapper.FulfillMapper;
import org.abijay.talenthire.mapper.RequestMapper;
import org.abijay.talenthire.mapper.TalentMapper;
import org.abijay.talenthire.repository.FulfillRepository;
import org.abijay.talenthire.service.FulfillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FulfillServiceImpl implements FulfillService {
    private FulfillRepository fulfillRepository;

    public FulfillServiceImpl(FulfillRepository fulfillRepository) {
        this.fulfillRepository = fulfillRepository;
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
}
