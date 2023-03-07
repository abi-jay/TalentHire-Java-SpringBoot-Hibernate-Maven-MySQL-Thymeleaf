package org.abijay.talenthire.mapper;

import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.entity.Talent;

public class TalentMapper {

    // Map Talent entity to TalentDto
    public static TalentDto mapToTalentDto(Talent talent){
        return TalentDto.builder()
                .id(talent.getId())
                .talent(talent.getTalent())
                .email(talent.getEmail())
                .url(talent.getUrl())
                .name(talent.getName())
                .location(talent.getLocation())
                .introduction(talent.getIntroduction())
                .memberSince(talent.getMemberSince())
                .rate(talent.getRate())
                .build();
    }

    // Map TalentDto to talent entity
    public static Talent mapToTalent(TalentDto talentDto){
        return Talent.builder()
                .id(talentDto.getId())
                .talent(talentDto.getTalent())
                .email(talentDto.getEmail())
                .url(talentDto.getUrl())
                .name(talentDto.getName())
                .location(talentDto.getLocation())
                .introduction(talentDto.getIntroduction())
                .location(talentDto.getLocation())
                .memberSince(talentDto.getMemberSince())
                .rate(talentDto.getRate())
                .build();
    }
}
