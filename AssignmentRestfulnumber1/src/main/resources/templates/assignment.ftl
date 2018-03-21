
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Assignment </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="asg.successMessage">{{asg.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="asg.errorMessage">{{asg.errorMessage}}</div>
                <form ng-submit="asg.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="asg.assignment.assignmentId" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="testResult">Detail</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="asg.assignment.testResult" id="testResult" class="username form-control input-sm" placeholder="Enter your testResult" required />
                            </div>
                        </div>
                    </div>
 
                    
 					 
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!asg.assignment.assignmentId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine"/>
                            <button type="button" ng-click="asg.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                   
                    
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Assignment </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>assignmentId</th>
                        <th>testResult</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in asg.getAllAssignments()">
                        <td>{{u.assignmentId}}</td>
                        <td>{{u.testResult}}</td>
                        <td><button type="button" ng-click="asg.editAssignment(u.assignmentId)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="asg.removeAssignment(u.assignmentId)" class="btn btn-danger custom-width">Remove</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>