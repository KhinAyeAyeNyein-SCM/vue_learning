package medium.vue.api.bl.service;

import java.util.List;

import medium.vue.api.persistence.entity.Category;
import medium.vue.api.web.form.CategoryForm;

public interface CategoryService {

    public List<CategoryForm> doGetCategoryList();

    public void doSaveCategory(Category category);

}
