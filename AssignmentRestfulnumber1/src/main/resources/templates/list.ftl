
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">Specific Task </span></div>
        <div class="panel-body">
            <div class="formcontainer">
                <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
                <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.task.tasksId" />
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="detail">Detail</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.task.detail" id="detail" class="username form-control input-sm" placeholder="Enter your detail" required />
                            </div>
                        </div>
                    </div>
 
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="endDate">Date end</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.task.endDate" id="endDate" class="form-control input-sm" placeholder="Enter your end Date." required />
                            </div>
                        </div>
                    </div>
     
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="nameTasks">name Tasks</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.task.nameTasks" id="nameTasks" class="form-control input-sm" placeholder="Enter your nameTasks." required />
                            </div>
                        </div>
                    </div>
 					 <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="startDate">startDate</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.task.startDate" id="startDate" class="form-control input-sm" placeholder="Enter your startDate." required />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit"  value="{{!ctrl.task.tasksId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine"/>
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                        </div>
                    </div>
                   
                    
                </form>
            </div>
        </div>    
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Tasks </span></div>
        <div class="panel-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>tasksId</th>
                        <th>detail</th>
                        <th>endDate</th>
                        <th>nameTasks</th>
                        <th>startDate</th>
                        <th width="100"></th>
                        <th width="100"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in ctrl.getAllTasks()">
                        <td>{{u.tasksId}}</td>
                        <td>{{u.detail}}</td>
                        <td>{{u.endDate}}</td>
                        <td>{{u.nameTasks}}</td>
                        <td>{{u.startDate}}</td>
                        <td><button type="button" ng-click="ctrl.editTask(u.tasksId)" class="btn btn-success custom-width">Edit</button></td>
                        <td><button type="button" ng-click="ctrl.removeTask(u.tasksId)" class="btn btn-danger custom-width">Remove</button></td>
                    </tr>
                    </tbody>
                </table>      
            </div>
        </div>
    </div>
</div>