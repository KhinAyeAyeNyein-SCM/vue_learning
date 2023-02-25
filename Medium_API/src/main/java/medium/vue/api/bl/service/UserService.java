package medium.vue.api.bl.service;

import java.io.IOException;

import medium.vue.api.web.form.UserForm;

public interface UserService {
    /**
     * <h2> doSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userform
     * @param realPath TODO
     * @return void
     * @throws IOException 
     */
    public void doSaveUser(UserForm userform, String realPath) throws IOException;
    
    /**
     * <h2> doGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @param realPath TODO
     * @return
     * @return UserForm
     */
    public UserForm doGetUserById(Integer userId, String realPath);

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

    /**
     * <h2> doUpdateUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param updatedForm
     * @return void
     */
    public void doUpdateUser(UserForm updatedForm);

}
