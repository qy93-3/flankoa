package com.flank.utils;

import com.flank.beans.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 树状菜单工具类
 */
public class TreeBuilder {
    /**
     * 两层循环实现建树
     *
     * @param menus 传入的树节点列表
     * @return
     */
    public static List<Menu> build(List<Menu> menus) {
        List<Menu> trees = new ArrayList<Menu>();
        for (Menu menu : menus) {
            if (menu.getPid() == 0) {
                trees.add(menu);
            }

            for (Menu child : menus) {
                if (child.getPid() == menu.getMenuId().intValue()) {
                    if (menu.getChildren() == null) {
                        menu.setChildren(new ArrayList<Menu>());
                    }
                    menu.getChildren().add(child);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     *
     * @param menus
     * @return
     */
    public static List<Menu> buildByRecursive(List<Menu> menus) {
        List<Menu> trees = new ArrayList<Menu>();
        for (Menu menu : menus) {
            if ("0".equals(menu.getPid())) {
                trees.add(findChildren(menu, menus));
            }
        }
        return trees;
    }

    public static Menu findChildren(Menu menu, List<Menu> menus) {
        for (Menu child : menus) {
            if (menu.getMenuId().equals(child.getPid())){
                if (menu.getChildren()==null){
                    menu.setChildren(new ArrayList<Menu>());
                }
                menu.getChildren().add(findChildren(child,menus));
            }
        }
        return menu;
    }
}
