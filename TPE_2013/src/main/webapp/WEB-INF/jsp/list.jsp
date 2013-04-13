<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class=restlist>
<ul>
	<c:forEach var="restaurant" items="${restaurantList}">
		<li> Restaurant:  <a href="view?id=${restaurant.id}">${restaurant.name}</a> 
		<br>
		Address: ${restaurant.address} <br>
		Area: ${restaurant.area} <br>
		Average Score: ${restaurant.avgScore} (Scored by ${restaurant.ratings} people) </li>
		</br> 
	</c:forEach>
	

</ul
</ul>
<hr/>
<div>
