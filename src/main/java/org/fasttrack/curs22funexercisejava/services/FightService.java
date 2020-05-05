package org.fasttrack.curs22funexercisejava.services;

import org.fasttrack.curs22funexercisejava.repositories.FightRepository;

public class FightService {

    private final FightRepository fightRepository;

    public FightService(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }


}
