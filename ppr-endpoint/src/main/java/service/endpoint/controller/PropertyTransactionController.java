package service.endpoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.endpoint.model.PropertyTransaction;
import service.endpoint.repository.PropertyTransactionRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PropertyTransactionController {

  private final PropertyTransactionRepository propertyTransactionRepository;

  public PropertyTransactionController(PropertyTransactionRepository propertyTransactionRepository) {
    this.propertyTransactionRepository = propertyTransactionRepository;
  }

  @GetMapping("/property-transactions")
  public ResponseEntity<List<PropertyTransaction>> getPropertyTransactions(
          @RequestParam(required = false) Date startDate,
          @RequestParam(required = false) Date endDate
  ) {
    List<PropertyTransaction> propertyTransactions = new ArrayList<>();

    if (startDate != null && endDate != null) {
      if (startDate.after(endDate)) return new ResponseEntity<>(propertyTransactions, HttpStatus.BAD_REQUEST);
      propertyTransactionRepository.findByDateDetails_DateBetween(startDate, endDate).forEach(propertyTransactions::add);
    }
    else if (endDate != null) propertyTransactionRepository.findByDateDetails_DateBetween(Date.valueOf("2009-12-31"), endDate).forEach(propertyTransactions::add);
    else if (startDate != null) {
      String todaysDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
      propertyTransactionRepository.findByDateDetails_DateBetween(startDate, Date.valueOf(todaysDate)).forEach(propertyTransactions::add);
    }
    else propertyTransactionRepository.findAll().forEach(propertyTransactions::add);

    return new ResponseEntity<>(propertyTransactions, HttpStatus.OK);
  }
}
