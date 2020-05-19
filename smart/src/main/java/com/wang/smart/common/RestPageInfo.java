package com.wang.smart.common;


import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class RestPageInfo extends RestPageResult {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private PageInfo pageInfo;

    public RestPageInfo(int code, String msg, Page page) {
        super(ErrorCode.SUCCESS, (String) null, 0, (List) page.getResult());
        this.pageInfo = new PageInfo(page.getPageSize(), page.getPageNum(), page.getPages(), page.getTotal());
    }

    public static RestPageInfo success(Page page){
        return new RestPageInfo(ErrorCode.SUCCESS,"",page);
    }

}


