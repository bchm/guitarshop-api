package nl.inholland.guitarshopapi.dao;

import nl.inholland.guitarshopapi.model.Color;
import nl.inholland.guitarshopapi.model.Guitar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
}
