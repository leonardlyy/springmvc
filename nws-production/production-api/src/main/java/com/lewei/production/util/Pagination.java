package com.lewei.production.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qwz on 2017/1/25 23:08.
 * Project lw4Home
 * @author quweizhe
 * Package com.homebase.util
 *
 * @Doc 对分页的基本数据进行一个简单的封装
 */
public class Pagination<T> {

    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private Integer pageNo = 1;// 页码，默认是第一页
    private Integer pageSize = 10; // 每页显示的记录数，默认是10
    private Integer total;// 总记录数
    private Integer totalPage;// 总页数
    private Integer offset; // 起始行数
    private String order;
    private String sort;
    private String url;//分页的url
    private List<T> results;// 对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo == null ? DEFAULT_PAGE_NO : pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        return totalPage ;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort==null? Code.DESC:sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
