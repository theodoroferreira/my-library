package br.com.mylibrary.application.ports.in;

import br.com.mylibrary.domain.dto.request.UserRequestDto;
import br.com.mylibrary.domain.model.User;

public interface UserUseCase {

    User create(UserRequestDto request);
}
