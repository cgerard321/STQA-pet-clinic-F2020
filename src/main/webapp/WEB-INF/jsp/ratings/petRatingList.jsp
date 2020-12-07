<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="ratings">

    <h1>Pet Ratings</h1>

    <c:if test="${petRatings.size() != 0}">
        <h2>Pet: <c:out value="${petRatings.get(0).pet.name}"/></h2>
        <h2>Owner: <c:out value="${petRatings.get(0).pet.owner.firstName} ${petRatings.get(0).pet.owner.lastName}"/></h2>

        <table id="ratingsTable" class="table table-striped table-bordered table-hover" aria-describedby="ratings">
            <thead>
            <tr>
                <th scope="col" style="width: 200px;">Rating Id</th>
                <th scope="col" style="width: 200px;">Username</th>
                <th scope="col" style="width: 150px;">Rating</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${petRatings}" var="rating">
                <tr>
                    <td>
                        <c:out value="${rating.id}"/>
                    </td>
                    <td>
                        <c:out value="${rating.username}"/>
                    </td>
                    <td>
                        <c:out value="${rating.rating}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${petRatings.size() == 0}">
        <h2>Sorry, but the pet with this name has no ratings.</h2>
    </c:if>

</petclinic:layout>

