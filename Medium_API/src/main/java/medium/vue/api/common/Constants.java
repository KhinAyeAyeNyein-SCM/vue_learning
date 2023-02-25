package medium.vue.api.common;

import java.util.Arrays;
import java.util.List;

/**
 * <h2> Constants Class</h2>
 * <p>
 * Process for Displaying Constants
 * </p>
 * 
 * @author KhinAyeAyeNyein
 *
 */
public final class Constants {
    /**
     * <h2> Storage Class</h2>
     * <p>
     * Process for Displaying Storage
     * </p>
     * 
     * @author KhinAyeAyeNyein
     *
     */
    public static final class Storage {
        public static final String IMG_PATH = "resources/img/postImg/";
        public static final String PROFILE_PATH = "resources/img/profile/";
    }
    
    /**
     * <h2> LEAVE_ATTACH_FILE_CONTENT_TYPES</h2>
     * <p>
     * LEAVE_ATTACH_FILE_CONTENT_TYPES
     * </p>
     */
    public static final List<String> IMAGE_ATTACH_FILE_TYPES = Arrays.asList("image/png", "image/jpg",
            "image/jpeg", "application/svg");
    
    /**
     * <h2> Character Class</h2>
     * <p>
     * Process for Displaying Character
     * </p>
     * 
     * @author KhinAyeAyeNyein
     *
     */
    public static final class Character {
        public static final String COMMA = ",";

        public static final String DOLLA_SIGN = "$";

        public static final String SPACE = " ";

        public static final String DOT = ".";

        public static final String SLASH = "/";

        public static final String BACK_SLASH = "\\";

        public static final String HYPHEN = "-";

        public static final String FULL_COLON = ":";
    }
}
