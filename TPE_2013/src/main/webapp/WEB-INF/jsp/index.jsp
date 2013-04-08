<<<<<<< Updated upstream
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
=======
<div class=index>
>>>>>>> Stashed changes
<ul>
	<c:forEach var="foodtype" items="${foodTypesList}">
		<li><a href="list?query='foodtypes'&id='${foodtype.id}'">${foodtype.name}(${foodtype.ammount})</a></li>
	</c:forEach>
</ul>
<hr/>
<ul>
	<c:forEach var="restaurant" items="${bestRestaurants}">
		<li><a href="restaurantView?&id='${restaurant.id}'">${restaurant.name}(${restaurant.rating})</a></li>
	</c:forEach>
</ul>
<div>
