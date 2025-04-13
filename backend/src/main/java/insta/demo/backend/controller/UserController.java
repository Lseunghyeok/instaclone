package insta.demo.backend.controller;

import insta.demo.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginInfo) {
        String email = loginInfo.get("email");
        String password = loginInfo.get("password");

        return userService.login(email, password)
                .map(user -> Map.of("message", "로그인 성공", "username", user.getUsername()))
                .orElseGet(() -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("message", "로그인 실패");
                    return result;
                });
    }
}
