<!DOCTYPE html>
 
<html lang="en" ng-app="crudApp">
    <head>
        <title>${title}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
        <meta charset="utf-8">
  		<meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
    	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  			<a class="navbar-brand" ui-sref="home">Home</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  			<div class="collapse navbar-collapse" id="collapsibleNavbar">
    			<ul class="navbar-nav">
      				<li class="nav-item">
        				<a class="nav-link" ui-sref="task">Task</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" ui-sref="personal">Personal</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" ui-sref="assignment">Assignment</a>
      				</li>    
    			</ul>
  			</div>  
		</nav>
		<br>

		<div class="container">
        	<div ui-view></div>
        	
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	 		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        	<script src="js/lib/angular.min.js" ></script>
        	<script src="js/lib/angular-ui-router.min.js" ></script>
        	<script src="js/lib/localforage.min.js" ></script>
        	<script src="js/lib/ngStorage.min.js"></script>
        	<script src="js/app.js"></script>
        	<script src="js/task/TaskService.js"></script>
        	<script src="js/task/TaskController.js"></script>
        	<script src="js/task/AssignmentService.js"></script>
        	<script src="js/task/AssignmentController.js"></script>
    </body>
</html>