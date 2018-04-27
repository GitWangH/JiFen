var
    httpHeaders,
    message,
    as = angular.module('HuatekApp', ['ngRoute',
        'ngAnimate',
        'ngResource',
        'ngAnimate',
        'ngCookies',
        'ngStorage',
        'ngMessages',
        'ui.bootstrap',
        'huatek.services',
        'huatek.controllers',
        'huatek.filters',
        'huatek.directives',
        'ngTouch',
        'ui.grid',
        'ui.grid.resizeColumns',
        'ui.grid.selection',
        'ui.grid.pinning',
        'ui.grid.pagination',
        'ui.grid.edit',
        'ui.grid.expandable',
        'ui.grid.grouping',
        'ui.grid.treeView',
        'ui.grid.cellNav',
        'ui.grid.autoResize',
        'ui.grid.exporter',
        'cgBusy',
        'oc.lazyLoad',
        'ui.grid.validate',
        'ui.grid.moveColumns',
        'nya.bootstrap.select',
        'ngDialog',
        'mgcrea.ngStrap',
        'blueimp.fileupload',
        'huatek.select2',
        /*'huatek.cnex4.dataPicker',*/
        'huatek.directives',
        /*'textAngular',*/
        'selectAddress',
        'oi.select',
        'hljs',
        'gettext',
        'purplefox.numeric',
        'multi-select-tree',
        'ng-htmlCompiler'
    ]);
