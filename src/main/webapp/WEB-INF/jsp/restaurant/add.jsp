<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="../header.jsp" />

<h2 class="restaurantTitle">Register Restaurant</h2>

<form:form class="form-horizontal" action="add" method="POST" commandName="restaurantForm">

    <%@ include file="restaurant_data_fields.jsp" %>

    <div class="control-group controls">
        <input class="btn btn-info" type="submit" value="Register Restaurant">
    </div>

</form:form>

<c:import url="../footer.jsp" />
