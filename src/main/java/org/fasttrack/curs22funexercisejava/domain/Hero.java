package org.fasttrack.curs22funexercisejava.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Hero {


    private Integer id;
    private String name;
    private Integer skill;
    private Integer stamina;
    private Integer wins = 0;

    public Hero(
            @JsonProperty("name") String name,
            @JsonProperty("skill") Integer skill,
            @JsonProperty("stamina") Integer stamina) {
        this(0, name, skill, stamina);
    }

    public Hero(Integer id, String name, Integer skill, Integer stamina) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.stamina = stamina;
    }

    public void addWin() {
        this.wins++;
    }
}
