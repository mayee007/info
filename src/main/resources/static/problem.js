var app = angular.module("ProblemManagement", []);
 
// Controller Part
app.controller("ProblemController", function($scope, $http) {
 
 
	$http({
        method: 'GET',
        url: '/technology/types'
    }).then(
        function(res) { // success
            $scope.technologytTypes = res.data;
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
	
	    //$scope.names = ["Emil", "Tobias", "Linus"];
	
    $scope.Problems = [];
    $scope.ProblemForm = {
        id: 1,
        problem: "",
        reasonForProblem: "", 
        solution: "",
        technologyType: "", 
        category: "",
        submitDate: "", 
        modifiedDate: ""
    };
 
    // Now load the data from server
    _refreshProblemData();
 
    // HTTP POST/PUT methods for add/edit Problem  
    // Call: http://localhost:8080/Problem
    $scope.submitProblem = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.ProblemForm.id == -1) {
            method = "POST";
            url = '/problem';
        } else {
            method = "PUT";
            url = '/problem';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.ProblemForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createProblem = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete Problem by Id
    // Call: http://localhost:8080/Problem/{empId}
    $scope.deleteProblem = function(Problem) {
        $http({
            method: 'DELETE',
            url: '/problem/' + Problem.id
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editProblem = function(Problem) {
        $scope.ProblemForm.id = Problem.id;
        $scope.ProblemForm.problem = Problem.problem;
        $scope.ProblemForm.solution = Problem.solution;
        $scope.ProblemForm.reasonForProblem = Problem.reasonForProblem;
        $scope.ProblemForm.submitDate = Problem.submitDate;
        $scope.ProblemForm.modifiedDate = Problem.modifiedDate;
        $scope.ProblemForm.technology.technologyType = Problem.technology.technologyType;
        $scope.ProblemForm.technology.category = Problem.technology.category;
    };
 
    // Private Method  
    // HTTP GET- get all Problems collection
    // Call: http://localhost:8080/Problems
    function _refreshProblemData() {
        $http({
            method: 'GET',
            url: '/problem'
        }).then(
            function(res) { // success
                $scope.problems = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
        _refreshProblemData();
        _clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
        $scope.ProblemForm.id = -1;
        $scope.ProblemForm.problem = "";
        $scope.ProblemForm.reasonForProblem = "";
        $scope.ProblemForm.solution = "";
        $scope.ProblemForm.technology.technologyType = "";
        $scope.ProblemForm.technology.category = "";
        $scope.ProblemForm.submitDate = "";
        $scope.ProblemForm.submitDate = "";
    };
});