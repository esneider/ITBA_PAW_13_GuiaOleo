<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">

    <link href="${ pageContext.request.contextPath }/assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${ pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet" type="text/css"/>


    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/jquery.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/assets/js/maps.js"></script>

    <title>${documentTitle}</title>
</head>
<body>

    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <form class="navbar-form" action="search" method="get">
                <div class="input-append">
                    <input type="text" class="span5" placeholder="Search Restaurant" name="query"/>
                    <input class="btn btn-primary" type="submit" value="Search"/>
                </div>
            </form>
            <ul id="user-menu" class="nav pull-right">
                <c:choose>
                    <c:when test="${not empty user}">
                        <li class="dropdown">
                            <a href="#" id="user-dd" role="button" class="dropdown-toggle menu-text" data-toggle="dropdown">
                                ${fn:escapeXml(user.username)}
                                <img class="avatar" src="${ pageContext.request.contextPath }/bin/image/showUserImage"/>
                            </a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="user-dd">
                                <li role="presentation">
                                    <a role="menuitem" href="${ pageContext.request.contextPath }/bin/user/edit">Modify data</a>
                                </li>
                                <li role="presentation">
                                    <a role="menuitem" href="${ pageContext.request.contextPath }/bin/user/logout">Logout</a>
                                </li>
                            </ul>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li class="login-text">
                            <a class="menu-text" href="${ pageContext.request.contextPath }/bin/user/login">Login or Register</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>

    <div class="container">
        <div id="main-logo">
            <div>
                <a href="${ pageContext.request.contextPath }/bin/index/list"><img src="${ pageContext.request.contextPath }/assets/img/logo.PNG" /></a>
                <h1>Oleo's guide</h1>
            </div>
        </div>

        <hr />

        <c:if test="${registerAction}">
            <div class="actionSuccess alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                You have been successfully registered. Congratulations!
            </div>
        </c:if>
        <c:if test="${modifyAction}">
            <div class="actionSuccess alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                Your data has been successfully updated.
            </div>
        </c:if>
        <c:if test="${loginAction}">
            <div class="actionSuccess alert alert-success">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                Welcome ${user.name}!
            </div>
        </c:if>

        <div id="main">
            <c:choose>
                <c:when test="${sidebar}">
                    <div class="tabbable tabs-left">
                        <ul class="nav nav-tabs">

                            <li class="${tab_all}"><a href="{ pageContext.request.contextPath }/bin/index/list?query=all">
                                <strong class="name text-warning">All restaurants</strong>
                                <br>
                                <c:if test="${numberOfRestaurants > 1}"><c:set var="s" value="s" /></c:if>
                                <span class="number muted">${numberOfRestaurants} restaurant${s}</span>
                            </a></li>

                            <c:forEach var="foodtype" items="${foodTypesList}">
                                <c:if test="${foodtype.ammount > 0}">
                                    <c:set var="mclass" value="" />
                                    <c:if test="${tab_active == foodtype.id}">
                                        <c:set var="mclass" value="active" />
                                    </c:if>
                                    <li class="${mclass}">
                                        <a href="${ pageContext.request.contextPath }/bin/index/list?query=foodtypes&id=${foodtype.id}">
                                            <strong class="name">${fn:escapeXml(foodtype.name)}</strong>
                                            <br>
                                            <c:if test="${foodtype.ammount > 1}"><c:set var="s" value="s" /></c:if>
                                            <span class="number muted">${foodtype.ammount} restaurant${s}</span>
                                        </a>
                                    </li>
                                </c:if>
                            </c:forEach>
                    	</ul>
                    	<div class="tab-content">
                            <div class="tab-pane active">
                 </c:when>
        			</c:choose>
