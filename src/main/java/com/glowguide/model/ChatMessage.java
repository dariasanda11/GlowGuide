package com.glowguide.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long concernId; // Links this message to the specific consultation
    private String senderRole; // Will be either "CLIENT" or "SPECIALIST"

    @Column(columnDefinition = "TEXT")
    private String text;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getConcernId() { return concernId; }
    public void setConcernId(Long concernId) { this.concernId = concernId; }

    public String getSenderRole() { return senderRole; }
    public void setSenderRole(String senderRole) { this.senderRole = senderRole; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}