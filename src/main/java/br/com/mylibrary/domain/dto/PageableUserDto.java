package br.com.mylibrary.domain.dto;

import br.com.mylibrary.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PageableUserDto {

    private Integer numberOfElements;
    private Long totalElements;
    private Integer totalPages;
    private List<User> users;
}
