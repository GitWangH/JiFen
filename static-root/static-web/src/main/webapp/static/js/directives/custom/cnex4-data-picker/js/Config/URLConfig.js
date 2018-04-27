/**
 * Created by Amit Thakkar on 24/03/15.
 */
(function (ng) {
    var treeServiceApp = ng.module('tree.factory', []);
    treeServiceApp.factory("URLConfig", [function () {
        return {
        	
        	$http.get("/api/org/treeOrg/auth").success(function(response){	
/**
 *      		tree: "static/directives/cnex4-data-picker/api/org.json"
 */    
        		tree:response
        	}
        }
    }]);
})(angular);
