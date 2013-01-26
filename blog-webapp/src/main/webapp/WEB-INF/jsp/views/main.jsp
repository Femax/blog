<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/util" %>
<%@ taglib prefix="tb" uri="/WEB-INF/taglib/taglib.tld" %>

<c:forEach items="${items}" var="item">
    <div class="blog-list-entry">
        <a href="/show/${item.titleEnglish}">
        					${item.title}
        			</a>

        			<div class="blog-list-entry-tags-blog">
        				<c:forEach items="${item.tags}" var="tag">
                            <span class="label">${tag}</span>
        				</c:forEach>
        			</div>
    </div>
</c:forEach>
