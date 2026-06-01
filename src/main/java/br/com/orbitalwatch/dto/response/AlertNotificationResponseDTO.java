package br.com.orbitalwatch.dto.response;

import java.time.LocalDateTime;

public class AlertNotificationResponseDTO {
    private Long id;
    private String notificationType;
    private LocalDateTime sentAt;
    private String recipientEmail;
    private String message;
    private String status;
    private String priority;
    private Boolean wasRead;
    private Long eventId;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        this.id = v;
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

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long v) {
        this.eventId = v;
    }
}
