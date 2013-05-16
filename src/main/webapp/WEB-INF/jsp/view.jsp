<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <dt>Food Type</dt>
            <dd>${restaurant.foodtype.name}</dd>
        <dt>Time range</dt>
            <dd>${restaurant.timerange}</dd>
        <dt>Website</dt>
            <dd><a href="//${restaurant.website}">${restaurant.website}</a></dd>

        <br>

        <dt>Average Score</dt>
            <dd>
                <span class="badge">${restaurant.avgScore}</span>
                (Scored by ${restaurant.ratings} people)
            </dd>

    </dl>

    <br />

    <div id="map-canvas" class="map-canvas" data-address='${restaurant.address}' data-description='${restaurant.name}'></div>

    <br />

    <p class="lead">List of user comments</p>

    <c:forEach var="comment" items="${commentList}">
        <p>
            <div class="span1">
                <img class="avatarPic" src="${ basePath }/getImage?imageId=${ comment.user.avatar.id }"/>
            </div>
            <div class="offset1 comment">
                <blockquote>
                    <p>
                        <strong>Score <span class="badge">${comment.score}</span></strong>:
                        ${comment.comment}
                    </p>
                    <small>By <em>${comment.user.name}</em> on ${comment.date}</small>
                </blockquote>
            </div>
        </p>
    </c:forEach>

    <br />

    <c:choose>
        <c:when test="${not empty userId and empty userComment}">
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
                <p class="lead"><a href="login">Log in to comment this restaurant</a></p>
            </c:when>
            <c:otherwise>
                <p class="lead">Restaurant already commented</p>
            </c:otherwise>
    </c:choose>
<div>
