
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="Login" />
</jsp:include>
<h1>Add Article</h1>

<c:url var="viewArticlesUrl" value="/articles.htm" />
<a href="${viewArticlesUrl}">Show All Articles</a>

<br />
<br />
<c:url var="saveArticleUrl" value="/articles/save.htm" />
<form:form modelAttribute="article" method="POST" action="${saveArticleUrl}">
	<c:out value="test"/>
	<form:label path="articleName">Article Name:</form:label>
	<form:input path="articleName" />
	<br />
	<form:label path="articleDesc">Article Desc:</form:label>
	<form:textarea path="articleDesc" />
	<br />
	<input type="submit" value="Save Article" />
</form:form>

