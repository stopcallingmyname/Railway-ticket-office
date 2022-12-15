<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
  <title>Users List</title>
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
      <h1>[ Users ]</h1>

            <table>
                  <thead>
                  <tr>
                    <th scope="col">Login</th>
                    <th scope="col">Role</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                  </tr>
                  </thead>
              <tbody>
                  <c:forEach var="user" items="${users_list}">
                    <tr>
                      <td>${user.email}</td>
                      <td>${user.role}</td>
                      <td>
                          <c:choose>
                              <c:when test="${user.isActivated == true}">
                                  <i class="fa-solid fa-user-check"></i>
                              </c:when>
                              <c:when test="${user.isActivated == false}">
                                  <i class="fa-solid fa-user-minus"></i>
                              </c:when>
                          </c:choose>
                      </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="change_user_role">
                                <input type="hidden" name="email" value="${user.email}">
                                <button type="submit">
                                    <c:choose>
                                        <c:when test="${user.role == 'USER'}">
                                            set admin
                                        </c:when>
                                        <c:when test="${user.role == 'ADMIN'}">
                                            unset admin
                                        </c:when>
                                    </c:choose>
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="change_user_is_active">
                                <input type="hidden" name="email" value="${user.email}">
                                <button type="submit">
                                    <c:choose>
                                        <c:when test="${user.isActivated == true}">
                                            deactivate
                                        </c:when>
                                        <c:when test="${user.isActivated == false}">
                                            activate
                                        </c:when>
                                    </c:choose>
                                </button>
                            </form>
                        </td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
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

