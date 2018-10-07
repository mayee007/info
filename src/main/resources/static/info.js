var app = angular.module("InfoManagement", []);
 
// Controller Part
app.controller("InfoController", function($scope, $http) {
 
 	console.log("its inside Info.js");
	$http({
        method: 'GET',
        url: '/technology/types'
    }).then(
        function(res) { // success
            $scope.technologyTypes = res.data;
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
	
	$http({
        method: 'GET',
        url: '/technology/category'
    }).then(
        function(res) { // success
            $scope.technologyCategory = res.data;
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );
    
	    //$scope.names = ["Emil", "Tobias", "Linus"];
	
    $scope.Infos = [];
    $scope.InfoForm = {
        id: 1,
        Info: "",
        reasonForInfo: "", 
        solution: "",
        technologyType: "", 
        category: "",
        submitDate: "", 
        modifiedDate: ""
    };
 
    // Now load the data from server
    _refreshInfoData();
 
    // HTTP POST/PUT methods for add/edit Info  
    // Call: http://localhost:8080/Info
    $scope.submitInfo = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.InfoForm.id == -1) {
            method = "POST";
            url = '/info';
        } else {
            method = "PUT";
            url = '/info';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.InfoForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createInfo = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete Info by Id
    // Call: http://localhost:8080/Info/{empId}
    $scope.deleteInfo = function(Info) {
        $http({
            method: 'DELETE',
            url: '/info/' + Info.id
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editInfo = function(Info) {
        $scope.InfoForm.id = Info.id;
        $scope.InfoForm.subject = Info.subject;
        $scope.InfoForm.description = Info.description;
        $scope.InfoForm.submitDate = Info.submitDate;
        $scope.InfoForm.modifiedDate = Info.modifiedDate;
        $scope.InfoForm.technologyType = Info.technology.technologyType;
        $scope.InfoForm.category = Info.technology.category;
    };
 
    // Private Method  
    // HTTP GET- get all Infos collection
    // Call: http://localhost:8080/Infos
    function _refreshInfoData() {
        $http({
            method: 'GET',
            url: '/info'
        }).then(
            function(res) { // success
                $scope.Infos = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
        _refreshInfoData();
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
        $scope.InfoForm.id = -1;
        $scope.InfoForm.subject = "";
        $scope.InfoForm.description = "";
        $scope.InfoForm.technology.technologyType = "";
        $scope.InfoForm.technology.category = "";
        $scope.InfoForm.submitDate = "";
        $scope.InfoForm.submitDate = "";
    };
});