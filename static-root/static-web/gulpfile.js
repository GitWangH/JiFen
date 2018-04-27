var gulp = require('gulp'),
    minifycss = require('gulp-minify-css'), //压缩css
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename'),
    jshint = require('gulp-jshint'),
    rev = require('gulp-rev');
// spritesmith=require('gulp.spritesmith');
var ngAnnotate = require('gulp-ng-annotate');
var ngmin = require('gulp-ngmin');
var gutil = require('gulp-util');
const runSequence = require('run-sequence');
//语法检查
// gulp.task('jshint',function () {
//     gulp.src([ ])
//         .pipe(jshint())
//         .pipe(jshint.reporter('default'));
// });
//压缩，合并 js
// 已经检查注释的问题
gulp.task('core', function() {
    gulp.src([
            "src/main/webapp/static/js/utils/map.js",
            "src/main/webapp/static/js/utils/array.js",
            "src/main/webapp/static/js/utils/cookie.js",
            "src/main/webapp/static/js/utils/encryption.js",
            /** 前台的配置文件 */
            "src/main/webapp/static/config/config.js",
            "src/main/webapp/static/config/page_elements.js",
            "src/main/webapp/static/config/report_search_elements.js",
            /** 更换更高版本jquery，以支持jsPanel */
            "src/main/webapp/static/js/coreJs/jquery/3.2.1/jquery-3.2.1.min.js",
            "src/main/webapp/static/js/coreJs/jquery/jquery-ui/1.12.1/jquery-ui.min.js",
            "src/main/webapp/static/js/coreJs/jquery/jquery-ui-touch-punch/0.2.3/jquery.ui.touch-punch.js",
            "src/main/webapp/static/js/directives/thirdPart/angular-treeTable/jquery.treetable.js",
            /** jsPanel js */
            "src/main/webapp/static/js/thirdPartJs/jsPanel/3.10.0/jquery.jspanel-compiled.min.js",
            /** ztree */
            "src/main/webapp/static/js/thirdPartJs/ztree/3.5/jquery.ztree.core-3.5.min.js",
            "src/main/webapp/static/js/thirdPartJs/ztree/3.5/jquery.ztree.excheck-3.5.min.js",
            /** 图片放大缩小 */
            "src/main/webapp/static/js/thirdPartJs/e-smart-zoom/e-smart-zoom-jquery-ext.js",
            /** 打印 */
            "src/main/webapp/static/js/thirdPartJs/printArea/2.4.0/jquery.PrintArea.js",
            /** bootstrap */
            "src/main/webapp/static/js/coreJs/bootstrap/3.3.5/bootstrap.min.js",
            "src/main/webapp/static/js/coreJs/bootstrap/2.0.2/bootstrap-hover-dropdown.min.js",
            /** bootbox */
            "src/main/webapp/static/js/thirdPartJs/bootbox/4.4.0/bootbox.js",
            /** io.select依赖库 */
            "src/main/webapp/static/js/directives/thirdPart/oi-select/0.2.21/js/highlight.min.js",
            /** layer 弹出层组件 */
            // "src/main/webapp/static/js/thirdPartJs/layer/2.2/layer.js",
            /**echart*/
            "src/main/webapp/static/js/thirdPartJs/echart/3.8.0/echarts.min.js",
            /** uitl */
            "src/main/webapp/static/js/utils/util.js",
            "src/main/webapp/static/js/utils/uuid.js",
            // "src/main/webapp/static/js/utils/message.pop.js",
            /** angularjs */
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular.js",
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular-animate.min.js",
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular-touch.min.js",
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular-route.js",
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular-resource.js",
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular-cookies.js",
            "src/main/webapp/static/js/coreJs/angular/1.5.0/angular-aria.js",
            /** ace */
            "src/main/webapp/static/js/thirdPartJs/aceTree/1.4.0/ace-elements.min.js",
            "src/main/webapp/static/js/thirdPartJs/aceTree/1.4.0/ace.min.js",
            /*unlock*/
            "src/main/webapp/static/js/thirdPartJs/unlock/unlock.js",
            /*alasql*/
            "src/main/webapp/static/js/thirdPartJs/alasql/0.3/alasql.min.js",
            "src/main/webapp/static/js/thirdPartJs/alasql/0.3/xlsx.core.min.js"
        ]) //需要操作的文件
        .pipe(concat('core.js')) //合并所有js到main.js
        .pipe(gulp.dest('src/main/webapp/static/compress/js')) //输出到文件夹
        // .pipe(rename({suffix: '.min'}))   //rename压缩后的文件名
        .pipe(uglify({
            mangle: false,
            compress: true, 
        })) 
        .on('error', function (err) { gutil.log(gutil.colors.red('[Error]'), err.toString()); })
        .pipe(gulp.dest('src/main/webapp/static/compress/js')); 
});

