
var app = angular.module('crudApp',['ui.router','ngStorage']);
///
app.constant('urls', {
    BASE: 'http://localhost:8081',
    TASK_SERVICE_API : 'http://localhost:8081/api/task/',
    ASSIGNMENT_SERVICE_API : 'http://localhost:8081/api/assignment/'
});
////
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
        	.state('home',{
        		url:'/',
        		templateUrl:'/'
        	})
            .state('task', {
                url: '/task',
                templateUrl: 'partials/list',
                controller:'TaskController',
                controllerAs:'ctrl',
                resolve: {
                    task: function ($q, TaskService) {
                        console.log('Load all task');
                        var deferred = $q.defer();
                        TaskService.loadAllTasks().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            })
            .state('assignment', {
        		url: '/assignment',
        		templateUrl: 'partials/assignment',
        		controller:'AssignmentController',
        		controllerAs:'asg',
        		resolve: {
        			assignment: function ($q, AssignmentService) {
        				console.log('Load all assignment');
        				var deferred = $q.defer();
        				AssignmentService.loadAllAssignments().then(deferred.resolve, deferred.resolve);
        				return deferred.promise;
        			}
        		}
        	})
        $urlRouterProvider.otherwise('/');
    }

]);
