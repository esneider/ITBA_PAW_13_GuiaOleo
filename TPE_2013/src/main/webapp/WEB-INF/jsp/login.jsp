<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="login-container">
	<div class="login">
		<c:if test="${invalidUser}">
			<p class="error">Incorrect user or password.</p>
		</c:if>
		<form class="login-form" action="login" method="POST">
			<div class="login-username">
				<label>User:</label>
				<input type="text" name="loginUsername" value="${fn:escapeXml(loginUsername)}">

				<c:if test="${loginUsernameEmpty}">
					<p class="error">You have to provide a username.</p>
				</c:if>
				<c:if test="${loginUsernameBadLength}">
					<p class="error">The username is too long.</p>
				</c:if>
			</div>
			<div class="login-password">
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
		<form class="register-form" action="register" method="POST">
			<div class="username">
				<label>User:</label>
				<input type="text" name="username">
			</div>
			<div class="password">
				<label>Password:</label>
				<input type="password" name="password">
			</div>
			<div class="repeat-password">
				<label>Repeat password:</label>
				<input type="password" name="repeat-password">
			</div>
			<div class="name">
				<label>Full name:</label>
				<input type="text" name="name">
			</div>
			<div class="mail">
				<label>Email:</label>
				<input type="text" name="mail">
			</div>
			<input type="submit" value="Register">
		</form>
	</div>
</div>