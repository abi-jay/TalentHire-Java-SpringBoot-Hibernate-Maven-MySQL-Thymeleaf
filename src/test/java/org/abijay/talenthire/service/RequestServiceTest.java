package org.abijay.talenthire.service;

import org.abijay.talenthire.dto.RequestDto;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.repository.FulfillRepository;
import org.abijay.talenthire.repository.RequestRepository;
import org.abijay.talenthire.repository.TalentRepository;
import org.abijay.talenthire.repository.UserRepository;
import org.abijay.talenthire.service.impl.RequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @Mock
    private RequestRepository mockRequestRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private TalentRepository mockTalentRepository;

    @Mock
    private FulfillRepository mockFulfillRepository;

    @Mock
    private Request mockRequest;

    private RequestService requestService;

    @BeforeEach
    void setUp() {
        requestService = new RequestServiceImpl(mockRequestRepository, mockTalentRepository, mockFulfillRepository, mockUserRepository);
    }

    @Test
    public void happyPathFindRequestAll() {
        //Given
        when(mockRequestRepository.findAll()).thenReturn(initializeRequest());

        // When
        List<RequestDto> actual = requestService.findAllRequests();

        // Then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());
    }

    private List<Request> initializeRequest() {
        return List.of(mockRequest);
    }
}


