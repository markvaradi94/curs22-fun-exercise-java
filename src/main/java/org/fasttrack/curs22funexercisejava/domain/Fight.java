package org.fasttrack.curs22funexercisejava.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Fight {

    private Integer id;
    private Integer firstFighter;
    private Integer secondFighter;
    private FightResult result;

    public Fight(@JsonProperty Integer firstFighter,
                 @JsonProperty Integer secondFighter) {
        this(0, firstFighter, secondFighter, new FightResult());
    }

    public Fight(Integer id, Integer firstFighter, Integer secondFighter) {
        this(id, firstFighter, secondFighter, new FightResult());
    }
}
