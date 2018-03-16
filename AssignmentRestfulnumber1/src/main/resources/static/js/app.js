
var app = angular.module('crudApp',['ui.router','ngStorage']);
 
app.constant('urls', {
    BASE: 'http://localhost:8081',
    TASK_SERVICE_API : 'http://localhost:8081/api/task/'
});
 
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/',
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
            });
        $urlRouterProvider.otherwise('/');
    }]);