<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <img class="img-responsive" alt="A cat and a dog" src="${petsImage}"/>
        </div>
    </div>
    <h2>Reminders</h2>
    <a class="btn btn-default" href='<spring:url value="/vets.html" htmlEscape="true"/>'>Add Reminder</a>
</petclinic:layout>
