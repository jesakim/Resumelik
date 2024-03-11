package com.example.resumlik.dto.response;

import com.example.resumlik.model.View;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ViewResponseDto {
    private Long id;
    private String username;
    private String ipAddress;
    private Date viewDate;

    public ViewResponseDto(View view) {
        this.id = view.getId();
        this.username = view.getUsername();
        this.ipAddress = view.getIpAddress();
        this.viewDate = view.getViewDate();
    }
}
