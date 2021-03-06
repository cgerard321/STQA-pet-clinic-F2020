<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${owner['new']}">New </c:if> Owner
    </h2>
    <form:form modelAttribute="owner" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <inputField label="Profile Picture" name="profile_picture" value="images_default"/>
            <div class="form-group ">
                <label class="col-sm-2 control-label">Profile Picture</label>
                <div class="col-sm-10">
                    <input type="text" id="profile_picture" name="profile_picture" value="images_default" class="form-control" required>
                </div>
            </div>
            <petclinic:inputField label="First Name" name="firstName"/>
            <petclinic:inputField label="Last Name" name="lastName"/>
            <petclinic:inputField label="Address" name="address"/>
            <petclinic:inputField label="City" name="city"/>
            <petclinic:inputField label="State Code" name="state"/>
            <petclinic:inputField label="Telephone" name="telephone"/>
            <petclinic:inputField label="Email" name="email"/>
            <petclinic:inputField label="Comment" name="comment"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${owner['new']}">
                        <button class="btn btn-default" type="submit">Add Owner</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Owner</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>

    <form:form action="http://localhost:8080/spring_framework_petclinic_war/owners/addMultipleOwnersFake" enctype="multipart/form-data">
        <input type="file" name="upload_file"/>
        <button class="btn btn-default" type="submit">Send file</button>
    </form:form>
</petclinic:layout>
