package br.com.mylibrary.application.service;

import br.com.mylibrary.application.ports.in.UserUseCase;
import br.com.mylibrary.domain.dto.request.UserRequestDto;
import br.com.mylibrary.domain.model.User;
import br.com.mylibrary.framework.adapter.out.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User create(UserRequestDto request) {
        User user = modelMapper.map(request, User.class);
        user.setTotalBorrowings(0);
        return userRepository.save(user);
    }
}
