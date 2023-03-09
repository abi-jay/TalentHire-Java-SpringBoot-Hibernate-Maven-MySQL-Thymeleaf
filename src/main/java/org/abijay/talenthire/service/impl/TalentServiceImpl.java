package org.abijay.talenthire.service.impl;

import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.entity.User;
import org.abijay.talenthire.mapper.TalentMapper;
import org.abijay.talenthire.repository.TalentRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.TalentService;
import org.abijay.talenthire.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Spring Service class is now registered as Spring Bean to the application context
@Service
public class TalentServiceImpl implements TalentService {

    // Constructor based Dependency injection to inject TalentRepository in a TalentServiceImpl class
    private TalentRepository talentRepository;
    private UserRepository userRepository;

    // Whenever Spring Bean has single constructor, Spring will automatically inject the dependency


    public TalentServiceImpl(TalentRepository talentRepository, UserRepository userRepository) {
        this.talentRepository = talentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TalentDto> findAllTalents() {
        List<Talent> talents = talentRepository.findAll();
        return talents.stream().map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }

    // get list of talents of logged in person
    @Override
    public List<TalentDto> findTalentsByUser() {
        // get email address of logged in person
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Talent> talents = talentRepository.findTalentsByUser(userId);
        return talents.stream()
                .map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createClient(TalentDto talentDto) {
        // get the logged person's email address
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        // set user object to talent object
        // create talent JPA entity
        Talent talent = TalentMapper.mapToTalent(talentDto);
        talent.setCreatedBy(user);
        talentRepository.save(talent);
    }

    @Override
    public void createTalent(TalentDto talentDto) {
        // get the logged person's email address
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        // set user object to talent object
        // create talent JPA entity
        Talent talent = TalentMapper.mapToTalent(talentDto);
        talent.setCreatedBy(user);
        talentRepository.save(talent);
    }

    @Override
    public TalentDto findClientById(Long clientId) {
        Talent talent = talentRepository.findById(clientId).get();
        // talent JPA entity to talentDTO
        return TalentMapper.mapToTalentDto(talent);
    }
    @Override
    public TalentDto findTalentById(Long talentId) {
        Talent talent = talentRepository.findById(talentId).get();
        // talent JPA entity to talentDTO
        return TalentMapper.mapToTalentDto(talent);
    }
    @Override
    public void updateClient(TalentDto talentDto) {
        // email address of the logged in person
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        // set the logged in user object to talent object
        Talent talent = TalentMapper.mapToTalent(talentDto);
        talent.setCreatedBy(createdBy);
        // if talent object contains id (primary key), then save method will update record
        // else it will save the new record
        talentRepository.save(talent);
    }
    @Override
    public void updateTalent(TalentDto talentDto) {
        // email address of the logged in person
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        // set the logged in user object to talent object
        Talent talent = TalentMapper.mapToTalent(talentDto);
        talent.setCreatedBy(createdBy);
        // if talent object contains id (primary key), then save method will update record
        // else it will save the new record
        talentRepository.save(talent);
    }

    @Override
    public void deleteClient(Long clientId) {

        talentRepository.deleteById(clientId);
    }
    @Override
    public void deleteTalent(Long talentId) {
        talentRepository.deleteById(talentId);
    }

    @Override
    public TalentDto findTalentByUrl(String talentUrl) {
        Talent talent = talentRepository.findByUrl(talentUrl).get();
        return TalentMapper.mapToTalentDto(talent);
    }

    @Override
    public List<TalentDto> searchTalents(String query) {
        List<Talent> talents = talentRepository.searchTalents(query);
        System.out.println("Talents: "+ talents);
        return talents.stream()
                .map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TalentDto> searchClientsByName(String query) {
        List<Talent> talents = talentRepository.searchClientsByName(query);
        // convert list of talent entities to talent dto
        return talents.stream()
                .map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TalentDto> searchClientsByLocation(String query) {
        List<Talent> talents = talentRepository.searchClientsByLocation(query);
        return talents.stream()
                .map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TalentDto> searchClientsByTalent(String query) {
        List<Talent> talents = talentRepository.searchClientsByTalent(query);
        return talents.stream()
                .map(TalentMapper::mapToTalentDto)
                .collect(Collectors.toList());
    }


}
