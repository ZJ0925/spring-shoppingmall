package com.zj.springshoppingmall.DataTransferObject;

import com.zj.springshoppingmall.constant.ProductCategory;

//直接將前端的參數變成一個class，以後如果要新增參數就可以加進來透過getter與setter的方式處理
public class ProductQueryParams {
    private ProductCategory category;
    private String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
