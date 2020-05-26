package com.wang.gateway.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * page请求方法
 *
 */
public class PageRequest {

    /**
     * 默认页
     * @param pageSize
     * @param page
     * @return
     */
    public static Page defaultPage(Integer page,Integer pageSize) {
        return PageHelper.startPage(page == null ? 1 : page, pageSize == null ? 10 : pageSize);
    }
    
    /**
     * 默认页
     * @param request
     * @return
     */
    public static Page defaultPage(HttpServletRequest request) {
        Integer page = StringUtils.isEmpty(request.getParameter("page")) ? 1 : Integer.valueOf(request.getParameter("page"));
        Integer pageSize = StringUtils.isEmpty(request.getParameter("pageSize")) ? 10 : Integer.valueOf(request.getParameter("pageSize"));
        return PageHelper.startPage(page, pageSize);
    }

}
