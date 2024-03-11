package com.example.resumlik.controller;

import com.example.resumlik.dto.request.AddressRequestDto;
import com.example.resumlik.dto.response.AddressResponseDto;
import com.example.resumlik.service.AddressService;
import com.example.resumlik.util.Constants;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.API_VERSION+"/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{resumeId}")
    public ResponseEntity<Response<List<AddressResponseDto>>> getAddressByResumeId(@PathVariable Long resumeId) {
        return ResponseEntity.ok(addressService.findByResumeId(resumeId));
    }

    @PostMapping
    public ResponseEntity<Response<AddressResponseDto>> create(@Valid @RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.ok(addressService.create(addressRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<AddressResponseDto>> update(@PathVariable Long id, @Valid @RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.ok(addressService.update(id, addressRequestDto));
    }



}
