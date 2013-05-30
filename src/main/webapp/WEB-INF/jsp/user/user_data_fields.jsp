<spring:bind path="password">
    <c:if test="${status.error}"><c:set var="registerPasswordClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="repassword">
    <c:if test="${status.error}"><c:set var="registerRePasswordClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="name">
    <c:if test="${status.error}"><c:set var="registerNameClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="surname">
    <c:if test="${status.error}"><c:set var="registerSurnameClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="email">
    <c:if test="${status.error}"><c:set var="registerEmailClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="avatar">
    <c:if test="${status.error}"><c:set var="registerAvatarClass" value="error" /></c:if>
</spring:bind>

<div class="registerPassword control-group ${registerPasswordClass}">
    <label class="control-label">Password:</label>
    <div class="controls">
        <p>
          <form:password type="password" path="password"/>
        </p>
        <p class="text-error"><form:errors path="password" /></p>
    </div>
</div>

<div class="registerRePassword control-group ${registerRePasswordClass}">
    <label class="control-label">Repeat password:</label>
    <div class="controls">
         <p>
          <form:password type="password" path="repassword"/>
        </p>
        <p class="text-error"><form:errors path="repassword" /></p>
    </div>
</div>

<div class="registerName control-group ${registerNameClass}">
    <label class="control-label">Name:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="name"/>
        </p>
        <p class="text-error"><form:errors path="name" /></p>

    </div>
</div>

<div class="registerSurname control-group ${registerSurnameClass}">
    <label class="control-label">Surname:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="surname"/>
        </p>
        <p class="text-error"><form:errors path="surname" /></p>
    </div>
</div>

<div class="registerEmail control-group ${registerEmailClass}">
    <label class="control-label">Email:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="email"/>
        </p>
        <p class="text-error"><form:errors path="email" /></p>
    </div>
</div>

<div class="registerAvatar control-group ${registerAvatarClass}">
    <label class="control-label">Avatar image:</label>
    <div class="controls">
   <p>
          <form:input type="file" path="avatar"/><br>
        	Valid formats: .jpg, .jpeg
	</p>
        <p class="text-error"><form:errors path="avatar" /></p>
    </div>
</div>
