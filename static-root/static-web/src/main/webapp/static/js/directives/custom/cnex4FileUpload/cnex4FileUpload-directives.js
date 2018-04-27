'use strict';
var cnex4_fileUpload_url = URL_PATH.FILE_HEADER + '/upload.do?actionMethod=process',
    cnex4_getFiles_url = URL_PATH.FILE_HEADER + '/getFiles.do?actionMethod=getFiles',
    cnex4_deleteFile_url = URL_PATH.FILE_HEADER + '/deleteFile.do?actionMethod=deleteFile';
customDirective.provider('cexn4FileUpload', function() {
        var scopeEvalAsync = function(expression) {
                var scope = angular.element(this)
                    .fileupload('option', 'scope');
                scope.$evalAsync(expression);
            },
            addFileMethods = function(scope, data) {
                var files = data.files,
                    file = files[0];
                angular.forEach(files, function(file, index) {
                    file._index = index;
                    file.$state = function() {
                        return data.state();
                    };
                    file.$processing = function() {
                        return data.processing();
                    };
                    file.$progress = function() {
                        return data.progress();
                    };
                    file.$response = function() {
                        return data.response();
                    };
                });
                file.$submit = function() {
                    if (!file.error) {
                        return data.submit();
                    }
                };
                file.$cancel = function() {
                    return data.abort();
                };
            },
            $config;
        $config = this.defaults = {
            handleResponse: function(e, data) {
                var files = data.result && data.result.files;
                if (files) {
                    data.scope.replace(data.files, files);
                } else if (data.errorThrown ||
                    data.textStatus === 'error') {
                    data.files[0].error = data.errorThrown ||
                        data.textStatus;
                }
            },
            add: function(e, data) {
                if (e.isDefaultPrevented()) {
                    return false;
                }
                var scope = data.scope,
                    filesCopy = [];
                angular.forEach(data.files, function(file) {
                    filesCopy.push(file);
                });
                scope.$parent.$applyAsync(function() {
                    addFileMethods(scope, data);
                    var method = scope.option('prependFiles') ?
                        'unshift' : 'push';
                    Array.prototype[method].apply(scope.queue, data.files);
                });
                data.process(function() {
                    return scope.process(data);
                }).always(function() {
                    scope.$parent.$applyAsync(function() {
                        addFileMethods(scope, data);
                        scope.replace(filesCopy, data.files);
                    });
                }).then(function() {
                    if ((scope.option('autoUpload') ||
                            data.autoUpload) &&
                        data.autoUpload !== false) {
                        data.submit();
                    }
                });
            },
            done: function(e, data) {
                if (e.isDefaultPrevented()) {
                    return false;
                }
                var that = this;
                data.scope.$apply(function() {
                    data.handleResponse.call(that, e, data);
                });
            },
            fail: function(e, data) {
                if (e.isDefaultPrevented()) {
                    return false;
                }
                var that = this,
                    scope = data.scope;
                if (data.errorThrown === 'abort') {
                    scope.clear(data.files);
                    return;
                }
                scope.$apply(function() {
                    data.handleResponse.call(that, e, data);
                });
            },
            stop: scopeEvalAsync,
            processstart: scopeEvalAsync,
            processstop: scopeEvalAsync,
            getNumberOfFiles: function() {
                var scope = this.scope;
                return scope.queue.length - scope.processing();
            },
            dataType: 'json',
            autoUpload: false
        };
        this.$get = [
            function() {
                return {
                    defaults: $config
                };
            }
        ];
    })
    .provider('cexn4FormatFileSizeFilter', function() {
        var $config = {
            units: [
                { size: 1000000000, suffix: ' GB' },
                { size: 1000000, suffix: ' MB' },
                { size: 1000, suffix: ' KB' }
            ]
        };
        this.defaults = $config;
        this.$get = function() {
            return function(bytes) {
                if (!angular.isNumber(bytes)) {
                    return '';
                }
                var unit = true,
                    i = 0,
                    prefix,
                    suffix;
                while (unit) {
                    unit = $config.units[i];
                    prefix = unit.prefix || '';
                    suffix = unit.suffix || '';
                    if (i === $config.units.length - 1 || bytes >= unit.size) {
                        return prefix + (bytes / unit.size).toFixed(2) + suffix;
                    }
                    i += 1;
                }
            };
        };
    })
    .config(['$httpProvider', 'cexn4FileUploadProvider',
        function($httpProvider, cexn4FileUploadProvider) {
            delete $httpProvider.defaults.headers.common['X-Requested-With'];
            cexn4FileUploadProvider.defaults.redirect = window.location.href.replace(
                /\/[^\/]*$/,
                '/cors/result.html?%s'
            );
            angular.extend(cexn4FileUploadProvider.defaults, {
                disableImageResize: /Android(?!.*Chrome)|Opera/
                    .test(window.navigator.userAgent),
                maxFileSize: 999000,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
            });
        }
    ])
    .controller('cnex4FileUploadController', ['$scope', '$compile', '$http', function($scope, $compile, $http) {
        var iconPathHead = 'static/js/directives/custom/cnex4FileUpload/images/';
        $scope.getImageIcon = function(fileName) {
            var fileExt = fileName.substr(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
            if ('xls' === fileExt || 'xlsx' === fileExt) {
                return iconPathHead + 'excel.png';
            } else if ('doc' === fileExt || 'docx' === fileExt) {
                return iconPathHead + 'word.png';
            } else if ('ppt' === fileExt || 'pptx' === fileExt) {
                return iconPathHead + 'ppt.png';
            } else if ('pdf' === fileExt) {
                return iconPathHead + 'pdf.png';
            } else if ('zip' === fileExt) {
                return iconPathHead + 'zip.png';
            } else if ('exe' === fileExt) {
                return iconPathHead + 'exe.png';
            } else if ('txt' === fileExt) {
                return iconPathHead + 'text.png';
            } else if ('html' === fileExt || 'htm' === fileExt) {
                return iconPathHead + 'html.png';
            } else {
                return undefined;
            }
        }

        /*是否显示图片预览*/
        $scope.isShowPreview = false;

        /*是否为多附件组件*/
        /*var cnex4Multiple = $scope.cnex4Multiple || false;*/

        /*添加文件按钮是否显示*/
        $scope.addFileBtnIsShow = true;

        /*是否显示删除按钮*/
        $scope.showDelete = false;

        /*$scope.files = [];*/
        $scope.addFiles = [];

        $scope.disableDeleteFilesArr = new Map();

        $scope.$on("fileSelected", function(event, args) {
            $scope.$apply(function() {
                if (args.file) {
                    if (args.file.size > 20000000) {
                        submitTips('文件大小不能超过20M', 'warning');
                    } else if (!$scope.cnex4Multiple && $scope.queue && $scope.queue.length > 0) {
                        submitTips('只能上传单个附件！', 'warning');
                    } else {
                        $scope.saveFiles(args.file);
                    }
                }
            });
        });

        $scope.saveFiles = function(file) {
            $http({
                method: 'POST',
                url: cnex4_fileUpload_url,
                headers: { 'Content-Type': undefined },
                transformRequest: function(data) {
                    var formData = new FormData();
                    formData.append("businessId", data.businessId);
                    formData.append("systemHeader", data.systemHeader);
                    formData.append("file", file);
                    return formData;
                },
                data: { businessId: $scope.ngModel, files: file, systemHeader: $scope.systemHeader || "system" }
            }).success(function(data, status, headers, config) {
                $scope.getFiles(false);
                console.log("success!");
            }).error(function(data, status, headers, config) {
                console.log("failed!");
            });
        };

        $scope.getFiles = function(onlyAdd) {
            var businessIds = $scope.ngModel;
            if (angular.isArray($scope.ngModel)) {
                businessIds = $scope.ngModel.toString();
            }
            $http({
                    method: 'POST',
                    url: cnex4_getFiles_url,
                    data: { businessIds: businessIds },
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    transformRequest: function(obj) {
                        var str = [];
                        for (var p in obj) {
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                })
                .success(function(data, status, headers, config) {
                    if ($scope.cnex4ShowBtn) {
                        angular.forEach(data.data, function(file) {
                            file.onlyAdd = true;
                        });
                    } else {
                        if (onlyAdd) {
                            angular.forEach(data.data, function(file) {
                                file.onlyAdd = onlyAdd;
                                $scope.disableDeleteFilesArr.put(file.id, file.id);
                            });
                        } else {
                            angular.forEach(data.data, function(file) {
                                if ($scope.disableDeleteFilesArr.get(file.id)) {
                                    file.onlyAdd = true;
                                } else {
                                    file.onlyAdd = onlyAdd;
                                }
                            });
                        }
                    }
                    $scope.queue = data.data;
                    $scope.files = [];

                    if ($scope.cnex4Multiple == false || $scope.cnex4Multiple == "false") {
                        if (data.data != null && data.data.length > 0) {
                            $scope.addFileBtnIsShow = false;
                            $scope.filesCount = data.data.length;
                        } else {
                            $scope.addFileBtnIsShow = true;
                        }
                    } else {
                        if (data.data != null && data.data.length > 0) {
                            if (data.data.length >= $scope.maxFilesCount) {
                                $scope.addFileBtnIsShow = false;
                            } else {
                                $scope.addFileBtnIsShow = true;
                            }
                            if ($scope.minFilesCount != null && data.data.length <= $scope.minFilesCount) {
                                $scope.showDelete = true;
                            } else {
                                $scope.showDelete = false;
                            }
                            $scope.filesCount = data.data.length;
                        }
                    }
                    if ($scope.cnex4ShowBtn == true || $scope.cnex4ShowBtn == "true") {
                        $scope.addFileBtnIsShow = false;
                        $scope.deleteFileBtnIsShow = false;
                    } else {
                        $scope.deleteFileBtnIsShow = true;
                    }
                })
                .error(function(data, status, headers, config) {
                    console.log("load files failed!");
                });
        };

        $scope.deleteFile = function(fileId) {
            $http({
                    method: 'GET',
                    url: cnex4_deleteFile_url + "&fileId=" + fileId
                })
                .success(function(data, status, headers, config) {
                    $scope.getFiles(false);
                    $scope.filesCount--;
                })
                .error(function(data, status, headers, config) {
                    console.log("load files failed!");
                });
        };
        $scope.showPreview = function(preViewURL, fileType) {
            if (!(isImage(fileType) || isOffice(fileType))) {
                return;
            }
            var jsPanelKey = uuid(10);

            controllerProvider.register(jsPanelKey, function($scope, $controller) {

                $scope.viewUrl = preViewURL;
                $scope.fileType = fileType;
                $scope.rootElementId = jsPanelKey;

                $controller("FileOnlineReaderController", { $scope: $scope });


            });
            var modal = "<div  ng-controller='" + jsPanelKey + "'>";
            $http.get('static/js/directives/custom/cnex4FileUpload/template/file_online_read.html').success(function(res) {
                modal += res
                modal += "</div>";
                var jsPanel = $.jsPanel({
                    id: jsPanelKey,
                    selector: '.content',
                    headerTitle: "文件在线浏览",
                    contentSize: { width: 800, height: 500 },
                    theme: 'dark',
                    position: 'center',
                    content: $compile(modal)($scope),
                    draggable: {
                        containment: "parent"
                    }

                });

            });
        }
    }])
    .controller('huatekFileUploadAndPreview', ['$scope', '$compile', '$http', function($scope, $compile, $http) {
        var iconPathHead = 'static/js/directives/custom/cnex4FileUpload/images/';
        $scope.getImageIcon = function(fileName) {
            var fileExt = fileName.substr(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
            if ('xls' === fileExt || 'xlsx' === fileExt) {
                return iconPathHead + 'excel.png';
            } else if ('doc' === fileExt || 'docx' === fileExt) {
                return iconPathHead + 'word.png';
            } else if ('ppt' === fileExt || 'pptx' === fileExt) {
                return iconPathHead + 'ppt.png';
            } else if ('pdf' === fileExt) {
                return iconPathHead + 'pdf.png';
            } else if ('zip' === fileExt) {
                return iconPathHead + 'zip.png';
            } else if ('exe' === fileExt) {
                return iconPathHead + 'exe.png';
            } else if ('txt' === fileExt) {
                return iconPathHead + 'text.png';
            } else if ('html' === fileExt || 'htm' === fileExt) {
                return iconPathHead + 'html.png';
            } else {
                return undefined;
            }
        }

        /*是否显示图片预览*/
        $scope.isShowPreview = false;

        /*是否为多附件组件*/
        /*var cnex4Multiple = $scope.cnex4Multiple || false;*/

        /*添加文件按钮是否显示*/
        $scope.addFileBtnIsShow = true;

        /*是否显示删除按钮*/
        $scope.showDelete = false;

        /*$scope.files = [];*/
        $scope.addFiles = [];

        $scope.disableDeleteFilesArr = new Map();

        $scope.$on("fileSelected", function(event, args) {
            $scope.$apply(function() {
                if (args.file) {
                    if (args.file.size > 20000000) {
                        submitTips('文件大小不能超过20M', 'warning');
                    } else if (!$scope.cnex4Multiple && $scope.queue && $scope.queue.length > 0) {
                        submitTips('只能上传单个附件！', 'warning');
                    } else {
                        $scope.saveFiles(args.file);
                    }
                }
            });
        });

        $scope.saveFiles = function(file) {
            $http({
                method: 'POST',
                url: cnex4_fileUpload_url,
                headers: { 'Content-Type': undefined },
                transformRequest: function(data) {
                    var formData = new FormData();
                    formData.append("businessId", data.businessId);
                    formData.append("systemHeader", data.systemHeader);
                    formData.append("file", file);
                    return formData;
                },
                data: { businessId: $scope.ngModel, files: file, systemHeader: $scope.systemHeader || "system" }
            }).success(function(data, status, headers, config) {
                $scope.getFiles(false);
                console.log("success!");
            }).error(function(data, status, headers, config) {
                console.log("failed!");
            });
        };

        $scope.getFiles = function(onlyAdd) {
            var businessIds = $scope.ngModel;
            if (angular.isArray($scope.ngModel)) {
                businessIds = $scope.ngModel.toString();
            }
            $http({
                    method: 'POST',
                    url: cnex4_getFiles_url,
                    data: { businessIds: businessIds },
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    transformRequest: function(obj) {
                        var str = [];
                        for (var p in obj) {
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                })
                .success(function(data, status, headers, config) {
                    if ($scope.cnex4ShowBtn) {
                        angular.forEach(data.data, function(file) {
                            file.onlyAdd = true;
                        });
                    } else {
                        if (onlyAdd) {
                            angular.forEach(data.data, function(file) {
                                file.onlyAdd = onlyAdd;
                                $scope.disableDeleteFilesArr.put(file.id, file.id);
                            });
                        } else {
                            angular.forEach(data.data, function(file) {
                                if ($scope.disableDeleteFilesArr.get(file.id)) {
                                    file.onlyAdd = true;
                                } else {
                                    file.onlyAdd = onlyAdd;
                                }
                            });
                        }
                    }
                    $scope.queue = data.data;
                    $scope.files = [];

                    if ($scope.cnex4Multiple == false || $scope.cnex4Multiple == "false") {
                        if (data.data != null && data.data.length > 0) {
                            $scope.addFileBtnIsShow = false;
                            $scope.filesCount = data.data.length;
                        } else {
                            $scope.addFileBtnIsShow = true;
                        }
                    } else {
                        if (data.data != null && data.data.length > 0) {
                            if (data.data.length >= $scope.maxFilesCount) {
                                $scope.addFileBtnIsShow = false;
                            } else {
                                $scope.addFileBtnIsShow = true;
                            }
                            if ($scope.minFilesCount != null && data.data.length <= $scope.minFilesCount) {
                                $scope.showDelete = true;
                            } else {
                                $scope.showDelete = false;
                            }
                            $scope.filesCount = data.data.length;
                        }
                    }
                    if ($scope.cnex4ShowBtn == true || $scope.cnex4ShowBtn == "true") {
                        $scope.addFileBtnIsShow = false;
                        $scope.deleteFileBtnIsShow = false;
                    } else {
                        $scope.deleteFileBtnIsShow = true;
                    }
                })
                .error(function(data, status, headers, config) {
                    console.log("load files failed!");
                });
        };

        $scope.deleteFile = function(fileId) {
            $http({
                    method: 'GET',
                    url: cnex4_deleteFile_url + "&fileId=" + fileId
                })
                .success(function(data, status, headers, config) {
                    $scope.getFiles(false);
                    $scope.filesCount--;
                })
                .error(function(data, status, headers, config) {
                    console.log("load files failed!");
                });
        };
        $scope.showPreview = function(preViewURL, fileType) {
            if (!(isImage(fileType) || isOffice(fileType))) {
                return;
            }
            var jsPanelKey = uuid(10);

            controllerProvider.register(jsPanelKey, function($scope, $controller) {

                $scope.viewUrl = preViewURL;
                $scope.fileType = fileType;
                $scope.rootElementId = jsPanelKey;

                $controller("FileOnlineReaderController", { $scope: $scope });


            });
            var modal = "<div  ng-controller='" + jsPanelKey + "'>";
            $http.get('static/js/directives/custom/cnex4FileUpload/template/file_online_read.html').success(function(res) {
                modal += res
                modal += "</div>";
                var jsPanel = $.jsPanel({
                    id: jsPanelKey,
                    selector: '.content',
                    headerTitle: "文件在线浏览",
                    contentSize: { width: 800, height: 500 },
                    theme: 'dark',
                    position: 'center',
                    content: $compile(modal)($scope),
                    draggable: {
                        containment: "parent"
                    }

                });

            });
        }
        $scope.showImage = null;
        $scope.rotate = 0;

        $scope.turnLeft = function() {
            $scope.rotate += -90;
        };
        $scope.turnRight = function() {
            $scope.rotate += 90;
        };
        /*点击图片切换*/
       /* $scope.clickImageForChange = function(image) {
            $scope.rotate = 0;
            $scope.setChoose(image);
        };*/
        /*/*点击左右切换*/
       /* $scope.clickNextForChange = function(index, position) {
            $scope.rotate = 0;
            if ($scope.queue != null && $scope.queue.length > 0) {
                if ("up" === position) {
                    index = index * 1 - 1;
                } else {
                    index = index * 1 + 1;
                }
                if (index < 0) {
                    index = $scope.queue.length - 1;
                } else if (index >= $scope.queue.length) {
                    index = 0;
                }
                $scope.setChoose($scope.queue[index]);
            }
        }*/
        var ind = ''
        /*点击图片切换*/
        $scope.clickImageForChange = function(image) {
            $scope.rotate = 0;
            ind = $scope.queue.indexOf(image)
            console.log(ind)
            $scope.setChoose(image);
        };
        /*点击左右切换*/
        $scope.clickNextForChange = function(index, position) {
            // console.log(index)
            // console.log($scope.queue);
            ind = $scope.queue.indexOf(index)
            $scope.rotate = 0;
            console.log($scope.queue[ind])
            if (ind !== -1) {
                if ("up" === position && ind >0) {
                    console.log('zuo')
                    ind--;
                } else if('down' === position && ind<$scope.queue.length-1) {
                    console.log('you')
                    ind++;
                }
            }
            $scope.setChoose($scope.queue[ind]);
        }


        /*设置选中样式*/
        $scope.setChoose = function(image) {
            angular.forEach($scope.queue, function(image) {
                image.css = "ps-item";
            });
            $scope.showImage = image;
            $scope.showImage.css = "ps-item ps-selected";
        };
    }])
    .controller('cenx4ImagePreviewController', ['$scope', '$http', function($scope, $http) {
        $scope.showImage = null;
        $scope.rotate = 0;

        $scope.turnLeft = function() {
            $scope.rotate += -90;
        };
        $scope.turnRight = function() {
            $scope.rotate += 90;
        };

        $scope.getFiles = function(newValue) {
            var businessIds = newValue;
            if (angular.isArray(newValue)) {
                businessIds = newValue.toString();
            }
            $http({
                    method: 'POST',
                    url: cnex4_getFiles_url,
                    data: { businessIds: businessIds },
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    transformRequest: function(obj) {
                        var str = [];
                        for (var p in obj) {
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                })
                .success(function(data, status, headers, config) {
                    if (undefined == data.data || data.data.length < 1) {
                        $scope.queue = [];
                        $scope.showImage = {};
                    } else {
                        $scope.queue = data.data;
                        angular.forEach($scope.queue, function(image, index) {
                            image.index = index;
                        });
                        $scope.showImage = $scope.queue[0];
                        $scope.showImage.css = "ps-item ps-selected";
                    }
                })
                .error(function(data, status, headers, config) {
                    console.log("load files failed!");
                });
        };
        /*设置选中样式*/
        $scope.setChoose = function(image) {
            angular.forEach($scope.queue, function(image) {
                image.css = "ps-item";
            });
            $scope.showImage = image;
            $scope.showImage.css = "ps-item ps-selected";
        };
    }])
    .controller('cenx4ImagePreviewListController', ['$scope', '$http', function($scope, $http) {
        $scope.showImage = null;
        $scope.getFiles = function() {
            var businessIds = $scope.ngModel;
            if (angular.isArray($scope.ngModel)) {
                businessIds = $scope.ngModel.toString();
            };
            $http({
                    method: 'POST',
                    url: cnex4_getFiles_url,
                    data: { businessIds: businessIds },
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    transformRequest: function(obj) {
                        var str = [];
                        for (var p in obj) {
                            str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        }
                        return str.join("&");
                    }
                })
                .success(function(data, status, headers, config) {
                    $scope.queue = data.data;
                })
                .error(function(data, status, headers, config) {
                    console.log("load files failed!");
                });
        };
    }])
    .controller('FileOnlineReaderController', ['$scope', '$http', function($scope, $http) {
        $scope.isImage = isImage($scope.fileType);
        $scope.isVideo = isVideo($scope.fileType);
        $scope.$watch('$viewContentLoaded', function() {
            if ($scope.isImage) {

                setTimeout($scope.startZoom, 200);
            }

        });
        $scope.zoomButtonClickHandler = function(e) {
            var scaleToAdd = 0.8;
            if (e.target.id == 'zoomOutButton')
                scaleToAdd = -scaleToAdd;
            $('#imageFullScreen', $("#" + $scope.rootElementId)).smartZoom('zoom', scaleToAdd);
        };
        $scope.startZoom = function() {
            $('#imageFullScreen', $("#" + $scope.rootElementId)).smartZoom({ 'containerClass': 'zoomableContainer' });
        }
        $scope.moveButtonClickHandler = function(e) {
            var pixelsToMoveOnX = 0;
            var pixelsToMoveOnY = 0;

            switch (e.target.id) {
                case "leftPositionMap":
                    pixelsToMoveOnX = 50;
                    break;
                case "rightPositionMap":
                    pixelsToMoveOnX = -50;
                    break;
                case "topPositionMap":
                    pixelsToMoveOnY = 50;
                    break;
                case "bottomPositionMap":
                    pixelsToMoveOnY = -50;
                    break;
            }
            $('#imageFullScreen', $("#" + $scope.rootElementId)).smartZoom('pan', pixelsToMoveOnX, pixelsToMoveOnY);
        };

    }])
    .directive('cenx4FileUpload', function() {
        return {
            require: '?ngModel',
            restrict: 'E',
            scope: { ngModel: '=', filesCount: "=", cnex4Multiple: '=', systemHeader: "=", maxFilesCount: "=", minFilesCount: "=", cnex4ShowBtn: "=", cnex4OnlyAdd: "=" },
            controller: 'cnex4FileUploadController',
            templateUrl: 'static/js/directives/custom/cnex4FileUpload/template/cnex4FileUpload_template.html',
            replace: true,
            link: function(scope, el, attrs, ngModel) {
                var businessId = getUUID();
                ngModel.$render = function() {
                    if ("" != ngModel.$viewValue && undefined != ngModel.$viewValue && ngModel.$viewValue != businessId) {
                        scope.getFiles(scope.cnex4OnlyAdd);
                    } else {
                        scope.ngModel = businessId;
                    }
                };
                if (scope.cnex4ShowBtn == true || scope.cnex4ShowBtn == "true") {
                    scope.addFileBtnIsShow = false;
                    scope.deleteFileBtnIsShow = false;
                } else {
                    scope.deleteFileBtnIsShow = true;
                }
                el.bind('change', function(event) {
                    var files = event.target.files;
                    for (var i = 0; i < files.length; i++) {
                        scope.$emit("fileSelected", { file: files[i] });
                    }
                });
            }
        };
    })
    .directive('cenx4FileUploadAndPreview', function() {
        return {
            require: '?ngModel',
            restrict: 'E',
            scope: { ngModel: '=', filesCount: "=", cnex4Multiple: '=', systemHeader: "=", maxFilesCount: "=", minFilesCount: "=", cnex4ShowBtn: "=", cnex4OnlyAdd: "=" },
            controller: 'huatekFileUploadAndPreview',
            templateUrl: 'static/js/directives/custom/cnex4FileUpload/template/huatekFile_upload_preview.html',
            replace: true,
            link: function(scope, el, attrs, ngModel) {
                var businessId = getUUID();
                ngModel.$render = function() {
                    if ("" != ngModel.$viewValue && undefined != ngModel.$viewValue && ngModel.$viewValue != businessId) {
                        scope.getFiles(scope.cnex4OnlyAdd);
                    } else {
                        scope.ngModel = businessId;
                    }
                };
                if (scope.cnex4ShowBtn == true || scope.cnex4ShowBtn == "true") {
                    scope.addFileBtnIsShow = false;
                    scope.deleteFileBtnIsShow = false;
                } else {
                    scope.deleteFileBtnIsShow = true;
                }
                el.bind('change', function(event) {
                    var files = event.target.files;
                    for (var i = 0; i < files.length; i++) {
                        scope.$emit("fileSelected", { file: files[i] });
                    }
                });
            }
        };
    })
    .directive('cenx4ImagePreview', function() {
        return {
            require: '?ngModel',
            restrict: 'E',
            scope: { ngModel: '=' },
            controller: 'cenx4ImagePreviewController',
            templateUrl: 'static/js/directives/custom/cnex4FileUpload/template/cnex4Image_preview.html',
            replace: true,
            link: function(scope, el, attrs, ngModel) {
                /*如果设置成readOnly，直接不显示*/
                scope.$watch('ngModel', function(newValue, oldValue, scope) {
                    if (("" != newValue && undefined != newValue) || newValue != oldValue) {
                        scope.getFiles(newValue);
                    }
                });
            }
        };
    })
    .directive('cenx4ImagePreviewList', function() {
        return {
            require: '?ngModel',
            restrict: 'E',
            scope: { ngModel: '=' },
            controller: 'cenx4ImagePreviewListController',
            templateUrl: 'static/js/directives/custom/cnex4FileUpload/template/cnex4Image_previewList.html',
            replace: true,
            link: function(scope, el, attrs, ngModel) {
                scope.$watch('ngModel', function(newValue, oldValue, scope) {
                    if ("" != newValue && undefined != newValue) {
                        scope.getFiles(ngModel.$viewValue);
                    }
                });
            }
        };
    });