/**
 *
 * * Filename:RequestServiceImpl.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.mapper.RequestMapper;
import org.abijay.talenthire.repository.FulfillRepository;
import org.abijay.talenthire.repository.RequestRepository;
import org.abijay.talenthire.repository.TalentRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.RequestService;
import org.abijay.talenthire.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    // inject dependencies
    private RequestRepository requestRepository;
    private TalentRepository talentRepository;
    private FulfillRepository fulfillRepository;
    private UserRepository userRepository;
    // Spring bean with single parameterized constructor- constructor based dependency injection


    public RequestServiceImpl(RequestRepository requestRepository, TalentRepository talentRepository, FulfillRepository fulfillRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.talentRepository = talentRepository;
        this.fulfillRepository = fulfillRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRequest(String talentUrl, RequestDto requestDto) {
        // one to many talent to request relationship
        Talent talent = talentRepository.findByUrl(talentUrl).get();
        Request request = RequestMapper.mapToRequest(requestDto);
        request.setTalent(talent);
        requestRepository.save(request);
        System.out.println("11111");
    }

    @Override
    public List<RequestDto> findAllRequests() {
        List<Request> requests = requestRepository.findAll();
        return requests.stream()
                .map(RequestMapper::mapToRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public Fulfill fulfillRequest(Long requestId) {
        Request request = requestRepository.findById(requestId).get();
        requestRepository.deleteById(requestId);
        System.out.println("Fest: "+request.getRequestMessage()+request.getTalent());
        Fulfill fulfill = new Fulfill();
        fulfill.setId(request.getId());
        fulfill.setRequestMessage(request.getRequestMessage());
        fulfill.setCreatedOn(request.getCreatedOn());
        fulfill.setEmail(request.getEmail());
        fulfill.setName(request.getName());
        fulfill.setUpdatedOn(request.getUpdatedOn());
        fulfill.setTalent(request.getTalent());
        fulfillRepository.save(fulfill);
        return fulfill;
    }

    @Override
    public void deleteRequest(Long requestId) {

        Request request = requestRepository.findById(requestId).get();
        requestRepository.deleteById(requestId);
    }

    @Override
    public List<RequestDto> findRequestsByTalent() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Request> requests = requestRepository.findRequestsByTalent(userId);
        return requests.stream()
                .map(RequestMapper::mapToRequestDto)
                .collect(Collectors.toList());
    }
}
