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
    <script type="text/javascript" src="${ basePath }/assets/js/jquery.js"></script>
    <script type="text/javascript" src="${ basePath }/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ basePath }/assets/js/maps.js"></script>

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
                        <li class="dropdown">
                            <a href="#" id="user-dd" role="button" class="dropdown-toggle menu-text" data-toggle="dropdown">
                                ${fn:escapeXml(user.username)}
                                <img class="avatar" src="" />
                            </a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="user-dd">
                                <li role="presentation">
                                    <a role="menuitem" href="#">Modify data</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" href="#">Logout</a>
                                </li>
                            </ul>

                            <!-- ${ basePath }/modify_user -->
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="login-text">
                            <a class="menu-text" href="${ basePath }/login">Login or Register</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>

    <div class="container">
        <div id="main-logo">
            <div>
                <a href="index"><img src="${ basePath }/assets/img/logo.PNG" /></a>
                <h1>Guia Oleo Facha</h1>
            </div>
        </div>

        <hr />

        <div id="main">
            <div id="sidebar">
            </div>
            <div>
                <c:import url="${documentBodyFile}" />
            </div>
        </div>
    </div>
</body>
</html>
