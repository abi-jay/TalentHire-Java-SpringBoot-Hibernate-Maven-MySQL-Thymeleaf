package com.perscholas.talenthire.service;

import com.perscholas.talenthire.dto.TalentDto;

import java.util.List;

public interface TalentService {
    List<TalentDto> findAllTalents();
    // method to create new client in database with the dto provided
    void createClient(TalentDto talentDto);
    TalentDto findClientById(Long clientId);
    void updateClient(TalentDto talentDto);
    void deleteClient(Long clientId);

    List<TalentDto> searchClients(String query);
}




