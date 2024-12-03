package com.smp.notification_listener.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
@Data
public class SchoolOnCreateDto {

    private Long schoolId;
    private String name;
    private String address;
    private LocalDateTime createDate;

}
