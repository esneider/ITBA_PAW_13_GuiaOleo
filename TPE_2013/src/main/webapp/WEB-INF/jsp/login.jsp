<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${loginUsernameError}"><c:set var="loginUsernameClass" value="error" /></c:if>
<c:if test="${loginPasswordError}"><c:set var="loginPasswordClass" value="error" /></c:if>
<c:if test="${registerUsernameError}"><c:set var="registerUsernameClass" value="error" /></c:if>

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

		<form class="loginForm form-horizontal" action="login" method="POST">
		
			<input type="hidden" name="from" value="${fn:escapeXml(from)}"/>

            <div class="loginUsername control-group ${loginUsernameClass}">
				<label class="control-label">User:</label>
                <div class="controls">
                    <p>
                        <input type="text" name="loginUsername" value="${fn:escapeXml(loginUsername)}">
                    </p>

                    <c:if test="${loginUsernameEmpty}">
                        <p class="text-error">You have to provide a username.</p>
                    </c:if>
                    <c:if test="${loginUsernameBadLength}">
                        <p class="text-error">The username is too long.</p>
                    </c:if>
                </div>
			</div>

            <div class="loginPassword control-group ${loginPasswordClass}">
				<label class="control-label">Password:</label>
                <div class="controls">
                    <p>
                        <input type="password" name="loginPassword">
                    </p>

                    <c:if test="${loginPasswordEmpty}">
                        <p class="text-error">You have to provide a password.</p>
                    </c:if>
                    <c:if test="${loginPasswordBadLength}">
                        <p class="text-error">The password is too long.</p>
                    </c:if>
                </div>
			</div>

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Login">
            </div>

		</form>
	</div>

	<div class="register span6 offset6">
        <div class="offset2 span4 section-header">
            <h2>Register</h2>
        </div>

		<form class="registerForm form-horizontal" action="register" method="POST">

			<input type="hidden" name="from" value="${fn:escapeXml(from)}"/>

            <div class="registerUsername control-group ${registerUsernameClass}">
                <label class="control-label">User:</label>
                <div class="controls">
                    <p>
                        <input type="text" name="registerUsername" value="${fn:escapeXml(registerUsername)}">
                    </p>

                    <c:if test="${registerUsernameEmpty}">
                        <p class="text-error">You have to provide a user name.</p>
                    </c:if>
                    <c:if test="${registerUsernameBadLength}">
                        <p class="text-error">The user name is too long.</p>
                    </c:if>
                    <c:if test="${usernameExists}">
                        <p class="text-error">The user name is already taken, please choose another one.</p>
                    </c:if>
                </div>
            </div>

			<c:import url="user_data_fields.jsp" />

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Register">
            </div>

		</form>
	</div>
</div>
