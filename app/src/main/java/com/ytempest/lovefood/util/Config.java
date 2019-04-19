package com.ytempest.lovefood.util;

import java.io.File;

/**
 * @author ytempest
 *         Description：
 */
public class Config {

    /**
     * 存储头像的临时文件
     */
    public static final String HEAD_IMAGE_CACHE = "head_cache.png";

    /**
     * 每一个列表展示的数量
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 活动列表展示的数量
     */
    public static final int ACTIVITY_PAGE_SIZE = 6;

    /**
     * 应用外置存储的根文件夹
     */
    public static final String EXTERNAL_ROOT_DIR = "LoveFood";

    /**
     * 存放压缩后的话题图片的文件夹，用于临时缓存
     */
    public static final String EXTERNAL_CACHE_TOPIC_IMAGE_DIR =
            EXTERNAL_ROOT_DIR + File.separator + "topicImage";

    /**
     * 话题列表展示的数量
     */
    public static final int TOPIC_PAGE_SIZE = 5;
}
