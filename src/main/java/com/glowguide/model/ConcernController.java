package com.glowguide.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/concerns")
public class ConcernController {

    @Autowired
    private ConcernRepository concernRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PostMapping
    public Concern submitConcern(@RequestBody Concern concern) {
        return concernRepository.save(concern);
    }

    @GetMapping
    public List<Concern> getAllConcerns() {
        return concernRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concern> getConcernById(@PathVariable Long id) {
        return concernRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/reply")
    public ResponseEntity<Concern> replyToConcern(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        return concernRepository.findById(id).map(concern -> {
            concern.setSpecialistReply(payload.get("reply"));
            concern.setReplied(true); // Changes the badge from "Pending" to "Replied"
            return ResponseEntity.ok(concernRepository.save(concern));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{email}")
    public ResponseEntity<List<Concern>> getConcernsByClient(@PathVariable String email) {
        return ResponseEntity.ok(concernRepository.findByClientEmail(email));
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long id) {
        return ResponseEntity.ok(chatMessageRepository.findByConcernId(id));
    }

    @PostMapping("/{id}/messages")
    public ResponseEntity<ChatMessage> addChatMessage(@PathVariable Long id, @RequestBody ChatMessage message) {
        message.setConcernId(id); // Link the message to the current concern
        return ResponseEntity.ok(chatMessageRepository.save(message));
    }


}