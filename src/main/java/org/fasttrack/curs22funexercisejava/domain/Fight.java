package org.fasttrack.curs22funexercisejava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Hero firstFighter;
    private Hero secondFighter;
    private FightResult result;

    public Fight(Hero firstFighter, Hero secondFighter, FightResult result) {
        this.firstFighter = firstFighter;
        this.secondFighter = secondFighter;
        this.result = result;
    }
}
