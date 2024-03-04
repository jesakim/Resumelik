package com.example.resumlik.dto.request;

import com.example.resumlik.enums.ContactType;
import com.example.resumlik.model.Contact;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactRequestDto {

    @NotNull(message = "Type is required")
    @NotBlank(message = "Type is required")
    private String type;

    @NotNull(message = "Text is required")
    @NotBlank(message = "Text is required")
    private String text;


    private String link;

    @NotNull(message = "Resume id is required.")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Resume id is not valid.")
    private Long resumeId;

    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setType(ContactType.fromString(type));
        contact.setText(text);
        contact.setLink(link);
        return contact;
    }
}
