package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.TalentDto;

import java.util.List;

public interface TalentService {
    List<TalentDto> findAllTalents();

    List<TalentDto> findTalentsByUser();
    // method to create new client in database with the dto provided
    void createClient(TalentDto talentDto);
    TalentDto findClientById(Long clientId);
    void updateClient(TalentDto talentDto);
    void deleteClient(Long clientId);

    List<TalentDto> searchClientsByName(String query);
    List<TalentDto> searchClientsByLocation(String query);
    List<TalentDto> searchClientsByTalent(String query);

    TalentDto findTalentByUrl(String talentUrl);

    List<TalentDto> searchTalents(String query);

    void createTalent(TalentDto talentDto);
    TalentDto findTalentById(Long talentId);
    void updateTalent(TalentDto talentDto);
    void deleteTalent(Long talentId);

}




