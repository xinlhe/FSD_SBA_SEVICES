package com.ibm.sba.microservice.smc.userclient.filter;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.alibaba.fastjson.JSON;

/**
 * JWTTokenFilter
 * 
 * @author XinLongHe
 *
 * @since 2019.11.29
 */
@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUserTokenUtil jwtTokenUtil;

    public final static String HEADER_AUTH = "Authorization";
    public final static String loginPath = "/smcuser/login";
    public final static String logoutPath = "/smcuser/logout";
    public final static String userCreate = "/smcuser/adduser";
    public final static String userMail = "/smcuser/mail";
    public final static String userActivate = "/smcuser/activate";

    public final static String tokenPath = "/smcuser/allowaccess";
    public final static String userUpdate = "/smcuser/update";

    public final static String urlPattern = "/smcuser/";
    
    public final static String ribbonLoginPath = "/smcuser/usersRibbonLogin";
    

    private boolean needValidate(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        if (url.endsWith(tokenPath) || url.endsWith(userUpdate)) {
            return false;
        }

        if (url.indexOf(urlPattern) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        Enumeration<?> names = request.getParameterNames();
        
        while (names.hasMoreElements()) {
        
            String name = (String) names.nextElement();
            
            System.out.print("----" + name + ":" + request.getParameter(name));
        }
        
        if (!needValidate(request)) {
        
            String reqHeader = request.getHeader(HEADER_AUTH);
            
            String msg = null;
            
            if (reqHeader == null) {
            
                msg = "Token is not provided";
                composeResponse(request, response, msg);
                return;
            }

            if (!reqHeader.startsWith(JWTUserTokenUtil.BEAR_TOKEN)) {
                
                msg = "Token type is not matched!";
                composeResponse(request, response, msg);
                return;
            }

            final String authToken = reqHeader.substring(JWTUserTokenUtil.BEAR_TOKEN.length());
           
            String username = null;
            
            try {
            
                username = jwtTokenUtil.getUserNameFromToken(authToken);
            } 
            catch (Exception e) {
                
                msg = e.getMessage();
                composeResponse(request, response, msg);
                return;
            }

            if (StringUtils.isEmpty(username)) {
               
                msg = "Invalid token";
                composeResponse(request, response, msg);
                return;
            }
            
            boolean isValid = false;

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            try {
                
                isValid = jwtTokenUtil.validateToken(authToken, userDetails);
            } 
            catch (Exception e) {
            
                msg = e.getMessage();
                composeResponse(request, response, msg);
                return;
            }
            if (isValid) {
                
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        
        chain.doFilter(request, response);

    }

    private void composeResponse(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        
        String url = request.getServletPath();
        
        if (url.endsWith(tokenPath)) {
        
            response.getWriter().write("false");
        } 
        else {
        
            Date curDate = new Date();
            
            Map<String, String> map = new HashMap<String, String>();
            
            map.put("timestamp", DateFormat.getInstance().format(curDate));
            map.put("error", msg);
            map.put("status", "403");
            map.put("path", request.getRequestURL().toString());
            
            response.getWriter().write(JSON.toJSONString(map));
        }
    }
}
