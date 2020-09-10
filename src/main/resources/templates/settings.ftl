
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>Butler Dashboard</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.3/examples/dashboard/">

    <!-- Bootstrap core CSS -->

        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
        <script src="/webjars/jquery/jquery.min.js"></script>
        <script src="/webjars/popper.js/umd/popper.min.js"></script>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

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
                                <a class="nav-link" id="dashboard-nav" href="/dashboard">
                                    <span data-feather="home"></span>
                                    Dashboard
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" id="projects-nav" href="/projects">
                                    <span data-feather="globe"></span>
                                    Projekte
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" id="container-nav" href="/container">
                                    <span data-feather="grid"></span>
                                    Container
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" id="networks-nav" href="/templates">
                                    <span data-feather="cloud"></span>
                                    Templates
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" id="nodes-nav" href="/nodes">
                                    <span data-feather="cpu"></span>
                                    Nodes
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link active" id="configuration-nav" href="/settings">
                                    <span data-feather="settings"></span>
                                    Konfiguration <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </ul>

                      
                        
                        </div>
                    </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Dashboard</h1>

                        </div>

                    <!-- Main content here!-->
 
                    </main>
                </div>
            </div>
   
        <script src="/webjars/feather-icons/dist/feather.min.js"></script>
        <script src="/dashboard.js"></script>
        </body>
    </html>
