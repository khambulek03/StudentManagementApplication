package com.codeio.studentmanagementapplication.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentContactUpdateRequest {
    private String email;
    private String phone;
}
