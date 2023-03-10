package org.abijay.talenthire.repository;


import org.abijay.talenthire.entity.Talent;
import org.abijay.talenthire.entity.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    static User testUser;

    @BeforeAll
    public static  void setUp(){
        testUser = new User();
        testUser.setName("Tezter");
        testUser.setEmail("tezter@gmail.com");
        testUser.setPassword("123");

    }

    @Test
    public void findByUrl() {
        userRepository.save(testUser);
        Talent talent = Talent.builder()
                .talent("Painting")
                .description("Painting classes offered")
                .shortDescription("Online classes")
                .rate(BigDecimal.valueOf(120.00))
                .location("Online")
                .url("painting")
                .createdBy(testUser)
                .build();

        talentRepository.save(talent);
        Assertions.assertNotNull(talent);
        talentRepository.delete(talent);
        userRepository.delete(testUser);
    }

    @Test
    void findByTalent() {
    }

    @Test
    void searchTalents() {
    }

    @Test
    void findTalentsByUser() {
    }

}