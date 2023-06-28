package br.com.mylibrary.framework.adapter.in;

import br.com.mylibrary.application.ports.in.UserUseCase;
import br.com.mylibrary.domain.dto.request.UserRequestDto;
import br.com.mylibrary.domain.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserUseCase userService;

    @PostMapping("/register")
    private ResponseEntity<User> create(@RequestBody @Valid UserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
}
