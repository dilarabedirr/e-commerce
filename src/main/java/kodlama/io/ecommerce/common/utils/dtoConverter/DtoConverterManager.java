package kodlama.io.ecommerce.common.utils.dtoConverter;

import kodlama.io.ecommerce.business.dto.BaseDto;
import kodlama.io.ecommerce.common.utils.mappers.ModelMapperService;
import kodlama.io.ecommerce.entities.BaseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DtoConverterManager implements DtoConverterService {
    private final ModelMapperService mapper;

    @Override
    public <T extends BaseEntity, S extends BaseDto> T toEntity(S dto, Class<T> entity) {
        return mapper.forRequest().map(dto, entity);
    }

    @Override
    public <S extends BaseDto, T extends BaseEntity> S toDto(T entity, Class<S> dto) {
        return mapper.forResponse().map(entity, dto);
    }

    @Override
    public <S extends BaseDto, T extends BaseEntity> List<S> toListDto(List<T> entity, Class<S> dto) {
        return entity.stream()
                .map(item -> toDto(item, dto))
                .toList();
    }
}
