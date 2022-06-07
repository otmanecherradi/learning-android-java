package me.otmane.ntic.dtos;

import java.util.ArrayList;

import me.otmane.ntic.models.School;

public class SchoolsDTOs {
    public static class ListSchoolRequestDTO {
    }

    public static class ListSchoolResponseDTO extends ArrayList<School> {
    }
}
