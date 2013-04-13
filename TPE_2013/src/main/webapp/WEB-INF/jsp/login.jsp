<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="login-container">
	<h1>Login or Register</h1>

	<div class="login">
		<c:if test="${invalidUser}">
			<p class="error">Incorrect user or password.</p>
		</c:if>
		<form class="loginForm" action="login" method="POST">
			<div class="loginUsername">
				<label>User:</label>
				<input type="text" name="loginUsername" value="${fn:escapeXml(loginUsername)}">

				<c:if test="${loginUsernameEmpty}">
					<p class="error">You have to provide a username.</p>
				</c:if>
				<c:if test="${loginUsernameBadLength}">
					<p class="error">The username is too long.</p>
				</c:if>
			</div>
			<div class="loginPassword">
				<label>Password:</label>
				<input type="password" name="loginPassword">

				<c:if test="${loginPasswordEmpty}">
					<p class="error">You have to provide a password.</p>
				</c:if>
				<c:if test="${loginPasswordBadLength}">
					<p class="error">The password is too long.</p>
				</c:if>
			</div>
			<input type="submit" value="Login">
		</form>
	</div>
	
	<hr />
	
	<div class="register">
		<form class="registerForm" action="register" method="POST">
			<c:import url="user_data_fields.jsp" />
			<input type="submit" value="Register">
		</form>
	</div>
</div>