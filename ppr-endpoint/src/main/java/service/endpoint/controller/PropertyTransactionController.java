package service.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.endpoint.model.PropertyTransaction;
import service.endpoint.model.PropertyTransactionStats;
import service.endpoint.repository.PropertyTransactionRepository;
import service.endpoint.repository.PropertyTransactionStatsRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PropertyTransactionController {

  @Autowired
  PropertyTransactionRepository propertyTransactionRepository;

  @Autowired
  PropertyTransactionStatsRepository propertyTransactionStatsRepository;

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

  @GetMapping("/property-transaction-stats")
  public ResponseEntity<List<PropertyTransactionStatsFormatted>> getPropertyTransactionStats() {
    List<PropertyTransactionStats> propertyTransactionStats = propertyTransactionStatsRepository.findAll();
    List<PropertyTransactionStatsFormatted> formattedStats = formatPropertyTransactionStats(propertyTransactionStats);
    return new ResponseEntity<>(formattedStats, HttpStatus.OK);
  }

  private List<PropertyTransactionStatsFormatted> formatPropertyTransactionStats(List<PropertyTransactionStats> statsList) {
    List<PropertyTransactionStatsFormatted> formattedStats = new ArrayList<>();

    Set<String> uniqueLocations = new TreeSet<>();
    statsList.stream().filter(pts -> uniqueLocations.add(pts.getLocation())).collect(Collectors.toList());

    for (String location: uniqueLocations) {
      PropertyTransactionStatsFormatted locationOutput = new PropertyTransactionStatsFormatted();
      locationOutput.setLocation(location);

      boolean firstMatch = false;
      for (PropertyTransactionStats stats: statsList) {
        if (stats.getLocation().equals(location)) {
          if (!firstMatch) firstMatch = true;
          PropertyTransactionStatsFormattedData statsData = new PropertyTransactionStatsFormattedData(
                  stats.getYear(),
                  stats.getMonth(),
                  stats.getMedianPrice(),
                  stats.getMeanPrice(),
                  stats.getTransactions()
          );
          locationOutput.getData().add(statsData);
        }
        else if (firstMatch) break;
      }
      formattedStats.add(locationOutput);
    }

    return formattedStats;
  }
}
