
'use strict';
 
angular.module('crudApp').factory('AssignmentService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
 
            var factory = {
                loadAllAssignments: loadAllAssignments,
                getAllAssignments: getAllAssignments,
                getAssignment: getAssignment,
                createAssignment: createAssignment,
                updateAssignment: updateAssignment,
                removeAssignment: removeAssignment
            };
 
            return factory;
 
            function loadAllAssignments() {
                console.log('Fetching all Assignments');
                var deferred = $q.defer();
                $http.get(urls.ASSIGNMENT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Assignments');
                            $localStorage.assignments = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading tasks');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function getAllAssignments(){
                return $localStorage.assignments;
            }
            
            function getAssignment(id) {
                console.log('Fetching Assignment with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ASSIGNMENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Assignment with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Assignment with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function createAssignment(assignment) {
                console.log('Creating Assignment');
                var deferred = $q.defer();
                $http.post(urls.ASSIGNMENT_SERVICE_API, assignment)
                    .then(
                        function (response) {
                            loadAllAssignments();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating task : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function updateAssignment(assignment, id) {
                console.log('Updating task with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ASSIGNMENT_SERVICE_API + id, assignment)
                    .then(
                        function (response) {
                            loadAllAssignments();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating task with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeAssignment(id) {
                console.log('Removing Assignment with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ASSIGNMENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllAssignments();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Assignment with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
        }
    ]);