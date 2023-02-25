package medium.vue.api.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import medium.vue.api.bl.service.CategoryService;
import medium.vue.api.persistence.entity.Category;

/**
 * <h2> DeploymentListener Class</h2>
 * <p>
 * Process for Displaying DeploymentListener
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Component
public class DeploymentListener {
    /**
     * <h2> categoryService</h2>
     * <p>
     * categoryService
     * </p>
     */
    @Autowired
    private CategoryService categoryService;
    
    /**
     * <h2> addMasterData</h2>
     * <p>
     * 
     * </p>
     *
     * @return void
     */
    @PostConstruct
    public void addMasterData() {
        if (this.categoryService.doGetCategoryList().size() <= 0) {
            for (int i = 0; i < 10; i++) {
                Category category = new Category(null, "Category" + i, new Date());
                this.categoryService.doSaveCategory(category);
            }
        }
    }
}
