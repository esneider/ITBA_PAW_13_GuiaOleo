<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
        <link href="${ basePath }/assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
		<link href="${ basePath }/assets/css/style.css" rel="stylesheet" type="text/css"/>

		<script type="text/javascript" src="${ basePath }/assets/js/jquery.js"></script>
		<script type="text/javascript" src="${ basePath }/assets/js/bootstrap-modal.js"></script>
        <title>${documentTitle}</title>
    </head>
    <body>
        
				<div class= 'header'>
				   <div class = "logo">
					<a href='index'><img src="${ basePath }/assets/img/logo.PNG" /img></a>
					<h1> Guia  Oleo  Facha</h1>
				   </div>
				   
				
				<div class= "user">
						<div class = "mybutton">
						<a class=' btn-large btn-success' href='register'>Register</a>
						</div>
						<div class = "mybutton">
						<a class='btn-large btn-primary' href='login'>Login</a>
						</div>
						<div class = "mybutton">
						<a class='btn-large btn-primary' href='list?query=all'>See all restaurants</a>
						</div>
				</div>
					
					
					
				   <div class= 'container' >
					<div class="input-append">
						<form action='search' method='get'>
							<input type='text' placeholder="Search Restaurant" name='query'/>
							<input class= 'btn-large btn-primary'type='submit' value='Send'/>
						</form>
					</div>
				
				   </div>

				<hr/>
			</div>
		
        <div>
			<c:import url="${documentBodyFile}" />
        </div>
    </body>
</html>
