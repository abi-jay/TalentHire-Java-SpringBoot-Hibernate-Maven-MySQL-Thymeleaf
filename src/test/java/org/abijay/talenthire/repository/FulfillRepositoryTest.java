package org.abijay.talenthire.repository;

import org.abijay.talenthire.entity.Fulfill;
import org.abijay.talenthire.entity.Request;
import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class FulfillRepositoryTest {
    @Autowired
    private FulfillRepository fulfillRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TalentRepository talentRepository;

    @Test
    public void findRequestsByTalent() {
        User testUser = new User();
        testUser.setName("Tezter");
        testUser.setEmail("tezter@gmail.com");
        testUser.setPassword("123");
        userRepository.save(testUser);
        Talent expectedTalent = Talent.builder()
                .talent("Painting")
                .description("Painting classes offered")
                .shortDescription("Online classes")
                .rate(BigDecimal.valueOf(120.00))
                .location("Online")
                .url("painting")
                .createdBy(testUser)
                .build();
        talentRepository.save(expectedTalent);
        Fulfill expectedFulfill = Fulfill.builder()
                .name("Katie Perry")
                .email("katie@gmail.com")
                .requestMessage("Hi I am interested!")
                .talent(expectedTalent)
                .build();
        fulfillRepository.save(expectedFulfill);
        Fulfill actualFulfill = fulfillRepository.searchClientsByName(expectedFulfill.getName()).get(0);
        Assertions.assertEquals(expectedFulfill.getTalent().getTalent(),actualFulfill.getTalent().getTalent());
        fulfillRepository.delete(expectedFulfill);
        talentRepository.delete(expectedTalent);
        userRepository.delete(testUser);
    }
}

