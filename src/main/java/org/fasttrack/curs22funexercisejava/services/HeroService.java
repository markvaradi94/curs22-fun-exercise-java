package org.fasttrack.curs22funexercisejava.services;

import org.fasttrack.curs22funexercisejava.domain.Hero;
import org.fasttrack.curs22funexercisejava.domain.HeroReader;
import org.fasttrack.curs22funexercisejava.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

@Service
public class HeroService {

    private final List<Hero> heroes = new ArrayList<>();

    public HeroService(HeroReader reader) {
        reader.read()
                .forEach(this::addHero);
    }

    public List<Hero> getAllHeroes() {
        return Collections.unmodifiableList(heroes);
    }

    public Hero addHero(Hero hero) {
        Hero newHero = new Hero(fetchLatestId(), hero.getName(), hero.getSkill(), hero.getStamina());
        addHeroInList(newHero);
        return newHero;
    }

    public Hero deleteHero(Integer id) {
        Hero heroToDelete = getOrThrow(id);
        heroes.remove(heroToDelete);
        return heroToDelete;
    }

    public Hero getHeroById(Integer id) {
        return getOrThrow(id);
    }

    public Hero updateHero(Integer id, Hero hero) {
        Hero heroToUpdate = getOrThrow(id);
        heroes.remove(heroToUpdate);
        Hero newHero = new Hero(id, hero.getName(), hero.getSkill(), hero.getStamina(), heroToUpdate.getWins());
        addHeroInList(newHero);
        return newHero;
    }

    public void addWinToHero(Hero hero) {
        hero.addWin();
    }

    private Hero getOrThrow(Integer id) {
        return heroes.stream()
                .filter(hero -> hero.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Could not find hero with id " + id));
    }

    private void addHeroInList(Hero newHero) {
        heroes.add(newHero.getId() - 1, newHero);
    }

    private int fetchLatestId() {
        final Set<Integer> existingIds = heroes.stream()
                .map(Hero::getId)
                .collect(toSet());
        return IntStream.iterate(1, i -> i + 1)
                .filter(id -> !existingIds.contains(id))
                .findFirst()
                .orElseThrow();
    }
}
