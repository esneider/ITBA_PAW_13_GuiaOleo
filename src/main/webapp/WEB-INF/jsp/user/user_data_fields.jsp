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
          <form:input type="password" name="registerPassword" path="password"/>
        </p>
       	<p class="text-error"><form:errors path="password" /></p>

    </div>
</div>

<div class="registerRePassword control-group ${registerRePasswordClass}">
	<label class="control-label">Repeat password:</label>
    <div class="controls">
         <p>
          <form:input type="password" path="repassword"/>
        </p>
       	<p class="text-error"><form:errors path="repassword" /></p>
    </div>
</div>

<div class="registerName control-group ${registerNameClass}">
	<label class="control-label">Name:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="name" path="name"/>
        </p>
       	<p class="text-error"><form:errors path="name" /></p>
        
    </div>
</div>

<div class="registerSurname control-group ${registerSurnameClass}">
	<label class="control-label">Surname:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="name" path="surname"/>
        </p>
       	<p class="text-error"><form:errors path="surname" /></p>
    </div>
</div>

<div class="registerEmail control-group ${registerEmailClass}">
	<label class="control-label">Email:</label>
    <div class="controls">
		<p>
          <form:input type="text" name="email" path="email"/>
        </p>
       	<p class="text-error"><form:errors path="email" /></p>
    </div>
</div>

<div class="registerAvatar control-group ${registerAvatarClass}">
	<label class="control-label">Avatar image:</label>
    <div class="controls">
   <p>
          <form:input type="file" name="avatar" path="avatar"/>
        </p>
       	<p class="text-error"><form:errors path="avatar" /></p>
    </div>
</div>
