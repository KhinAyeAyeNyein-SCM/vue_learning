package medium.vue.api.bl.service;

import medium.vue.api.web.form.UserForm;

public interface UserService {
    /**
     * <h2> doSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userform
     * @return void
     */
    public void doSaveUser(UserForm userform);
    
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
    public UserForm doGetUserById(Integer userId);

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
    public UserForm doGetUserByEmail(String email);

    public void doUpdateUser(UserForm updatedForm);

}
