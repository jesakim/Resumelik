package com.example.resumlik.service;

import com.example.resumlik.dto.request.ContactRequestDto;
import com.example.resumlik.dto.response.ContactResponseDto;
import com.example.resumlik.enums.ContactType;
import com.example.resumlik.model.Contact;
import com.example.resumlik.model.Resume;
import com.example.resumlik.repository.ContactRepository;
import com.example.resumlik.repository.ResumeRepository;
import com.example.resumlik.util.AuthHelper;
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
        Resume resume = resumeRepository.findById(contactRequestDto.getResumeId()).orElseThrow(()->new RuntimeException("Resume not found"));
        if (!AuthHelper.checkOwnerShip(resume)){
            throw new RuntimeException("You are not authorized to add contact to this resume");
        }
        Contact contact = contactRequestDto.toEntity();
        contact.setResume(resume);

        return Response.<ContactResponseDto>builder()
                .result(new ContactResponseDto(contactRepository.save(contact)))
                .message("Contact created successfully")
                .build();
    }

    public Response<String> delete(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not found"));
        if (!AuthHelper.checkOwnerShip(contact.getResume())){
            throw new RuntimeException("You are not authorized to delete this contact");
        }

        contactRepository.deleteById(id);

        return Response.<String>builder()
                .message("Contact deleted successfully")
                .build();
    }

    public Response<ContactResponseDto> update(Long id, ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.findById(id).orElseThrow(()->new RuntimeException("Contact not found"));
        if (!AuthHelper.checkOwnerShip(contact.getResume())){
            throw new RuntimeException("You are not authorized to update this contact");
        }

        contact.setType(ContactType.fromString(contactRequestDto.getType()));
        contact.setText(contactRequestDto.getText());
        contact.setLink(contactRequestDto.getLink());

        return Response.<ContactResponseDto>builder()
                .result(new ContactResponseDto(contactRepository.save(contact)))
                .message("Contact updated successfully")
                .build();
    }
}
