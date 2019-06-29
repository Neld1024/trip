package cn.wolfcode.trip.admin.web.filter;

import cn.wolfcode.trip.admin.util.UploadUtil;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ImageFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //手动去对应的路径中找文件并响应

        //获取用户请求资源的URI
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();


        //拼接一个完整的路径,找到文件并响应
        String filePath = UploadUtil.commonPath + requestURI;
        File f = new File(filePath);
        if (f.exists()) {
            response.getOutputStream().write(FileUtils.readFileToByteArray(f));
        } else {
            //如果文件不存在,直接放行
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }
}
