package medium.vue.api.bl.service.impl;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medium.vue.api.bl.service.UserService;
import medium.vue.api.persistence.dao.UserDAO;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.UserForm;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    /**
     * <h2> doSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userform
     * @return void
     */
    @Override
    public void doSaveUser(UserForm userform) {
        User user = new User(userform);
        this.userDAO.dbSaveUser(user);
    }
    
    /**
     * <h2> doGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @return
     * @return UserForm
     */
    @Override
    public UserForm doGetUserById(Integer userId) {
        User user =  this.userDAO.dbGetUserById(userId);
        return user == null ? null : new UserForm(user);
    }
    
    /**
     * <h2> doGetUserByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param email
     * @return
     * @return UserForm
     */
    @Override
    public UserForm doGetUserByEmail(String email) {
        User user =  this.userDAO.dbGetUserByEmail(email);
        return user == null ? null : new UserForm(user);
    }
    
    @Override
    public void doUpdateUser(UserForm updatedForm) {
        User user = this.userDAO.dbGetUserById(updatedForm.getId());
        user.setName(updatedForm.getName());
        user.setEmail(updatedForm.getEmail());
        user.setBio(updatedForm.getBio());
        user.setUpdatedAt(new Date());
        this.userDAO.dbUpdateUser(user);
    }
}
