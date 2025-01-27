package com.zj.springshoppingmall.util;

import java.util.List;

public class Page<T> {
    private Integer limit;
    private Integer offset;
    private Integer total;//表示商品總筆數
    private List<T> result;//存放查詢出來的數據

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
