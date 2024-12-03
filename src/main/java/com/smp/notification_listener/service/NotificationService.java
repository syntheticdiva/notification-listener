package com.smp.notification_listener.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smp.notification_listener.ExceptionDb;
import com.smp.notification_listener.dto.EventDto;
import com.smp.notification_listener.entity.EventEntity;
import com.smp.notification_listener.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, ObjectMapper objectMapper) {
        this.notificationRepository = notificationRepository;
        this.objectMapper = objectMapper;
    }
    public void processEvent(String entity, String event, Object msg) throws JsonProcessingException, ExceptionDb {
        String jsonMsg = objectMapper.writeValueAsString(msg);
        EventEntity eventEntity = new EventEntity(entity, event, jsonMsg);
        eventEntity.setMsg(jsonMsg);
        try {
            notificationRepository.save(eventEntity);
        } catch (Exception e){
            throw new ExceptionDb("Cannot insert entity msg", e);
        }
    }
    public void processEvent(EventDto dto) throws JsonProcessingException, ExceptionDb {
        processEvent(dto.getEntity(), dto.getEventType(), dto.getMsg());
    }
}