package com.ytempest.lovefood.http.data;

import com.ytempest.lovefood.util.DataUtils;

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
        List<String> list1 = new ArrayList<>();
        list1.add("广东小吃");
        list1.add("四川小吃");
        list1.add("重庆小吃");
        list1.add("陕西小吃");
        list1.add("湖南小吃");
        list1.add("福建小吃");
        list1.add("北京小吃");
        list1.add("天津小吃");
        list1.add("山东小吃");
        list1.add("江浙小吃");
        list1.add("其他小吃");
        COOK_CLASSIFY_LIST.add(new CookClassify("小吃", list1));

        List<String> list2 = new ArrayList<>();
        list2.add("糖水");
        list2.add("果汁");
        list2.add("布丁");
        list2.add("果冻");
        list2.add("酸奶");
        list2.add("其他");
        COOK_CLASSIFY_LIST.add(new CookClassify("甜品", list2));

        List<String> list3 = new ArrayList<>();
        list3.add("粤菜");
        list3.add("川菜");
        list3.add("鲁菜");
        list3.add("闽菜");
        list3.add("湘菜");
        list3.add("苏菜");
        list3.add("豫菜");
        list3.add("潮汕菜");
        list3.add("北京菜");
        list3.add("上海菜");
        list3.add("东北菜");
        list3.add("云南菜");
        list3.add("其他");
        COOK_CLASSIFY_LIST.add(new CookClassify("菜系", list3));

        List<String> list4 = new ArrayList<>();
        list4.add("蛋糕");
        list4.add("面包");
        list4.add("饼干");
        list4.add("蛋挞");
        list4.add("曲奇");
        list4.add("派");
        list4.add("卷");
        list4.add("吐司");
        list4.add("其他");
        COOK_CLASSIFY_LIST.add(new CookClassify("烘焙", list4));
    }

    public static final List<CookClassify> getCookClassifyList() {
        return COOK_CLASSIFY_LIST;
    }


    public static List<String> getGroupList() {
        List<String> list = new ArrayList<>();
        for (int i = 0, len = DataUtils.getSize(COOK_CLASSIFY_LIST); i < len; i++) {
            CookClassify classify = COOK_CLASSIFY_LIST.get(i);
            String group = classify.getGroup();
            list.add(group);

        }
        return list;

    }

    public static List<String> getTypeByGroup(String group) {
        for (int i = 0, len = DataUtils.getSize(COOK_CLASSIFY_LIST); i < len; i++) {
            CookClassify classify = COOK_CLASSIFY_LIST.get(i);
            if (group.equals(classify.getGroup())) {
                return COOK_CLASSIFY_LIST.get(i).getTypeList();
            }
        }
        return new ArrayList<>();
    }
}
