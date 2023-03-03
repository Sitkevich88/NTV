package ru.ntv.dto.request.admin;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateThemeRequest {

    @Size(min = 2, max = 100)
    private String name;
}
