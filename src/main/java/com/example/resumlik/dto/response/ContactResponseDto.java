package com.example.resumlik.dto.response;

import com.example.resumlik.model.Contact;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactResponseDto {

    private Long id;
    private String type;
    private String text;
    private String link;
    private Long resumeId;



    public ContactResponseDto(Contact contact) {
        this.id = contact.getId();
        this.type = contact.getType().name();
        this.text = contact.getText();
        this.link = contact.getLink();
        this.resumeId = contact.getResume().getId();
    }
}
