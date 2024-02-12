package com.mappings.manytomany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDto {
    private Long id;
    private String title;
    private List<Long> studentId;
}
