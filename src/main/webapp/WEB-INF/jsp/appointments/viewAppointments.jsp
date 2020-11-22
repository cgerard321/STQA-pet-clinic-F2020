<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<petclinic:layout pageName="viewForm">
<head>
    <title>View Appointments</title>
</head>
<body>

<h2>View Appointments</h2>

<table class="table table-striped table-bordered table-hover" id="appointmentsTable">
    <thead class="thead-dark">
    <tr>
        <th>Date</th>
        <th>Pet ID</th>
        <th>Pet Name</th>
        <th>Description</th>
    </tr>
    </thead>
    <c:forEach items="${visits}" var="v">
        <tr>
            <td>${v.date}</td>
            <td>${v.pet.id}</td>
            <td>${v.pet.name}</td>
            <td>${v.description}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</petclinic:layout>
