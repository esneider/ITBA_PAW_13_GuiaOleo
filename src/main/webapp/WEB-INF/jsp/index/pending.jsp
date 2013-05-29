<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../header.jsp" />

<div class="restlist">
     <c:choose>
        <c:when test="${fn:length(restaurantList) gt 0}">

        <h3> Pending Restaurants </h3>

            <c:forEach var="restaurant" items="${restaurantList}">
                <dl class="dl-horizontal mainList">
                    <dt>Publisher</dt>
                        <dd>${restaurant.registerUser.name}</dd>
                    <dt>Publishing Date</dt>
                        <dd>${restaurant.applicationDate}</dd>
                    <dt>View Request</dt>
                        <dd><a href="${pageContext.request.contextPath}/bin/restaurant/publish?id=${restaurant.id}"> Here </a> </dd>

                    <dt>Restaurant</dt>
                        <dd><strong>${restaurant.name}</strong></dd>

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

