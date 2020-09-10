
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
                                <a class="nav-link active" id="networks-nav" href="/templates">
                                    <span data-feather="cloud"></span>
                                    Templates <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" id="nodes-nav" href="/nodes">
                                    <span data-feather="cpu"></span>
                                    Nodes
                                    </a>
                                </li>
                            <li class="nav-item">
                                <a class="nav-link" id="configuration-nav" href="/settings">
                                    <span data-feather="settings"></span>
                                    Konfiguration
                                    </a>
                                </li>
                            </ul>



                        </div>
                    </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Templates</h1>

                        </div>

                    <!-- Main content here!-->
                    <form action="javascript:void(0);">
                        <div class="text-right">
                            <button type="submit" class="btn btn-primary btn-sm" id="newtemplate"><span data-feather="plus"></span></button>
                        </div>
                     </form>
                    
                    <div id="newtemplateplaceholder"></div>
                    
                    <p></p>
                    <div id="templates-list">
                    <#list templates as template>
                        <form  class="templateform" action="/templates/${template.name}" method="post">
                            <h2 name="name">${template.name}</h2>
                            <input type="hidden" name="name" value="${template.name}">
                            <div class="form-group row">
                                <label for="${template.name}-code" class="col-sm-2 col-form-label">Template</label>
                                <div class="col-sm-10">
                                    <textarea type="input" class="form-control" id="${template.name}-code" name="${template.name}-code" value="${template.text}">${template.text}</textarea>
                                </div>
                            </div>
                            
                            <#list template.parameters as pKey, pValue>
                            <div class="form-group row">
                                <label for="newcode" class="col-sm-2 col-form-label">Parameter name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="newcode" value="${pKey}">
                                </div>
                            </div>
                            </#list>

                            <div class="text-right">
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                            </form>
                    </#list>
                        </div>


                    </main>
                </div>
            </div>

        <script src="/webjars/feather-icons/dist/feather.min.js"></script>
        <script src="/dashboard.js"></script>
        </body>
    </html>
