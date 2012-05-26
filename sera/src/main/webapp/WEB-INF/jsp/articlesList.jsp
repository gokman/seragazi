<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="common/header.jsp">
	<jsp:param name="pageTitle" value="Login" />
</jsp:include>
<html>
<head>
<title>All Articles</title>
</head>
<body>
<h1>List Articles</h1>
<a href="articles/add.htm"><fmt:message key="addArticle" /></a>

<c:if test="${!empty articles}">
	<table>
		<tr>
			<th>Article ID</th>
			<th>Article Name</th>
			<th>Article Desc</th>
			<th>Added Date</th>
		</tr>

		<c:forEach items="${articles}" var="article">
			<tr>
				<td><c:out value="${article.articleId}"/></td>
				<td><c:out value="${article.articleName}"/></td>
				<td><c:out value="${article.articleDesc}"/></td>
				<td><c:out value="${article.addedDate}"/></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>