var routeProviderRef;
as.config(['$routeProvider',
        '$httpProvider',
        '$modalProvider',
        '$asideProvider',
        '$datepickerProvider',
        '$controllerProvider',
        function($routeProvider,
            $httpProvider,
            $modalProvider,
            $asideProvider,
            $datepickerProvider,
            $controllerProvider) {
            angular.extend($datepickerProvider.defaults, {
                placement: 'bottom-left'
            });
            angular.extend($modalProvider.defaults, {
                html: true
            });
            angular.extend($asideProvider.defaults, {
                container: 'body',
                html: true
            });
            controllerProvider = $controllerProvider;
            routeProviderRef = $routeProvider;

            $httpProvider.interceptors.push(function($rootScope, $q) {
                return {
                    'request': function(config) {
                        return config || $q.when(config);
                    },
                    'response': function(response) {
                        if (cnex4_isEmpty_str(response.data.text)) {
                            submitTips(response.data.text, 'success');
                        }
                        return response || $q.when(response);
                    },
                    'responseError': function(response) {
                        if (cnex4_isEmpty_str(response.config.url) && 'login' == response.config.url) {
                            $rootScope.setMessage({ data: { text: response.data.text, type: response.data.type, show: true } });
                            /*changeImg();*/
                        } else if (undefined != response.data && undefined != response.data.text) {
                            console.warn("错误提示:%s ,异常信息: %s", response.data.text, response.data.exception);
                            submitTips(response.data.text, 'error');
                        }
                        if (response.status === 401) {
                            var deferred = $q.defer(),
                                req = {
                                    config: response.config,
                                    deferred: deferred
                                };
                            window.clearInterval($rootScope.queryMessageCountTimer);

                            $rootScope.$broadcast('event:loginRequired');
                            return deferred.promise;
                        }
                        return $q.reject(response);
                    }
                };
            });
            httpHeaders = $httpProvider.defaults.headers;
        }
    ])
    .run(['$rootScope', '$http', '$route', '$location', 'base64', '$document', '$ocLazyLoad', '$timeout', 'cacheService', '$document', function($rootScope, $http, $route, $location, base64, $document, $ocLazyLoad, $timeout, cacheService, $document) {

        $rootScope.setMessage = function(response) {
            if (response.data.text && response.data.type) {
                message = {
                    text: response.data.text,
                    type: response.data.type,
                    show: true
                };
            }
        };

        $rootScope.message = function() {
            return message;
        };
        /**
         * Holds all the requests which failed due to 401 response.
         */
        $rootScope.requests401 = [];
        var transFn = function(data) {
                return $.param(data);
            },

            postCfg = {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
                transformRequest: transFn
            };
        $rootScope.$on('event:loginRequired', function() {
            $http.post('logout', { id: 1234, q: 1234566 }, postCfg).success(function() {
                /*changeImg();*/
            });
            $rootScope.authenticated = false;
            $location.path('/login');
        });

        /**
         * On 'event:loginRequest' send credentials to the server.
         */

        /*$rootScope.$on('event:loginRequest', function(event, username, password, verifyCode) {
            var user = new Object();
            user.acctName = username;
            user.acctPwd = base64.encode(password);
            user.verifyCode = verifyCode;
            $http.post('login', user)
                .success(function(response) {
                    if (message) {
                        message.show = false;
                    }
                    if (response.loginRequest) {
                        $rootScope.authenticated = false;
                        return;
                    }
                    $rootScope.systemdatas = response.systemdata;
                    $rootScope.authenticated = true;
                    $rootScope.name = response.showName;
                    $rootScope.acctId = response.acctId;
                    $rootScope.acctName = response.acctName;
                    $rootScope.userName = response.userName;
                    $rootScope.deptName = response.deptName;
                    $rootScope.deptId = response.deptId;
                    $rootScope.orgName = response.orgName;
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
                    loadAllUserTenders($http, $rootScope);
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
                    loadAllMenu($http, $rootScope);
                    getProData($http, $rootScope);
                    loadUserOrg($http, $rootScope);

                    var message_refresh_interval = cacheService.getData("config.message_refresh_interval");
                    if (!message_refresh_interval) {
                        message_refresh_interval = 10;
                    }

                    $rootScope.queryMessageCountTimer = setInterval(message_refresh_interval * 1000);
                    $rootScope.$broadcast('event:loginConfirmed');
                }).error(function(data) {
                    console.log('login failed...@' + data);
                });
        });*/


        


        /**
         * On 'logoutRequest' invoke logout on the server and broadcast 'event:loginRequired'.
         */
        $rootScope.$on('event:logoutRequest', function() {
            $rootScope.authenticated = false;
            delete $rootScope.name;
            delete httpHeaders.common['Authorization'];
            window.location.href = "./";

        });
        var routesOpenToPublic = [];
        angular.forEach($route.routes, function(route, path) {
            route.publicAccess && (routesOpenToPublic.push(path));
        });
        $rootScope.$on('$routeChangeStart', function(event, nextLoc, currentLoc) {
            previousLoc = currentLoc;
            var closedToPublic = (-1 === routesOpenToPublic.indexOf($location.path()));
            if (closedToPublic && !$rootScope.authenticated) {
                $rootScope.$broadcast('event:loginRequired');
            } else if (!!$rootScope.authenticated) {
                if (!!nextLoc && !!nextLoc.originalPath) {
                    var path = nextLoc.originalPath;
                    if (path.indexOf(':') > -1) {
                        path = path.substring(0, path.indexOf(':') - 1);
                    }
                    var sourceModule = actionMap.get(path);
                    if (sourceModule != null && sourceModule.id) {
                        var menuIdPath = sourceModule.id;
                        var currentModel = sourceModule;
                        var count = 0;
                        while (count < 5 && currentModel.parentid > 0 && (currentModel = menuMap.get(currentModel.parentid)) != null) {
                            menuIdPath = currentModel.id + '/' + menuIdPath;
                            count++;
                        }
                        document.cookie = "menuIds=" + menuIdPath + ";path=/";

                    }
                }
            }
        });

        /***
         * 日期选择
         */
        $rootScope.today = function() {
            $rootScope.dt = new Date();
        };
        $rootScope.today();
        $rootScope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1,
            showWeeks: false,
            formatDayTitle: 'yyyy MMMM'
        };
        $rootScope.formats = ['yyyy-MM-dd', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $rootScope.open = function() {
            $rootScope.popup.opened = true;
        };
        $rootScope.popup = {
            opened: false
        };

        /**
         * 得到传入时间前一天的时间
         * @param strDate
         */
        $rootScope.getOneDayBefore = function(strDate) {
            if (undefined != strDate && null != strDate && '' != strDate && strDate.split("-").length == 3) {
                var today = stringToDate(strDate);
                var yesterday_milliseconds = today.getTime() - 1000 * 60 * 60 * 24;
                var yesterday = new Date();
                yesterday.setTime(yesterday_milliseconds);
                var strYear = yesterday.getFullYear();
                var strDay = yesterday.getDate();
                var strMonth = yesterday.getMonth() + 1;
                if (strMonth < 10) {
                    strMonth = "0" + strMonth;
                }
                return strYear + "-" + strMonth + "-" + strDay;
            }
            return null;
        }

        /*设置点击后按钮禁止*/
        $rootScope.disableBtnForSecond = function($event) {
            if ($event.target.disabled == false) {
                $event.target.disabled = true;
                $timeout(function() {
                    $event.target.disabled = false;
                }, $rootScope.disabled_wait_time);
            }
        };
        /**
         * 得到传入时间后一天的时间
         * @param strDate
         */
        $rootScope.getOneDayAfter = function(strDate) {
            if (undefined != strDate && null != strDate && '' != strDate && strDate.split("-").length == 3) {
                var today = stringToDate(strDate);
                var tomorrow_milliseconds = today.getTime() + 1000 * 60 * 60 * 24;
                var tomorrow = new Date();
                tomorrow.setTime(tomorrow_milliseconds);
                var strYear = tomorrow.getFullYear();
                var strDay = tomorrow.getDate();
                var strMonth = tomorrow.getMonth() + 1;
                if (strMonth < 10) {
                    strMonth = "0" + strMonth;
                }
                return strYear + "-" + strMonth + "-" + strDay;
            }
            return null;
        }

        $rootScope.operatorItems = [{ name: "等于", id: "=" }, { name: "大于", id: ">" }, { name: "小于", id: "<" }, { name: "大于等于", id: ">=" }, { name: "小于等于", id: "<=" }];
        /**
         * 消息提醒
         * @return {[type]} [description]
         * @author lis 2017-11-25
         */
        $rootScope.queryMessageFunction = function() {
            $http.get(URL_PATH.OA_HEADER + "/oaMsgInfo/count").success(function(response) {
                if ($rootScope.messageCount == undefined) {
                    if (response > 0) {
                        /*new Pop("未读消息提醒", "#/oaMsgInfo/home", "您有" + response + "条消息未读！");*/
                    }
                } else
                if (response > $rootScope.messageCount) {
                    $http.get(URL_PATH.OA_HEADER + "/oaMsgInfo/lastMessage").success(function(msg) {
                        if (msg) {
                            var path = msg.linkUrl;
                            if (path == null || path.trim() == "" || path.trim() == "null") {
                                /*new Pop(msg.msgTitle, "#/oaMsgInfo/home", msg.msgContent);*/
                            } else {
                                /*new Pop(msg.msgTitle, "#/oaMsgInfo/home", msg.msgContent, "#" + path);*/
                            }
                        }

                    });
                }
                $rootScope.messageCount = response;
            });
            var session_nodo_timeout = cacheService.getData("config.session_nodo_timeout");
            if (!session_nodo_timeout) {
                session_nodo_timeout = 1800;
            }
            if (new Date().getTime() - $rootScope.lastActiveTime > (session_nodo_timeout * 1000)) {
                localStorage.setItem("isAutoLogout", 'true');
                localStorage.setItem("autoLogoutMesg", '您长时间未操作，系统自动注销，请重新登录');
                $scope.logout();
            }
            /*保持session ，方面做在线人数统计*/
            $http.get("api_task/session/keeper").success(function(response) {
                if (response == 'true') {
                    localStorage.setItem("isAutoLogout", 'true');
                    localStorage.setItem("autoLogoutMesg", '系统正在维护，自动注销，请稍后重新登录');
                    $scope.logout();
                }
            });
        };
        /**
         * 监控系统操作
         * @type {Date}
         */
        $rootScope.lastActiveTime = new Date().getTime();
        $rootScope.onMouseMove = function() {
            $rootScope.lastActiveTime = new Date().getTime();
        }
        $document.on("click", $rootScope.onMouseMove);
        $document.on("keydown", $rootScope.onMouseMove);
        $document.on("mousemove", $rootScope.onMouseMove);
        $document.on("mousewheel", $rootScope.onMouseMove);

        if (localStorage.getItem("isAutoLogout") && localStorage.getItem("isAutoLogout") == 'true') {
            $rootScope.setMessage({ data: { text: localStorage.getItem("autoLogoutMesg"), type: 'danger', show: true } });
            localStorage.setItem("isAutoLogout", 'false')
        }

        /**
         * @ param useCase 使用场景，值分别为query和form，为query的时候只返回name，为form的时候返回整个对象
         */
        $rootScope.getLocation = function(val, queryUrl, field, useCase, queryParam) {
            var queryParams = null;
            if (queryParam != undefined && queryParam != null) {
                queryParams = { name: val, queryParam: queryParam, sensor: false };
            } else {
                queryParams = { name: val, sensor: false };
            }
            return $http.get(queryUrl, {
                params: queryParams
            }).then(function(response) {
                if (response.data == "") {
                    return false;
                }
                if (field == null || field == undefined) {
                    return response.data.map(function(item) {
                        return item.name;
                    });
                } else {
                    /*名称映射，例如从field映射成_name,一便前端模板通过_name来取值，保持模板的一致性*/
                    angular.forEach(response.data, function(data, index, array) {
                        data._name = data[field];
                    });
                    if (useCase == 'form') {
                        return response.data;
                    } else if (useCase == 'query') {
                        return response.data.map(function(item) {
                            return item['_name'];
                        });
                    }
                }
            });
        };

        $rootScope.getLocationExtend = function(val, fieldObj) {
            fieldObj._choosed = false;
            var queryParams = null;
            var returnObj = null;
            if (fieldObj.queryParam != undefined && fieldObj.queryParam) {
                queryParams = { name: val, queryParam: fieldObj.queryParam, sensor: false };
            } else {
                queryParams = { name: val, sensor: false };
            }
            return $http.get(fieldObj.dropDataUrl, {
                params: queryParams
            }).then(function(response) {
                returnObj = response.data;
                if (response.data == "") {
                    if (angular.isFunction(fieldObj.operator)) {
                        fieldObj.operator();
                    }
                    if (fieldObj.model != 'autocomplete-fill-or-select' && fieldObj.ObjectType != 'queryParam') {
                        fieldObj.value = '';
                    }
                    return false;
                }
                /**以上方法由于不同步，会有bug，改为同步的方式*/
                var showField = null,
                    returnField = null;
                if (fieldObj.showField != null) {
                    showField = fieldObj.showField;
                    if (fieldObj.returnField == null) {
                        returnField = fieldObj.showField;
                    } else {
                        returnField = fieldObj.returnField;
                    }
                } else {
                    showField = fieldObj.showName;
                    if (fieldObj.returnValue == 'code') {
                        returnField = fieldObj.showName;
                    } else {
                        returnField = fieldObj.returnValue;
                    }
                }
                angular.forEach(response.data, function(data, index, array) {
                    data._returnField = data[returnField];
                    data._showField = data[showField];
                });
            }).then(function() {
                return returnObj;
            });
        };

        $rootScope.querySelectFn = function(items, query) {
            return findOptions(query);
        };



        /**
         * 根据本地结果集检索符合条件的记录
         */
        $rootScope.getNativeLocation = function(viewValue, queryField) {
            var returnArr = [];
            angular.forEach(queryField.itmes, function(item) {
                if (trimRight(trimLeft(viewValue)).indexOf(item._returnField) >= 0 && returnArr.length <= 20) {
                    returnArr.push(item);
                }
            });
            return returnArr;
        }

        /**判断对象的类型**/
        $rootScope.cnex_type_of = function(obj) {
            return typeof obj;
        }

        /**下拉搜索组件的最大显示条数**/
        $rootScope.cnex4_limit_quantity = 20;

        /**模糊检索的输入等待时间**/
        $rootScope.autocomplete_wait_time = 500;

        $rootScope.disabled_wait_time = 3000;

        $rootScope.fillDataBack = function(field) {
            if (cnex4_isEmpty_str(field)) {
                var itemsMap = getMap(field.items, '_returnField');
                field.value = itemsMap.get(field.value);
            }
        }

        $rootScope.chooseSelect = function(formField) {
            formField._choosed = true;
        }

        $rootScope.checkAutoCompleteEvent = function(formField) {
            if (!formField._choosed) {
                formField.value = '';
            }
        }

        /***
         * 查询对应url返回的ParamDto列表数据.
         * 用于选择使用.
         */
        $rootScope.getSearchItems = function(val, fieldObj) {
            var queryParams = null;
            var returnObj = null;
            queryParams = { name: val };
            return $http.post(fieldObj.dropDataUrl, queryParams).then(function(response) {
                returnObj = response.data;
                if (response.data == "") {
                    fieldObj.value = '';
                    fieldObj.displayValue = '';
                    return false;
                }
            }).then(function() {
                return returnObj;
            });
        };
        /***
         *@paramDto 是一个只包含name和code属性的对象.
         *@fieldObj 表单字段输入域.
         *该方法可以扩展到可以翻页查询，目前暂时不实现.
         */
        $rootScope.onSelectQueryItem = function(paramDto, fieldObj) {
            if (fieldObj != null && fieldObj != undefined) {
                if (fieldObj.returnField == 'id') {
                    fieldObj.value = paramDto.id;
                } else {
                    fieldObj.value = paramDto.code;
                }
                if (fieldObj.showField == 'name code') {
                    fieldObj.displayValue = paramDto.name + "(" + paramDto.code + ")";
                } else {
                    fieldObj.displayValue = paramDto.name;
                }
            }
        };

    }]);

