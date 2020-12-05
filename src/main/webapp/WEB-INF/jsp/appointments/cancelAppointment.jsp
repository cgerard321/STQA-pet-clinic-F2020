<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="appointments">
    <h2 id="appointment">Cancel an appointment</h2>

    <c:if test="${showWarning}">
        <div class="alert alert-warning">
            <strong>Warning:</strong> Fees may occur if you cancel an appointment scheduled in less than 72 hours.
        </div>
    </c:if>
    <form:form modelAttribute="visits">
        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-dark">
            <tr>
                <th></th>
                <th>Visit Description</th>
                <th>Pet</th>
                <th>Date of visit</th>
            </tr>
            </thead>
            <c:forEach items="${visits}" var="v">
                <tr>
                    <td><input type="checkbox" name="${v.id}" /></td>
                    <td><c:out value="${v.description}" /></td>
                    <td><c:out value="${v.pet.name}" /></td>
                    <td><c:out value="${v.date}" /></td>
                </tr>
            </c:forEach>
        </table>

        <button class="btn btn-default" type="submit" onclick="return confirm('Are you sure you want to remove this appointment for ${v.pet.name}?')">Cancel appointment</button>
        <button class="btn btn-default" onclick="event.preventDefault(); history.back();">Go back</button>
    </form:form>
</petclinic:layout>