// 已经检查注释的问题
gulp.task('service', function() {
    gulp.src([
            "src/main/webapp/static/js/services/baseService.js",
            "src/main/webapp/static/js/services/cacheService.js",
            "src/main/webapp/static/js/services/base64Service.js",
            "src/main/webapp/static/js/services/listService.js",
            "src/main/webapp/static/js/services/editService.js",
            "src/main/webapp/static/js/services/httpService.js",
            "src/main/webapp/static/js/services/batchEditeService.js",
            "src/main/webapp/static/js/services/excelService.js",
            "src/main/webapp/static/js/services/i18nService.js",
            "src/main/webapp/static/js/services/modalListService.js",
            "src/main/webapp/static/js/services/shareService.js",
            "src/main/webapp/static/js/services/toolsService.js",
            "src/main/webapp/static/js/services/treeGridService.js"
        ]) //需要操作的文件
        .pipe(concat('service.js')) //合并所有js到main.js
        .pipe(gulp.dest('src/main/webapp/static/compress/js')); //输出到文件夹
        // .pipe(uglify({
        //     mangle: false, 
        //     compress: true, 
        // })) 
        // .pipe(gulp.dest('src/main/webapp/static/compress/js')); 
});

gulp.task('directive', function() {
    gulp.src([
        /** ocLazyLoad */
            "src/main/webapp/static/js/directives/thirdPart/ocLazyLoad/1.0.9/ocLazyLoad.js",
            /** service end */
            /** filters begin */
            "src/main/webapp/static/js/filters/filter.js",
            /** filters end */
            /** directive begin */
            /** customDirective */
            "src/main/webapp/static/js/directives/custom/customDirective.js",
            /** ngStoreage */
            "src/main/webapp/static/js/directives/thirdPart/ngStorage/0.3.6/ngStorage.min.js",
            /** message */
            "src/main/webapp/static/js/directives/thirdPart/ngStorage/0.3.6/ngStorage.min.js",
            /** cgbusy */
            "src/main/webapp/static/js/directives/thirdPart/angularBusy/4.1.4/angular-busy.js",
            /** ngDialog */
            "src/main/webapp/static/js/directives/thirdPart/ngDialog/1.2.0/ngDialog.js",
            /** gettext */
            "src/main/webapp/static/js/directives/thirdPart/angular-gettext/angular-gettext.min.js",
            /** highlightjs */
            "src/main/webapp/static/js/directives/thirdPart/angular-highlightjs/0.6.2/angular-highlightjs.min.js",
            /** ui-grid */
            // "src/main/webapp/static/js/directives/thirdPart/ui-grid/3.1.0/csv.js",
            // "src/main/webapp/static/js/directives/thirdPart/ui-grid/3.1.0/vfs_fonts.js",
            // "src/main/webapp/static/js/directives/thirdPart/ui-grid/3.1.0/loadsh.min.js",
            // "src/main/webapp/static/js/directives/thirdPart/ui-grid/3.1.0/jszip.min.js",
            // "src/main/webapp/static/js/directives/thirdPart/ui-grid/3.1.0/excel-builder.dist.js",
            "src/main/webapp/static/js/directives/thirdPart/ui-grid/3.1.0/ui-grid.min.js",
            /** pageDirective */
            "src/main/webapp/static/js/directives/custom/pageDirective/pageDirectives.js",
            /** kindeditor 文本编辑器 需要依赖ocLazyLoad*/
            "src/main/webapp/static/js/directives/thirdPart/kindEditor/4.1.7/kindeditor-all.js",
            "src/main/webapp/static/js/directives/thirdPart/kindEditor/4.1.7/kindEditor-directive.js",
            "src/main/webapp/static/js/directives/thirdPart/kindEditor/4.1.7/lang/zh_CN.js",
            /** bootstrap-datepicker */
            "src/main/webapp/static/js/directives/thirdPart/bootstrap-datetimepicker/2.4.4/bootstrap-datetimepicker.min.js",
            /** ui-bootstrap */
            "src/main/webapp/static/js/directives/thirdPart/uiBootstrap/1.3.3/ui-bootstrap-tpls.js",
            /** angular-translate */
            "src/main/webapp/static/js/directives/thirdPart/angular-translate/2.7.2/angular-translate.min.js",
            /** cnex4ImageScanDirective */
            "src/main/webapp/static/js/directives/custom/cnex4ImageScan/cnex4ImageScanDirective.js",
            /** nya-bs 多选下拉 */
            "src/main/webapp/static/js/directives/thirdPart/nya-bs-select/2.1.6/nya-bs-public.js",
            "src/main/webapp/static/js/directives/thirdPart/nya-bs-select/2.1.6/nya-bs-config.js",
            "src/main/webapp/static/js/directives/thirdPart/nya-bs-select/2.1.6/nya-bs-select-ctrl.js",
            "src/main/webapp/static/js/directives/thirdPart/nya-bs-select/2.1.6/nya-bs-select.js",
            "src/main/webapp/static/js/directives/thirdPart/nya-bs-select/2.1.6/nya-bs-option.js",
            /** angular-strap */
            "src/main/webapp/static/js/directives/thirdPart/angular-strap/2.3.8/angular-strap.js",
            "src/main/webapp/static/js/directives/thirdPart/angular-strap/2.3.8/angular-strap.tpl.js",
            /** 地址选择插件 （此组件和多个组件冲突） */
            "src/main/webapp/static/js/directives/custom/selectAddress/selectAddress2_returnName.js",
            /** file upload */
            "src/main/webapp/static/js/thirdPartJs/fileupload/vendor/jquery.ui.widget.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/JavaScript-Load-Image/load-image.all.min.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/JavaScript-Canvas-to-Blob/canvas-to-blob.min.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/Gallery/jquery.blueimp-gallery.min.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.iframe-transport.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload-process.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload-image.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload-audio.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload-video.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload-validate.js",
            "src/main/webapp/static/js/thirdPartJs/fileupload/jquery.fileupload-angular.js",
            /** 自定义文件上传组件 */
            "src/main/webapp/static/js/directives/custom/cnex4FileUpload/cnex4FileUpload-directives.js",
            /** select2 下拉带模糊检索 依赖于jquery.js 和 angular.js */
            "src/main/webapp/static/js/directives/thirdPart/select2/3.4.8/select2.js",
            "src/main/webapp/static/js/directives/thirdPart/select2/3.4.8/select2-directives.js",
            /** textAngular */
            /*"src/main/webapp/static/js/directives/thirdPart/textAngular/1.3.10/textAngular-rangy.min.js",
            "src/main/webapp/static/js/directives/thirdPart/textAngular/1.3.10/textAngular-sanitize.min.js",
            "src/main/webapp/static/js/directives/thirdPart/textAngular/1.3.10/textAngular.min.js",*/
            /** oi.select(支持动态加载和模糊检索) */
            "src/main/webapp/static/js/directives/thirdPart/oi-select/0.2.21/src/module.js",
            "src/main/webapp/static/js/directives/thirdPart/oi-select/0.2.21/src/directives.js",
            "src/main/webapp/static/js/directives/thirdPart/oi-select/0.2.21/src/filters.js",
            "src/main/webapp/static/js/directives/thirdPart/oi-select/0.2.21/src/services.js",
            "src/main/webapp/static/js/directives/thirdPart/oi-select/0.2.21/src/select-old-tpls.min.js",
            /** numeric-directive */
            "src/main/webapp/static/js/directives/thirdPart/angular-numeric/0.9.8/numeric-directive.js",
            /** ngMessages */
            "src/main/webapp/static/js/directives/thirdPart/angular-messages/1.5.0/angular-messages.js",
            /*列表树*/
            "src/main/webapp/static/js/directives/thirdPart/adapt-strap/2.6.1/adapt-strap.js",
            "src/main/webapp/static/js/directives/thirdPart/adapt-strap/2.6.1/adapt-strap.tpl.js",
            /**tree-select*/
            "src/main/webapp/static/js/directives/thirdPart/angular-select-tree/0.1.0/angular-multi-select-tree.js",
            "src/main/webapp/static/js/directives/thirdPart/angular-select-tree/0.1.0/angular-multi-select-tree.tpl.js",
            "src/main/webapp/static/js/directives/thirdPart/ng-htmlCompiler/ng-htmlCompiler.js"
        ]) //需要操作的文件
        .pipe(concat('directive.js')) //合并所有js到main.js
        .pipe(gulp.dest('src/main/webapp/static/compress/js')); //输出到文件夹
        // .pipe(uglify({
        //     mangle: false, 
        //     compress: true, 
        // })) 
        // .pipe(gulp.dest('src/main/webapp/static/compress/js'));
});