var getReportServerList = function($http, $rootScope) {
    $http.get(URL_PATH.CMD_HEADER + "/reportServer/queryList").success(function(response) {
        var serverList = [];
        angular.forEach(response, function(data) {
            serverList.push(data.serverAddress + ":" + data.point);
        });
        $rootScope.reportServerList = serverList.concat(serverList);
    });
}
/**
 * 构造菜单的source_path的map用于判断是否显示tab页
 */
var getSourcePathMap = function(sourcePathData, $rootScope) {
    var map = new Map();
    angular.forEach(sourcePathData, function(data) {
        if (!map.get(data.url)) {
            map.put(data.url, data);
        }
    });
    $rootScope.sourcePathMap = map;
}



function loadContent() {
    /*window.location.href=document.getElementById("contentBtn").href;*/
}
var compare = function(prop) {
    return function(obj1, obj2) {
        var val1 = obj1[prop];
        var val2 = obj2[prop];
        if (val1 < val2) {
            return -1;
        } else if (val1 > val2) {
            return 1;
        } else {
            return 0;
        }
    }
}

/**load data of all menu*/
function loadAllMenu($scope,$http, $rootScope) {
    $scope.promise = $http.get('api/menu/public/loadAllUserSource').success(function(response) {
        data = response;
        var menu = [];
        getSourcePathMap(response.data, $rootScope);
        for (i = 0; i < data.length; i++) {

            var item = data[i];
            var label = item.label;
            var title = item.title;
            var url = item.url;
            var isMenu = item.isMenu;
            var isShow = item.isShow;
            var level = item.level;
            var view = item.view;
            var controller = item.controller;
            var cssClass;
            if (item.cssClass != null) {
                cssClass = item.cssClass;
            }
            var parentid = item.parentid;
            var id = item.id;
            var path = item.path;
            if (!actionMap) {
                actionMap = new Map();
            }
            actionMap.put(url, item);

        }
        var menus = [];
        for (var i = 0; i < data.length; i++) {
            if (data[i].isMenu < 0) {
                continue;
            }
            if (!data[i].parentId && data[i].isShow == 1) {
                menus.push(data[i])
            }
        }
        var parents = menus;
        var childrens = [];
        while (parents.length > 0) {
            for (var j = 0; j < parents.length; j++) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].isMenu < 0) {
                        continue;
                    }
                    if (data[i].parentId == parents[j].id && data[i].isShow == 1) {
                        if (parents[j].items) {
                            parents[j].items.push(data[i]);
                        } else {
                            parents[j].items = [data[i]];
                        }
                        childrens.push(data[i]);
                    }
                }
            }
            parents = childrens;
            childrens = [];
        }
        $rootScope.userMenus = menus;
        /*是否显示添加常用功能*/
        $rootScope.canAddFavourite = actionMap.get("/favourite/add") != null;
        $rootScope.canChangePass = actionMap.get("/password/changePass") != null;
        /*获取用户常用功能*/
        getFavouriteSource($http, $rootScope);
    }).error(function(data) {});
}

