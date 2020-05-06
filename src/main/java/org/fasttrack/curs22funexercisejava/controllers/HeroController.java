package org.fasttrack.curs22funexercisejava.controllers;

import org.fasttrack.curs22funexercisejava.domain.Hero;
import org.fasttrack.curs22funexercisejava.services.HeroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("heroes")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @GetMapping("{id}")
    public Hero getHeroById(@PathVariable Integer id) {
        return heroService.getHeroById(id);
    }

    @PostMapping
    public Hero addHero(@RequestBody Hero hero) {
        return heroService.addHero(hero);
    }

    @PutMapping("{id}")
    public Hero updateHero(@PathVariable Integer id, @RequestBody Hero hero) {
        return heroService.updateHero(id, hero);
    }

    @DeleteMapping("{id}")
    public Hero deleteHero(@PathVariable Integer id) {
        return heroService.deleteHero(id);
    }
}
