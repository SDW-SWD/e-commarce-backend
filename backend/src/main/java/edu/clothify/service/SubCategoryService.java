package edu.clothify.service;

import edu.clothify.dto.SubCategoryDto;
import jakarta.validation.Valid;


import java.util.List;

public interface SubCategoryService {
    public boolean saveSubCategory(@Valid SubCategoryDto SubCategoryDto);

    public List<SubCategoryDto> getAllSubCategories();

    SubCategoryDto getSubCategoryById(Long id);

    boolean deleteSubCategoryById(Long id);

    SubCategoryDto getCategoryByName(String name);
}
