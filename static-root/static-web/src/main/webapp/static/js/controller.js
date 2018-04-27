'use strict';
angular.module('huatek.controllers', [])
    .controller('MainController', ['$q',
        '$scope',
        '$rootScope',
        '$http',
        '$location',
        '$modal',
        'shareData',
        '$timeout',
        '$ocLazyLoad',
        '$compile',
        '$parse',
        'httpService',
        'listService',
        '$document',
        'cacheService',
        function($q,
            $scope,
            $rootScope,
            $http,
            $location,
            $modal,
            shareData,
            $timeout,
            $ocLazyLoad,
            $compile,
            $parse,
            httpService,
            listService,
            $document,
            cacheService) {
    		$rootScope.refreshToDoCount=function(){
	        	$http.get("api_workflow/myjob/toDoCount").success(function(response){
	        		$scope.toDoCount=response;
	        	});
	        }
	        $scope.showToDo=function(){
	        	$scope.createTab({ id: 25252663, url: "/workflow/taskToDo", title: "未办理", controller: "WorkflowToDoController", view: "static/templates/list/template_home.html" });
	        }
            /**登录跳转时根据时间戳获取登录用户数据*/
            if ($location.url()) {
                var response = null;
                if (localStorage) {
                    response = localStorage.getItem($location.url().replace(/\//g, ''));
                } else {
                    response = getCookie($location.url().replace(/\//g, ''));
                }
                if (response) {
                    response = JSON.parse(window.pwdString.decrypt(response));
                    if (message) {
                        message.show = false;
                    }
                    if (response.loginRequest) {
                        $rootScope.authenticated = false;
                        return;
                    }
                    /*加载系统*/
                    $rootScope.systemdatas = response.systemdata;
                    /**重新登录先清空菜单*/
                    $rootScope.authenticated = true;
                    $rootScope.name = response.showName;
                    /**当前登录用户其他信息*/
                    $rootScope.acctId = response.acctId;
                    $rootScope.acctName = response.acctName;
                    $rootScope.userName = response.userName;
                    $rootScope.deptName = response.deptName;
                    $rootScope.deptId = response.deptId;
                    $rootScope.orgName = response.orgName;
                    $rootScope.orgShortName = response.orgShortName;
                    $rootScope.orgId = response.orgId;
                    $rootScope.orgCode = response.orgCode;
                    $rootScope.orgType = response.orgType;
                    $rootScope.groupId = response.groupId;
                    $rootScope.groupName = response.groupName;
                    $rootScope.tenantId = response.tenantId;
                    $rootScope.currProId = response.currProId;
                    $rootScope.currProName = response.currProName;
                    $rootScope.IDNumber = response.IDNumber;
                    $rootScope.clientIp = response.clientIp;
                    /*用户标段信息*/
                    /*$rootScope.userTenders = response.userTenders;*/
                    loadAllUserTenders($http, $rootScope);
                    /*用户集团信息*/
                    $rootScope.userCompList = response.userCompList;
                    if (response.orgType == 5) {
                        $rootScope.parentOrgCode1 = response.parentOrgCode;
                        $rootScope.orgCode1 = response.orgCode;
                        $("#officeCode").show();
                    } else {
                        $rootScope.parentOrgCode1 = response.orgCode;
                    }
                    var indexHead = angular.element($document[0].querySelector('#loginIndex'));

                    indexHead.show();
                    /*加载body首页内容*/
                    loadAllMenu($scope,$http, $rootScope);
                    /*加载用户项目数据*/
                    getProData($http, $rootScope);
                    loadUserOrg($http, $rootScope);

                    /*submitTips('您的登录ip是:'+$rootScope.clientIp+",为了确保操作安全，退出时请及时点注销按钮",'info');*/
                    var message_refresh_interval = cacheService.getData("config.message_refresh_interval");
                    if (!message_refresh_interval) {
                        message_refresh_interval = 10;
                    }
                    $rootScope.queryMessageCountTimer = setInterval($rootScope.queryMessageFunction,message_refresh_interval * 1000);
                    $rootScope.refreshToDoCount();
                    $rootScope.$broadcast('event:loginConfirmed');
                } else {
                    window.location.href = "login.html";
                }
            } else {
                window.location.href = "login.html";
            }

            $scope.remainingSecond = 0;
            $scope.intervalRemainingSecondCounter = null;
            $scope.remainingSecondCounter = function() {
                if ($scope.remainingSecond > 0) {
                    $scope.remainingSecond = $scope.remainingSecond - 1;
                } else {
                    $scope.canClickLongin = false;
                    window.clearInterval($scope.intervalRemainingSecondCounter);
                }
            };

            $scope.activeWhen = function(value) {
                return value ? 'active' : '';
            };

            $scope.path = function() {
                return $location.url();
            };

            $scope.changeTime = function() {
                $.post('common/baseSysdate/save', { "sysDate": $scope.changeSysdate }).success(function(response) {
                    bootbox.alert(response.text);
                })
            }
            /*post数据转换方法*/
            var transFn = function(data) {
                return $.param(data);
            };
            /*post请求头*/
            var postCfg = {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
                transformRequest: transFn
            };
            /*登出*/
            $scope.logout = function() {
                $http.post('logout', { id: 1234, q: 1234566 }, postCfg).success(function() {
                    $rootScope.user = null;
                    $scope.username = $scope.password = $scope.verifyCode = null;
                    $scope.$emit('event:logoutRequest');
                    window.clearInterval($rootScope.queryMessageCountTimer);
                });

            };
            /*回车登录*/
            $(document).keypress(function(e) {
                if (e.which == 13 && !$rootScope.authenticated && !$scope.isLoginEnable) {
                    $scope.login();
                }
            });
            /**
             * { 项目切换 }
             * @author     (cloud_liu)
             * @param      {<type>}  id      The identifier
             */
            $scope.switchPro = function(id) {
                var data = [{ "id": id }];
                $scope.promise = $http.get('/api/org/switch/' + id).success(function(response) {
                    $rootScope.currProId = response.currProId;
                    $rootScope.currProName = response.currProName;
                    /*重新加载当前用户组织数据*/
                    $scope.promise = $http.get('api/org/getCurrentAndSubOrg').success(function(res) {
                        $rootScope.reportUserOrg = buildTree(res, 1, "parentId", "id");
                        $rootScope.$currentOrg = res;
                    });
                    $scope.promise = $http.get("/api/org/getAllUserTendsers").success(function(data) {
                        $rootScope.userTenders = data.userTenders;
                        if ($scope.tabMap.size() > 1) {
                            var cloneMap = new Map();
                            $scope.tabMap.each(function(key, value, index) {
                                if (key != 'home') {
                                    cloneMap.put(key, value);
                                }
                            });
                            cloneMap.each(function(key, value, index) {
                                $scope.deleteTab(value);
                            });
                        }
                    });
                });
            };

            /**
             * [打开所选择常用功能]
             *
             * @param      {<type>}  source  The source
             */
            $scope.openFavourite = function(source, $event) {
                $scope.createTab(source, $event);
            }

            /**
             * [添加常用功能, 添加后重新加载用户常用功能]
             */
            $scope.addFavourite = function($event) {
                /*$scope.createTab(actionMap.get("/favourite/add"), $event);*/
                var jsPanelKey = uuid(10);
                $ocLazyLoad.load(controllerMap.get('FwFavouriteController') + "?version=" + huatek.version).then(function() {
                    controllerProvider.register(jsPanelKey, function($scope, $controller) {
                        $controller('FwFavouriteController', { $scope: $scope });
                    });

                    var modal = "<div   id='" + jsPanelKey + "' ng-controller='FwFavouriteController'> <div class='posi-rela' cg-busy={promise:promise,templateUrl:'static/templates/busy_template.html',message:'请稍候.....',backdrop:true,delay:300,minDuration:700,wrapperClass:'cg-busy'}>";
                    var sourceView = "static/business/system/myFavourite/templates/favouriteSource.html";
                    $http.get((sourceView ? sourceView : TEMPLATE_NOT_FOUND_PATH) + "?version=" + huatek.version).success(function(res) {
                        modal += res;
                        modal += "</div></div>";
                        var jsPanel = $.jsPanel({
                            id: urlToString('/'),
                            selector: '.content',
                            headerTitle: '添加常用功能',
                            contentSize: { width: 800, height: 500 },
                            theme: 'dark',
                            position: 'center',
                            content: $compile(modal)($scope),
                            paneltype: false,
                            draggable: {
                                containment: "parent"
                            },
                            onclosed: function() {}
                        });
                        $scope.jsPanel = jsPanel;
                    }).error(function() {
                        console.log("加载模板失败,模版路径为：" + source.view);
                    });
                });
            }

            /**
             * 修改密码
             *
             * @param      {<type>}  $event  The event
             */
            $scope.changePassWord = function($event) {
                /*$scope.createTab(actionMap.get("/favourite/add"), $event);*/
                var jsPanelKey = uuid(10);
                $ocLazyLoad.load(controllerMap.get('ChangePasswordController') + "?version=" + huatek.version).then(function() {
                    controllerProvider.register(jsPanelKey, function($scope, $controller) {
                        $controller('ChangePasswordController', { $scope: $scope });
                    });

                    var modal = "<div id='" + jsPanelKey + "' ng-controller='ChangePasswordController'><div class='posi-rela' cg-busy={promise:promise,templateUrl:'static/templates/busy_template.html',message:'请稍候.....',backdrop:true,delay:300,minDuration:700,wrapperClass:'cg-busy'}>";
                    var sourceView = "/static/business/system/user/templates/editPassword.html";
                    $http.get((sourceView ? sourceView : TEMPLATE_NOT_FOUND_PATH) + "?version=" + huatek.version).success(function(res) {
                        modal += res;
                        modal += "</div></div>";
                        var jsPanel = $.jsPanel({
                            id: urlToString('/'),
                            selector: '.content',
                            headerTitle: '修改密码',
                            contentSize: { width: 800, height: 500 },
                            theme: 'dark',
                            position: 'center',
                            content: $compile(modal)($scope),
                            paneltype: false,
                            draggable: {
                                containment: "parent"
                            },
                            onclosed: function() {}
                        });
                        $scope.jsPanel = jsPanel;
                    }).error(function() {
                        console.log("加载模板失败,模版路径为：" + source.view);
                    });
                });
            }

            /**
             * @author lis
             * 增加tab页处理代码
             */
            /*tab的数量*/
            /*tabMap对象，用于存放tab对象*/
            $scope.tabMap = new Map();
            /*冗余存储tab对象，以便于遍历和其他操作*/
            var tabArr = [];
            /*弹出modal对象数组*/
            /*添加默认首页*/
            $scope.tabMap.put('home', { title: '首页', mapKey: 'home', active: 'active', panelShow: true, panels: [] });
            tabArr.push({ title: '首页', active: 'active', panelShow: true, mapKey: "home" });
            /*创建tab对象*/
            $scope.createTab = function(item, $event) {
                $(".nav-list li a").removeClass("active");
                if ($event) {
                    $($event.target).addClass("active");
                }

                if (!item.items || item.items.length <= 0) {
                    if (!$scope.tabMap.get(urlToString(item.url))) {
                        if ($scope.tabMap.size() >= 10) {
                            submitTips('警告：当前同时打开的Tab个数超过上限（10），请关闭部分Tab页后再打开。', 'warning');
                            return;
                        } else {
                            /**如果不存在，则说明是新开的tab页*/
                            var tab = new tabObject(item.title, item.controller, item.view, urlToString(item.url));
                            $scope.tabMap.put(tab.mapKey, tab);
                            createTabHeader(tab);
                            createTabPanel(tab, item);
                            $scope.selectTab(tab);
                            tabArr.push(tab);
                        }
                    } else {
                        /**如果存在，则说明已经打开过，不需要再打开*/
                        $scope.selectTab($scope.tabMap.get(urlToString(item.url)));
                    }
                }
            };
            /*删除tab对象*/
            var removeTab = function(tab) {
                $scope.tabMap.remove(tab.mapKey);
                var arrIdex = 0;
                angular.forEach(tabArr, function(value, key) {
                    if (value.mapKey == tab.mapKey) {
                        arrIdex = key;
                        return;
                    }
                });
                selectTab(tabArr[arrIdex - 1]);
                tabArr.splice(arrIdex, 1);
            };
            /*创建tab头*/
            var createTabHeader = function(tab) {
                var headerTemplate = "<li id='" + tab.mapKey + "' role='presentation' class={{tabMap.get(" + "'" + tab.mapKey + "'" + ").active}} ng-click=selectTab(tabMap.get(" + "'" + tab.mapKey + "'" + "))><a>" + tab.title + "<img style='cursor:pointer' ng-click=deleteTab(tabMap.get(" + "'" + tab.mapKey + "'" + ")) src='static/images/tab-close.png'></img></a></li>";
                tab.header = $compile(headerTemplate)($scope);
                angular.element('#headerDiv').append(tab.header);
            };
            /*删除tab头*/
            var removeTabHeader = function(tab) {
                tab.header.remove();
            };
            /*创建tab对应的panel对象*/
            var createTabPanel = function(tab, item) {
                var jsPanelKey = uuid(10);
                if (controllerMap.get(tab.controller)) {
                    $ocLazyLoad.load(controllerMap.get(tab.controller) + "?version=" + huatek.version).then(function() {
                        controllerProvider.register(jsPanelKey, function($scope, $controller) {
                            $scope.passParams = item.params;
                            $scope.menuId = item.id;
                            $controller(tab.controller, { $scope: $scope });
                        });
                        var panelTemplate = "<div ng-controller='" + jsPanelKey + "' ng-show=tabMap.get(" + "'" + tab.mapKey + "'" + ").panelShow class='panel-body'><div cg-busy='{promise:promise,templateUrl:promiseObj.templateUrl,message:promiseObj.message,backdrop:promiseObj.backdrop,delay:promiseObj.delay,minDuration:promiseObj.minDuration}'>";
                        $rootScope.menuId = item.id;
                        $http.get(templateBasePath + tab.templateUrl + "?version=" + huatek.version).success(function(response) {
                            panelTemplate += response
                            panelTemplate += "</div></div>";
                            tab.panel = $compile(panelTemplate)($scope);
                            angular.element('#panelDiv').append(tab.panel);
                            var newScope = $('div[ng-controller="' + jsPanelKey + '"]').scope();
                            newScope.tab = tab;
                        }).error(function() {
                            console.log("加载模板失败,模版路径为：" + templateBasePath + tab.templateUrl);
                            var panelTemplate = "<div ng-controller='404Controller' ng-show=tabMap.get(" + "'" + tab.mapKey + "'" + ").panelShow class='panel-body'>";
                            $http.get(TEMPLATE_NOT_FOUND_PATH).success(function(response) {
                                panelTemplate += response
                                panelTemplate += "</div>";
                                tab.panel = $compile(panelTemplate)($scope);
                                angular.element('#panelDiv').append(tab.panel);
                                var newScope = $('div[ng-controller="404Controller"]').scope();
                                newScope.tab = tab;
                                newScope.menuId = item.id;
                            });
                        });
                    });
                } else {
                    console.log("该controller未配置对应的文件路径！")
                }
            };
            /*删除tab对应的panel对象*/
            var removeTabPanel = function(tab) {
                var scope = $('div[ng-controller="' + tab.mapKey + '"]').scope();
                if (scope) {
                    scope.$destroy();
                }
                tab.panel.remove();
            };
            /*删除弹出的modal*/
            var removeModal = function(tab) {
                if (tab.panels.length > 0) {
                    for (var i = 0; i < tab.panels.length; i++) {
                        tab.panels[i].close();
                    }
                    tab.panels = [];
                }
            };
            /*删除tab对象以及所属当前tab的panel和modal*/
            $scope.deleteTab = function(tab) {
                /*删除header*/
                removeTabHeader(tab);
                /*删除tab对应的panel对象*/
                removeTabPanel(tab);
                /*删除panel中弹出的modal对象*/
                removeModal(tab);
                /*删除tab对象*/
                removeTab(tab);
            };

            /*tab选中事件*/
            var selectTab = $scope.selectTab = function(tab) {
                $scope.tabMap.each(function(k, v) {
                    if (k == tab.mapKey) {
                        v.active = 'active';
                        v.panelShow = true;
                    } else {
                        if (v.active == 'active' && v.panels.length > 0) {
                            for (var i = 0; i < v.panels.length; i++) {
                                v.panels[i].minimize();
                            }
                        }
                        v.active = '';
                        v.panelShow = false;
                    }
                });
            };
            $scope.showMessage = function() {
                $scope.createTab({ id: 25252101241, url: "/oaMsgInfo/home", title: "系统消息", controller: "OaMsgInfoController", view: "static/templates/list/template_home.html" });
            }
            

            $scope.toTrain = function() {
                window.open('train.html', '_blank');
            }
        }
    ])
    .controller('404Controller', function() {

    })
    .directive('tabHighlight', [function() {
        return {
            restrict: 'A',
            link: function(scope, element) {
                var x, y, initial_background = '#c3d5e6';

                element
                    .removeAttr('style')
                    .mousemove(function(e) {
                        if (!element.hasClass('active')) {
                            x = e.pageX - this.offsetLeft;
                            y = e.pageY - this.offsetTop;

                            element
                                .css({ background: '-moz-radial-gradient(circle at ' + x + 'px ' + y + 'px, rgba(255,255,255,0.4) 0px, rgba(255,255,255,0.0) 45px), ' + initial_background })
                                .css({ background: '-webkit-radial-gradient(circle at ' + x + 'px ' + y + 'px, rgba(255,255,255,0.4) 0px, rgba(255,255,255,0.0) 45px), ' + initial_background })
                                .css({ background: 'radial-gradient(circle at ' + x + 'px ' + y + 'px, rgba(255,255,255,0.4) 0px, rgba(255,255,255,0.0) 45px), ' + initial_background });
                        }
                    })
                    .mouseout(function() {
                        element.removeAttr('style');
                    });
            }
        };
    }])
    .directive('draggable', ['$document', function($document) {
        return function(scope, element, attr) {
            var startX = 0,
                startY = 0,
                x = 0,
                y = 0;
            element = angular.element(document.getElementsByClassName("modal"));
            element.css({
                position: 'relative',
                cursor: 'move'
            });

            element.on('mousedown', function(event) {
                event.preventDefault();
                startX = event.pageX - x;
                startY = event.pageY - y;
                $document.on('mousemove', mousemove);
                $document.on('mouseup', mouseup);
            });

            function mousemove(event) {
                y = event.pageY - startY;
                x = event.pageX - startX;
                element.css({
                    top: y + 'px',
                    left: x + 'px'
                });
            }

            function mouseup() {
                $document.off('mousemove', mousemove);
                $document.off('mouseup', mouseup);
            }
        };
    }])
    .directive('cenx4Workflow', function() {
        return {
            require: '?ngModel',
            restrict: 'E',
            scope: { ngModel: "=", processInstanceId: "=" },
            controller: 'processProgressController',
            templateUrl: 'static/business/workflow/process/templates/template_processProgress.html' + '?t=' + huatek.version,
            replace: true,
            link: function(scope, el, attrs, ngModel) {
                scope.canSetPerson = false;
            }
        };
    });;