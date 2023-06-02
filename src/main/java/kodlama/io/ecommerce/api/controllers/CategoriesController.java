package kodlama.io.ecommerce.api.controllers;

import jakarta.validation.Valid;
import kodlama.io.ecommerce.business.abstracts.CategoryService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateCategoryRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateCategoryRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetAllCategoriesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.category.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateCategoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {
    private final CategoryService service;

    @GetMapping
    public List<GetAllCategoriesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateCategoryResponse add(@Valid @RequestBody CreateCategoryRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateCategoryResponse update(@PathVariable int id, @Valid @RequestBody UpdateCategoryRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
