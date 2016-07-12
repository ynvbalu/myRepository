var app = angular.module('myApp', []);  
 app.controller('java2blogContoller', function($scope,$http) {  
        
  var url = "countries.json";  
  $http.get(url).success( function(response) {  
        $scope.countries = response;   
     });  
 });  
