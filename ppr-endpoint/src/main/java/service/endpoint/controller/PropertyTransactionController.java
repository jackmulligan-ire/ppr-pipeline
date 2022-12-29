package service.endpoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.endpoint.model.PropertyTransaction;
import service.endpoint.repository.PropertyTransactionRepository;

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
  public ResponseEntity<List<PropertyTransaction>> getAllPropertyTransactions() {
    List<PropertyTransaction> propertyTransactions = new ArrayList<>();
    propertyTransactionRepository.findAll().forEach(propertyTransactions::add);
    return new ResponseEntity<>(propertyTransactions, HttpStatus.OK);
  }
}
