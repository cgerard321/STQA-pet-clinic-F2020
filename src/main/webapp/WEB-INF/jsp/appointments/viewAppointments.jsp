<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
        <th>Action</th>
    </tr>
    </thead>

    <c:forEach items="${visits}" var="v">
        <spring:url value="/appointments/${v.id}/cancel" var="cancelUrl" />
        <form action="${fn:escapeXml(cancelUrl)}" method="post">
        <tr>
            <td>${v.date}</td>
            <td>${v.pet.id}</td>
            <td>${v.pet.name}</td>
            <td>${v.description}</td>
            <td><input type="submit" id=${v.id} value="Cancel"></td>
        </tr>
        </form>
    </c:forEach>
</table>

    <c:if test="${filter.equals('upcoming')}">
        <table class="table table-striped table-bordered table-hover" id="filteredTable">
            <thead class="thead-dark">
            <tr>
                <th>Date</th>
                <th>Pet ID</th>
                <th>Pet Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            </thead>
        <c:forEach items="${filter}" var="u">
            <td>${u.date}</td>
            <td>${u.pet.id}</td>
            <td>${u.pet.name}</td>
            <td>${u.description}</td>
            <spring:url value="/appointments/${u.id}/cancel" var="cancelUrl" />
        </c:forEach>
        </table>
    </c:if>
    <spring:url value="/appointments/viewForm?filter=" var="filterUrl"/>
    <div class="container">
        <div class="text-left row">
            <form action="${fn:escapeXml(filterUrl)}" method="get" class="col-md-3">
                <input type="hidden" value="upcoming" id="upcomingFilter" name="filter"/>
                <input type="submit" class="btn btn-success"  value="View Upcoming Appointments" />
            </form>
            <form action="${fn:escapeXml(filterUrl)}" method="get" class="col-md-3">
                <input type="hidden" value="duplicate" id="duplicateFilter" name="filter"/>
                <input type="submit" class="btn btn-success"  value="View Duplicate Appointments" />
            </form>
            <form action="${fn:escapeXml(filterUrl)}" method="get" class="col-md-3">
                <input type="hidden" value="petappts" id="petappts" name="filter"/>
                <input type="submit" class="btn btn-success"  value="Sort Pets By Appointments" />
            </form>
            <form action="${fn:escapeXml(filterUrl)}" method="get" class="col-md-3">
                <input type="hidden" value="seeAll" id="nofilter" name="filter"/>
                <input type="submit" class="btn btn-success"  value="View all Appointments" />
            </form>
        </div>
    </div>
</body>

</petclinic:layout>
