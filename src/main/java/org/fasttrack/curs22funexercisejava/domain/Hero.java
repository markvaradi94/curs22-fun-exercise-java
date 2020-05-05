package org.fasttrack.curs22funexercisejava.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer skill;
    private Integer stamina;
    private Integer wins;

    public Hero(String name, Integer skill, Integer stamina, Integer wins) {
        this.name = name;
        this.skill = skill;
        this.stamina = stamina;
        this.wins = wins;
    }
}
