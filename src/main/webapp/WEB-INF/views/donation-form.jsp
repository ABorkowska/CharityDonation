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
    <title>Donation</title>

    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>"/>
</head>

<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${user.name}
                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="#">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="<c:url value="/donation/add"/>" class="btn btn--without-border active">Start</a></li>
            <li><a href="" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="" class="btn btn--without-border">O nas</a></li>
            <li><a href="" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li><div><em>1</em><span>Wybierz rzeczy</span></div></li>
                    <li><div><em>2</em><span>Spakuj je w worki</span></div></li>
                    <li><div><em>3</em><span>Wybierz fundację</span></div></li>
                    <li><div><em>4</em><span>Zamów kuriera</span></div></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form method="post" modelAttribute="donation">

            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>
                <c:forEach var="cat" items="${categories}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categories" value="${cat.name}" multiple="true"/>
                            <span class="checkbox"></span>
                            <span class="description">${cat.name}</span>
                        </label>
                    </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60 l worków, w które spakowałeś/aś rzeczy:</h3>
                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60 l worków:
                        <input type="number" name="bags" step="1" min="1"/>
                    </label>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Wybierz organizację, której chcesz pomóc:</h3>
                <c:forEach var="inst" items="${institutions}">
                <div class="form-group form-group--checkbox">
                    <label>
                        <input type="radio" name="organization" value="${inst.name}" required/>
                        <span class="checkbox radio"></span>
                        <span class="description">
                            <div class="title">Fundacja “${inst.name}”</div>
                            <div class="subtitle">Cel i misja: ${inst.description}</div>
                        </span>
                    </label>
                </div>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica <input type="text" name="street" required/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto <input type="text" name="city" required/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Kod pocztowy <input type="text" name="postcode" pattern="^[0-9]{2}-[0-9]{3}$" required/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Numer telefonu <input type="phone" name="phone" required/> </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <input type="date" name="date" required/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina <input type="time" name="time" required/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <textarea name="more_info" rows="5"></textarea>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="packages"></span>
                            </li>
                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="institution"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column" id="address"></div>
                        <div class="form-section--column" id="time"></div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form>
    </div>
</section>

</body>
<%@ include file="./footer.jsp" %>
</html>
