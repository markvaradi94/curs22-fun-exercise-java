package org.fasttrack.curs22funexercisejava.services;

import org.fasttrack.curs22funexercisejava.domain.Fight;
import org.fasttrack.curs22funexercisejava.domain.FightReader;
import org.fasttrack.curs22funexercisejava.domain.FightResult;
import org.fasttrack.curs22funexercisejava.domain.Hero;
import org.fasttrack.curs22funexercisejava.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

@Service
public class FightService {

    private final HeroService heroService;
    private final List<Fight> fights = new ArrayList<>();

    public FightService(HeroService heroService, FightReader reader) {
        this.heroService = heroService;
        reader.read()
                .forEach(this::addFight);
    }

    public List<Fight> getAllFights() {
        return Collections.unmodifiableList(fights);
    }

    public Fight addFight(Fight fight) {
        Fight newFight = new Fight(fetchLatestId(), fight.getFirstFighter(), fight.getSecondFighter());
        Fight fightWithResult = fightWithResult(newFight);
        fights.add(fightWithResult);
        return fightWithResult;
    }

    public Fight getFightById(Integer id) {
        return getAllFights().stream()
                .filter(fight -> fight.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Could not find fight with id " + id));
    }

    public List<Fight> fightsWithHeroInvolved(Integer heroId) {
        return getAllFights().stream()
                .filter(fight -> isInFight(fight, heroId))
                .collect(toList());
    }

    private Integer fetchLatestId() {
        Set<Integer> existingIds = fights.stream()
                .map(Fight::getId)
                .collect(toSet());
        return IntStream.iterate(1, i -> i + 1)
                .filter(id -> !existingIds.contains(id))
                .findFirst()
                .orElseThrow();
    }

    private boolean isInFight(Fight fight, Integer heroId) {
        return fight.getFirstFighter().equals(heroId) || fight.getSecondFighter().equals(heroId);
    }

    private Double generateDamage() {
        DecimalFormat df = new DecimalFormat("#.####");
        Random random = new Random();
        return Double.valueOf(df.format(random.nextDouble()));
    }

    private int damagedStamina(Hero hero1, Hero hero2) {
        double stamina = hero1.getStamina() - generateDamage() * hero2.getSkill();
        return stamina > 0 ? (int) stamina : 0;
    }

    private Fight fightWithResult(Fight fight) {
        Hero hero1 = heroService.getHeroById(fight.getFirstFighter());
        Hero hero2 = heroService.getHeroById(fight.getSecondFighter());
        Hero damagedHero1 = new Hero(hero1.getName(), hero1.getSkill(), damagedStamina(hero1, hero2));
        Hero damagedHero2 = new Hero(hero2.getName(), hero2.getSkill(), damagedStamina(hero2, hero1));
        FightResult result;

        if (damagedHero1.getStamina() > damagedHero2.getStamina()) {
            result = new FightResult(damagedHero1.getName() + ", remaining stamina = " + damagedHero1.getStamina(),
                    damagedHero2.getName() + ", remaining stamina = " + damagedHero2.getStamina());
            heroService.addWinToHero(hero1);
        } else if (damagedHero1.getStamina() < damagedHero2.getStamina()) {
            result = new FightResult(damagedHero2.getName() + ", remaining stamina = " + damagedHero2.getStamina(),
                    damagedHero1.getName() + ", remaining stamina = " + damagedHero1.getStamina());
            heroService.addWinToHero(hero2);
        } else {
            result = new FightResult("Tie" + ", remaining stamina = " + damagedHero1.getStamina(),
                    "Tie" + ", remaining stamina = " + damagedHero2.getStamina());
        }
        return new Fight(fetchLatestId(), fight.getFirstFighter(), fight.getSecondFighter(), result);
    }

}
