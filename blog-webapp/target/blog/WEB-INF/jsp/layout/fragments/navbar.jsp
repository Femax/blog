<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld" %>

<tiles:importAttribute name="view" toName="selectedView" />

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="<spring:url value="/main/"/>">dimafeng</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <c:forEach var="item" items="main,contact">
                        <spring:message code="navbar.${item}.title" var="itemTitle"/>
                        <c:choose>
                            <c:when test="${item eq selectedView}">
                                <li class="active"><a href="/${item}/">${itemTitle}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="/${item}/">${itemTitle}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div> <!--/.nav-collapse -->
        </div>
    </div>
</div>