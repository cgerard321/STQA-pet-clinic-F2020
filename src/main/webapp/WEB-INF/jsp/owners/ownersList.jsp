<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<petclinic:layout pageName="owners">
    <h2 id="owners">Owners</h2>

    <form:form action="${pageContext.request.contextPath}/sort" method="POST" id="search-owner-form">
        <label for="sortingField">Choose a field to sort by</label>
        <select name="sortingField" id="sortingField">
            <option value="FirstName">First Name</option>
            <option value="LastName">Last Name</option>
            <option value="City">City Name</option>
            <option value="State">State Name</option>
        </select>
        <button type="submit" class="btn btn-default" id="findAllOwners">Sort</button>
    </form:form>


    <table id="ownersTable" class="table table-striped" aria-describedby="owners">
        <thead>
        <tr>
            <th scope="col" style="width: 150px;">Profile Picture</th>
            <th scope="col" style="width: 150px;">Name</th>
            <th scope="col" style="width: 200px;">Address</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col" style="width: 120px">Telephone</th>
            <th scope="col" style="width: 120px">Email</th>
            <th scope="col" style="width: 120px">Comment</th>
            <th scope="col">Pets</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="owner">
            <tr class="ownerObject">
                <spring:url value="/resources/images/ownersProfilePictures/${owner.profile_picture}.png" var="profilePictureUrl" />
                <td headers="profile_picture"><img src="${fn:escapeXml(profilePictureUrl)}" alt="owner image"></td>
                <td>
                    <spring:url value="/owners/{ownerId}.html" var="ownerUrl">
                        <spring:param name="ownerId" value="${owner.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${owner.firstName} ${owner.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${owner.address}"/>
                </td>
                <td>
                    <c:out value="${owner.city}"/>
                </td>
                <td>
                    <c:out value="${owner.state}"/>
                </td>
                <td>
                    <c:out value="${owner.telephone}"/>
                </td>
                <td>
                    <c:out value="${owner.email}"/>
                </td>
                <td>
                    <c:out value="${owner.comment}"/>
                </td>
                <td>
                    <c:forEach var="pet" items="${owner.pets}">
                        <c:out value="${pet.name} "/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
