<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${registerPasswordError}"><c:set var="registerPasswordClass" value="error" /></c:if>
<c:if test="${registerRePasswordError}"><c:set var="registerRePasswordClass" value="error" /></c:if>
<c:if test="${registerNameError}"><c:set var="registerNameClass" value="error" /></c:if>
<c:if test="${registerSurnameError}"><c:set var="registerSurnameClass" value="error" /></c:if>
<c:if test="${registerMailError}"><c:set var="registerMailClass" value="error" /></c:if>

<div class="registerPassword control-group ${registerPasswordClass}">
	<label class="control-label">Password:</label>
    <div class="controls">
        <p>
            <input type="password" name="registerPassword">
        </p>

        <c:if test="${registerPasswordEmpty}">
            <p class="text-error">You have to provide a password.</p>
        </c:if>
        <c:if test="${registerPasswordBadLength}">
            <p class="text-error">The password is too long.</p>
        </c:if>
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

<div class="registerMail control-group ${registerMailClass}">
	<label class="control-label">Email:</label>
    <div class="controls">
        <p>
            <input type="text" name="registerMail" value="${fn:escapeXml(registerMail)}">
        </p>

        <c:if test="${registerMailEmpty}">
            <p class="text-error">You have to provide an email.</p>
        </c:if>
        <c:if test="${registerMailBadLength}">
            <p class="text-error">The email is too long.</p>
        </c:if>
        <c:if test="${registerMailInvalidFormat}">
            <p class="text-error">The email is not valid.</p>
        </c:if>
    </div>
</div>
