package service.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import service.endpoint.controller.PropertyTransactionController;
import service.endpoint.model.DateDetails;
import service.endpoint.model.Location;
import service.endpoint.model.PropertyDetails;
import service.endpoint.model.PropertyTransaction;
import service.endpoint.repository.PropertyTransactionRepository;
import service.endpoint.repository.PropertyTransactionStatsRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyTransactionController.class)
public class PropertyTransactionControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  PropertyTransactionRepository propertyTransactionRepository;

  @MockBean
  PropertyTransactionStatsRepository propertyTransactionstatsRepository;

  PropertyTransaction RECORD_1 = new PropertyTransaction(
          1L,
          new DateDetails(Date.valueOf("2023-01-10"), 1, 2023),
          new Location(1L, "Carlow", "Leinster"),
          new PropertyDetails(1L, "New Dwelling house /Apartment"),
          157709.25F);

  PropertyTransaction RECORD_2 = new PropertyTransaction(
          2L,
          new DateDetails(Date.valueOf("2023-01-09"), 1, 2023),
          new Location(2L, "Wexford", "Leinster"),
          new PropertyDetails(2L, "New Dwelling house /Apartment"),
          257709.25F);

  List<PropertyTransaction> TRANSACTIONS = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2));

  String ENDPOINT = "/api/property-transactions";

  @Test
  public void getAllTransactions_success() throws Exception {


    Mockito.when(propertyTransactionRepository.findAll()).thenReturn(TRANSACTIONS);

    mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].dateDetails.date", is("2023-01-10")))
            .andExpect(jsonPath("$[0].location.county", is("Carlow")))
            .andExpect(jsonPath("$[0].location.province", is("Leinster")))
            .andExpect(jsonPath("$[0].propertyDetails.buildType", is("New Dwelling house /Apartment")))
            .andExpect(jsonPath("$[0].transactionPrice", is(157709.25)));
  }

  @Test
  public void getTransactionsAfterStartDate_success() throws Exception {
    String todaysDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());

    Mockito
            .when(propertyTransactionRepository.findByDateDetails_DateBetween(Date.valueOf("2022-12-31"), Date.valueOf(todaysDate)))
            .thenReturn(TRANSACTIONS);

    mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT + "?startDate=2022-12-31")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].dateDetails.date", is("2023-01-10")))
            .andExpect(jsonPath("$[0].location.county", is("Carlow")))
            .andExpect(jsonPath("$[0].location.province", is("Leinster")))
            .andExpect(jsonPath("$[0].propertyDetails.buildType", is("New Dwelling house /Apartment")))
            .andExpect(jsonPath("$[0].transactionPrice", is(157709.25)));
  }

  @Test
  public void getTransactionsBeforeEndDate_success() throws Exception {
    PropertyTransaction testTransaction = new PropertyTransaction(
            1L,
            new DateDetails(Date.valueOf("2022-12-10"), 12, 2022),
            new Location(1L, "Wexford", "Leinster"),
            new PropertyDetails(1L, "New Dwelling house /Apartment"),
            257709.25F);

    List<PropertyTransaction> testTransactionList = new ArrayList<>(Arrays.asList(testTransaction));

    Mockito
            .when(propertyTransactionRepository.findByDateDetails_DateBetween(Date.valueOf("2009-12-31"), Date.valueOf("2022-12-31")))
            .thenReturn(testTransactionList);

    mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT + "?endDate=2022-12-31")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(testTransactionList.size())))
            .andExpect(jsonPath("$[0].dateDetails.date", is(testTransaction.getDateDetails().getDate().toString())))
            .andExpect(jsonPath("$[0].location.county", is(testTransaction.getLocation().getCounty())))
            .andExpect(jsonPath("$[0].location.province", is(testTransaction.getLocation().getProvince())))
            .andExpect(jsonPath("$[0].propertyDetails.buildType", is(testTransaction.getPropertyDetails().getBuildType())))
            .andExpect(jsonPath("$[0].transactionPrice", is((double) testTransaction.getTransactionPrice())));
  }

  @Test
  public void getTransactionBetweenDates_success() throws Exception {
    PropertyTransaction testTransaction = new PropertyTransaction(
            1L,
            new DateDetails(Date.valueOf("2022-12-10"), 12, 2022),
            new Location(1L, "Wexford", "Leinster"),
            new PropertyDetails(1L, "New Dwelling house /Apartment"),
            257709.25F);

    List<PropertyTransaction> testTransactionList = new ArrayList<>(Arrays.asList(testTransaction));

    Mockito
            .when(propertyTransactionRepository.findByDateDetails_DateBetween(Date.valueOf("2022-12-01"), Date.valueOf("2022-12-31")))
            .thenReturn(testTransactionList);

    mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT + "?startDate=2022-12-01&endDate=2022-12-31")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(testTransactionList.size())))
            .andExpect(jsonPath("$[0].dateDetails.date", is(testTransaction.getDateDetails().getDate().toString())))
            .andExpect(jsonPath("$[0].location.county", is(testTransaction.getLocation().getCounty())))
            .andExpect(jsonPath("$[0].location.province", is(testTransaction.getLocation().getProvince())))
            .andExpect(jsonPath("$[0].propertyDetails.buildType", is(testTransaction.getPropertyDetails().getBuildType())))
            .andExpect(jsonPath("$[0].transactionPrice", is((double) testTransaction.getTransactionPrice())));
  }

  @Test
  public void getTransactionBetweenDates_failure_wrongWay() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
                    .get(ENDPOINT + "?startDate=2023-12-01&endDate=2021-12-31")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getTransactionBetweenDates_failure_wrongType() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
                    .get(ENDPOINT + "?startDate=\"2022-12-01\"&endDate=\"2022-12-31\"")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void getTransactionBetweenDates_failure_badDate() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders
                    .get(ENDPOINT + "?startDate=2022-13-01&endDate=2022-31-12")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
  }

}
