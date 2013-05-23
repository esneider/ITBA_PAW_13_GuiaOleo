<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.jsp" />
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
	<div class="restdetail">
			    <h2 class="restaurantTitle">${restaurant.name}</h2>
			
			    <dl class="dl-horizontal">
			
			        <dt>Address</dt>
			            <dd>${restaurant.address}</dd>
			        <dt>Area</dt>
			            <dd>${restaurant.area}</dd>
			        <dt>Telephone</dt>
			            <dd>${restaurant.telephone}</dd>
			
			        <br>
			
			        <dt>Average Price</dt>
			            <dd>$${restaurant.avgprice}</dd>
			        <dt>Time range</dt>
			            <dd>${restaurant.timerange}</dd>
			        <dt>Website</dt>
			            <dd><a href="//${restaurant.website}">${restaurant.website}</a></dd>
			
			        <br>
			
			    			
			    </dl>
			   
				<div class="control-group controls"> 	
					<form action="publish" method="POST">
						<input type="hidden" name="action" value="accept">
						<input type="hidden" name="id" value="${restaurant.id}">
						<input type="submit" class="btn btn-info" value="Accept" > 
					</form>
					<form action="publish" method="POST">
					    <input type="hidden" name="action" value="decline">
					    <input type="hidden" name="id" value="${restaurant.id}">
					    
		  				<input type="submit" class="btn btn-info" value="Decline" > 
					</form>    
	     	    </div>
	     	  
			    <br />
			
			    <div id="map-canvas" class="map-canvas" data-address='${restaurant.address}' data-description='${restaurant.name}'></div>
			
			    <br />
			
			  
				 
				
	
<c:import url="../footer.jsp" />