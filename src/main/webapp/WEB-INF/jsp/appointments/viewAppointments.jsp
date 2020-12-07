<%@ page import="org.springframework.samples.petclinic.repository.VisitRepository" %>
<%@ page import="javax.persistence.Query" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.persistence.PersistenceContext" %>
<%@ page import="org.springframework.samples.petclinic.repository.jpa.JpaVisitRepositoryImpl" %>
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
                    <td><input type="submit" id=${v.id} value="Cancel" onclick="return confirm('Are you sure you want to remove this appointment for ${v.pet.name}?')"></td>
                </tr>
            </form>
        </c:forEach>
    </table><br>
    <spring:url value="/welcome" var="returnUrl"/>
    <a href="${fn:escapeXml(returnUrl)}">Return to Navigation</a>
    </body>

</petclinic:layout>
