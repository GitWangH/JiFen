'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiMemberController', function($scope, $location, $http, $rootScope, listService, httpService, cacheService, shareData,excelService) {


    $scope.items = cacheService.getData("dic.blacklist");
    console.log($scope.items);
    
    $scope.returnCellTemplate = function() {
        return '<div  ng-if="!row.entity.isEdit" class="ui-grid-cell-contents ng-binding ng-scope">{{row.entity.blacklist_1}}</div>' +
            '<div  style="text-align:center" ng-class="ui-grid-cell-contents ng-binding ng-scope"  ng-if="row.entity.isEdit">' +
            '<select  ng-model="row.entity.blacklist">' +
            '<option ng-repeat="item in grid.appScope.items" value ="{{item.code}}">{{item.name}}</option>' +
            '</select>' +
            '</div>';
    }

    /***
     * 定义显示的表格.
     */
    var checkedMember = null;
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100,2000],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableGridMenu : true,
        multiSelect: true,
        columnDefs: [
            { name: '会员uid', field: 'uid', width: '10%', enableColumnMenu: false },
            { name: '会员名', field: 'userName', width: '10%', enableColumnMenu: false, cellFilter: "userNameFilter" },
            { name: '手机号码', field: 'tel', width: '10%', enableColumnMenu: false },
            { name: '性别', field: 'sex1', width: '10%', enableColumnMenu: false,decode: { field: 'sex', dataKey: 'dic.sex'}},
            { name: '会员等级', field: 'memberGrade', width: '10%', enableColumnMenu: false, decode: { field: 'memberGrade', dataKey: 'dic.member_grade' } },
            { name: '经验积分', field: 'allScore', width: '10%', enableColumnMenu: true },
            { name: '可用积分', field: 'enableScore', width: '10%', enableColumnMenu: true },
            { name: '是否是Plus会员', field: 'isplusMember1', width: '10%', enableColumnMenu: true, decode: { field: 'isplusMember', dataKey: 'dic.is_plusMember'}},
            { name: 'plus会员开通时间', field: 'plusOpenDate', width: '10%', enableColumnMenu: false },
            { name: 'Plus会员有效期', field: 'validTime', width: '10%', enableColumnMenu: true },
            { name: '兑换订单', field: 'orderCount', width: '10%', enableColumnMenu: true, cellTemplate: listService.getLinkCellTmplate("viewOrderData", "orderCount") },
            { name: '注册时间', field: 'createTime', width: '10%', enableColumnMenu: false },
            { name: '是否黑名单', field: 'blacklist_1', width: '10%', enableColumnMenu: true, decode: { field: "blacklist", dataKey: "dic.blacklist" }, cellTemplate: $scope.returnCellTemplate() },
            { name: '操作', field: 'operate', width: '10%', enableColumnMenu: false, cellTemplate: '<a ng-if="!row.entity.isEdit" sytle="cursor:pointer;" ng-click=grid.appScope.editPropertyrow(row,"edit")>编辑</a><a ng-if="row.entity.isEdit" sytle="cursor:pointer;" ng-click=grid.appScope.editPropertyrow(row,"save")>保存</a>' },
           // { name: '测试', field: 'test',width: '10%', enableColumnMenu: false,cellTemplate:'<a sytle="cursor:pointer;" ng-click=grid.appScope.editMemberAndCpouns(row.entity.id)>测试会员</a>'},
        ]
    };

    $scope.editPropertyrow = function(row, opration) {
        if (opration == "edit") {
            row.entity.isEdit = true;
        } else {
            httpService.post($scope, "api/phiMember/editBlackList/" + row.entity.id + "/" + row.entity.blacklist).success(function(response) {
                if ($scope.items) {
                    for (var i = 0; i < $scope.items.length; i++) {
                        if (row.entity.blacklist == $scope.items[i].code) {
                            row.entity.blacklist_1 = $scope.items[i].name;
                            break;
                        }
                    }
                }
                row.entity.isEdit = false;
            });
            var postQueryPage = copyQueryPage($scope.queryPage);
            if ($scope.notNeedQueryPage) {
                postQueryPage.orderBy = '';
                postQueryPage.queryParamList = [];
            }
            $scope.load();
        }
    };

    /* 测试 */
    	/*$scope.editMemberAndCpouns = function(id){
    		httpService.post($scope, 'api/phiMember/openPlusMember/'+id).success(function(response){
    			});
    	}
*/
    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
   // listService.init($scope, $http);

    /***
     * 注册gridApi的选择器.
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
    };

    listService.init($scope,$http, $scope.tableGrid, $scope.gridApi, 'api/phiMember/query');
    //定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    //定义搜索框
    //queryPage.addParam(new queryParam('会员id', 'id', 'string', 'like'));
    queryPage.addParam(new queryParam('会员手机号', 'tel', 'string', 'alllike'));
    queryPage.addParam(new queryParam('会员名', 'userName', 'string', 'alllike'));
    var membergrade = new queryParam('会员等级', 'phiMembergrade.id', 'string', '=', null, "dic.member_grade");
    membergrade.componentType = 'select';
    queryPage.addParam(membergrade);
    var isPlusMember = new queryParam('是否是Plus会员', 'isplusMember', 'string', '=', null, 'dic.is_plusMember');
    isPlusMember.componentType = 'select';
    queryPage.addParam(isPlusMember);
    var plusOpenDate = new queryParam('plus开通时间-开始','plusOpenDate','dateTime','>=');
//    plusOpenDate.placeholder = 'plus开通开始时间';
    queryPage.addParam(plusOpenDate); 
    var plusOpenEndDate = new queryParam('plus开通时间-结束','plusOpenDate','dateTime','<=');
//    plusOpenEndDate.placeholder = 'plus开通结束时间';
    queryPage.addParam(plusOpenEndDate);  
    var validTimeStart = new queryParam('plus有效期时间-开始','validTime','dateTime','>=');
//    validTimeStart.placeholder = 'plus有效期开始时间';
    queryPage.addParam(validTimeStart);  
    var validTimeEnd = new queryParam('plus有效期时间-结束','validTime','dateTime','<=');
//    validTimeEnd.placeholder = 'plus有效期结束时间';
    queryPage.addParam(validTimeEnd);     
    var blacklist = new queryParam('是否拉黑', 'blacklist', 'string', '=', null, "dic.blacklist");
    blacklist.componentType = 'select';
    queryPage.addParam(blacklist);
    //新加的5个查询(20180326)
    var sex  = new queryParam('性别', 'sex', 'string', '=',null,"dic.sex");
    sex.componentType = 'select';
    queryPage.addParam(sex);
    var allScore_min = new queryParam('最小经验积分', 'allScore', 'long', '>='); 
    queryPage.addParam(allScore_min);
    var allScore_max = new queryParam('最大经验积分', 'allScore', 'long', '<='); 
    queryPage.addParam(allScore_max);
    var enableScore_min = new queryParam('最小可用积分', 'enableScore', 'long', '>=');
    queryPage.addParam(enableScore_min);
    var enableScore_max = new queryParam('最大可用积分', 'enableScore', 'long', '<=');
    queryPage.addParam(enableScore_max);
    queryPage.addParam(new queryParam('兑换订单', 'orderCount', 'long', '='));
    var  regist_start = new queryParam('注册开始时间-开始', 'createTime', 'dateTime', '>=');
    queryPage.addParam(regist_start);
    var  regist_end = new queryParam('注册结束时间-结束', 'createTime', 'dateTime', '<=');
    queryPage.addParam(regist_end);
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);

    //listService.init($scope);
    //定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('批量拉黑', 'blackListMany', '/phiMember/blackListMany', 'blackListData'));
    //btnArray.push(new pageButton('批量导出', 'export', '/phiMember/export', 'exportData'));

    btnArray.push(new pageButton('导出','export','/phiMember/export','exportData'));
    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'blackListData') {
             //listService.editData($scope, $scope.gridApi, new popup("修改", '/phiMember/edit'));
            this.blackListMany($scope.gridApi, '/phiMember/blackListMany', '1');
        } else if (operation == 'viewOrderData') {
            var tabItem = cloneObj(actionMap.get('/phiOrder/home'));
            tabItem.params = { "memberId": param.id };
            $scope.$parent.createTab(tabItem);
        }else if(operation=='exportData'){ /** 导出 **/
