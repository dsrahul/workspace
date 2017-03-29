var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
    .when('/',{
        templateUrl : 'home.html',
        controller : 'HomeController'
    })
    .when('/merchants',{
        templateUrl : 'merchant.html',
        controller : 'MerchantController'
    })
    .when('/offers',{
        templateUrl : 'offer.html',
        controller : 'OfferManagementController'
    })
    .otherwise({redirectTo : '/'});
});



app.service('selectMerchantService', function(){
    var _selectedMerchant = {};
    
    this.selectedMerchant = _selectedMerchant; 
});