<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div>
    <ul class="span5 offset3">
        <c:forEach var="restaurant" items="${bestRestaurants}">
            <li><a href="view?&id=${restaurant.id}">${fn:escapeXml(restaurant.name)}</a>
            <br> Average Score: (${restaurant.avgScore})</li>
        </c:forEach>
    </ul>
</div>

