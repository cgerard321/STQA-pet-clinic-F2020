<%@ page import="java.time.DayOfWeek" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>


<petclinic:layout pageName="vets">

    <div>
        <h1>${vet.firstName}'s Weekly Schedule</h1>

<form:form method="POST" th:object="${vet}" modelAttribute="vet">

    <form:checkboxes element="span class='checkbox'" path="schedules" items="${selectableDays}" itemValue="id" />

    <button type="submit">Save Changes</button>
</form:form>

    </div>

    <div class="col-md-2">
        <a href="<spring:url value="/scheduleList" htmlEscape="true" />">Return</a>
    </div>


</petclinic:layout>
