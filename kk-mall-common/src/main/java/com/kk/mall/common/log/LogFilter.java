package com.kk.mall.common.log;


import cn.hutool.core.util.IdUtil;
import com.kk.mall.common.Const;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
@Component
@Order(1)
class LogFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean bInsertMDC = insertMDC();
        try {
            chain.doFilter(request, response);
        } finally {
            if(bInsertMDC) {
                MDC.remove(Const.REQUEST_ID);
            }
        }

    }


    private boolean insertMDC() {
        MDC.put(Const.REQUEST_ID, IdUtil.simpleUUID());
        return true;
    }


    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig)  {

    }

}
