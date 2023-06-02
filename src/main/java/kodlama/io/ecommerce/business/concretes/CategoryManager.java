package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateCategoryResponse;
import kodlama.io.ecommerce.business.rules.CategoryBusinessRules;
import kodlama.io.ecommerce.common.utils.dtoConverter.DtoConverterService;
import kodlama.io.ecommerce.entities.Category;
import kodlama.io.ecommerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryRepository repository;
    private final DtoConverterService dtoConverter;
    private final CategoryBusinessRules rules;

    @Override
    public List<GetAllCategoriesResponse> getAll() {
        var categories = repository.findAll();
        var response = dtoConverter.toListDto(categories, GetAllCategoriesResponse.class);
        return response;
    }

    @Override
    public GetCategoryResponse getById(int id) {
        rules.checkIfCategoryExists(id);
        var category = repository.findById(id).orElseThrow();
        var response = dtoConverter.toDto(category, GetCategoryResponse.class);
        return response;
    }

    @Override
    public CreateCategoryResponse add(CreateCategoryRequest request) {
        var category = dtoConverter.toEntity(request, Category.class);
        category.setId(0);
        repository.save(category);
        var response = dtoConverter.toDto(category, CreateCategoryResponse.class);
        return response;
    }

    @Override
    public UpdateCategoryResponse update(int id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExists(id);
        var category = dtoConverter.toEntity(request, Category.class);
        category.setId(id);
        repository.save(category);
        var response = dtoConverter.toDto(category, UpdateCategoryResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfCategoryExists(id);
        repository.deleteById(id);
    }
}
