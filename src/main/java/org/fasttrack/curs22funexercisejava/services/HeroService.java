package org.fasttrack.curs22funexercisejava.services;

import org.fasttrack.curs22funexercisejava.domain.Hero;
import org.fasttrack.curs22funexercisejava.repositories.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public List<Hero> getAllHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroRepository.findAll().forEach(heroes::add);
        return heroes;
    }

    public Hero addHero(Hero hero) {
        heroRepository.save(hero);
        return hero;
    }

    public Hero deleteHero(Integer id) {
        Hero heroToDelete = getOrThrow(id);
        heroRepository.delete(heroToDelete);
        return heroToDelete;
    }

    public Hero getHeroById(Integer id) {
        return getOrThrow(id);
    }

    public Hero updateHero(Integer id, Hero hero) {
        Hero heroToUpdate = getOrThrow(id);
        heroToUpdate.setName(hero.getName());
        heroToUpdate.setSkill(hero.getSkill());
        heroToUpdate.setStamina(hero.getStamina());
        return heroToUpdate;
    }

    private Hero getOrThrow(Integer id) {
        return heroRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find hero with id " + id));
    }
}
