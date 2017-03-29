
app.controller("MerchantController", function($scope, $rootScope, $http, $location, selectMerchantService) {
    
    $scope.merchants = [];
    
    if ($rootScope.user.username == undefined) {
        $location.path("/home");
    } else {
        //Now load the data from server
        _refreshPageData();
    }
    
    
    /* Private Methods */
    function _refreshPageData() {
        $http({
            method : 'GET',
            url : $rootScope.restserver+ 'merchants'
        }).then(function successCallback(response) {
            $scope.merchants = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }
    
    
    $scope.pickMerchant = function(merchant) {
        selectMerchantService.selectedMerchant = merchant;
    };
});