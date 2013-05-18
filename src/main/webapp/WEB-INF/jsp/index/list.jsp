<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="../header.jsp" />
<div class="tabbable tabs-left">
    <ul class="nav nav-tabs">

        <li class="${tab_all}"><a href="list?query=all">
            <strong class="name text-warning">All restaurants</strong>
            <br>
            <c:if test="${numberOfRestaurants > 1}"><c:set var="s" value="s" /></c:if>
            <span class="number muted">${numberOfRestaurants} restaurant${s}</span>
        </a></li>

        <c:forEach var="foodtype" items="${foodTypesList}">
            <c:if test="${foodtype.ammount > 0}">
                <c:set var="mclass" value="" />
                <c:if test="${tab_active == foodtype.id}">
                    <c:set var="mclass" value="active" />
                </c:if>
                <li class="${mclass}">
                    <a href="list?query=foodtypes&id=${foodtype.id}">
                        <strong class="name">${fn:escapeXml(foodtype.name)}</strong>
                        <br>
                        <c:if test="${foodtype.ammount > 1}"><c:set var="s" value="s" /></c:if>
                        <span class="number muted">${foodtype.ammount} restaurant${s}</span>
                    </a>
                </li>
            </c:if>
        </c:forEach>

    </ul>
    <div class="tab-content">
        <div class="tab-pane active">
            <div class="restlist">
			     <c:choose>
					<c:when test="${fn:length(restaurantList) gt 0}">
						<c:forEach var="restaurant" items="${restaurantList}">
						    <dl class="dl-horizontal mainList">
			                    <dt>Restaurant</dt>
			                        <dd><strong><a href="../restaurant/view?id=${restaurant.id}">${restaurant.name}</a></strong></dd>
			                    <dt>Address</dt>
			                        <dd>${restaurant.address}</dd>
								<dt>Food type</dt>
			                        <dd>${restaurant.foodtype.name}</dd>
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
			            <p id="no_results" class="lead text-center">No results found for "${squery}" </p>
					</c:otherwise>
				</c:choose>
			</div>
        </div>
    </div>
</div>


<c:import url="../footer.jsp" />

