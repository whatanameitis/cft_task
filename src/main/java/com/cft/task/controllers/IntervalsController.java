package com.cft.task.controllers;

import com.cft.task.services.IntervalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/intervals")
@AllArgsConstructor
public class IntervalsController {

    private final IntervalService intervalService;

    @PostMapping("/merge")
    public void postInterval(@RequestBody List<List<Object>> intervals, @RequestParam(name = "kind") String kind) {
        intervalService.postInterval(intervals, kind);
    }

    @GetMapping("/min")
    public ResponseEntity<List<Object>> getMinimalInterval(@RequestParam(name = "kind") String kind) {
        return ResponseEntity.ok(intervalService.getMinimalInterval(kind));
    }
}
