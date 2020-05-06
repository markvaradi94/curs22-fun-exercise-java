package org.fasttrack.curs22funexercisejava.controllers;

import org.fasttrack.curs22funexercisejava.domain.Fight;
import org.fasttrack.curs22funexercisejava.services.FightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fights")
public class FightController {

    private final FightService fightService;

    public FightController(FightService fightService) {
        this.fightService = fightService;
    }

    @GetMapping
    public List<Fight> getAllFights(@RequestParam(required = false) Integer heroId) {
        if (heroId == null) {
            return fightService.getAllFights();
        } else {
            return fightService.fightsWithHeroInvolved(heroId);
        }
    }

    @GetMapping("{id}")
    public Fight getFightById(@PathVariable Integer id) {
        return fightService.getFightById(id);
    }

    @PostMapping
    public Fight createFight(@RequestBody Fight fight) {
        return fightService.addFight(fight);
    }

    @PostMapping("/create")
    public Fight createByHeroIds(@RequestParam(required = false) Integer firstFighter,
                                 @RequestParam(required = false) Integer secondFighter) {
        return fightService.addFight(new Fight(firstFighter, secondFighter));
    }


}
