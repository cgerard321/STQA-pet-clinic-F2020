<%@ page import="java.time.DayOfWeek" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<petclinic:layout pageName="ratings">


    <div class="row">
    <h2 id="ratings">Ratings</h2>
    <table id="ratingsTable" class="table table-striped" aria-describedby="ratings">
    <thead>
    <tr>
    <th scope="col">Rating Id</th>
    <th scope="col">Username</th>
    <th scope="col">Pet Name</th>
    <th scope="col">Rating</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ratings.ratingList}" var="rating">
        <tr>
            <td>
                <c:out value="${rating.id}"/>
            </td>
            <td>
                <c:out value="${rating.username}"/>
            </td>
            <td>
                <c:out value="${rating.pet.name}"/>
            </td>
            <td>
                <c:out value="${rating.rating}"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
    </div>
</petclinic:layout>
