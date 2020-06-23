package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.dao.GuitarRepository;
import nl.inholland.guitarshopapi.dao.StockRepository;
import nl.inholland.guitarshopapi.model.Color;
import nl.inholland.guitarshopapi.model.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GuitarService {

  @Autowired
  private GuitarRepository guitarRepository;

  @Autowired
  private StockRepository stockRepository;

  public GuitarService() {
  }

  public List<Guitar> getAllGuitars() {
    return StreamSupport
            .stream(guitarRepository.getAllByPriceGreaterThanEqualOrderById(200.00).spliterator(), false)
            .collect(Collectors.toList());
  }

  public List<Color> getAllColorsOfGuitar() {
    List<Guitar> guitars = StreamSupport
            .stream(guitarRepository.getAllByPriceGreaterThanEqualOrderById(200.00).spliterator(), false)
            .collect(Collectors.toList());
    List<Color> colors = guitars.get(0).getColors();
    colors.addAll(guitars.get(1).getColors());
    colors.addAll(guitars.get(2).getColors());

    return colors;
  }

  public Guitar getGuitarById(long id) {
    return guitarRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  public void addGuitar(Guitar guitar) {
    guitarRepository.save(guitar);
    System.out.println(guitar);
  }

  public int valueByGuitarId(long id) {
    return stockRepository.getStockValueByGuitarId(id);
  }

  public List<Guitar> getGuitarsByMinimumPrice(double minimum) {
    return guitarRepository.getAllByPriceGreaterThanEqualOrderById(minimum);
  }
}
