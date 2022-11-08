<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <script src="https://kit.fontawesome.com/c291e34b67.js" crossorigin="anonymous"></script>
    </head>

    <body onload="changeHashOnLoad();">

    <div class="reg-container">
        <div class="reg-form">
            <div class="reg-title">
                <h1>Sign Up</h1>
                <div class="goto-login">
                    <p class="sing-in-label">Already have an account?</p>
                    <form action="${pageContext.request.contextPath}/controller" method="get">
                        <input type="hidden" name="command" value="move_to_login_page">
                        <button type="submit" class="btn-a-type">Log in</button>
                    </form>
                </div>
            </div>

            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="sign_up">

                <label for="floatingEmail"><input type="email" placeholder="Email" id="floatingEmail" name="email" <c:if test="${not empty email}">value="${email}"</c:if> minlength="1" required maxlength="30" pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$"></label>

                <label for="floatingName"><input type="text" placeholder="Name" id="floatingName" name="first_name" <c:if test="${not empty first_name}">value="${first_name}"</c:if> required minlength="1" maxlength="30"></label>

                <label for="floatingSurname"><input type="text" placeholder="Surname" id="floatingSurname" name="surname" <c:if test="${not empty surname}">value="${surname}"</c:if> required minlength="1" maxlength="30"></label>

                <label for="floatingPhone"><input type="text" placeholder="Phone" id="floatingPhone" name="phone"></label>

                <label for="floatingPassword"><input type="password" placeholder="Password" id="floatingPassword" name="password" required minlength="8" maxlength="30"></label>

                <label for="floatingRepeatPassword"><input type="password" placeholder="Confirm Password" id="floatingRepeatPassword" name="repeated_password" required minlength="8" maxlength="30"></label>

                <button class=btn-create type="submit"><i class="fa-solid fa-lock"></i>Create Account</button>

                <div>
                    <c:if test="${not empty error_message}">
                        <div style="width: 6rem;">
                                ${error_message}
                        </div>
                    </c:if>
                </div>

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
