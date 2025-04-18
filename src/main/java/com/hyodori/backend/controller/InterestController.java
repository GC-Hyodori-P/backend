// controller/InterestController.java
package com.hyodori.backend.controller;

import com.hyodori.backend.dto.InterestResponseDto;
import com.hyodori.backend.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    @GetMapping
    public ResponseEntity<List<InterestResponseDto>> getAllInterests() {
        List<InterestResponseDto> response = interestService.getAllInterests();
        return ResponseEntity.ok(response);
    }
}