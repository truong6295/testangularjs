'use strict';
 
angular.module('crudApp').controller('AssignmentController',
    ['AssignmentService', '$scope',  function( AssignmentService, $scope) {
 
        var self = this;
        self.assignment = {};
        self.assignments=[];
 
        self.submit = submit;
        self.getAllAssignments = getAllAssignments;
        self.createAssignment = createAssignment;
        self.updateAssignment = updateAssignment;
        self.removeAssignment = removeAssignment;
        self.editAssignment = editAssignment;
        self.reset = reset;
 
        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
 
        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.assignment.assignmentId === undefined || self.assignment.assignmentId === null) {
                console.log('Saving New assignment', self.assignment);
                createAssignment(self.assignment);
            } else {
                updateAssignment(self.assignment, self.assignment.assignmentId);
                console.log('Assignment updated with id ', self.assignment.assignmentId);
            }
        }
 
        function createAssignment(assignment) {
            console.log('About to create assignment');
            AssignmentService.createAssignment(assignment)
                .then(
                    function (response) {
                        console.log('Assignment created successfully');
                        self.successMessage = 'Assignment created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.assignment={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Assignment');
                        self.errorMessage = 'Error while creating Assignment: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
 
 
        function updateAssignment(assignment, id){
            console.log('About to update Assignment');
            AssignmentService.updateAssignment(assignment, id)
                .then(
                    function (response){
                        console.log('Assignment updated successfully');
                        self.successMessage='Assignment updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Assignment');
                        self.errorMessage='Error while updating Assignment '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
 
 
        function removeAssignment(id){
            console.log('About to remove Assignment with id '+id);
            AssignmentService.removeAssignment(id)
                .then(
                    function(){
                        console.log('Assignment '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Assignment '+id +', Error :'+errResponse.data);
                    }
                );
        }
 
 
        function getAllAssignments(){
            return AssignmentService.getAllAssignments();
        }
 
        function editAssignment(id) {
            self.successMessage='';
            self.errorMessage='';
            AssignmentService.getAssignment(id).then(
                function (assignment) {
                    self.assignment = assignment;
                },
                function (errResponse) {
                    console.error('Error while removing assignment ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.assignment={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);