/**
 * Gets the organization data.
 * description { 获取用户项目数据 }
 * @param      {<type>}  $http       The http
 * @param      {<type>}  $rootScope  The root scope
 */
function getProData($http, $rootScope) {
    $http.get("/api/org/getProData").success(function(data) {
        $rootScope.proDatas = data;
        if (data.length > 0) {
            $rootScope.proDatasShow = true;
        } else {
            $rootScope.proDatasShow = false;
        }
    });
};

/**
 * [获取用户常用功能]
 *
 * @param      {<type>}  $http       The http
 * @param      {<type>}  $rootScope  The root scope
 */
function getFavouriteSource($http, $rootScope) {
    $http.get("/api/fwFavourite/getUserFavourite").success(function(data) {
        for (var i = data.length - 1; i >= 0; i--) {
            if (actionMap.get(data[i].url) == null || actionMap.get(data[i].url).isShow != 1) {
                data.splice(i, 1);
            }
        }
        $rootScope.myFavourites = data;
    });
}

/**
 * 获取用户所在标段
 */
function loadAllUserTenders($http, $rootScope) {
    $http.get("/api/org/getAllUserTendsers").success(function(data) {
        $rootScope.userTenders = data.userTenders;
    });
}

var loadUserOrg = function(http, rootScope) {
    http.get('api/org/getCurrentAndSubOrg').success(function(res) {
        rootScope.reportUserOrg = buildTree(res, 1, "parentId", "id");
        rootScope.$currentOrg = res;
    });
}
/*左侧菜单收缩时右边内容宽度自适应*/
$("#sidebar-collapse").click(function() {
    if (!$("#sidebar").hasClass("menu-min")) {
        $(".wrap-content .n-cont").css("left", "40px")
        $(".menu-change-width").hide();
    } else {
        $(".wrap-content .n-cont").css("left", "190px")
        $(".menu-change-width").show();
    }
})
$(window).resize(function() {
    setGridHeight()
})

