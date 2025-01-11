/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.cie.controller;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    /**
     * Log object
     */
    protected Logger logger = Logger.getLogger(this.getClass());

    /**
     * Stores the HttpServletRequest object of the current thread
     */
    private static ThreadLocal<HttpServletRequest> httpServletRequestThreadLocal = new ThreadLocal<HttpServletRequest>();

    /**
     * Stores the Model object of the current thread
     */
    private static ThreadLocal<Model> modelThreadLocal = new ThreadLocal<Model>();

    /**
     * Methods marked with the @ModelAttribute annotation are called before the access to any controller method.
     * @param request
     * @param model
     */
    @ModelAttribute
    protected void setThreadLocal(HttpServletRequest request, Model model) {
        httpServletRequestThreadLocal.set(request);
        modelThreadLocal.set(model);
    }


    /**
     * Retrieves the HttpServletRequest object for the current thread.
     * @return the HttpServletRequest object associated with the current thread.
     */
    protected HttpServletRequest getRequest() {
        return httpServletRequestThreadLocal.get();
    }

    /**
     * Retrieves the HttpSession object for the current thread.
     * @return the HttpSession object associated with the current thread.
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * Retrieves the Model object for the current thread.
     * @return the Model object associated with the current thread.
     */
    protected Model getModel() {
        return modelThreadLocal.get();
    }

     /**
     * Retrieves the current ServletContext object.
     *
     * @return the current ServletContext object.
     */
    protected ServletContext getContext() {
        return getRequest().getServletContext();
    }





    /**
     * Sets an attribute value in the Model.
     *
     * @param name  the name of the attribute to set
     * @param value the value to set for the attribute
     */
    protected void setModelAttribute(String name, Object value) {
        getModel().addAttribute(name, value);
    }



    /**
     * Sets an attribute value in the HttpServletRequest.
     *
     * @param name  the name of the attribute to set
     * @param value the value to set for the attribute
     */
    protected void setRequestAttribute(String name, Object value) {
        HttpServletRequest request = this.getRequest();
        request.setAttribute(name, value);
    }

    /**
     * Sets an attribute value in the HttpSession.
     *
     * @param name  the name of the attribute to set
     * @param value the value to set for the attribute
     */
    public void setSessionAttribute(String name, Object value) {
        HttpSession session = this.getSession();
        session.setAttribute(name, value);
    }


    /**
     * Retrieves an attribute value from the HttpSession.
     *
     * @param name the name of the attribute to retrieve
     * @return the value of the attribute, or null if the attribute does not exist
     */
    protected Object getSessionAttribute(String name) {
        HttpSession session = this.getSession();
        Object value = session.getAttribute(name);
        return value;
    }

    /**
     * Retrieves an attribute value from the HttpServletRequest.
     *
     * @param name the name of the attribute to retrieve
     * @return the value of the attribute, or null if the attribute does not exist
     */
    protected Object getRequestAttribute(String name) {
        HttpServletRequest request = this.getRequest();
        Object value = request.getAttribute(name);
        return value;
    }

    protected String getUserAgent() {
        return this.getRequest().getHeader("User-Agent");
    }

    protected String getRemoteIp() {
        String remoteIp;
        remoteIp = this.getRequest().getHeader("x-forwarded-for");
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("HTTP_CLIENT_IP");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getHeader("HTTP_X_FORWARDED-FOR");
        }
        if (remoteIp == null || remoteIp.length() == 0 || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = this.getRequest().getRemoteAddr();
        }


        // In cases where the request passes through multiple proxies, the first IP is the client's real IP, and multiple IPs are separated by commas.
        if (remoteIp != null && remoteIp.length() > 15) { //"***.***.***.***".length() = 15
            if (remoteIp.indexOf(",") > 0) {
                remoteIp = remoteIp.substring(0, remoteIp.indexOf(","));
            }
        }
        return remoteIp;
    }

}
