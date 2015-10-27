var notifManagerModule = angular.module('sample1.1.f', ['ngAnimate']);

notifManagerModule.controller('notifManagerController', function ($scope,$http) {
	
	var urlBase="http://localhost:8080/sample1.1.f/addnotifs";
	$scope.toggle=true;
	$scope.selection = [];
	$scope.statuses=['New','Seen'];
	$scope.priorities=['HIGH','LOW','MEDIUM'];
	$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	
	//get all notifs and display initially
	$http.get(urlBase+'/notifs').
    	success(function(data) {
	        $scope.notifs = data;
	        for(var i=0;i<$scope.notifs.length;i++){
	            if($scope.notifs[i].notifStatus=='Seen'){
	           	 $scope.selection.push($scope.notifs[i].notifId);
	        }
	        }
    });
	

		
	// toggle selection for a given notif by notif id
	  $scope.toggleSelection = function toggleSelection(notifId) {
	    var idx = $scope.selection.indexOf(notifId);

	    // is currently selected
	    if (idx > -1) {
	      $http.post(urlBase + '/notifs/' +notifId+'/New').
		  success(function(data) {
			// alert("Notif unmarked");
			 $scope.notifs = data;		       
		    });
	      $scope.selection.splice(idx, 1);
	    }

	    // is newly selected
	    else {
	      $http.post(urlBase + '/notifs/' +notifId+'/Seen').
		  success(function(data) {
			 //alert("Notif marked completed");
			 $scope.notifs = data;
		    });
	      $scope.selection.push(notifId);
	    }
	  };
	  
	
	// Archive Completed Notifs
	  $scope.archiveNotifs = function archiveNotifs() {
		  $http.post(urlBase + '/notifs/archive/' + $scope.selection).
		  success(function(data) {
			  $scope.notifs = data;
		       alert("Successfully Archived");
		    });
	  };
	
});

//Angularjs Directive for confirm dialog box
notifManagerModule.directive('ngConfirmClick', [
	function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);