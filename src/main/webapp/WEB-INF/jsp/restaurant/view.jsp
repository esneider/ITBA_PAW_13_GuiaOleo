<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.jsp" />
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
	<div class="restdetail">
		<c:choose>
			<c:when test="${not empty restaurant}">
		
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
			
			        <dt>Average Score</dt>
			            <dd>
			                <span class="badge">${restaurant.avgScore}</span>
			                (Scored by ${restaurant.ratingsAmmount} people)
			            </dd>
			            
			            <c:if test="${not empty restaurant.registerUser}">
			        		<dt>Published by </dt>
			        			<dd>	
			        				<a href="${ pageContext.request.contextPath }/bin/user/profile?userId=${restaurant.registerUser.id}">${restaurant.registerUser.name} ${restaurant.registerUser.surname}</a>
			        			</dd>
			            </c:if>
			
			    </dl>
			
			    <br />
			
			    <div id="map-canvas" class="map-canvas" data-address='${restaurant.address}' data-description='${restaurant.name}'></div>
			
			    <br />
			    
			    <c:if test="${restaurant.state == 'Pending' and user.type == 'Admin'}">
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
	     	    </c:if>
			
				<c:if test="${restaurant.state == 'Accepted'}">
			
				    <p class="lead">List of user comments</p>
				
				    <c:forEach var="comment" items="${restaurant.ratings}">
				        <p>
				            <div class="span1">
				                <img class="avatarPic" src="${ pageContext.request.contextPath }/bin/image/show?userId=${comment.user.avatar.id }"/>
				            </div>
				            <div class="offset1 comment">
				                <blockquote>
				                    <p>
				                        <strong>Score <span class="badge">${comment.score}</span></strong>:
				                        ${comment.comment}
				                    </p>
				                    <small>By <em><a href="${ pageContext.request.contextPath }/bin/user/profile?userId=${comment.user.id}">${comment.user.name}</a></em> on ${comment.date}</small>
				                	<c:if test="${user.type == 'Admin'}">
				                		<form action="deleteComment" method="post">
				      						<input type="hidden" name="ratingId" value="${comment.id}"/>
				      						<input type="hidden" name="restId" value="${restaurant.id}"/>
				      						<input type="submit" class="btn-mini btn-danger " value="Delete"/>
				                		</form>
				                	</c:if>
				                </blockquote>
				            </div>
				        </p>
				    </c:forEach>
				
				    <br />
				
				    <c:choose>
				        <c:when test="${not empty user and empty userComment}">
				            <p class="lead">Rate this restaurant:</p>
				                <form action='view' method='post' class="form-inline">
				                    <input type='hidden' name='id' value='${restaurant.id}'/>
				                    <input type='hidden' name='uid' value='${userId}'/>
				
				                    <label> <strong>Rating: &nbsp</strong>
				                    <label class="radio">0<input type="radio" name="restaurant_rating" value="0"/></label>
				                    <label class="radio">1<input type="radio" name="restaurant_rating" value="1"/></label>
				                    <label class="radio">2<input type="radio" name="restaurant_rating" value="2"/></label>
				                    <label class="radio">3<input type="radio" name="restaurant_rating" value="3" checked="checked"/></label>
				                    <label class="radio">4<input type="radio" name="restaurant_rating" value="4"/></label>
				                    <label class="radio">5<input type="radio" name="restaurant_rating" value="5"/></label>
				                    </label>
				
				                    <br />
				                    <br />
				
				                    <label class="">
				                        <textarea id="commentText" name="comment" class="span5" placeholder="Comment"></textarea>
				                        <br />
				                        <br />
				                        <input class="btn btn-primary" type='submit' value='Send'/>
				                    </label>
				                </form>
				            </c:when>
				            <c:when test="${empty userComment}">
				                <p class="lead"><a href="${ pageContext.request.contextPath }/bin/user/login">Log in to comment this restaurant</a></p>
				            </c:when>
				            <c:otherwise>
				                <p class="lead">Restaurant already commented</p>
				            </c:otherwise>
				    </c:choose>
				</c:if>
		    </c:when>
			<c:otherwise>
				Sorry, this restaurant doesn't exist
			</c:otherwise>
		</c:choose>
	<div>
<c:import url="../footer.jsp" />