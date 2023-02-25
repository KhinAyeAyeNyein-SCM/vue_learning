package medium.vue.api.persistence.dao;

import java.util.List;

import medium.vue.api.persistence.entity.Category;

/**
 * <h2> CategoryDAO Class</h2>
 * <p>
 * Process for Displaying CategoryDAO
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public interface CategoryDAO {
    /**
     * <h2> dbGetCategoryList</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return List<Category>
     */
    public List<Category> dbGetCategoryList();

    /**
     * <h2> dbSaveCategory</h2>
     * <p>
     * 
     * </p>
     *
     * @param category
     * @return void
     */
    public void dbSaveCategory(Category category);

    /**
     * <h2> dbGetCategoryByName</h2>
     * <p>
     * 
     * </p>
     *
     * @param categoryName
     * @return
     * @return Category
     */
    public Category dbGetCategoryByName(String categoryName);
}
