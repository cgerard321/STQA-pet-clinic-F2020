<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2 id="ownerInformation">Owner Information</h2>

    <table class="table table-striped" aria-describedby="ownerInformation">
        <tr>
            <th id="profile_picture">Profile Picture</th>
            <spring:url value="/resources/images/ownersProfilePictures/${owner.profile_picture}.png" var="profilePictureUrl" />
            <td headers="profile_picture"><img src="${fn:escapeXml(profilePictureUrl)}" alt="owner image"></td>
        </tr>
        <tr>
            <th id="name">Name</th>
            <td headers="name"><strong><c:out value="${owner.firstName} ${owner.lastName}"/></strong></td>
        </tr>
        <tr>
            <th id="address">Address</th>
            <td headers="address"><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th id="city">City</th>
            <td headers="city"><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th id="state">State</th>
            <td headers="state"><c:out value="${owner.state}"/></td>
        </tr>
        <tr>
            <th id="telephone">Telephone</th>
            <td headers="telephone"><c:out value="${owner.telephone}"/></td>
        </tr>
        <tr>
            <th id="email">Email</th>
            <td headers="email"><c:out value="${owner.email}"/></td>
        </tr>
        <tr>
            <th id="comment">Comment</th>
            <td headers="comment"><c:out value="${owner.comment}"/></td>
        </tr>
    </table>

    <spring:url value="{ownerId}/edit.html" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Owner</a>

    <spring:url value="{ownerId}/pets/new.html" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Pet</a>

    <c:if test="${hasFutureVisits}">
        <spring:url value="{ownerId}/appointments/cancel.html" var="cancelAppointmentUrl">
            <spring:param name="ownerId" value="${owner.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(cancelAppointmentUrl)}" class="btn btn-default">Cancel Appointment</a>
    </c:if>

    <br/>
    <br/>
    <br/>
    <h2 id="petsAndVisits">Pets and Visits</h2>

    <table class="table table-striped" aria-describedby="petsAndVisits">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <th scope="col">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><spring:url value="/owners/{ownerId}/pets/{petId}/view" var="petUrl">
                            <spring:param name="ownerId" value="${owner.id}"/>
                            <spring:param name="petId" value="${pet.id}"/>
                        </spring:url>
                            <a href="${fn:escapeXml(petUrl)}"><c:out value="${pet.name}"/></a></dd>
                        <dt>Birth Date</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </th>
                <td>
                    <table class="table-condensed" aria-describedby="petsAndVisits">
                        <thead>
                        <tr>
                            <th id="visitDate">Visit Date</th>
                            <th id="visitDescription">Description</th>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td headers="visitDate"><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td headers="visitDescription"><c:out value="${visit.description}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}">Edit Pet</a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Add Visit</a>
                            </td>
                            <td>
                                <!--Last minute Appointments link-->
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/lastMin" var="lastMinuteAppointment">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(lastMinuteAppointment)}">Last Minute Visit</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

</petclinic:layout>
