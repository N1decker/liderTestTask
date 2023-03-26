package ru.nidecker.liderTestTask.util;

import ru.nidecker.liderTestTask.dto.TeamDto;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.entity.Team;

public class PrepareTeamTeamDto {

    public static Team teamDtoToTeam(TeamDto dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setDate(dto.getDate());

        return team;
    }

    public static Team teamDtoToTeam(TeamDto dto, SportType sportType) {
        Team team = teamDtoToTeam(dto);
        team.setSportType(sportType);

        return team;
    }

    public static TeamDto teamToTeamDto(Team team) {
        TeamDto dto = new TeamDto();
        dto.setName(team.getName());
        dto.setDate(team.getDate());

        return dto;
    }
}