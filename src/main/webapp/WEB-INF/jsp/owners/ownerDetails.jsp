<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="owners">

    <h2 id="ownerInformation">Owner Information</h2>

    <table class="table table-striped" aria-describedby="ownerInformation">
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

    <a href="#myModal" class="btn btn-default" data-toggle="modal" id="removeOwnerID" >Remove Owner</a>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .modal-confirm {
                color: #636363;
                width: 400px;
                margin: 30px auto;
            }
            .modal-confirm .modal-content {
                padding: 20px;
                border-radius: 5px;
                border: none;
                text-align: center;
                font-size: 14px;
            }
            .modal-confirm .modal-header {
                border-bottom: none;
                position: relative;
            }
            .modal-confirm h4 {
                text-align: center;
                font-size: 26px;
                margin: 30px 0 -10px;
            }
            .modal-confirm .close {
                position: absolute;
                top: -5px;
                right: -2px;
            }
            .modal-confirm .modal-body {
                color: #999;
            }
            .modal-confirm .modal-footer {
                border: none;
                text-align: center;
                border-radius: 5px;
                font-size: 13px;
                padding: 10px 15px 25px;
            }
            .modal-confirm .modal-footer a {
                text-align: center;
                vertical-align: middle;
            }
            .modal-confirm .icon-box {
                width: 80px;
                height: 80px;
                margin: 0 auto;
                border-radius: 50%;
                z-index: 9;
                text-align: center;
                border: 3px solid #f15e5e;
            }
            .modal-confirm .icon-box i {
                color: #f15e5e;
                font-size: 46px;
                display: inline-block;
                margin-top: 13px;
            }
            .modal-confirm .btn {
                border-radius: 4px;
                text-decoration: none;
                transition: all 0.4s;
                line-height: normal;
                min-width: 120px;
                border: none;
                min-height: 40px;
                border-radius: 3px;
                margin: 0 5px;
                outline: none !important;
            }
            .modal-confirm .btn-info {
                background: #c1c1c1;
            }
            .modal-confirm .btn-info:hover, .modal-confirm .btn-info:focus {
                background: #a8a8a8;
            }
            .modal-confirm .btn-danger {
                background: #f15e5e;
            }
            .modal-confirm .btn-danger:hover, .modal-confirm .btn-danger:focus {
                background: #ee3535;
            }
            #delete {
                padding-top: 12px;
            }
        </style>
    </head>
    <!-- Modal HTML -->
    <div id="myModal" class="modal fade">
        <div class="modal-dialog modal-confirm">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="icon-box">
                        <i class="material-icons">&#xE5CD;</i>
                    </div>
                    <h4 class="modal-title">Are you sure?</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p>Do you really want to delete ${owner.lastName}, ${owner.firstName}? This process cannot be undone.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

                    <spring:url value="{ownerId}/remove.html" var="removeOwner">
                        <spring:param name="ownerId" value="${owner.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(removeOwner)}" class="btn btn-default" id="delete">Delete</a>

                </div>
            </div>
        </div>
    </div>

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
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>

</petclinic:layout>
