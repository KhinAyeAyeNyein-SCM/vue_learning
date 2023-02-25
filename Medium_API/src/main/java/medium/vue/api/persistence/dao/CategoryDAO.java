package medium.vue.api.persistence.dao;

import java.util.List;

import medium.vue.api.persistence.entity.Category;

public interface CategoryDAO {
    public List<Category> dbGetCategoryList();

    public void dbSaveCategory(Category category);

}
