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

import java.sql.Date;
import java.text.SimpleDateFormat;
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

  PropertyTransaction RECORD_1 = new PropertyTransaction(
          1L,
          new DateDetails(Date.valueOf("2023-01-10")),
          new Location(1L, "Carlow", "Leinster"),
          new PropertyDetails(1L, "New Dwelling house /Apartment"),
          157709.25F);

  PropertyTransaction RECORD_2 = new PropertyTransaction(
          2L,
          new DateDetails(Date.valueOf("2023-01-09")),
          new Location(2L, "Wexford", "Leinster"),
          new PropertyDetails(2L, "New Dwelling house /Apartment"),
          257709.25F);

  @Test
  public void getAllTransactions_success() throws Exception {
    List<PropertyTransaction> transactions = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2));

    Mockito.when(propertyTransactionRepository.findAll()).thenReturn(transactions);

    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/property-transactions")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].dateDetails.date", is("2023-01-10")))
            .andExpect(jsonPath("$[0].location.county", is("Carlow")))
            .andExpect(jsonPath("$[0].location.province", is("Leinster")))
            .andExpect(jsonPath("$[0].propertyDetails.buildType", is("New Dwelling house /Apartment")))
            .andExpect(jsonPath("$[0].transactionPrice", is(157709.25)));
  }



}
