<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class=index>
<ul>
	<c:forEach var="foodtype" items="${foodTypesList}">
		<li><a href="list?query=foodtypes&id=${foodtype.id}">${foodtype.name}(${foodtype.ammount})</a></li>
	</c:forEach>
</ul>
<hr/>
<ul>
	<c:forEach var="restaurant" items="${bestRestaurants}">
		<li><a href="view?&id=${restaurant.id}">${restaurant.name} (${restaurant.avgScore})</a></li>
	</c:forEach>
</ul>
<div>
