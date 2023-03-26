package ru.nidecker.liderTestTask.util;

import ru.nidecker.liderTestTask.dto.TeamDto;
import ru.nidecker.liderTestTask.entity.Team;

public class PrepareTeamTeamDto {

    public static Team teamDtoToTeam(TeamDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setDate(dto.getDate());
        team.setTypeOfSport(dto.getTypeOfSport());

        return team;
    }

    public static TeamDto teamToTeamDto(Team team) {
        TeamDto dto = new TeamDto();
        dto.setName(team.getName());
        dto.setDate(team.getDate());
        dto.setTypeOfSport(team.getTypeOfSport());

        return dto;
    }
}
