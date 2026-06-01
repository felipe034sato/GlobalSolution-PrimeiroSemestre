package br.com.orbitalwatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert_notifications")
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String notificationType;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime sentAt;

    private String recipientEmail;
    private String message;
    private String status;
    private String priority;
    private Boolean wasRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private SatelliteEvent event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String v) {
        this.notificationType = v;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime v) {
        this.sentAt = v;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String v) {
        this.recipientEmail = v;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String v) {
        this.message = v;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String v) {
        this.status = v;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String v) {
        this.priority = v;
    }

    public Boolean getWasRead() {
        return wasRead;
    }

    public void setWasRead(Boolean v) {
        this.wasRead = v;
    }

    public SatelliteEvent getEvent() {
        return event;
    }

    public void setEvent(SatelliteEvent v) {
        this.event = v;
    }
}
