package com.example.resumlik.service;

import com.example.resumlik.dto.request.ContactRequestDto;
import com.example.resumlik.dto.response.ContactResponseDto;
import com.example.resumlik.enums.ContactType;
import com.example.resumlik.model.Contact;
import com.example.resumlik.repository.ContactRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ResumeRepository resumeRepository;

    //getContactByResumeId
    public Response<List<ContactResponseDto>> findByResumeId(Long resumeId) {

            List<ContactResponseDto> contactDtoList = contactRepository.findAllByResumeId(resumeId).stream()
                    .map(ContactResponseDto::new)
                    .toList();

            return Response.<List<ContactResponseDto>>builder()
                    .result(contactDtoList)
                    .build();
    }


    public Response<ContactResponseDto> save(ContactRequestDto contactRequestDto) {
        Contact contact = contactRequestDto.toEntity();
        contact.setResume(resumeRepository.findById(contactRequestDto.getResumeId()).orElseThrow(()->new RuntimeException("Resume not found")));

        return Response.<ContactResponseDto>builder()
                .result(new ContactResponseDto(contactRepository.save(contact)))
                .message("Contact created successfully")
                .build();
    }

    public Response<String> delete(Long id) {
        contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not found"));

        contactRepository.deleteById(id);

        return Response.<String>builder()
                .message("Contact deleted successfully")
                .build();
    }

    public Response<ContactResponseDto> update(Long id, ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not found"));

        contact.setType(ContactType.fromString(contactRequestDto.getType()));
        contact.setText(contactRequestDto.getText());
        contact.setLink(contactRequestDto.getLink());

        return Response.<ContactResponseDto>builder()
                .result(new ContactResponseDto(contactRepository.save(contact)))
                .message("Contact updated successfully")
                .build();
    }
}
