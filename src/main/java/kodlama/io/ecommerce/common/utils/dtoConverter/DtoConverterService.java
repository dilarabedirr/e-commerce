package kodlama.io.ecommerce.common.utils.dtoConverter;

import kodlama.io.ecommerce.common.dto.BaseDto;
import kodlama.io.ecommerce.entities.BaseEntity;

import java.util.List;

public interface DtoConverterService {
    <T extends BaseEntity, S extends BaseDto> T toEntity(S dto, Class<T> entity);

    <S extends BaseDto, T extends BaseEntity> S toDto(T entity, Class<S> dto);

    <S extends BaseDto, T extends BaseEntity> List<S> toListDto(List<T> entity, Class<S> dto);
}
