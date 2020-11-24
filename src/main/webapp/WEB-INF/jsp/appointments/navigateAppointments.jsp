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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<petclinic:layout pageName="navigateAppointments">
<head>
    <title>Appointments Navigation</title>
</head>
<body>
<h1>Appointments Navigation</h1><br><br>
<h2>Management</h2><br>
<a href="appointments/create" id="createHREF">Create Appointments</a><br>
<a href="appointments/cancel" id="cancelHREF">Cancel Appointments</a><br><br>

<h2>Find Appointments</h2><br>
<a href="appointments/viewForm" id="viewHREF">View Appointments</a>
<br><br><br>
<a href="http://localhost:8080/spring_framework_petclinic_war/">Return to Homepage</a>

</body>
</petclinic:layout>
