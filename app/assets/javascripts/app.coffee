###
 A multiline comment, perhaps a LICENSE.
###

dependencies = [
    'ngRoute',
    'ui.bootstrap',
    'myApp.filters',
    'myApp.services',
    'myApp.controllers',
    'myApp.directives',
    'myApp.common',
    'myApp.routeConfig',
    'ionic'
]



app = angular.module('myApp', dependencies)

angular.module('myApp.routeConfig', ['ngRoute'])
 .run(($ionicPlatform, $httpBackend,$rootScope,$state, $window,$http) ->

 			$rootScope.localurl = 'http://192.168.42.236';
 			
 			alert($rootScope.localurl);
 			
 			options = {
 					  enableHighAccuracy: true,
 					  timeout: 5000,
 					  maximumAge: 0
 					};
 			
 			success = (pos) ->
 				  crd = pos.coords;

 				  console.log('Your current position is:');
 				  console.log('Latitude : ' + crd.latitude);
 				  console.log('Longitude: ' + crd.longitude);
 				  console.log('More or less ' + crd.accuracy + ' meters.');
 			
 			 error= (err) ->
 				  console.warn('ERROR(' + err.code + '): ' + err.message);
 				
 			navigator.geolocation.getCurrentPosition(success, error, options);
 										

 )	 
	 .config ($routeProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/partials/main.html'
            })
            .when('/users/create', {
                templateUrl: '/partials/create.html'
            })
            .when('/users/edit/:firstName/:lastName', {
                templateUrl: '/partials/update.html'
            })
            .otherwise({redirectTo: '/'})
    .config ($locationProvider) ->
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })

@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])