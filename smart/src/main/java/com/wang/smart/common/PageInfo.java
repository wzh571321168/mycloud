package com.wang.smart.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PageInfo implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 每页数量
     */
    private int pageSize;
    /**
     * 页码
     */
    private int page;
    
    /**
     * 总页数
     */
    private int pages;
    /**
     * 数据总数
     */
    private Long total;
}