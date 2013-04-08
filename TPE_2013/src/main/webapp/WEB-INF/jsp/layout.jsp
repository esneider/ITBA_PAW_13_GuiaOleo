<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <title>${documentTitle}</title>
    </head>
    <body>
        <div>
            <h1>Guia Oleo Facha</h1>
            <ul>
            	<li><a href='register'>Register</a></li>
            	<li><a href='login'>Login</a></li>
            	<li><a href='list'>See all restaurants</a></li>
            </ul>
        </div>

        <hr/>

        <div>
<c:import url="${documentBodyFile}" />
        </div>
    </body>
</html>
