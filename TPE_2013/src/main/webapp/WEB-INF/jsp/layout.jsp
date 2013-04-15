<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">

    <link href="${ basePath }/assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${ basePath }/assets/css/style.css" rel="stylesheet" type="text/css"/>

	<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
	<script type="text/javascript" src="${ basePath }/assets/js/maps.js"></script>
    <script type="text/javascript" src="${ basePath }/assets/js/jquery.js"></script>
    <script type="text/javascript" src="${ basePath }/assets/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="${ basePath }/assets/js/maps.js"></script>
    <script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>

    <title>${documentTitle}</title>
</head>
<body>

    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <form class="navbar-form" action="search" method="get">
                <input type="text" class="span5" placeholder="Search Restaurant" name="query"/>
                <input class="btn btn-primary" type="submit" value="Search"/>
            </form>
            <ul id="user-menu" class="nav pull-right">
                <c:choose>
                    <c:when test="${not empty user}">
                        <li><a href="${ basePath }/modify_user">
                            ${fn:escapeXml(user.username)}
                            <img class="avatar" src="" />
                        </a></li>
                    </c:when>

                    <c:otherwise>
                        <li><a href="${ basePath }/login">Login or Register</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>

    <div class="container">
        <div class="header row">
            <div class="logo">
                <a href="index"><img src="${ basePath }/assets/img/logo.PNG" /></a>
                <h1>Guia Oleo Facha</h1>
            </div>
            <div class="user span3 offset3">
                <div class="mybutton">
                    <a class="btn-large btn-success" href="register">Register</a>
                </div>
                <div class="mybutton">
                    <a class="btn-large btn-primary" href="login">Login</a>
                </div>
                <div class="mybutton">
                    <a class="btn-large btn-primary" href="list?query=all">See all restaurants</a>
                </div>
            </div>

            <div class="search span3 offset6 input-append">
                <form action="search" method="get">
                    <input type="text" placeholder="Search Restaurant" name="query"/>
                    <input class="btn-large btn-primary" type="submit" value="Send"/>
                </form>
            </div>
        </div>

        <div>
            <c:import url="${documentBodyFile}" />
        </div>
    </div>
</body>
</html>
