package com.ytempest.lovefood.http.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ytempest
 * @date 2019/3/4
 */
public class CookClassify {
    private String group;
    private List<String> typeList;

    public CookClassify(String group, List<String> typeList) {
        this.group = group;
        this.typeList = typeList;
    }

    public String getGroup() {
        return group;
    }

    public List<String> getTypeList() {
        return typeList;
    }


    public static final List<CookClassify> COOK_CLASSIFY_LIST = new ArrayList<>();

    static {
        List<String> typeList = new ArrayList<>();
        typeList.add("广东小吃");
        typeList.add("广西小吃");
        typeList.add("福建小吃");
        typeList.add("北京小吃");
        typeList.add("上海小吃");
        typeList.add("四川小吃");
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", typeList));
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", typeList));
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", typeList));
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", typeList));
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", typeList));
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", typeList));
    }

    public static final List<CookClassify> getCookClassifyList() {
        return COOK_CLASSIFY_LIST;
    }
}
