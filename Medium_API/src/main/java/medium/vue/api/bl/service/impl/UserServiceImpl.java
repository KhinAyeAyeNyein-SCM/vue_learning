package medium.vue.api.bl.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import medium.vue.api.bl.service.PostService;
import medium.vue.api.bl.service.UserService;
import medium.vue.api.common.Constants.Character;
import medium.vue.api.common.Constants.Storage;
import medium.vue.api.persistence.dao.UserDAO;
import medium.vue.api.persistence.entity.User;
import medium.vue.api.web.form.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
    /**
     * <h2>userDAO</h2>
     * <p>
     * userDAO
     * </p>
     */
    @Autowired
    private UserDAO userDAO;

    /**
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

    /**
     * <h2>doSaveUser</h2>
     * <p>
     * 
     * </p>
     *
     * @param userform
     * @return void
     * @throws IOException
     */
    @Override
    public void doSaveUser(UserForm userform, String realPath) throws IOException {
        if (userform.getFile() != null && userform.getFile().getSize() > 0) {
            String fileName = this.writeLeaveAttachFile(userform.getFile(), userform.getName(), realPath);
            userform.setProfile(fileName);
        }
        User user = new User(userform);
        this.userDAO.dbSaveUser(user);
    }

    /**
     * <h2>doGetUserById</h2>
     * <p>
     * 
     * </p>
     *
     * @param userId
     * @return
     * @return UserForm
     */
    @Override
    public UserForm doGetUserById(Integer userId, String realPath) {
        User user = this.userDAO.dbGetUserById(userId);
        UserForm userForm = new UserForm(user);
        if (user == null)
            return null;
        if (user.getProfile() != null & user.getProfile().contains("resources")) {
            userForm.setProfile(user.getProfile().split("/")[user.getProfile().split("/").length - 1]);
        }
        String path = Storage.PROFILE_PATH + user.getName() + Character.SLASH + userForm.getProfile();
        // if (realPath != null) {
        if (user.getProfile() != null && !user.getProfile().isEmpty()) {
            boolean isExist = this.isExistPhoto(path, realPath);
            if (isExist) {
                userForm.setProfile(path);
            } else
                userForm.setProfile(Storage.IMG_PATH + "noImage.jpg");
        } else
            userForm.setProfile(Storage.IMG_PATH + "noImage.jpg");
        // }
        Integer postCount = this.postService.doGetPostCountByUserId(userId);
        userForm.setPostCount(postCount);
        return userForm;
    }

    /**
     * <h2>doGetUserByEmail</h2>
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
        User user = this.userDAO.dbGetUserByEmail(email);
        return user == null ? null : new UserForm(user);
    }

    /**
     * <h2>doUpdateUser</h2>
     * <p>
     * 
     * </p>
     * 
     * @param updatedForm
     */
    @Override
    public void doUpdateUser(UserForm updatedForm) {
        User user = this.userDAO.dbGetUserById(updatedForm.getId());
        user.setName(updatedForm.getName());
        user.setEmail(updatedForm.getEmail());
        user.setBio(updatedForm.getBio());
        user.setUpdatedAt(new Date());
        this.userDAO.dbUpdateUser(user);
    }

    /**
     * <h2>writeLeaveAttachFile</h2>
     * <p>
     * 
     * </p>
     *
     * @param attachFile
     * @param employeeId
     * @param fileUploadPath
     * @return
     * @throws IOException
     * @return String
     */
    private String writeLeaveAttachFile(MultipartFile attachFile, String name, String fileUploadPath)
            throws IOException {
        String profileAttachFile = fileUploadPath + Storage.PROFILE_PATH + name;
        byte[] bytes = attachFile.getBytes();
        File attachDir = new File(profileAttachFile);
        if (!attachDir.exists()) {
            attachDir.mkdirs();
        }
        String fileName = name + System.currentTimeMillis() + "."
                + FilenameUtils.getExtension(attachFile.getOriginalFilename());
        File serverFile = new File(attachDir.getAbsolutePath() + File.separator + fileName);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return fileName;
    }

    /**
     * <h2>isExistPhoto</h2>
     * <p>
     * 
     * </p>
     *
     * @param photoPath
     * @param realContextPath
     * @return
     * @return boolean
     */
    private boolean isExistPhoto(String photoPath, String realContextPath) {
        File photo = new File(realContextPath + photoPath);
        if (!photo.exists()) {
            return false;
        }
        return true;
    }
}
