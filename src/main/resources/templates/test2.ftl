
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v3.8.5">
        <title>Dashboard Template · Bootstrap</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/dashboard/">

        <!-- Bootstrap core CSS -->

        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script src="/webjars/popper.js/umd/popper.min.js"></script>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" href="/webjars/codemirror/lib/codemirror.css">
        <script src="/webjars/codemirror/lib/codemirror.js"></script>

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
            </style>
            <!-- Custom styles for this template -->
        <link href="/dashboard.css" rel="stylesheet">
        </head>
    <body>
        <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Zuara AG</a>

            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="#">Sign out</a>
                    </li>
                </ul>
            </nav>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link active" href="#">
                                    <span data-feather="home"></span>
                                    Dashboard <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </ul>


                        </div>
                    </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Dashboard</h1>

                        </div>

                     <code id="codeblock" class="template-editor"></code>
                    
                    <p></p>
                    <p></p>
                    <p></p>
                    
                    <form action="/test2" method="post">
                        <h2 name="name">${forms2.name}</h2>
                        <input type="hidden" name="name" value="${forms2.name}">
                        <#list forms2.elements as formKey, formContent>
                        <div class="form-group row">
                            <label for="${formKey}" class="col-sm-2 col-form-label">${formKey}</label>
                            <div class="col-sm-10">
                                <input type="input" class="form-control" id="${formKey}" name="elements[${formKey}]" value="${formContent}">
                            </div>
                        </div>
                        </#list>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>


                            </main>
                            </div>
                        </div>


                    <script src="/webjars/feather-icons/dist/feather.min.js"></script>
                    <!-- script src="chart/Chart.min.js"></script -->
                    <script src="/dashboard.js"></script>
                    </body>
                    </html>
