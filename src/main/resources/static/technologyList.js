var app = angular.module("TechnologyListManagement", []);
 
// Controller Part
app.controller("TechnologyTypeController", function($scope, $http) {
	
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

});
// Controller Part
app.controller("TechnologyCategoryController", function($scope, $http) {
	
	$http({
        method: 'GET',
        url: '/technology/category'
    }).then(
        function(res) { // success
            $scope.technologyCategorys = res.data;
        },
        function(res) { // error
            console.log("Error: " + res.status + " : " + res.data);
        }
    );

});


