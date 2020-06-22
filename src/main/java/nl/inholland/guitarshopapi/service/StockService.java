package nl.inholland.guitarshopapi.service;

import nl.inholland.guitarshopapi.dao.StockRepository;
import nl.inholland.guitarshopapi.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StockService {

  @Autowired
  private StockRepository stockRepository;

  public List<Stock> getAllStocks() {
    return StreamSupport
            .stream(stockRepository.getAllByIdGreaterThan(1000001L).spliterator(), false)
            .collect(Collectors.toList());
  }
}
