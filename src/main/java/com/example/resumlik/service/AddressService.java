package com.example.resumlik.service;

import com.example.resumlik.dto.request.AddressRequestDto;
import com.example.resumlik.dto.response.AddressResponseDto;
import com.example.resumlik.model.Address;
import com.example.resumlik.repository.AddressRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final ResumeRepository resumeRepository;
    public Response<List<AddressResponseDto>> findByResumeId(Long resumeId) {

        List<AddressResponseDto> addressDtoList = addressRepository.findAllByResumeId(resumeId).stream()
                .map(AddressResponseDto::new)
                .toList();

        return Response.<List<AddressResponseDto>>builder()
                .result(addressDtoList)
                .build();



    }

    public Response<AddressResponseDto> create(AddressRequestDto addressRequestDto) {
        Address address = addressRequestDto.toEntity();

        address.setResume(resumeRepository.findById(addressRequestDto.getResumeId()).orElseThrow(() -> new RuntimeException("Resume not found")));

        return Response.<AddressResponseDto>builder()
                .result(new AddressResponseDto(addressRepository.save(address)))
                .message("Address created successfully")
                .build();
    }


    public Response<String> delete(Long id) {
        addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));

        addressRepository.deleteById(id);

        return Response.<String>builder()
                .message("Address deleted successfully")
                .build();
    }
}
