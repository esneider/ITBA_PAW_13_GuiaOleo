<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:import url="../header.jsp" />
<div class="modify-user offset2 span8">
    <div class="offset2 span4 section-header clearfix">
        <h2>Modify your data</h2>
    </div>
    
    <c:if test="${registerOldPasswordError}"><c:set var="registerOldPasswordClass" value="error" /></c:if>

	<div>
		<form:form class="modifyUserForm form-horizontal" action="modify_user" enctype="multipart/form-data" method="POST" commandName="registerForm">

			<div class="registerPassword control-group ${registerOldPasswordClass}">
				<label class="control-label">Previous Password:</label>
			    <div class="controls">
			        <p>
			            <form:input type="password" name="registerOldPassword" path="oldPassword">
			            <p class="text-error"><form:errors path="oldPassword" /></p>
			        </p>

			    </div>
			</div>
			
			<c:import url="user_data_fields.jsp" />

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Modify">
            </div>

		</form>
	</div>
</div>
<c:import url="../footer.jsp" />
