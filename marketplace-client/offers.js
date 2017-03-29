
app.controller("OfferManagementController", function($rootScope, $scope, $http, $location, selectMerchantService) {
    $scope.offers = [];
    $scope.form = {
        id : -1,
        title : "",
        validFrom : "",
        validTo : "",
        merchantId : "",
        typeId : "",
        categoryId : "",
        deleted : "",
        description : ""
    };
    if (selectMerchantService.selectedMerchant.id == undefined) {
        
        $location.path("/merchants");
    } else {//Now load the data from server
        _refreshPageData();
        
    }
 
    //HTTP POST/PUT methods for add/edit Offer
    $scope.submitOffer = function() {

        var method = "";
        var url = "";
        if ($scope.form.id == -1) {
            //Id is absent so add employee - POST operation
            method = "POST";
            url = $rootScope.restserver + 'merchants/'+selectMerchantService.selectedMerchant.id+'/offers';
        } else {
            //If Id is present, it's edit operation - PUT operation
            method = "PUT";
            url = $rootScope.restserver + 'merchants/'+selectMerchantService.selectedMerchant.id+'/offers/' + $scope.form.id;
        }
        console.log(angular.toJson($scope.form));
        $http({
            method : method,
            url : url,
            data : angular.toJson($scope.form),
            headers : {
                'Content-Type' : 'application/json'
            }
        }).then( _success, _error );
    };

    //HTTP DELETE- delete employee by Id
    $scope.removeOffer = function(offer) {
        $http({
            method : 'DELETE',
            url : $rootScope.restserver + 'merchants/'+selectMerchantService.selectedMerchant.id+'/offers/' + offer.id
        }).then(_success, _error);
    };


    //In case of edit offer, populate form with offer data
    $scope.editOffer = function(offer) {
        $scope.form.title = offer.title;
        $scope.form.description = offer.description;
        $scope.form.validFrom = offer.validFrom;
        $scope.form.validTo = offer.validTo;
        $scope.form.merchantId = offer.merchantId;
        $scope.form.categoryId = offer.categoryId;
        $scope.form.typeId = offer.typeId;
        $scope.form.deleted = offer.deleted;
        $scope.form.id = offer.id;
    };

    /* Private Methods */
    //HTTP GET- get all offers collection
    function _refreshPageData() {
        $http({
            method : 'GET',
            url : $rootScope.restserver + 'merchants/'+selectMerchantService.selectedMerchant.id+'/offers'
        }).then(function successCallback(response) {
            $scope.offers = response.data;
            $scope.merchant = selectMerchantService.selectedMerchant;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }



    function _success(response) {
        _refreshPageData();
        _clearForm()
    }

    function _error(response) {
        console.log(response.statusText);
    }

    //Clear the form
    function _clearForm() {
        $scope.form.title = "";
        $scope.form.description = "";
        $scope.form.validFrom = "";
        $scope.form.validTo = "";
        $scope.form.merchantId = "";
        $scope.form.categoryId = "";
        $scope.form.typeId = "";
        $scope.form.deleted = "";
        $scope.form.id = "";
    };
});