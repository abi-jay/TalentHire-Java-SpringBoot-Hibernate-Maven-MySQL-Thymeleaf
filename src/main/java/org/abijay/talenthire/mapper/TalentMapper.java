/**
 *
 * * Filename: TalentMapper.java
 * * 03/13/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.abijay.talenthire.mapper;

import org.abijay.talenthire.dto.TalentDto;
import org.abijay.talenthire.entity.Talent;

import java.util.stream.Collectors;

public class TalentMapper {

    // Map Talent entity to TalentDto
    public static TalentDto mapToTalentDto(Talent talent){
        return TalentDto.builder()
                .id(talent.getId())
                .talent(talent.getTalent())
                .url(talent.getUrl())
                .location(talent.getLocation())
                .shortDescription(talent.getShortDescription())
                .description(talent.getDescription())
                .memberSince(talent.getMemberSince())
                .rate(talent.getRate())
                .requests(talent.getRequests().stream()
                        .map(RequestMapper::mapToRequestDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    // Map TalentDto to talent entity
    public static Talent mapToTalent(TalentDto talentDto){
        return Talent.builder()
                .id(talentDto.getId())
                .talent(talentDto.getTalent())
                .url(talentDto.getUrl())
                .location(talentDto.getLocation())
                .shortDescription(talentDto.getShortDescription())
                .description(talentDto.getDescription())
                .location(talentDto.getLocation())
                .memberSince(talentDto.getMemberSince())
                .rate(talentDto.getRate())
                .build();
    }
}
