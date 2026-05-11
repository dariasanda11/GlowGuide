package com.glowguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Connects to your client_signup.html
    @PostMapping("/client/register")
    public ResponseEntity<String> registerClient(@RequestBody RegisterDto request) {
        try {
            // Pass the name, email, password, and hardcoded role to the service
            authService.registerUser(request.getName(), request.getEmail(), request.getPassword(), "ROLE_CLIENT");
            return ResponseEntity.ok("Client registration successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Connects to your specialist_signup.html
    @PostMapping("/specialist/register")
    public ResponseEntity<String> registerSpecialist(@RequestBody RegisterDto request) {
        try {
            // Pass the name, email, password, and hardcoded role to the service
            authService.registerUser(request.getName(), request.getEmail(), request.getPassword(), "ROLE_SPECIALIST");
            return ResponseEntity.ok("Specialist registration successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // NEW: Connects to your login pages
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto request) {
        try {
            User user = authService.loginUser(request.getEmail(), request.getPassword());

            // Return JSON containing the role so your frontend Javascript
            // knows whether to redirect to the Client or Specialist Dashboard
            return ResponseEntity.ok().body("{\"message\": \"Login successful\", \"role\": \"" + user.getRole() + "\"}");
        } catch (Exception e) {
            // Return a clean JSON error message if login fails
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    // CHANGED: Added 'name' to the RegisterDto
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

    // NEW: Created a DTO specifically for logging in (doesn't need 'name')
    public static class LoginDto {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}