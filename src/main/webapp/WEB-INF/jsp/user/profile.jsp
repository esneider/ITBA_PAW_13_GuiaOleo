<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../header.jsp" />
<div class="detail">
	<h2 class="title">${profileUser.username}</h2>
	<img class="profilePic" src="${ pageContext.request.contextPath }/bin/image/show?userId=${profileUser.id}"/>
	<dl class="dl-horizontal fRight">							
			        <dt>Name</dt>
			            <dd>${profileUser.name}</dd>
			        <dt>Surname</dt>
			            <dd>${profileUser.surname}</dd>
			        <dt>Mail</dt>
			            <dd> ${profileUser.email}</dd>		
	</dl>
</div>
<c:import url="../footer.jsp" />