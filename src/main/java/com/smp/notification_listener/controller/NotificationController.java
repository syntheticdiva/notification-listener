package com.smp.notification_listener.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smp.notification_listener.ExceptionDb;
import com.smp.notification_listener.dto.EventDto;
import com.smp.notification_listener.dto.MessageDto;
import com.smp.notification_listener.dto.SchoolOnCreateDto;
import com.smp.notification_listener.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationController {

    public static final String URL_ON_CREATE = "/on_create";
    public static final String URL_ON_UPDATE = "/on_update";
    public static final String URL_ON_DELETE = "/on_delete";
    public static final String ENTITY_SCHOOL = "school";
    public static final String EVENT_ON_CREATE = "on_create";
    public static final String EVENT_ON_UPDATE = "on_update";
    public static final String EVENT_ON_DELETE = "on_delete";

    @Autowired
    private NotificationService notificationService;
    @PostMapping("/event")
    public ResponseEntity<?> handleEvent(@RequestBody EventDto eventDto) {
        try {
            notificationService.processEvent(eventDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Событие обработано");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка обработки события: " + e.getMessage());
        }
    }

    @PostMapping(URL_ON_CREATE)
    public ResponseEntity<?> onCreate(@RequestBody SchoolOnCreateDto schoolOnCreateDto) {
        try {
            notificationService.processEvent(ENTITY_SCHOOL, EVENT_ON_CREATE, schoolOnCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Событие обработано");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка обработки события: " + e.getMessage());
        } catch (ExceptionDb e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка обработки события: " + e.getMessage());
        }
    }
    @PostMapping(URL_ON_UPDATE)
    public ResponseEntity<?> onUpdate(@RequestBody SchoolOnCreateDto schoolOnCreateDto) {
        try {
            notificationService.processEvent(ENTITY_SCHOOL, EVENT_ON_UPDATE, schoolOnCreateDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Событие обработано");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка обработки события: " + e.getMessage());
        } catch (ExceptionDb e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка обработки события: " + e.getMessage());
        }
    }
}