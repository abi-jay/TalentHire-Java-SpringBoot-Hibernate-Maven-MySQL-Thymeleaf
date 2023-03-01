package com.perscholas.talenthire.service.impl;

import com.perscholas.talenthire.dto.TalentDto;
import com.perscholas.talenthire.entity.Talent;
import com.perscholas.talenthire.mapper.TalentMapper;
import com.perscholas.talenthire.repository.TalentRepository;
import com.perscholas.talenthire.service.TalentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Spring Service class is now registered as Spring Bean to the application context
@Service
public class TalentServiceImpl implements TalentService {

    // Constructor based Dependency injection to inject TalentRepository in a TalentServiceImpl class
    private TalentRepository talentRepository;
    // Whenever Spring Bean has single constructor, Spring will automatically inject the dependency
    public TalentServiceImpl(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    @Override
    public List<TalentDto> findAllTalents() {
        List<Talent> talents = talentRepository.findAll();
        return talents.stream().map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }
}
