<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${registerPasswordError}"><c:set var="registerPasswordClass" value="error" /></c:if>
<c:if test="${registerRePasswordError}"><c:set var="registerRePasswordClass" value="error" /></c:if>
<c:if test="${registerNameError}"><c:set var="registerNameClass" value="error" /></c:if>
<c:if test="${registerSurnameError}"><c:set var="registerSurnameClass" value="error" /></c:if>
<c:if test="${registerEmailError}"><c:set var="registerEmailClass" value="error" /></c:if>
<c:if test="${registerAvatarError}"><c:set var="registerAvatarClass" value="error" /></c:if>

<div class="registerPassword control-group ${registerPasswordClass}">
	<label class="control-label">Password:</label>
    <div class="controls">
        <p>
            <input type="password" name="registerPassword">
        </p>
    </div>
</div>

<div class="registerRePassword control-group ${registerRePasswordClass}">
	<label class="control-label">Repeat password:</label>
    <div class="controls">
        <p>
            <input type="password" name="registerRePassword">
        </p>

        <c:if test="${registerRePasswordEmpty}">
            <p class="text-error">You have to re-enter the password.</p>
        </c:if>
        <c:if test="${registerRePasswordDoesntMatch}">
            <p class="text-error">The passwords don't match!</p>
        </c:if>
    </div>
</div>

<div class="registerName control-group ${registerNameClass}">
	<label class="control-label">Name:</label>
    <div class="controls">
        <p>
            <input type="text" name="registerName" value="${fn:escapeXml(registerName)}">
        </p>

        <c:if test="${registerNameEmpty}">
            <p class="text-error">You have to provide a name.</p>
        </c:if>
        <c:if test="${registerNameBadLength}">
            <p class="text-error">The name is too long.</p>
        </c:if>
    </div>
</div>

<div class="registerSurname control-group ${registerSurnameClass}">
	<label class="control-label">Surname:</label>
    <div class="controls">
        <p>
            <input type="text" name="registerSurname" value="${fn:escapeXml(registerSurname)}">
        </p>

        <c:if test="${registerSurnameEmpty}">
            <p class="text-error">You have to provide a surname.</p>
        </c:if>
        <c:if test="${registerSurnameBadLength}">
            <p class="text-error">The surname is too long.</p>
        </c:if>
    </div>
</div>

<div class="registerEmail control-group ${registerEmailClass}">
	<label class="control-label">Email:</label>
    <div class="controls">
        <p>
            <input type="text" name="registerEmail" value="${fn:escapeXml(registerEmail)}">
        </p>

        <c:if test="${registerEmailEmpty}">
            <p class="text-error">You have to provide an email.</p>
        </c:if>
        <c:if test="${registerEmailBadLength}">
            <p class="text-error">The email is too long.</p>
        </c:if>
        <c:if test="${registerEmailInvalidFormat}">
            <p class="text-error">The email is not valid.</p>
        </c:if>
        <c:if test="${registerEmailNotAvailable}">
            <p class="text-error">The email is already in use with another account.</p>
        </c:if>
    </div>
</div>

<div class="registerAvatar control-group ${registerAvatarClass}">
	<label class="control-label">Avatar image:</label>
    <div class="controls">
        <p>
            <input type="file" name="registerAvatar">
        </p>

        <c:if test="${registerAvatarEmpty}">
            <p class="text-error">You have to provide an avatar image.</p>
        </c:if>
    </div>
</div>
