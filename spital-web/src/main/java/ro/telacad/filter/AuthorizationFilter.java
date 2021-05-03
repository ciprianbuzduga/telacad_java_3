/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ro.telacad.util.SessionUtil;

/**
 *
 * @author Buzy
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
 
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
 
            String reqURI = reqt.getRequestURI();
            
            //System.out.println("reqURI: " + reqURI);
            if (reqURI.indexOf("/login.xhtml") >= 0
                    || (ses != null && ses.getAttribute(SessionUtil.USER_NAME_KEY) != null)) {
                //System.out.println(">>>>> doFilter");
                chain.doFilter(request, response);
            } else {
                //System.out.println(">>>>> sendRedirect");
                resp.sendRedirect(reqt.getContextPath() + "/faces/public/login.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    @Override
    public void destroy() {
 
    }
}
