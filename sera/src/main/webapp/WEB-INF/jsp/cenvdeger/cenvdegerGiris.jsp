<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form  modelAttribute="cenvdeger" enctype="multipart/form-data">
<form:select path="tip1">
<form:option value="0" label="seç"/>
<form:options items="${tipListe}" itemValue="agacElemanlar" itemLabel="agacElemanlar"/>
</form:select>
</form:form>
</body>
</html>