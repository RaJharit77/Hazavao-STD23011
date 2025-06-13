package com.hazavao.demo.endpoint.rest.controller;

import com.hazavao.demo.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WordController {
  private final WordService service;

  @GetMapping("/hazavao")
  public String Word(@RequestParam String teny) {
    return service.getDefinition(teny);
  }
}
