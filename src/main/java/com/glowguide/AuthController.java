package com.glowguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/client/register")
    public ResponseEntity<String> registerClient(@RequestBody RegisterDto request) {
        try {
            authService.registerUser(request.getName(), request.getEmail(), request.getPassword(), "ROLE_CLIENT");
            return ResponseEntity.ok("Client registration successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/specialist/register")
    public ResponseEntity<String> registerSpecialist(@RequestBody RegisterDto request) {
        try {
            authService.registerUser(request.getName(), request.getEmail(), request.getPassword(), "ROLE_SPECIALIST");
            return ResponseEntity.ok("Specialist registration successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request) {
        try {
            User user = authService.loginUser(request.getEmail(), request.getPassword());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", user.getRole());
            response.put("name", user.getName());
            response.put("email", user.getEmail());

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    public static class RegisterDto {
        private String name;
        private String email;
        private String password;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginDto {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}