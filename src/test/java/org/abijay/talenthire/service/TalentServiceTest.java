package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.repository.TalentRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.impl.TalentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TalentServiceTest {
    @Mock
    private TalentRepository mockTalentRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private Talent mockTalent;

    private TalentService talentService;

    @BeforeEach
    void setUp() {
        talentService = new TalentServiceImpl(mockTalentRepository, mockUserRepository);
    }

    @Test
    public void happyPathFindAll() {
        // Given
        when(mockTalentRepository.findAll()).thenReturn(List.of(mockTalent));

        // When
        List<TalentDto> actual = talentService.findAllTalents();

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
    }
}