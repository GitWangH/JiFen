(function(){
  "use strict";
  
  var app = angular
    .module('flexcalendar', ['pascalprecht.translate','mgcrea.ngStrap'])
    .directive('flexCalendar', flexCalendar);

  var defaultTranslation = angular
    .module('flexcalendar.defaultTranslation', ['flexcalendar'])
    .config(defaultTranslationConfig);

  var modalTemplateElement;
  var inputTittle;
  var userEventList = [];
  var storageInfo = [];
  defaultTranslationConfig.$inject = ['$translateProvider'];
  function defaultTranslationConfig($translateProvider){
    $translateProvider.translations('en', {
      JANUARY: 'January',
      FEBRUARY: 'February',
      MARCH: 'March',
      APRIL: 'April',
      MAI: 'Mai',
      JUNE: 'June',
      JULY: 'July',
      AUGUST: 'August',
      SEPTEMBER: 'September',
      OCTOBER: 'October',
      NOVEMBER: 'November',
      DECEMBER: 'December',

      SUNDAY: 'Sunday',
      MONDAY: 'Monday',
      TUESDAY: 'Tuesday',
      WEDNESDAY: 'Wednesday',
      THURSDAY: 'Thurday',
      FRIDAY: 'Friday',
      SATURDAY: 'Saturday'
    });
    $translateProvider.preferredLanguage('en');
    $translateProvider.useSanitizeValueStrategy('escape');
  }

  function flexCalendar() {

    var template =
    '<div class="flex-calendar">'+
      '<div class="row">'+
        '<div class="col-lg-offset-2 col-xs-2 col-sm-2 col-lg-3 text-right {{arrowPrevClass}}"><button class="btn btn-default btn-sm left-btn" ng-click="prevMonth()"><i class="glyphicon glyphicon-chevron-left"></i></button></div>'+
        '<div class="col-xs-8 col-sm-8 col-lg-4 calendar-header">{{selectedYear}}年<span>{{ selectedMonth | translate }}</span></div>'+
        '<div class="col-xs-2 col-sm-2 col-lg-3 {{arrowNextClass}}"><button class="btn btn-default btn-sm right-btn" ng-click="nextMonth()"><i class="glyphicon glyphicon-chevron-right"></i></button></div>'+
      '</div>'+
      '<div class="week">'+
        '<div class="day" ng-repeat="day in weekDays(options.dayNamesLength) track by $index">{{ day }}</div>'+
      '</div>'+
      '<div class="days" ng-repeat="week in weeks">'+
        '<div class="day"'+
          'ng-repeat="day in week track by $index"'+
          'ng-class="[getDayClass(day), {current: isDefaultDate(day), event: day.event[0], disabled: day.disabled, out: !day}]"'+
          'ng-click="onClick(day, $index, $event)"'+
        '>'+
          '<div class="everyday" ng-class="{selected: hasEvent && day.day == thisDay}">'+
            '<div class="number">'+
              '<div class="month-date">{{day.day}}</div>'+
              '<ul class="month-event" ng-if="day.day">'+
                '<li ng-if="exit.day == day.day" ng-repeat="exit in exitEvent">'+
//                    '<p style="padding:0px 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;text-align: center;">{{exit.name}}</p>'+
                '<a style="padding:0px 10px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;text-align: center;" href="javascript:;" ng-click="addNewEvent(exit,$index)">{{exit.name}}</a>'+
                '</li>'+
                '<li ng-repeat="userEvent in day.userEventList">'+
                    '<a href="javascript:;" ng-click="addNewEvent(userEvent,$index)">{{userEvent.eventName}}</a>'+
                '</li>'+
//                '<li class="addevent" ng-if="hasEvent && day.day == thisDay">'+
//                  '<a href="javascript:;" ng-class="{activeEvent: todayEvent && day.day == thisItem && $index == thisIndex}" ng-click="addNewEvent(day)">新建事件…</a>'+
//                '</li>'+
              '</ul>'+
            '</div>'+ 
          '</div>'+
        '</div>'+
      '</div>'+
    '</div>';

    var directive = {
      restrict: 'E',
      scope: {
        options: '=?',
        events: '=?'
      },
      template: template,
      controller: Controller
    };

    return directive;

  }

  Controller.$inject = ['$scope' , '$filter' , '$compile' , '$http' , 'shareData' , '$rootScope'];
  function Controller($scope , $filter , $compile , $http , shareData , $rootScope) {


    $scope.days = [];
    $scope.options = $scope.options || {};
    $scope.events = $scope.events || [];
    $scope.options.dayNamesLength = $scope.options.dayNamesLength || 1;
    $scope.options.mondayIsFirstDay = $scope.options.mondayIsFirstDay || false;

    $scope.eventStartTime = {
        dateFormat :"yyyy-MM-dd",//日期格式
        autoclose :false,//是否自动关闭 boolean
        dateType :"string",//预期类型  date | number | unix | iso | string
        timeFormat:"H:mm:ss"
    };

    $scope.eventEndTime = {
        dateFormat :"yyyy-MM-dd",//日期格式
        autoclose :false,//是否自动关闭 boolean
        dateType :"string",//预期类型  date | number | unix | iso | string
        timeFormat:"H:mm:ss"
    };

    $scope.onClick = onClick;
    $scope.allowedPrevMonth = allowedPrevMonth;
    $scope.allowedNextMonth = allowedNextMonth;
    $scope.weekDays = weekDays;
    $scope.isDefaultDate = isDefaultDate;
    $scope.prevMonth = prevMonth;
    $scope.nextMonth = nextMonth;

    $scope.getDayClass = getDayClass;
    $scope.addNewEvent = addNewEvent;
    $scope.arrowPrevClass = "visible";
    $scope.arrowNextClass = "visible";

    // $scope.userEventList = userEventList;
    $scope.remove = remove;
    $scope.save = save;



   /* 查询当前月份下每一天已存在的事件   by Wing  2016/10/24
     parms 月份，如果parms 为undefinded 为默认查询当前月份的已存在事件 parms为数值则查询用户所选的月份进行查询 */

      var loadExitEvent = function(month,year){
    	  var queryPage = new QueryPage(1,10,"id desc","false");
    	    //定义搜索框
    	    var month_temp = new queryParam('日期所在月','beginTime','dateMonth','like');
    	    if(undefined == month && undefined == year){
    	    	var currDate = new Date();
    	    	//如果传入值为空则设置默认值
    	    	month_temp.value = currDate.getFullYear() + '-' +parseInt(currDate.getMonth()+1);
    	    }else{
    	    	//指定传入的年/月
    	    	var moth_var = monthTran(month);
    	    	month_temp.value = year+'-'+moth_var;
    	    }
    	   queryPage.addParam(month_temp);
    	   
    	   var fillPersonId = new queryParam('填写人','fillPersonId','string','=');
		    fillPersonId.isShow = false;
		    if(undefined != shareData.personalLogId){
		    	fillPersonId.value = shareData.personalLogId;
		    }else{
		    	fillPersonId.value = $rootScope.acctId;
		    }
		    
		    var operator = new queryParam('操作人','operator','string','=');
		    operator.isShow = false;
		    if(undefined != shareData.personalScheduleId){
		    	operator.value = shareData.personalScheduleId;
		    }else{
		    	operator.value = $rootScope.acctId;
		    }
    	   $scope.URL = '';
    	   if(undefined != shareData.monthType && shareData.monthType == 'schedule'){
    		   /* 今日要览日历数据显示  */
    		   var type = new queryParam('类型','type','string','=','1');
    		   queryPage.addParam(type);
    		    queryPage.addParam(operator);
    		   $scope.URL = URL_PATH.OA_HEADER+'/oaWorkSchedule/queryForMonth';
    		   
    	   }else if(undefined != shareData.monthType && shareData.monthType == 'log'){
    		   /* 工作日志日历数据显示  */
    		    queryPage.addParam(fillPersonId);
    		   $scope.URL = URL_PATH.OA_HEADER+'/oaWorkLog/queryForMonth';
    		   
    	   }/*else{
    		    queryPage.addParam(fillPersonId);
    		   $scope.URL = URL_PATH.OA_HEADER+'/oaWorkLog/queryForMonth';
    		   
    	   }*/
    	   if(cnex4_isEmpty_str($scope.URL)){
    		   $http.post($scope.URL,queryPage).success(function (data) {
    			   console.log(data.content);
    			   $scope.exitEvent = [];
    			   if(undefined != data.content && data.content.length > 0){
    				   for(var i = 0 ; i< data.content.length;i++){
    					   var month_ = data.content[i].beginTime.split(" ")[0].split("-")[1];
    					   var day_ = data.content[i].beginTime.split(" ")[0].split("-")[2];
    					   var obj = new Object();
    					   obj = data.content[i];
    					   obj.day = day_;
    					   obj.eventName = obj.name;
    					   obj.month = month_;
    					   $scope.exitEvent.push(obj);
    					   var eventnameTemp = obj.name;
    					   obj.eventStartTimeminValue = parserDate(obj.beginTime);
    					   obj.eventStartTimemaxValue = parserDate(obj.beginTime);
    					   obj.eventEndTimeminValue = parserDate(obj.endTime);
    					   obj.eventEndTimemaxValue = parserDate(obj.endTime);
    					   obj.eventAddress = obj.address;
    					   obj.eventNote = obj.content;
    					   storageInfo[eventnameTemp] = obj;
    					   
    				   }
    			   }
    			   
    		   })
    	   }

        if(undefined != shareData.selectMonthData && shareData.selectMonthData.length >0){
          var selectDateMonth = shareData.selectMonthData[0];
          var enMonth =["1","2","3","4","5","6","7","8","9","10","11","12"];
          selectDateMonth = monthTran(selectDateMonth);
          var currIndex = selectDateMonth;
          $scope.selectedMonth = MONTHS [currIndex -1];
          $scope.selectedYear = year;
          var month = {name: $scope.selectedMonth, index: currIndex + 1, _index: currIndex+2 };
          var year = {name: $scope.selectedYear, index: currIndex + 1, _index: currIndex+2 };
          $scope.options.changeMonth(month, year);
          shareData.selectMonthData = [];
        }
  };
  
	  /**
	   * 日期转换
	   */
	  var parserDate = function (date) {  
		    var t = Date.parse(date);  
		    if (!isNaN(t)) {  
		        return new Date(Date.parse(date.replace(/-/g, "/")));  
		    } else {  
		        return new Date();  
		    }  
		}; 
  
	  /**
	   * 将月份由英文转换为对应的数字
	   */
	  var monthTran = function(selectDateMonth){
		  switch (selectDateMonth){
	      case 'JANUARY':selectDateMonth = 1;break;
	      case 'FEBRUARY':selectDateMonth = 2 ;break;
	      case 'MARCH':selectDateMonth = 3;break;
	      case 'APRIL':selectDateMonth = 4 ;break;
	      case 'MAY':selectDateMonth = 5 ;break;
	      case 'JUNE':selectDateMonth = 6 ;break;
	      case 'JULY':selectDateMonth = 7 ;break;
	      case 'AUGUST':selectDateMonth = 8 ;break;
	      case 'SEPTEMBER':selectDateMonth = 9 ;break;
	      case 'OCTOBER':selectDateMonth = 10 ;break;
	      case 'NOVEMBER':selectDateMonth = 11 ;break;
	      case 'DECEMBER':selectDateMonth = 12 ;break;
	    }
		  return selectDateMonth;
	  }

  	shareData.loadExitEvent = loadExitEvent;
    var $translate = $filter('translate');

    var MONTHS = ['JANUARY', 'FEBRUARY', 'MARCH', 'APRIL', 'MAI', 'JUNE', 'JULY', 'AUGUST', 'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER'];
    var WEEKDAYS = ['SUNDAY' , 'MONDAY' , 'TUESDAY' , 'WEDNESDAY' , 'THURSDAY' , 'FRIDAY' , 'SATURDAY'];

    if($scope.options.mondayIsFirstDay)
    {
      var sunday = WEEKDAYS.shift();
      WEEKDAYS.push(sunday)
    }

    if ($scope.options.minDate) {
      $scope.options.minDate = new Date($scope.options.minDate);
    }

    if ($scope.options.maxDate) {
      $scope.options.maxDate = new Date($scope.options.maxDate);
    }

    if($scope.options.disabledDates) {
      createMappedDisabledDates();
    }

    if($scope.events)
    {
      createMappedEvents();
    }

    registerEvents();

    function createMappedDisabledDates(){
      if(!$scope.options.disabledDates) return;
      $scope.mappedDisabledDates = $scope.options.disabledDates.map(function(date)
      {
        return new Date(date);
      });
    }

    function createMappedEvents(){
      $scope.mappedEvents = $scope.events.map(function(obj)
      {
        obj.date = new Date(obj.date);
        return obj;
      });
    }

    function registerEvents(){
      var prevMonthEvent = $scope.options.prevMonthEvent || 'flexcalendar:prevMonthEvent';
      $scope.$on(prevMonthEvent, prevMonth);

      var nextMonthEvent = $scope.options.nextMonthEvent || 'flexcalendar:nextMonthEvent';
      $scope.$on(nextMonthEvent, nextMonth);
    }

    $scope.$watch('options.defaultDate', function() {
      calculateSelectedDate();
    });

    $scope.$watch('options.disabledDates', function() {
      if($scope.options.disabledDates) {
          createMappedDisabledDates();
          calculateDisabledDates();
      }
    });

    $scope.$watch('events', function() {
      createMappedEvents();
      calculateWeeks();
    });

    $scope.$watch('weeks', function(weeks) {
      var filteredEvents = [];
      angular.forEach(weeks, function(week) {
        angular.forEach(week, function (day){
          if(day && day.event){
            angular.forEach(day.event, function(event) {
              filteredEvents.push(event);
            });
          }
        });
      });
      if('function' === typeof $scope.options.filteredEventsChange){
        $scope.options.filteredEventsChange(filteredEvents);
      }
    });

    $scope.$watch('selectedYear', function(year, previousYear) {
      if(year !== previousYear) calculateWeeks();
    });
    $scope.$watch('selectedMonth', function(month, previousMonth) {
      if(month !== previousMonth) calculateWeeks();
    });


    function onClick(date, index, domEvent) {      
      if (!date || date.disabled) { 
          return; 
      }else{
        $scope.hasEvent = true;
        $scope.thisDay = date.day;
      }
      // $scope.options.defaultDate = date.date;
      // if (date.event.length && $scope.options.eventClick) {
      //   $scope.options.eventClick(date, domEvent);
      // }
      
      // $scope.options.dateClick(date, domEvent);
    }

    function bindEvent(date) {
      if (!date || !$scope.mappedEvents) { return; }
      date.event = [];
      $scope.mappedEvents.forEach(function(event) {
        if (date.date.getFullYear() === event.date.getFullYear()
            && date.date.getMonth() === event.date.getMonth()
            && date.date.getDate() === event.date.getDate()) {
          date.event.push(event);
        }
      });
    }

    function allowedDate(date) {
      if (!$scope.options.minDate && !$scope.options.maxDate) {
        return true;
      }
      var currDate = date.date;
      if ($scope.options.minDate && (currDate < $scope.options.minDate)) { return false; }
      if ($scope.options.maxDate && (currDate > $scope.options.maxDate)) { return false; }
      return true;
    }

    function disabledDate(date) {
      if (!$scope.mappedDisabledDates) return false;
      for(var i = 0; i < $scope.mappedDisabledDates.length; i++){
        if(date.year === $scope.mappedDisabledDates[i].getFullYear() && date.month === $scope.mappedDisabledDates[i].getMonth() && date.day === $scope.mappedDisabledDates[i].getDate()){
          return true;
          break;
        }
      }
    }

    function allowedPrevMonth() {
      var prevYear = null;
      var prevMonth = null;
      if (!$scope.options.minDate) { return true; }
      var currMonth = MONTHS.indexOf($scope.selectedMonth);
      if (currMonth === 0) {
        prevYear = ($scope.selectedYear - 1);
        prevMonth = 11;
      } else {
        prevYear = $scope.selectedYear;
        prevMonth = (currMonth - 1);
      }
      if (prevYear < $scope.options.minDate.getFullYear()) { return false; }
      if (prevYear === $scope.options.minDate.getFullYear()) {
        if (prevMonth < $scope.options.minDate.getMonth()) { return false; }
      }
      return true;
    }

    function allowedNextMonth() {
      var nextYear = null;
      var nextMonth = null;
      if (!$scope.options.maxDate) { return true; }
      var currMonth = MONTHS.indexOf($scope.selectedMonth);
      if (currMonth === 11) {
        nextYear = ($scope.selectedYear + 1);
        nextMonth = 0;
      } else {
        nextYear = $scope.selectedYear;
        nextMonth = (currMonth + 1);
      }
      if (nextYear > $scope.options.maxDate.getFullYear()) { return false; }
      if (nextYear === $scope.options.maxDate.getFullYear()) {
        if (nextMonth > $scope.options.maxDate.getMonth()) { return false; }
      }
      return true;
    }

    function calculateWeeks() {
      $scope.weeks = [];
      var week = null;
      var daysInCurrentMonth = new Date($scope.selectedYear, MONTHS.indexOf($scope.selectedMonth) + 1, 0).getDate();

      for (var day = 1; day < daysInCurrentMonth + 1; day += 1) {
        var date = new Date($scope.selectedYear, MONTHS.indexOf($scope.selectedMonth), day);
        var dayNumber = new Date($scope.selectedYear, MONTHS.indexOf($scope.selectedMonth), day).getDay();
        if($scope.options.mondayIsFirstDay)
        {
          dayNumber = (dayNumber + 6) % 7;
        }
        week = week || [null, null, null, null, null, null, null];
        week[dayNumber] = {
          year: $scope.selectedYear,
          month: MONTHS.indexOf($scope.selectedMonth),
          day: day,
          date: date,
          _month : date.getMonth() + 1
        };

        if (allowedDate(week[dayNumber])) {
          if ($scope.mappedEvents) { bindEvent(week[dayNumber]); }
        } else {
          week[dayNumber].disabled = true;
        }

        if (week[dayNumber] && disabledDate(week[dayNumber])) {
          week[dayNumber].disabled = true;
        }

        if (dayNumber === 6 || day === daysInCurrentMonth) {
          $scope.weeks.push(week);
          week = undefined;
        }
      }
      (!$scope.allowedPrevMonth()) ? $scope.arrowPrevClass = "hidden" : $scope.arrowPrevClass = "visible";
      (!$scope.allowedNextMonth()) ? $scope.arrowNextClass = "hidden" : $scope.arrowNextClass = "visible";
    }

    function calculateSelectedDate() {
      if ($scope.options.defaultDate) {
        $scope.options._defaultDate = new Date($scope.options.defaultDate);
      } else {
        $scope.options._defaultDate = new Date();
      }

      $scope.selectedYear  = $scope.options._defaultDate.getFullYear();
      $scope.selectedMonth = MONTHS[$scope.options._defaultDate.getMonth()];
      $scope.selectedDay   = $scope.options._defaultDate.getDate();
    }

    /* 查询当前月份是否有已存在的事件 by Wing */
    loadExitEvent($scope.selectedMonth,$scope.selectedYear);

    function calculateDisabledDates() {
      if (!$scope.mappedDisabledDates || $scope.mappedDisabledDates.length === 0) return;
      for(var i = 0; i < $scope.mappedDisabledDates.length; i++){
        $scope.mappedDisabledDates[i] = new Date($scope.mappedDisabledDates[i]);
      }
      calculateWeeks();
    }

    function weekDays(size) {
      return WEEKDAYS.map(function(name) { return $translate(name).slice(0, size); });
    }

    function isDefaultDate(date) {
      if (!date) { return; }
      var result = date.year === $scope.options._defaultDate.getFullYear() &&
        date.month === $scope.options._defaultDate.getMonth() &&
        date.day === $scope.options._defaultDate.getDate();
      return result;
    }

    function prevMonth() {
      if (!$scope.allowedPrevMonth()) { return; }
      var currIndex = MONTHS.indexOf($scope.selectedMonth);
      if (currIndex === 0) {
        $scope.selectedYear -= 1;
        $scope.selectedMonth = MONTHS[11];
      } else {
        $scope.selectedMonth = MONTHS[currIndex - 1];
      }
      var month = {name: $scope.selectedMonth, index: currIndex - 1, _index: currIndex+2 };
      $scope.options.changeMonth(month, $scope.selectedYear);

        /* 点击前一个月时，去查询上一个月是否有已存在事件 by Wing */
        loadExitEvent(month.name,$scope.selectedYear);
    }

    function nextMonth() {

      if (!$scope.allowedNextMonth()) { return; }
      var currIndex = MONTHS.indexOf($scope.selectedMonth);
      if (currIndex === 11) {
        $scope.selectedYear += 1;
        $scope.selectedMonth = MONTHS[0];
      } else {
        $scope.selectedMonth = MONTHS[currIndex + 1];
      }
      var month = {name: $scope.selectedMonth, index: currIndex + 1, _index: currIndex+2 };
      $scope.options.changeMonth(month, $scope.selectedYear);

        /* 点击下一个月时，去查询下一个月是否有已存在事件 by Wing */
        loadExitEvent(month.name,$scope.selectedYear);
    }

    function getDayClass(day){
      if (!day || !day.event || day.event.length === 0){
        return '';
      }
      return day.event.map(function(e){return e.eventClass||'';}).join(' ');
    }

    function addNewEvent(day,index){
        /* 根据入参判断是执行新建事件还是显示已建事件。index为已建事件  by wing  2016/9/6 */
        if(index === null || index === undefined|| index === ""){
                $scope.eventName ='';
                $scope.eventStartTime.minValue = '';
                $scope.eventStartTime.maxValue='';
                $scope.eventEndTime.minValue ='';
                $scope.eventEndTime.maxValue='';
                $scope.eventAddress ='';
                $scope.eventNote='';
        }else{
            inputTittle = day.eventName;
            /* 判断用户输入的数据，如果storageInfo里面有则显示storageInfo信息，没有则显示空的  by wing  2016/9/6 */
            if(storageInfo && storageInfo[inputTittle]){
                $scope.eventName = storageInfo[inputTittle].eventName;
                $scope.eventStartTime.minValue = storageInfo[inputTittle].eventStartTimeminValue;
                $scope.eventStartTime.maxValue = storageInfo[inputTittle].eventStartTimemaxValue;
                $scope.eventEndTime.minValue = storageInfo[inputTittle].eventEndTimeminValue;
                $scope.eventEndTime.maxValue = storageInfo[inputTittle].eventEndTimemaxValue;
                $scope.eventAddress = storageInfo[inputTittle].eventAddress;
                $scope.eventNote = storageInfo[inputTittle].eventNote;
            }else{
                $scope.eventName ='';
                $scope.eventStartTime.minValue = '';
                $scope.eventStartTime.maxValue='';
                $scope.eventEndTime.minValue ='';
                $scope.eventEndTime.maxValue='';
                $scope.eventAddress ='';
                $scope.eventNote='';
            }
        }

        $scope.todayEvent = true;
        $scope.day = day;
        $scope.thisIndex = index;

        var modalTemplate = 
        '<div class="modal-open">'+
            '<div class="modal-backdrop am-fade"></div>'+
            '<div class="modal" tabindex="-1" role="dialog" style="display:block;">'+
                '<div class="modal-dialog" style="width:600px;">'+
                      '<div class="modal-content">'+
                            '<div class="modal-header">'+
                                '<button type="button" class="close" ng-click="remove()">&times;</button>'+
                                '<h4 class="modal-title">添加事件</h4>'+
                            '</div>'+
                            '<div class="modal-body">'+
                                '<div class="form">'+
                                    '<div class="form-column">'+
                                        '<div class="column-group">'+
                                            '<form class="form-horizontal" id="calendarForm" name="calendarForm" novalidate="">'+
                                                '<div class="container-fluid">'+
                                                    '<div class="row">'+
                                                        '<div class="col-xs-12">'+
                                                            '<div class="form-group">'+
                                                                '<label class="col-xs-2 control-label">事件名称</label>'+
                                                                '<div class="col-xs-9 item">'+
                                                                    '<input type="text" class="form-control" ng-model="eventName" required=""/>'+
                                                                '</div>'+
                                                            '</div>'+
                                                        '</div>'+
                                                        '<div class="col-xs-12">'+
                                                            '<div class="form-group">'+
                                                                '<label class="col-xs-2 control-label">开始时间</label>'+
                                                                '<div class="col-xs-6 item">'+
                                                                    '<input type="text" class="form-control"'+
                                                                        'ng-model="eventStartTime.minValue"'+
                                                                        'data-date-format="{{eventStartTime.dateFormat}}"'+
                                                                        'data-autoclose="{{eventStartTime.autoclose}}"'+
                                                                        'data-date-type="{{eventStartTime.dateType}}"'+
                                                                        'bs-datepicker>'+
                                                                '</div>'+
                                                                '<div class="col-xs-3 item">'+
                                                                    '<input type="text" class="form-control"'+
                                                                        'ng-model="eventStartTime.maxValue"'+
                                                                        'data-time-format="{{eventStartTime.timeFormat}}"'+
                                                                        'data-autoclose="{{eventStartTime.autoclose}}"'+
                                                                        'data-time-type="{{eventStartTime.timeType}}"'+
                                                                        'bs-timepicker>'+
                                                                '</div>'+
                                                            '</div>'+
                                                        '</div>'+
                                                        '<div class="col-xs-12">'+
                                                            '<div class="form-group">'+
                                                                '<label class="col-xs-2 control-label">结束时间</label>'+
                                                                '<div class="col-xs-6 item">'+
                                                                    '<input type="text" class="form-control"'+
                                                                        'ng-model="eventEndTime.minValue"'+
                                                                        'data-date-format="{{eventEndTime.dateFormat}}"'+
                                                                        'data-autoclose="{{eventEndTime.autoclose}}"'+
                                                                        'data-date-type="{{eventEndTime.dateType}}"'+
                                                                        'bs-datepicker>'+
                                                                '</div>'+
                                                                '<div class="col-xs-3 item">'+
                                                                    '<input type="text" class="form-control"'+
                                                                        'ng-model="eventEndTime.maxValue"'+
                                                                        'data-time-format="{{eventEndTime.timeFormat}}"'+
                                                                        'data-autoclose="{{eventEndTime.autoclose}}"'+
                                                                        'data-time-type="{{eventEndTime.timeType}}"'+
                                                                        'bs-timepicker>'+
                                                                '</div>'+
                                                            '</div>'+
                                                        '</div>'+
                                                        '<div class="col-xs-12">'+
                                                            '<div class="form-group">'+
                                                                '<label class="col-xs-2 control-label">地址</label>'+
                                                                '<div class="col-xs-9 item">'+
                                                                    '<input type="text" class="form-control" ng-model="eventAddress" required=""/>'+
                                                                '</div>'+
                                                            '</div>'+
                                                        '</div>'+
                                                        '<div class="col-xs-12">'+
                                                            '<div class="form-group">'+
                                                                '<label class="col-xs-2 control-label">备注</label>'+
                                                                '<div class="col-xs-9 item">'+
                                                                    '<textarea class="form-control" ng-model="eventNote"></textarea>'+
                                                                '</div>'+
                                                            '</div>'+
                                                        '</div>'+
                                                    '</div>'+
                                                '</div>'+
                                            '</form>'+
                                        '</div>'+
                                    '</div>'+
                               '</div>'+
                            '</div>'+
                            '<div class="modal-footer">'+
                              '<button class="btn btn-clear" ng-click="remove()">取消</button>'+
//                              '<button class="btn btn-danger" type="button" ng-click="save()" ng-disabled="calendarForm.$invalid">确定</button>'+
                            '</div>'+
                      '</div>'+
                '</div>'+
            '</div>'+
        '</div>';
    	var modalTemplate = angular.element(modalTemplate);
    	modalTemplateElement = $compile(modalTemplate)($scope);
    	angular.element(document.body).append(modalTemplateElement);
    }

    /*事件*/
    function remove(){
        if (modalTemplateElement) {
            modalTemplateElement.remove();
        }
    }

    function save(){
        inputTittle = $scope.eventName;
        var eventName = $scope.eventName;
        var eventStartTime = $scope.eventStartTime.minValue + ' ' + $scope.eventStartTime.maxValue;
        var eventEndTime = $scope.eventEndTime.minValue + ' ' + $scope.eventEndTime.maxValue;
        var eventAddress = $scope.eventAddress;
        var eventNote = $scope.eventNote;
        /* 把界面输入的数据存进storageInfo中  by wing  2016/9/7 */
         storageInfo[inputTittle] = {
            eventName:$scope.eventName,
            eventStartTimeminValue:$scope.eventStartTime.minValue,
            eventStartTimemaxValue:$scope.eventStartTime.maxValue,
            eventEndTimeminValue:$scope.eventEndTime.minValue,
            eventEndTimemaxValue:$scope.eventEndTime.maxValue,
            eventAddress:$scope.eventAddress,
            eventNote:$scope.eventNote
        };

        var event = {eventName:eventName,eventStartTime:eventStartTime,eventEndTime:eventEndTime,eventAddress:eventAddress,eventNote:eventNote};
        if(undefined == $scope.day.userEventList){
            $scope.day.userEventList = [];
        }
        $scope.day.userEventList.push(event);
        modalTemplateElement.remove();
    }
  }

})();
