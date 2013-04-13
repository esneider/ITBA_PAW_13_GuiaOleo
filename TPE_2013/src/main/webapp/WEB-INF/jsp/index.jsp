<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul>
	<c:forEach var="foodtype" items="${foodTypesList}">
		<li><a href="list?query=foodtypes&id=${foodtype.id}">${foodtype.name}</a>
		<br> ${foodtype.ammount} restaurant(s) serve it</li>
		<br>
	</c:forEach>
		
	
</ul>
<hr/>
<ul>
	<c:forEach var="restaurant" items="${bestRestaurants}">
		<li><a href="view?&id=${restaurant.id}">${restaurant.name}</a> 
		<br> Average Score: (${restaurant.avgScore})</li>
	</c:forEach>
</ul>

