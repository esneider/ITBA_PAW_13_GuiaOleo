<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../header.jsp" />

<div class="detail">
    <h2 class="title">${profileUser.username}</h2>
    <div class="profileHeader">
        <img class="profilePic" style = "width : 100px; height: 100px" src="${ pageContext.request.contextPath }/bin/image/show?userId=${profileUser.id}"/>
        <div class="profileFields">
            <dt class="inline">Name</dt>
                <dd class="inline">${profileUser.name}</dd><br>
            <dt class="inline">Registration Date</dt>
                <dd class="inline">${profileUser.registerDate}</dd><br>
            <dt class="inline">Surname</dt>
                <dd class="inline">${profileUser.surname}</dd><br>
            <dt class="inline">Mail</dt>
                <dd class="inline">${profileUser.email}</dd>
        </div>
    </div>
    <div>
        <dl class="dl-horizontal">
            <c:if test="${user.type == 'Admin'}">
                <div class="control-group controls">
                    <form action="administrate" method="POST">
                        <input type="hidden" name="id" value="${profileUser.id}">
                    <c:if test="${notMe and profileUser.type != 'Admin'}">
                        <input type="hidden" name="action" value="setadmin">
                        <input type="submit" class="btn btn-info" value="Set as administrator" >
                    </c:if>
                    <c:if test="${notMe and profileUser.type == 'Admin'}">
                        <input type="hidden" name="action" value="unsetadmin">
                        <input type="submit" class="btn btn-info" value="Unset as administrator" >
                    </c:if>
                    </form>
                </div>
            </c:if>

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
            <br><br>
            <p class="lead">List of restaurants registered by this user</p>
            <c:forEach var="restaurant" items="${profileUser.registeredRestaurants}">
                <c:if test="${restaurant.state == 'Accepted'}">
                    <dl class="dl-horizontal">
                        <dt>${restaurant.name}</dt>
                            <dd><a href="${ pageContext.request.contextPath }/bin/restaurant/view?id=${restaurant.id}">View</a></dd>
                    </dl>
                </c:if>
            </c:forEach>
        </dl>
    </div>

</div>

<c:import url="../footer.jsp" />

