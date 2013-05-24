<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.jsp" />
<div class="detail">
	<h2 class="title">${profileUser.username}</h2>
	<img class="profilePic" style = "width : 100px; height: 100px" src="${ pageContext.request.contextPath }/bin/image/show?userId=${profileUser.id}"/>
	<dl class="dl-horizontal fRight">							
			        <dt>Name</dt>
			            <dd>${profileUser.name}</dd>
			        <dt>Registration Date</dt>
			            <dd>${profileUser.registerDate}</dd>
			        <dt>Surname</dt>
			            <dd>${profileUser.surname}</dd>
			        <dt>Mail</dt>
			            <dd>${profileUser.email}</dd>
			       
			            
			            <br><br><br><br>
				
			            <c:if test="${notMe and profileUser.type != 'Admin'}">
			            <div class="control-group controls"> 	
							<form action="administrate" method="POST">
								<input type="hidden" name="action" value="setadmin">
								<input type="hidden" name="id" value="${profileUser.id}">
								<input type="submit" class="btn btn-info" value="Set as administrator" > 
							</form>
						   
	     	    		</div>
	     	    	
			            </c:if>
			            <c:if test="${notMe and profileUser.type == 'Admin'}">
			            <div class="control-group controls"> 	
							<form action="administrate" method="POST">
								<input type="hidden" name="action" value="unsetadmin">
								<input type="hidden" name="id" value="${profileUser}">
								<input type="submit" class="btn btn-info" value="Unset as administrator" > 
							</form>
						   
	     	    		</div>
			            </c:if>
			            <br><br><br><br>
			            <p class="lead">List of user comments</p>
			
			    <c:forEach var="comment" items="${profileUser.comments}">
			        
			         
			            <div class="offset1 comment">
			                <blockquote>
			                    <p>
			                        <strong>Score <span class="badge">${comment.score}</span></strong>:
			                        ${comment.comment}
			                    </p>
			                    <small>By <em>${comment.user.name}</em> on ${comment.date} to ${comment.restaurant.name}</small>
			                </blockquote>
			            </div>
			       
			    </c:forEach>	
	</dl>
	
	
	
</div>
<c:import url="../footer.jsp" />