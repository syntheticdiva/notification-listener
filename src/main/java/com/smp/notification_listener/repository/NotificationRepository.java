package com.smp.notification_listener.repository;


import com.smp.notification_listener.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<EventEntity, Long> {
}
