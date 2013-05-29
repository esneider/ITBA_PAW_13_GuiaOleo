<spring:bind path="name">
    <c:if test="${status.error}"><c:set var="nameClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="address">
    <c:if test="${status.error}"><c:set var="addressClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="area">
    <c:if test="${status.error}"><c:set var="areaClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="avgprice">
    <c:if test="${status.error}"><c:set var="avgpriceClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="telephone">
    <c:if test="${status.error}"><c:set var="telephoneClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="timerange">
    <c:if test="${status.error}"><c:set var="timerangeClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="website">
    <c:if test="${status.error}"><c:set var="websiteClass" value="error" /></c:if>
</spring:bind>
<spring:bind path="foodTypes">
    <c:if test="${status.error}"><c:set var="foodTypesClass" value="error" /></c:if>
</spring:bind>

<div class="control-group ${nameClass}">
    <label class="control-label">Name:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="name"/>
        </p>
        <p class="text-error"><form:errors path="name" /></p>
    </div>
</div>

<div class="control-group ${addressClass}">
    <label class="control-label">Address:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="address"/>
        </p>
        <p class="text-error"><form:errors path="address" /></p>
    </div>
</div>

<div class="control-group ${areaClass}">
    <label class="control-label">Area:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="area"/>
        </p>
        <p class="text-error"><form:errors path="area" /></p>
    </div>
</div>

<div class="control-group ${avgpriceClass}">
    <label class="control-label">Average Price:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="avgprice"/>
        </p>
        <p class="text-error"><form:errors path="avgprice" /></p>
    </div>
</div>

<div class="control-group ${telephoneClass}">
    <label class="control-label">Telephone:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="telephone"/>
        </p>
        <p class="text-error"><form:errors path="telephone" /></p>
    </div>
</div>

<div class="control-group ${timerangeClass}">
    <label class="control-label">Time Range:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="timerange"/>
        </p>
        <p class="text-error"><form:errors path="timerange" /></p>
    </div>
</div>

<div class="control-group ${websiteClass}">
    <label class="control-label">Website:</label>
    <div class="controls">
        <p>
          <form:input type="text" path="website"/>
        </p>
        <p class="text-error"><form:errors path="website" /></p>
    </div>
</div>

<div class="control-group ${foodTypesClass}">
    <label class="control-label">Food Types:</label>
    <div class="controls" id="chk_container">
         <form:checkboxes path="foodTypes" items="${foodTypesList}" itemLabel="name" itemValue="id"/>
         <p class="text-error"><form:errors path="foodTypes" /></p>
    </div>
</div>

