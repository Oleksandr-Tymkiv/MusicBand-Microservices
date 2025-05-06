package com.musicband.merch.controller;

import com.musicband.merch.dto.MerchDto;
import com.musicband.merch.dto.MerchOrderDto;
import com.musicband.merch.entity.Merch;
import com.musicband.merch.service.MerchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MerchController {

    private final MerchService merchService;

    @GetMapping("find-all-merch")
    public ResponseEntity<List<Merch>> findAllMerch() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(merchService.getAllMerch());
    }

    @PostMapping("add-merch")
    public ResponseEntity<String> addMerch(@Valid @RequestBody MerchDto merchDto) {
        merchService.addMerch(merchDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Merch added successfully");
    }

    @PutMapping("update-merch")
    public ResponseEntity<String> updateMerch(@Valid @RequestBody MerchDto merchDto) {
        merchService.updateMerch(merchDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Merch updated successfully");
    }

    @DeleteMapping("delete-merch/{id}")
    public ResponseEntity<String> deleteMerch(@RequestParam("id")
                                                  @NotEmpty(message = "Id cannot be a null or empty")
                                                  Long id ) {
        merchService.deleteMerch(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Merch deleted successfully");
    }

    @PostMapping("order-merch")
    public ResponseEntity<String> orderMerch(@Valid @RequestBody MerchOrderDto merchOrderDto) {
        merchService.orderMerch(merchOrderDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Order created successfully");
    }

}
