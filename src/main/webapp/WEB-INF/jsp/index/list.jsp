<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="../header.jsp" />
<div class="restlist">
     <c:choose>
		<c:when test="${fn:length(restaurantList) gt 0}">
			<c:forEach var="restaurant" items="${restaurantList}">
			    <dl class="dl-horizontal mainList">
                    <dt>Restaurant</dt>
                        <dd><strong><a href="../restaurant/view?id=${restaurant.id}">${restaurant.name}</a></strong></dd>
                    <dt>Address</dt>
                        <dd>${restaurant.address}</dd>
                    <dt>Area</dt>
                        <dd>${restaurant.area}</dd>
                    <dt>Average Score</dt>
                        <dd>
                            <span class="badge">${restaurant.avgScore}</span>
                            (Scored by people)
                        </dd>
                </dl>
                <hr />
			</c:forEach>
		</c:when>
		<c:otherwise>
            <p id="no_results" class="lead text-center">No results found for "${squery}" </p>
		</c:otherwise>
	</c:choose>
</div>
<c:import url="../footer.jsp" />