gulp.task('home', function() {
    gulp.src([
            "src/main/webapp/static/js/init.js",
            /** main controller */
            "src/main/webapp/static/js/controller.js",
            /** 功能controller begin */
            /**报表*/
            "src/main/webapp/static/business/chart/controllers/chartController.js",
            "src/main/webapp/static/business/workflow/easyformTemplate/controllers/EasyFormController.js",
            "src/main/webapp/static/business/cmd/excelImport/controllers/ExcelUploadController.js"
        ]) //需要操作的文件
        .pipe(concat('home.js')) //合并所有js到main.js
        .pipe(gulp.dest('src/main/webapp/static/compress/js')) //输出到文件夹
        // .pipe(rename({suffix: '.min'}))   //rename压缩后的文件名
        .pipe(uglify({
            mangle: false, 
            compress: true, 
        })) 
        .pipe(gulp.dest('src/main/webapp/static/compress/js')); 
});

gulp.task('css', function() {
    gulp.src([
            /** 自定义文件上传*/
            "src/main/webapp/static/css/cnex4FileUpload/preview-pgwslideshow.css" ,
            /** bootStrap样式*/
            "src/main/webapp/static/css/theme-default/bootstrap.min.css" ,
            /** jquery-ui样式*/
            "src/main/webapp/static/css/theme-default/jquery-ui.min.css" ,
            /** angularstrap*/
            "src/main/webapp/static/css/theme-default/angular-strap.css" ,
            /** angular-busy*/
            "src/main/webapp/static/css/theme-default/angular-busy.css" ,
            /** ui-grid*/
            "src/main/webapp/static/css/theme-default/ui-grid.min.css" ,
            /** nya-bs-select*/
            "src/main/webapp/static/css/theme-default/nya-bs-select.min.css" ,
            /** ngDialog*/
            "src/main/webapp/static/css/ngDialog/ngDialog.css" ,
            "src/main/webapp/static/css/ngDialog/ngDialog-theme-default.css" ,
            "src/main/webapp/static/css/ngDialog/ngDialog-theme-plain.css" ,
            "src/main/webapp/static/css/ngDialog/ngDialog-custom-width.css" ,
            /** uploadify*/
            "src/main/webapp/static/css/theme-default/uploadify.css" ,
            /** jquery.fileupload*/
            "src/main/webapp/static/css/theme-default/jquery.fileupload.css" ,
            /** select2样式*/
            "src/main/webapp/static/css/select2/select2.css" ,
            "src/main/webapp/static/css/select2/select2-bootstrap.css" ,
            /** textAngular*/
            // "src/main/webapp/static/css/textAngular/textAngular.css" ,
             "src/main/webapp/static/css/textAngular/font-awesome.min.css" ,
            /** cnex4-data-picker*/
            // "src/main/webapp/static/css/cnex4-data-picker/cnex4.data.picker.css" ,
            // "src/main/webapp/static/css/cnex4-data-picker/asides.css" ,
            /** main css*/
            "src/main/webapp/static/css/theme-default/mod.main.css" ,
            /** io.select(支持动态加载)*/
            "src/main/webapp/static/css/oi-select/github.min.css" ,
            "src/main/webapp/static/css/oi-select/select-old.min.css" ,
            "src/main/webapp/static/css/oi-select/select.css" ,
            /** kindeditor 文本编辑器*/
            "src/main/webapp/static/css/kindEditor/default.css" ,
            /** jsPanel css*/
            "src/main/webapp/static/css/jsPanel/3.10.0/jquery.jspanel.min.css" ,
            /** ztree*/
           "src/main/webapp/static/css/ztree/zTreeStyle.css" ,
            /** 左侧导航菜单*/
            "src/main/webapp/static/css/theme-default/ace.min.css" ,
            "src/main/webapp/static/css/adapt-strap/adapt-strap.css" ,
            /** select-tree*/
            "src/main/webapp/static/js/directives/thirdPart/angular-select-tree/0.1.0/angular-multi-select-tree.css" ,
            /** unlock*/
            "src/main/webapp/static/css/unlock/unlock.css" ,
             /** 左侧菜单图标样式*/
            "src/main/webapp/static/css/theme-default/icon-font.css"
        ]) //需要操作的文件
        .pipe(concat('min.css'))                            //- 合并后的文件名
            .pipe(minifycss())                                      //- 压缩处理成一行
            .pipe(gulp.dest('src/main/webapp/static/compress/css'));  
});
gulp.task('default',[ 'core','directive','service', 'home', 'css']);
// gulp.task('default',[ 'core','directive','service', 'home', 'css']);