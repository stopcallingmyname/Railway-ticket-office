<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <%--    <link  rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>--%>
    <script src="https://kit.fontawesome.com/c291e34b67.js" crossorigin="anonymous"></script>
</head>

<body onload="changeHashOnLoad();">
<%--<jsp:include page="parts/navbar.jsp"/>--%>

<div class="reg-container">
    <div class="reg-form">
        <div class="reg-title">
            <h1>[ Profile ]</h1>
            <h2>Email: ${user.email}</h2>
            <h2>Name: ${user.name}</h2>
            <h2>Surname: ${user.surname}</h2>
            <h2>Phone: ${user.phone}</h2>
            <c:if test="${role == 'ADMIN'}">
                <h2>Role: ${user.role}</h2>
            </c:if>
            <form action="${pageContext.request.contextPath}/controller" method="get">
                <input type="hidden" name="command" value="move_to_main_page">
                <button type="submit" class="sing-in-btn"><i class="fa-solid fa-house"></i></button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript">
    let storedHash = window.location.hash;
    function changeHashOnLoad() {
        window.location.hash = "1";
    }
    window.onhashchange = function () {
        window.location.hash = storedHash;
    }
</script>

</body>
</html>

