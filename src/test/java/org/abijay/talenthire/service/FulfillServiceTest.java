package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.FulfillDto;
import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.repository.FulfillRepository;
import org.abijay.talenthire.service.impl.FulfillServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FulfillServiceTest {

    @Mock
    private FulfillRepository mockFulfillRepository;

    @Mock
    private Fulfill mockFulfill;

    private FulfillService fulfillService;

    @BeforeEach
    void setUp() {
        fulfillService = new FulfillServiceImpl(mockFulfillRepository);
    }

    @Test
    public void happyPathFindAllFulfills() {
        // Given
        when(mockFulfillRepository.findAll()).thenReturn(List.of(mockFulfill));

        // When
        List<FulfillDto> actual = fulfillService.findAllFulfills();

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
    }
}
