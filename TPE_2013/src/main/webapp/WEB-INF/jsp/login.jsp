<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="login-container">
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
			<div class="registerUsername">
				<label>User:</label>
				<input type="text" name="registerUsername" value="${fn:escapeXml(registerUsername)}">

				<c:if test="${registerUsernameEmpty}">
					<p class="error">You have to provide a user name.</p>
				</c:if>
				<c:if test="${registerUsernameBadLength}">
					<p class="error">The user name is too long.</p>
				</c:if>
				<c:if test="${usernameExists}">
					<p class="error">The user name is already taken, please choose another one.</p>
				</c:if>
			</div>
			<div class="registerPassword">
				<label>Password:</label>
				<input type="password" name="registerPassword">

				<c:if test="${registerPasswordEmpty}">
					<p class="error">You have to provide a password.</p>
				</c:if>
				<c:if test="${registerPasswordBadLength}">
					<p class="error">The password is too long.</p>
				</c:if>
			</div>
			<div class="registerRePassword">
				<label>Repeat password:</label>
				<input type="password" name="registerRePassword">

				<c:if test="${registerRePasswordEmpty}">
					<p class="error">You have to re-enter the password.</p>
				</c:if>
				<c:if test="${registerPasswordsDontMatch}">
					<p class="error">The passwords don't match!</p>
				</c:if>
			</div>
			<div class="registerName">
				<label>Name:</label>
				<input type="text" name="registerName" value="${fn:escapeXml(registerName)}">

				<c:if test="${registerNameEmpty}">
					<p class="error">You have to provide a name.</p>
				</c:if>
				<c:if test="${registerNameBadLength}">
					<p class="error">The name is too long.</p>
				</c:if>
			</div>
			<div class="registerSurname">
				<label>Surname:</label>
				<input type="text" name="registerSurname" value="${fn:escapeXml(registerSurname)}">

				<c:if test="${registerSurnameEmpty}">
					<p class="error">You have to provide a surname.</p>
				</c:if>
				<c:if test="${registerSurnameBadLength}">
					<p class="error">The surname is too long.</p>
				</c:if>
			</div>
			<div class="registerMail">
				<label>Email:</label>
				<input type="text" name="registerMail" value="${fn:escapeXml(registerMail)}">

				<c:if test="${registerMailEmpty}">
					<p class="error">You have to provide an email.</p>
				</c:if>
				<c:if test="${registerMailBadLength}">
					<p class="error">The email is too long.</p>
				</c:if>
			</div>
			<input type="submit" value="Register">
		</form>
	</div>
</div>