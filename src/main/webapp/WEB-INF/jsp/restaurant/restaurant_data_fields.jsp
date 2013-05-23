<div class="control-group">
	<label class="control-label">Name:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerName" path="name"/>
        </p>
       	<p class="text-error"><form:errors path="name" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Address:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerAddress" path="address"/>
        </p>
       	<p class="text-error"><form:errors path="address" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Area:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerArea" path="area"/>
        </p>
       	<p class="text-error"><form:errors path="area" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Average Price:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerAverageprice" path="avgprice"/>
        </p>
       	<p class="text-error"><form:errors path="avgprice" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Telephone:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerTelephone" path="telephone"/>
        </p>
       	<p class="text-error"><form:errors path="telephone" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Time Range:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerTimeRange" path="timerange"/>
        </p>
       	<p class="text-error"><form:errors path="timerange" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Website:</label>
    <div class="controls">
        <p>
          <form:input type="text" name="registerWebsite" path="website"/>
        </p>
       	<p class="text-error"><form:errors path="website" /></p>
    </div>
</div>
<div class="control-group">
	<label class="control-label">Food Types:</label>
    <div class="controls">
		 <c:forEach var="foodtype" items="${foodTypesList}">
		 	${foodtype.name}&nbsp;&nbsp;<form:input type="checkbox" class="mbot5" name="${foodtype.name}" value="${foodtype.id}" path="foodTypes[${foodtype.id}]"/><br/>
		 </c:forEach>
		 <p class="text-error"><form:errors path="foodTypes" /></p>
	</div>
</div>
 
 