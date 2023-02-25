package medium.vue.api.persistence.dao;

import medium.vue.api.persistence.entity.User;

public interface UserDAO {

    /**
     * <h2> dbSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param user
     * @return void
     */
    public void dbSaveUser(User user);

    /**
     * <h2> dbGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @return
     * @return Users
     */
    public User dbGetUserById(Integer userId);

    /**
     * <h2> dbGetUserByEmail</h2>
     * <p>
     * 
     * </p>
     *
     * @param email
     * @return
     * @return Users
     */
    public User dbGetUserByEmail(String email);

    public void dbUpdateUser(User user);

}
