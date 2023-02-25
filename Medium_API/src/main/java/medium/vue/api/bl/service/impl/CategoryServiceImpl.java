package medium.vue.api.bl.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medium.vue.api.bl.service.CategoryService;
import medium.vue.api.persistence.dao.CategoryDAO;
import medium.vue.api.persistence.entity.Category;
import medium.vue.api.web.form.CategoryForm;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Override
    public List<CategoryForm> doGetCategoryList() {
        List<Category> categoryList = this.categoryDAO.dbGetCategoryList();
        List<CategoryForm> formList = new ArrayList<CategoryForm>();
        CategoryForm form;
        for (Category category : categoryList) {
            form = new CategoryForm(category);
            formList.add(form);
        }
        return formList;
    }
    
    @Override
    public void doSaveCategory(Category category) {
        this.categoryDAO.dbSaveCategory(category);
    }
    
    @Override
    public Category doGetCategoryByName(String name) {
        return this.categoryDAO.dbGetCategoryByName(name);
    }
}