//    		excelService.init($scope);
    		excelService.exp("E180417163109678",queryPage);
        }
        /*else if(operation == 'editMemberData') {
           	   this.editMemberData($scope.gridApi, '/phiMember/editMemberAndCpouns');
           	}*/
    }

    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiMember/query', $scope.tableGrid);
    }
    load();

    /*查询需要调用的函数. */
    $scope.search = function() {
        load();
    };

    $scope.blackListMany = function(gridApi, toUrl, val) {
        if (gridApi.selection.getSelectedRows().length < 1) {
            /*          bootbox.alert("请至少勾选一条数据！");*/
            submitTips('请至少勾选一条数据！', 'warning');
            return false;
        }
        bootbox.confirm('确定要提交所选的数据吗?', function(result) {
            if (result) {
                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                    if (data.id == null || data.id == undefined) {
                        return;
                    }
                    if (data.blacklist == '1') {
                        submitTips('该用户已在黑名单里，不得重复操作！', 'warning');
                        return false;
                    }
                    var actionUrl = "api/phiMember/setBacklist/" + data.id + "/" + val;
                    httpService.post($scope, actionUrl).success(function() {
                        var postQueryPage = copyQueryPage($scope.queryPage);
                        if ($scope.notNeedQueryPage) {
                            postQueryPage.orderBy = '';
                            postQueryPage.queryParamList = [];
                        }
                        $scope.load();
                    });
                });
            }
        });
    }
load();


    /*  $scope.exportData = function(_scope, impl, param) {
            _scope.actionUrl = exportServerPath + impl;
            for (_scope.num = 0; _scope.num < _scope.queryFieldList.length; _scope.num++) {
                if (_scope.queryFieldList[_scope.num].value != null && _scope.queryFieldList[_scope.num].value != '') {
                    _scope.actionUrl += '&' + _scope.queryFieldList[_scope.num].field + '=' + _scope.queryFieldList[_scope.num].value;
                }
            }
            if (param) {
                _scope.actionUrl += param
            }
            /*window.location.href = _scope.actionUrl;
        };*/

}).filter("userNameFilter", function() { 
     var filterFunction = function(val) {
        if(!cnex4_isNotEmpty_str(val) || val == 'null'){
            return '';
        } 
        return val;
        
    };
    return filterFunction;
});

