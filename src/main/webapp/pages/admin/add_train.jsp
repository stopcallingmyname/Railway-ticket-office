<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add Train</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <%--    <link  rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>--%>
  <script src="https://kit.fontawesome.com/c291e34b67.js" crossorigin="anonymous"></script>
</head>
<body onload="changeHashOnLoad();">
  <jsp:include page="../parts/navbar.jsp"/>

  <div class="reg-container">
    <div class="reg-form">
      <div class="reg-title">
        <h1>[ Add Train ]</h1>

        <form action="${pageContext.request.contextPath}/controller" method="post">
          <input type="hidden" name="command" value="add_train">

          <label for="floatingRoute"><input type="text" placeholder="Route" id="floatingRoute" name="train_route_id" <c:if test="${not empty train_route_id}">value="${train_route_id}"</c:if> required minlength="1"></label>

          <label for="floatingDate"><input type="date" placeholder="Date" id="floatingDate" name="train_date" <c:if test="${not empty train_date}">value="${train_date}"</c:if> required minlength="10" maxlength="10"></label>

          <label for="floatingDepartureTime"><input type="text" placeholder="Departure Time" id="floatingDepartureTime" name="train_departure_time"></label>

          <label for="floatingDestinationTime"><input type="text" placeholder="Destination Time" id="floatingDestinationTime" name="train_destination_time"></label>

          <label for="floatingSuiteReserved"><input type="text" placeholder="Suite Reserved" id="floatingSuiteReserved" name="train_suit_reserved"></label>

          <label for="floatingCoupeReserved"><input type="text" placeholder="Coupe Reserved" id="floatingCoupeReserved" name="train_coupe_reserved"></label>

          <label for="floatingBerthReserved"><input type="text" placeholder="Berth Reserved" id="floatingBerthReserved" name="train_berth_reserved"></label>

          <button class=btn-create type="submit"><i class="fa-solid fa-plus"></i>Add Train</button>

        </form>

      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script type="text/javascript">
    var storedHash = window.location.hash;
    function changeHashOnLoad() {
      window.location.hash = "1";
    }
    window.onhashchange = function () {
      window.location.hash = storedHash;
    }
  </script>
</body>
</html>