function setGridHeight() {
    /* 最外层高度*/
    var contHeight = $(".content.n-cont").height();
    /* 顶部tab框的高度*/
    var tabsHeight = "";
    $(".bs-example #headerDiv").each(function() {
        if ($(this).height() > 0) {
            tabsHeight = $(this).height();
        }
    });
    /* 搜索框的高度*/
    var searchHeight = "";
    /* 搜索框行数*/
    $(".search-area").each(function() {
        if ($(this).height() > 0) {
            searchHeight = $(this).height();
        }
    });
    /*表格顶部功能按钮高度*/
    var btnsHeight = "";
    $(".results .row").each(function() {
        if ($(this).height() > 0) {
            btnsHeight = $(this).height();
        }
    });
    /* 表格高度*/
    /* 表格高度*/
    $(".grid").height(contHeight - (tabsHeight + btnsHeight + searchHeight + 60));
    $(".ztree").css({
        "min-height": contHeight - (tabsHeight + btnsHeight + searchHeight + 60) + "px",
        "max-height": contHeight - (tabsHeight + btnsHeight + searchHeight + 60) + "px"
    })

};
$(".menu-change-width").draggable({
    axis: "x",
    drag: function(event, ui) {
        if (ui.offset.left > 400 || ui.offset.left < 190) {
            $(".menu-change-width").draggable('destory');
            return;
        } else {
            $(".left-menu .sidebar").width(ui.offset.left);
            $(".wrap-content .n-cont").css("left", ui.offset.left + "px");
        }
    }
});
$(".sidebar .nav").css({
    "max-height": $(document).height() - 88 + "px",
    "overflow-y": "auto"
});
$(window).resize(function() {
    $(".sidebar .nav").css({
        "max-height": $(document).height() - 88 + "px",
        "overflow-y": "auto"
    });
})
$(function() {
    $(".dropdown-menu.comm-fearture").on('click', function(e) {
        e.stopPropagation();
    });
});