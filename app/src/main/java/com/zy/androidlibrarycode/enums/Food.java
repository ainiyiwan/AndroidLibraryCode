package com.zy.androidlibrarycode.enums;

/**
 * ================================================
 * 作    者：zhangyang
 * 版    本: V4.0.0
 * 描    述：
 * ================================================
 * // <editor-folder desc="静态成员变量区域">
 * // </editor-folder>
 * <p>
 * // <editor-folder desc="View区域">
 * // </editor-folder>
 * <p>
 * // <editor-folder desc="对象区域">
 * // </editor-folder>
 * <p>
 * // <editor-folder desc="基础类型区域">
 * // </editor-folder>
 */
public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;
    }
    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;
    }
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;
    }
}


