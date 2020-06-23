package nl.inholland.guitarshopapi.configuration;

import nl.inholland.guitarshopapi.dao.ColorRepository;
import nl.inholland.guitarshopapi.dao.GuitarRepository;
import nl.inholland.guitarshopapi.dao.StockRepository;
import nl.inholland.guitarshopapi.model.Color;
import nl.inholland.guitarshopapi.model.Guitar;
import nl.inholland.guitarshopapi.model.Stock;
import nl.inholland.guitarshopapi.service.GuitarService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@ConditionalOnProperty(prefix = "guitarshop.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
@Transactional
public class MyApplicationRunner implements ApplicationRunner {

  private GuitarRepository guitarRepository;
  private StockRepository stockRepository;
  private ColorRepository colorRepository;

  public MyApplicationRunner(GuitarRepository guitarRepository, StockRepository stockRepository, ColorRepository colorRepository) {
    this.guitarRepository = guitarRepository;
    this.stockRepository = stockRepository;
    this.colorRepository = colorRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    List<Color> colors =
            Arrays.asList(
                    new Color("yellow", 1000001L),
                    new Color("red", 1000001L),
                    new Color("blue", 1000001L));
    colors.forEach(colorRepository::save);
    List<Guitar> guitars =
        Arrays.asList(
            new Guitar("Fender", "Telecaster", 899),
            new Guitar("Fender", "Stratocaster", 1299),
            new Guitar("Gibson", "Les Paul", 2999));

    guitars.forEach(guitarRepository::save);


//    guitarRepository.findAll().forEach(System.out::println);

    guitarRepository
        .findAll()
        .forEach(guitar -> stockRepository.save(new Stock(new Random().nextInt(100), guitar)));

//    stockRepository.findAll().forEach(System.out::println);

    Iterable<Stock> stocks = stockRepository.getAllByQuantityGreaterThanOrderByQuantity(30);
//    stocks.forEach(System.out::println);
    stocks = stockRepository.getAllByIdGreaterThan(50000002L);
//    System.out.println("my own: " + stocks.toString());
    int quantity = stockRepository.getStockValueByGuitarId(1000001L);
//    System.out.println("Quantity: " + quantity);
    System.out.println(StreamSupport
            .stream(guitarRepository.getAllByPriceGreaterThanEqualOrderById(200.00).spliterator(), false)
            .collect(Collectors.toList()).toString());
  }
}
