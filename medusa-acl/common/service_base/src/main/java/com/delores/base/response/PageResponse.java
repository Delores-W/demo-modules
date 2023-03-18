package com.delores.base.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.delores.base.utils.enums.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author William
 * @date 5/15/21 1:20 AM
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PageResponse<T> extends BaseResponse<List<T>> {

    private boolean first;

    private boolean last;

    private long currentPageNumber;

    private int itemsInPage;

    private long pageSize;

    private long totalPages;

    private long totalItems;

    private Sort sort;

//    private List<T> items;

    public PageResponse(StatusCode statusCode) {
        super(statusCode);
    }

    public PageResponse(StatusCode statusCode, String msg) {
        super(statusCode, msg);
    }

//    // for org.springframework.data.domain.Page 页码从0开始！！！
//    public void setPageStats(Page<T> pg, boolean setDefaultMessage) {
//        this.first = pg.isFirst();
//        this.last = pg.isLast();
//        this.currentPageNumber = pg.getNumber();
//        this.itemsInPage = pg.getNumberOfElements();
//        this.pageSize = pg.getSize();
//        this.totalPages = pg.getTotalPages();
//        this.totalItems = pg.getTotalElements();
//        this.sort = pg.getSort();
//        this.setData(pg.getContent());
////        this.items = pg.getContent();
//        if (setDefaultMessage) {
//            super.setCode(StatusCode.SUCCESS.getCode());
//            super.setMsg("Page " + pg.getNumber() + 1 + " of " + pg.getTotalPages());
//        }
//    }

    public PageResponse(IPage<T> pg, boolean setDefaultMessage) {
        this.setData(pg.getRecords());
        this.totalItems = pg.getTotal();
        this.totalPages = pg.getPages();
        this.currentPageNumber = pg.getCurrent();
        this.pageSize = pg.getSize();
        if (setDefaultMessage) {
            super.setCode(StatusCode.SUCCESS.getCode());
            super.setMsg("Page " + pg.getCurrent() + " of " + pg.getPages());
        }
    }

    // for com.baomidou.mybatisplus.core.metadata.IPage
    public void setPageStatsMybatisPlus(IPage<T> pg, boolean setDefaultMessage) {
//        this.items = pg.getRecords();
        this.setData(pg.getRecords());
        this.totalItems = pg.getTotal();
        this.totalPages = pg.getPages();
        this.currentPageNumber = pg.getCurrent();
        this.pageSize = pg.getSize();
        if (setDefaultMessage) {
            super.setCode(StatusCode.SUCCESS.getCode());
            super.setMsg("Page " + pg.getCurrent() + " of " + pg.getPages());
        }
    }

//    // for PageHelper Page
//    public void setPageStatsPageHelper(com.github.pagehelper.Page<T> pg, boolean setDefaultMessage) {
////        this.items = pg.getRecords();
//        this.setData(pg.getResult());
//        this.totalItems = pg.getTotal();
//        this.totalPages = pg.getPages();
//        this.currentPageNumber = pg.getPageNum();
//        this.pageSize = pg.getPageSize();
//        if (setDefaultMessage) {
//            super.setCode(StatusCode.SUCCESS.getCode());
//            super.setMsg("Page " + pg.getPageNum() + " of " + pg.getPages());
//        }
//    }
}
