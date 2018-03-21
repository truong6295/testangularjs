
'use strict';
 
angular.module('crudApp').factory('TaskService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllTasks: loadAllTasks,
                getAllTasks: getAllTasks,
                getTask: getTask,
                createTask: createTask,
                updateTask: updateTask,
                removeTask: removeTask
            };
 
            return factory;
 
            function loadAllTasks() {
                console.log('Fetching all tasks');
                var deferred = $q.defer();
                $http.get(urls.TASK_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all tasks');
                            $localStorage.tasks = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading tasks');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllTasks(){
                return $localStorage.tasks;
            }
            
            function getTask(id) {
                console.log('Fetching task with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.TASK_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully task with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading task with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createTask(task) {
                console.log('Creating task');
                var deferred = $q.defer();
                $http.post(urls.TASK_SERVICE_API, task)
                    .then(
                        function (response) {
                            loadAllTasks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating task : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateTask(task, id) {
                console.log('Updating task with id '+id);
                var deferred = $q.defer();
                $http.put(urls.TASK_SERVICE_API + id, task)
                    .then(
                        function (response) {
                            loadAllTasks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating task with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeTask(id) {
                console.log('Removing Task with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.TASK_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllTasks();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing task with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);