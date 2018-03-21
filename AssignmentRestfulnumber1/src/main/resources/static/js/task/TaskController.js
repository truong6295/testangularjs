'use strict';
 
angular.module('crudApp').controller('TaskController',
    ['TaskService', '$scope',  function( TaskService, $scope) {
 
        var self = this;
        self.task = {};
        self.tasks=[];
 
        self.submit = submit;
        self.getAllTasks = getAllTasks;
        self.createTask = createTask;
        self.updateTask = updateTask;
        self.removeTask = removeTask;
        self.editTask = editTask;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.task.tasksId === undefined || self.task.tasksId === null) {
                console.log('Saving New User', self.task);
                createTask(self.task);
            } else {
                updateTask(self.task, self.task.tasksId);
                console.log('User updated with id ', self.task.tasksId);
            }
        }
 
        function createTask(task) {
            console.log('About to create user');
            TaskService.createTask(task)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.task={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
 
        function updateTask(task, id){
            console.log('About to update user');
            TaskService.updateTask(task, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Task');
                        self.errorMessage='Error while updating Task '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
 
        function removeTask(id){
            console.log('About to remove User with id '+id);
            TaskService.removeTask(id)
                .then(
                    function(){
                        console.log('Task '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing user '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
 
        function getAllTasks(){
            return TaskService.getAllTasks();
        }
 
        function editTask(id) {
            self.successMessage='';
            self.errorMessage='';
            TaskService.getTask(id).then(
                function (task) {
                    self.task = task;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.task={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);