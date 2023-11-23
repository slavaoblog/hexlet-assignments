package exercise.mapper;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.dto.UserCreateDTO;
import exercise.dto.UserDTO;
import exercise.model.Task;
import exercise.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {

    // BEGIN
    @Mapping(source = "assignee.id", target = "assigneeId")
    public abstract TaskDTO map(Task model);
    @Mapping(target = "assignee", ignore = true)
    public abstract Task map(TaskCreateDTO taskCreateDTO);
    @Mapping(target = "assignee", ignore = true)
    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task model);
    // END
}
