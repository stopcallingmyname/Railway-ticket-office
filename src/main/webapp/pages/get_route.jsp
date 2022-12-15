<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>${route.id}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script src="https://kit.fontawesome.com/c291e34b67.js" crossorigin="anonymous"></script>
</head>
<body onload="changeHashOnLoad();">
<jsp:include page="parts/navbar.jsp"/>

<div class="route-container">
    <div class="route-form">
        <div class="reg-container">
            <h1>${route.id}</h1>
        </div>

        <h2>Departure Station: ${route.departureStation}</h2>
        <h2>Destination Station: ${route.destinationStation}</h2>

        <table>
            <thead>
            <tr>
                <th scope="col">Train</th>
                <th scope="col">Departure Time</th>
                <th scope="col">Destination Time</th>
                <th scope="col">Suit Reserved</th>
                <th scope="col">Coupe Reserved</th>
                <th scope="col">Berth Reserved</th>
                <th scope="col">Date</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="train" items="${trains_list}">
                    <tr>
                        <td>[${train.id}] ${train.routeID}</td>
                        <td>${train.departureTime}</td>
                        <td>${train.destinationTime}</td>
                        <td>${train.suitReserved}</td>
                        <td>${train.coupeReserved}</td>
                        <td>${train.berthReserved}</td>
                        <td>${train.date}</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="move_to_main_page">
            <button type="submit" class="btn-create"><i class="fa-solid fa-arrow-left"></i> Back To Main Page</button>
        </form>
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
