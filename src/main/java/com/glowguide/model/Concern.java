package com.glowguide.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "concerns")
public class Concern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientEmail;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(columnDefinition = "TEXT")
    private String specialistReply;

    private boolean replied = false;
    private LocalDate date = LocalDate.now();

    // --- GETTERS AND SETTERS ---
    // (You can generate these in IntelliJ by Right-Clicking -> Generate -> Getter and Setter)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientEmail() { return clientEmail; }
    public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isReplied() { return replied; }
    public void setReplied(boolean replied) { this.replied = replied; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getSpecialistReply() { return specialistReply; }
    public void setSpecialistReply(String specialistReply) { this.specialistReply = specialistReply; }
}