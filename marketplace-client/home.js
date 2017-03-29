app.controller("HomeController", function($rootScope, $scope, $http, $location) {
    
    $rootScope.restserver = "http://localhost:8080/";
    $rootScope.user = {};
    $scope.form = {
        username : "",
        password : ""
    };
    
    $scope.login = function() {
        
        if ($scope.form.username == "" || $scope.form.password == "") {
            $location.path("/home");
        } else {
            var method = "POST";
            var url = $rootScope.restserver + 'login';
            $http({
                method : method,
                url : url,
                data : angular.toJson($scope.form),
                headers : {
                    'Content-Type' : 'application/json'
                }
            }).then(function successCallback(response) {
                $rootScope.user = response.data;
                $location.path("/merchants");
            }, function errorCallback(response) {
                console.log(response);
            });            
        }
    };
});