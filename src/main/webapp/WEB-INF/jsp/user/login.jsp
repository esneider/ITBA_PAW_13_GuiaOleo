<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:import url="../header.jsp" />

<div class="login-container">
	<div class="login span6 absolute">
        <div class="offset2 span4 section-header">
            <h2>Login</h2>

            <c:if test="${invalidUser}">
                <p class="text-error text-left">
                    <strong>Error:</strong> incorrect user or password.
                </p>
            </c:if>
        </div>

		<form:form class="loginForm form-horizontal" action="login" method="POST" commandName="loginForm">
		
			<input type="hidden" name="from" value="${fn:escapeXml(from)}"/>

            <div class="loginUsername control-group ${loginUsernameClass}">
				<label class="control-label">User:</label>
                <div class="controls">
                    <p>
                        <form:input path="username"/>
                    	<p class="text-error"><form:errors path="username" /></p>
                    </p>
                </div>
			</div>

            <div class="loginPassword control-group ${loginPasswordClass}">
				<label class="control-label">Password:</label>
                <div class="controls">
                    <p>
                        <form:input type="password" path="password"/>
                    	<p class="text-error"><form:errors path="password" /></p>
                    </p>
                </div>
			</div>

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Login">
            </div>

		</form:form>
	</div>

	<div class="register span6 offset6">
        <div class="offset2 span4 section-header">
            <h2>Register</h2>
        </div>

		<form:form class="registerForm form-horizontal" action="register" enctype="multipart/form-data" method="POST" commandName="registerForm">
		
			<form:hidden path="userId" />
			
			<input type="hidden" name="from" value="${fn:escapeXml(from)}"/>

            <div class="registerUsername control-group ${registerUsernameClass}">
                <label class="control-label">User:</label>
                <div class="controls">
                    <p>
                    	<form:input path="username"/>
                    	<p class="text-error"><form:errors path="username" /></p>
                    </p>
                </div>
            </div>

			<%@ include file="user_data_fields.jsp" %>

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Register">
            </div>

		</form:form>
	</div>
</div>
