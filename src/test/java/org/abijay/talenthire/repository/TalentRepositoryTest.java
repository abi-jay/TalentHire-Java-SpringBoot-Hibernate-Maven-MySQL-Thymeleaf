package org.abijay.talenthire.repository;


import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
public class TalentRepositoryTest {

    @Autowired
    private TalentRepository talentRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUrl() {
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
        Talent actualTalent = talentRepository.findByUrl("painting").get();
        Assertions.assertEquals(expectedTalent.getTalent(),actualTalent.getTalent());
        talentRepository.delete(expectedTalent);
        userRepository.delete(testUser);
    }

    @Test
    void findByTalent() {
        User testUser = new User();
        testUser.setName("Tezter");
        testUser.setEmail("tezter@gmail.com");
        testUser.setPassword("123");
        userRepository.save(testUser);
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
        Talent actualTalent = talentRepository.findByTalent("Painting").get();
        Assertions.assertEquals(expectedTalent.getTalent(),actualTalent.getTalent());
        talentRepository.delete(expectedTalent);
        userRepository.delete(testUser);
    }

    @Test
    void searchTalents() {
        User testUser = new User();
        testUser.setName("Tezter");
        testUser.setEmail("tezter@gmail.com");
        testUser.setPassword("123");
        userRepository.save(testUser);
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
        Talent actualTalent = talentRepository.searchTalents("Painting").get(0);
        Assertions.assertEquals(expectedTalent.getTalent(),actualTalent.getTalent());
        talentRepository.delete(expectedTalent);
        userRepository.delete(testUser);

    }

    @Test
    void findTalentsByUser() {
        User testUser = new User();
        testUser.setName("Tezter");
        testUser.setEmail("tezter@gmail.com");
        testUser.setPassword("123");
        userRepository.save(testUser);
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
        Talent actualTalent = talentRepository.findTalentsByUser(testUser.getId()).get(0);
        Assertions.assertEquals(expectedTalent.getTalent(),actualTalent.getTalent());
        talentRepository.delete(expectedTalent);
        userRepository.delete(testUser);
    }

}