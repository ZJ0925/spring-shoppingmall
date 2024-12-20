package com.zj.springshoppingmall.constant;

public class tetsEnum {
    public static void main(String[] args) {
        //category裡的值為FOOD的值
        ProductCategory category = ProductCategory.FOOD;
        //可使用Enum裡的name方法將Enum的值轉換為字串
        String s = category.name();

        String s2 = "CAR";
        //可以知到在ProductCategory裏面有沒有字是符合s2的
        ProductCategory category2 = ProductCategory.valueOf(s2);
    }
}
