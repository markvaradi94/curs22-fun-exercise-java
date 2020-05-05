package org.fasttrack.curs22funexercisejava.repositories;

import org.fasttrack.curs22funexercisejava.domain.Hero;
import org.springframework.data.repository.CrudRepository;

public interface HeroRepository extends CrudRepository<Hero, Integer> {
}
