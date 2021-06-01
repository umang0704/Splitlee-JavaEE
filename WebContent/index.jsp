<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Splitlee</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css">
    <link rel="icon" href="images/logo2.png">
</head>

<body>
    <!--Header Content-->
    <header class="jumbotron">
        <div class="contanier">
            <div class="row row-header offset-1">
                <div class="col-12 col-sm-1">
                    <img src="./images/logo/logo2.png" style="width: 75%; margin-top: -5px; max-width: 100px;">
                </div>
                <div class="col-12 col-sm-1" style="margin-left: -25px;">
                    <h1><a href="./index.jsp" style="color: floralwhite; text-decoration: none;">Splitlee</a></h1>
                </div>
            </div> 
        </div>
    </header>

    <!--Main Body Content-->
    <section class="section">

        <!--Buttons with functionality Login and SignUp-->
        <div class="container">
            <div class="row button-row">
                <div class="col-12 offset-11">
                    <a class="btn btn-dark" href="login.jsp" role="button" style="position: fixed; margin-left: 90px; margin-top: -10px;">Login</a>
                    <a class="btn btn-dark" href="signup.jsp" role="button" style="position: fixed; margin-left: 160px; margin-top: -10px;">SignUp</a>
                </div>
            </div>
        </div>

        <div class="container">
            <!--CardBox 1-->
            <div class="row row-content align-items-center">
                <div class="col-12 col-sm-4 order-sm-first col-md-3 offset-1">
                    <img src="./images/test.png">
                </div>
                <div class="col-12 col-sm order-sm-last col-md ">
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                    <p>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s</p>
                </div>
            </div>

            <!--CardBox 2-->
            <div class="row row-content align-items-center">
                <div class="col-12 col-sm-4 col-md-3 order-sm-last">
                    <img src="./images/test.png">
                </div>
                <div class="col-12 col-sm col-md order-sm-first offset-1">
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                    <p>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s</p>
                </div>
            </div>

            <!--CardBox 3-->
            <div class="row row-content align-items-center">
                <div class="col-12 col-sm-4 order-sm-first col-md-3 offset-1">
                    <img src="images/test.png">
                </div>
                <div class="col-12 col-sm order-sm-last col-md ">
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                    <p>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s</p>
                </div>
            </div>
        </div>

    </section>

    <!--Footer Content-->
	<jsp:include page="footer1.jsp"></jsp:include>


</body>

</html>