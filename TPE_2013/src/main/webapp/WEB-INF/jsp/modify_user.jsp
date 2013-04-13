<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="modify-user">
	<h1>Modify your data</h1>
	
	<div>
		<form class="modifyUserForm" action="modify_user" method="POST">
			<c:import url="user_data_fields.jsp" />
			<input type="submit" value="Modify">
		</form>
	</div>
</div>