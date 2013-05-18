<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="modify-user offset2 span8">
    <div class="offset2 span4 section-header clearfix">
        <h2>Modify your data</h2>
    </div>
    
    <c:if test="${registerOldPasswordError}"><c:set var="registerOldPasswordClass" value="error" /></c:if>

	<div>
		<form class="modifyUserForm form-horizontal" action="modify_user" enctype="multipart/form-data" method="POST">

			<div class="registerPassword control-group ${registerOldPasswordClass}">
				<label class="control-label">Previous Password:</label>
			    <div class="controls">
			        <p>
			            <input type="password" name="registerOldPassword">
			        </p>
			
			        <c:if test="${registerOldPasswordEmpty}">
			            <p class="text-error">You have to provide a password.</p>
			        </c:if>
			        <c:if test="${registerOldPasswordBadLength}">
			            <p class="text-error">The password is too long.</p>
			        </c:if>
			        <c:if test="${registerOldPasswordInvalid}">
			            <p class="text-error">The password is invalid.</p>
			        </c:if>
			    </div>
			</div>
			
			<c:import url="user_data_fields.jsp" />

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Modify">
            </div>

		</form>
	</div>
</div>
