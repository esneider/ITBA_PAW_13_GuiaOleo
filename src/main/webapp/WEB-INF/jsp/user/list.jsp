<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../header.jsp" />

<div class="userlist">
     <c:choose>
        <c:when test="${fn:length(userList) gt 0}">
            <c:forEach var="user" items="${userList}">

                <dl class="dl-horizontal mainList">

                    <dt>User</dt>
                        <dd><strong><a href="../user/profile?userId=${user.id}">${user.name}</a></strong></dd>
                    <dt>Type</dt>
                        <dd>${user.type}</dd>
                    <dt>Email</dt>
                        <dd>${user.email}</dd>
                    <dt>Registration Date</dt>
                        <dd>${user.registerDate}</dd>

                </dl>
                <hr />
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p id="no_results" class="lead text-center">There are no users registered </p>
        </c:otherwise>
    </c:choose>
</div>

<c:import url="../footer.jsp" />

