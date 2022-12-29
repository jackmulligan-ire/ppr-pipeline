package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  TestRepository testRepository;

  @GetMapping("/test")
  public ResponseEntity<List<Test>> getAllTest() {
    List<Test> testList = new ArrayList<>();
    testRepository.findAll().forEach(testList::add);
    return new ResponseEntity<>(testList, HttpStatus.OK);
  }
}
