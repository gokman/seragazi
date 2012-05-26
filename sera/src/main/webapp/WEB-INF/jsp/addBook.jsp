<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="include.jsp"%>
<html>
<head><title>Add Book</title>
</head>
<body>
	<div align="center">
		<h1 onclick="popUp()">Add Book</h1>	
		<c:url var="viewBooksUrl" value="/books.html" />
		<a href="${viewBooksUrl}">Show All Books</a>
	</div>



<br />
<c:url var="saveBookUrl" value="/books/save.html" />
<form:form modelAttribute="book" method="POST" action="${saveBookUrl}" enctype="multipart/form-data">
	<table align="center">
		<tr>
			<td>		
				<form:label path="title">Book Title:</form:label>
			</td>
			<td>
				<form:input path="title" />
			</td>
		</tr>
		<tr>	
			<td>			
				<form:label path="ISBN">ISBN :</form:label>
			</td>
			<td>	
				<form:input path="ISBN" />
			</td>
		</tr>
		<tr>
			<td>
				<form:label path="writersID">Writers :</form:label>
			</td>
			<td>	
				<form:input path="writersID" />
			</td>
		</tr>		
		<tr>
			<td>	
				<form:label path="publisher">Publisher :</form:label>
			</td>
			<td>	
				<form:input path="publisher"/>
			</td>
		</tr>
		<tr>		
			<td>
				<form:label path="publishDate">Publish Date:</form:label>
			</td>
			<td>
				<form:input path="publishDate" />
			</td>
		</tr>		
		<tr>
			<td>
				<form:label path="publishPlace">Publish Place:</form:label>
			</td>
			<td>
				<form:input path="publishPlace" />
			</td>
				
		</tr>
		<tr>
			<td>File To Be Uploaded
			</td>
			<td>
				<form:input path="coverImage" type="file"/>
			</td>
		</tr>			
		<tr align="center" >
			<td align="center" colspan="2">
				<input align="middle" type="submit" value="Save Article" />
			</td>
		</tr>	
	</table>	
		
</form:form>

</body>
</html>