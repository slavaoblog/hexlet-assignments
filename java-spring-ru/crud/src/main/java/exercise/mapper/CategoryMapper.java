package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class CategoryMapper {
    public abstract CategoryDTO map(Category model);

    public abstract Category map(CategoryCreateDTO dto);
}
// END
