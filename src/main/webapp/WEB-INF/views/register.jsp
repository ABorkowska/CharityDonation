<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Rejestracja</title>
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>
<body>
<%@ include file="header-login.jsp" %>

<section class="login-page">
    <h2>Załóż konto</h2>
    <c:if test="${error!= null}">
    <div cssClass="error">${error}
        </c:if>
    <form method="post">
        <div class="form-group">
            <input path="name" type="text" name="name" placeholder="Imię" required/>
        </div>
        <div class="form-group">
            <input type="text" name="surname" placeholder="Nazwisko" required/>
        </div>
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" pattern="^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" required/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>
        <div class="form-group form-group--buttons">
            <a href="<c:url value="donation/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
        <div class="form-group">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
    </form>
</section>
</body>
<%@ include file="./footer.jsp" %>
</html>
