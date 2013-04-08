<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class=restlist>
<ul>
	<c:forEach var="restaurant" items="${restaurantList}">
		<li> Restaurante:  <a href="view?query=restaurant&id='${restaurant.id}'">${restaurant.name}</a></li>
	   </br> ${restaurant.area}
	</c:forEach>
</ul>
<hr/>
<div>
