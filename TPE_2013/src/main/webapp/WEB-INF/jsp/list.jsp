<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="restlist">
	<c:choose>
		<c:when test="${fn:length(restaurantList) gt 0}">
			<c:forEach var="restaurant" items="${restaurantList}">
			    <dl class="dl-horizontal">
                    <dt>Restaurant</dt>
                        <dd><strong><a href="view?id=${restaurant.id}">${restaurant.name}</a></strong></dd>
                    <dt>Address</dt>
                        <dd>${restaurant.address}</dd>
                    <dt>Area</dt>
                        <dd>${restaurant.area}</dd>
                    <dt>Average Score</dt>
                        <dd>
                            <span class="badge">${restaurant.avgScore}</span>
                            (Scored by ${restaurant.ratings} people)
                        </dd>
                </dl>
                <hr />
			</c:forEach>
		</c:when>
		<c:otherwise>
            <p>No restaurants found</p>
		</c:otherwise>
	</c:choose>
<div>

