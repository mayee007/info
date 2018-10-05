var app = angular.module("TechnologyManagement", []);
 
// Controller Part
app.controller("TechnologyController", function($scope, $http) {
 
 
    $scope.Technologys = [];
    
    $scope.TechnologyForm = {
        technologyId: 1,
        technologyType: "",
        category: ""
    };
 
    // Now load the data from server
    _refreshTechnologyData();
 
    // HTTP POST/PUT methods for add/edit Technology  
    // Call: http://localhost:8080/Technology
    $scope.submitTechnology = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.technologyForm.technologyId == -1) {
            method = "POST";
            url = '/technology';
        } else {
            method = "PUT";
            url = '/technology';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.technologyForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createTechnology = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete Technology by Id
    // Call: http://localhost:8080/Technology/{empId}
    $scope.deleteTechnology = function(Technology) {
        $http({
            method: 'DELETE',
            url: '/technology/' + Technology.technologyId
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editTechnology = function(Technology) {
        $scope.technologyForm.technologyId = Technology.technologyId;
        $scope.technologyForm.technologyType = Technology.technologyType;
        $scope.technologyForm.category = Technology.category;
    };
 
    // Private Method  
    // HTTP GET- get all Technologys collection
    // Call: http://localhost:8080/Technologys
    function _refreshTechnologyData() {
        $http({
            method: 'GET',
            url: '/technology'
        }).then(
            function(res) { // success
                $scope.techs = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
        _refreshTechnologyData();
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
        $scope.TechnologyForm.technologyId = -1;
        $scope.TechnologyForm.technologyType = "";
        $scope.TechnologyForm.category = ""
    };
});