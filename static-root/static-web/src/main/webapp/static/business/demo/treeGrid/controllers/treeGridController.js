angular.module('huatek.controllers').controller('treeGridDemoController', function($scope, $timeout, $http, $compile, treeGridService) {

    var columnsArr = [
        { field: "name", name: "名称", width: "30%" },
        { field: "priceRange", name: "价格区间", width: "50%" }
    ];

    treeGridService.init($scope, columnsArr,"/api/otherContractDetail/queryTopLevel","/api/otherContractDetail/queryChildNodes/","/api/otherContractDetail/saveData");

    $scope.huatekTree.loadData();
    // var huatekTree = $scope.huatekTree = {
    //     columns: [
    //         { field: "name", name: "名称", width: "30%" },
    //         { field: "priceRange", name: "价格区间", width: "50%" }
    //     ],
    //     all: { launch: false, checked: false, topLevelCount: 0 },
    //     modify: { btnShow: false, panelShow: false, offTop: 0, rowIndex: 0, row: {} },
    //     addtop: { isShow: false, row: {} },
    //     child: { isShow: false, orderNumber: 1, rowIndex: -1, row: {}, parentIndex: -1 },
    //     rows: [],
    //     editMap: new Map(),
    //     lookChild: function(index, row, callBack) {
    //         /*展开或者收缩单个节点*/
    //         if (!row.launch) {
    //             if (row.looking == true) {
    //                 for (var i = index + 1; i < this.rows.length; i++) {
    //                     if (this.rows[i].groupLevel <= row.groupLevel) {
    //                         break;
    //                     } else {
    //                         this.rows[i].isShow = true;
    //                     }
    //                 }
    //             } else {
    //                 loadChild(this.rows, index, callBack);
    //                 row.looking = true;
    //             }
    //             row.launch = true;
    //         } else {
    //             for (var i = index + 1; i < this.rows.length; i++) {
    //                 if (this.rows[i].groupLevel <= row.groupLevel) {
    //                     break;
    //                 } else {
    //                     this.rows[i].isShow = false;
    //                 }
    //             }
    //             row.launch = false;
    //         }
    //     },
    //     allLaunch: function() {
    //         /*展开所有节点*/
    //         if (this.rows.length > 0) {
    //             if (this.all.launch) {
    //                 this.all.launch = false;
    //                 for (var i = 0; i < this.rows.length; i++) {
    //                     if (this.rows[i].groupLevel <= 1) {
    //                         this.rows[i].launch = false;
    //                     } else {
    //                         this.rows[i].launch = false;
    //                         this.rows[i].isShow = false;
    //                     }
    //                 }
    //             } else {
    //                 this.all.launch = true;
    //                 for (var i = 0; i < this.rows.length; i++) {
    //                     if (this.rows[i].groupLevel <= 1) {
    //                         this.rows[i].launch = true;
    //                     } else {
    //                         if (this.rows[i].looking) {
    //                             this.rows[i].launch = false;
    //                         }
    //                         this.rows[i].isShow = true;
    //                     }
    //                 }
    //             }
    //         }
    //     },
    //     allChecked: function() {
    //         /*选中所有节点*/
    //         if (this.rows.length > 0) {
    //             if (this.all.checked) {
    //                 this.all.checked = false;
    //                 for (var i = 0; i < this.rows.length; i++) {
    //                     this.rows[i].checked = false;
    //                 }
    //             } else {
    //                 this.all.checked = true;
    //                 for (var i = 0; i < this.rows.length; i++) {
    //                     this.rows[i].checked = true;
    //                 }
    //             }
    //         }
    //     },
    //     addTopNode: function() {
    //         /*添加顶级节点*/
    //         this.addtop.isShow = true;
    //         this.addtop.row = {
    //             "id": uuid(10),
    //             "parentId": 0,
    //             "groupLevel": 1,
    //             "isLeaf": 1,
    //             "launch": false,
    //             "isShow": true,
    //             "orderNumber": this.all.topLevelCount + 1
    //         }
    //     },
    //     saveTopNode: function() {
    //         /*保存顶级节点*/
    //         this.addtop.isShow = false;
    //         this.rows.push(cloneObj(this.addtop.row));
    //         this.addtop.row = {};
    //         this.addToEditMap(this.addtop.row, "add");
    //         this.all.topLevelCount += 1;
    //     },
    //     cacelTopNode: function() {
    //         /*取消保存顶级节点*/
    //         this.addtop.isShow = false;
    //     },
    //     editNode: function($event, index, row) {
    //         /*编辑当前节点*/
    //         this.modify.panelShow = true;
    //         this.modify.offTop = $($event.target).offset().top - 167;
    //         this.modify.rowIndex = index;
    //         this.modify.row = cloneObj(row);
    //     },
    //     saveNode: function() {
    //         /*保存当前节点*/
    //         this.modify.panelShow = false;
    //         this.modify.offTop = 0;
    //         this.rows[this.modify.rowIndex] = cloneObj(this.modify.row);
    //         this.modify.rowIndex = -1;
    //         this.modify.row = {};
    //     },
    //     cancelNode: function() {
    //         /*取消保存当前节点*/
    //         this.modify.panelShow = false;
    //         this.modify.offTop = 0;
    //         // this.modify.row = {};
    //     },
    //     deleteNode: function(index, row) {
    //         /*删除当前节点*/
    //         if (row.isLeaf == 1) {
    //             /*先判断当前节点是否有父节点，如果有，则判断当前父节点的子节点个数，如果只有当前一个子节点，则将父节点的isLeaf设置成true*/
    //             if (row.parent != 0) {
    //                 if (this.rows.length == index + 1) {
    //                     if (this.rows[index - 1].parentId != row.parentId) {
    //                         this.rows[index - 1].isLeaf = 1;
    //                         /*将节点放到编辑map中*/
    //                         this.addToEditMap(this.rows[index - 1], "update");
    //                     }
    //                 } else {
    //                     if (this.rows[index - 1].parentId != row.parentId && this.rows[index + 1].parentId != row.parentId) {
    //                         this.rows[index - 1].isLeaf = 1;
    //                         this.addToEditMap(this.rows[index - 1], "update");
    //                     }
    //                 }
    //             }
    //             /*将节点放到编辑map中*/
    //             this.addToEditMap(row, "delete");
    //             /*删除当前节点*/
    //             this.rows.splice(index, 1);
    //         } else {
    //             submitTips('警告：当前节点有子节点，不能删除。', 'warning');
    //             return;
    //         }
    //     },
    //     moveNode: function(index, row, moveModel) {
    //         /*节点的上移和下移*/
    //         var rowIndex = index,
    //             beforeRowIndex = -1,
    //             afterRowBeginIndex = -1,
    //             afterRowEndIndex = -1;
    //         for (var i = index - 1; i >= 0; i--) {
    //             if (this.rows[i].groupLevel == row.groupLevel) {
    //                 beforeRowIndex = i;
    //                 break;
    //             }
    //             if (this.rows[i].groupLevel < row.groupLevel) {
    //                 break;
    //             }
    //         }
    //         for (var j = index + 1; j < this.rows.length; j++) {
    //             if (this.rows[j].groupLevel == row.groupLevel) {
    //                 if (afterRowBeginIndex == -1) {
    //                     afterRowBeginIndex = j;
    //                     continue;
    //                 }
    //             }
    //             if (this.rows[j].groupLevel <= row.groupLevel) {
    //                 if (afterRowBeginIndex != -1) {
    //                     afterRowEndIndex = j;
    //                 } else {
    //                     afterRowBeginIndex = j;
    //                     afterRowEndIndex = j;
    //                 }
    //                 break;
    //             }
    //         }
    //         if (beforeRowIndex == -1 && moveModel == TREE_NODE_UP) {
    //             submitTips('警告：当前节点不能上移。', 'warning');
    //             return;
    //         } else if (afterRowBeginIndex == -1 && moveModel == TREE_NODE_DOWN) {
    //             submitTips('警告：当前节点不能下移。', 'warning');
    //             return;
    //         }
    //         if (moveModel == TREE_NODE_UP) {
    //             /*更新节点的orderNumber*/
    //             var beforeRow = this.rows[beforeRowIndex];
    //             var beforeOrder = beforeRow.orderNumber;
    //             beforeRow.orderNumber = row.orderNumber;
    //             row.orderNumber = beforeOrder;
    //             this.addToEditMap(beforeRow, "update");
    //             this.addToEditMap(row, "update");
    //             /*重新构造树的rows数组*/
    //             var firstArr = this.rows.slice(0, beforeRowIndex),
    //                 secondArr = this.rows.slice(beforeRowIndex, rowIndex),
    //                 thirdArr = afterRowBeginIndex == -1 ? this.rows.slice(rowIndex) : this.rows.slice(rowIndex, afterRowBeginIndex),
    //                 fourthArr = afterRowBeginIndex == -1 ? [] : this.rows.slice(afterRowBeginIndex);
    //             this.rows = firstArr.concat(thirdArr, secondArr, fourthArr);
    //         } else {
    //             /*更新节点的orderNumber*/
    //             var afterRow = this.rows[afterRowBeginIndex];
    //             var afterOrder = afterRow.orderNumber;
    //             afterRow.orderNumber = row.orderNumber;
    //             row.orderNumber = afterOrder;
    //             this.addToEditMap(afterRow, "update");
    //             this.addToEditMap(row, "update");
    //             /*重新构造树的rows数组*/
    //             var firstArr = this.rows.slice(0, rowIndex),
    //                 secondArr = this.rows.slice(rowIndex, afterRowBeginIndex),
    //                 thirdArr = afterRowEndIndex == -1 ? this.rows.slice(afterRowBeginIndex) : this.rows.slice(afterRowBeginIndex, afterRowEndIndex),
    //                 fourthArr = afterRowEndIndex == -1 ? [] : this.rows.slice(afterRowEndIndex);
    //             this.rows = firstArr.concat(thirdArr, secondArr, fourthArr);
    //         }
    //     },
    //     addChild: function(index, row) {
    //         /*给当前节点添加子节点*/
    //         // this.child.rowIndex = index;
    //         if (row.isLeaf == 0 && row.launch == false) {
    //             this.lookChild(index, row, this.createChildNode);
    //         } else {
    //             this.createChildNode(this, index, row);
    //         }
    //     },
    //     createChildNode: function(treeGrid, index) {
    //         treeGrid.child.rowIndex = index;
    //         if (index + 1 != treeGrid.rows.length) {
    //             for (var i = index + 1; i < treeGrid.rows.length; i++) {
    //                 ++treeGrid.child.rowIndex;
    //                 if (treeGrid.rows[i].groupLevel == treeGrid.rows[index].groupLevel + 1) {
    //                     treeGrid.child.orderNumber = treeGrid.rows[i].orderNumber + 1;
    //                 }
    //                 if (treeGrid.rows[i].groupLevel < treeGrid.rows[index].groupLevel + 1) {
    //                     break;
    //                 }
    //             }
    //         }
    //         ++treeGrid.child.rowIndex;
    //         treeGrid.child.row = {
    //             "id": uuid(10),
    //             "parentId": treeGrid.rows[index].id,
    //             "groupLevel": treeGrid.rows[index].groupLevel + 1,
    //             "isLeaf": 1,
    //             "launch": false,
    //             "isShow": true,
    //             "orderNumber": treeGrid.child.orderNumber
    //         };
    //         treeGrid.child.parentIndex = index;
    //         treeGrid.child.isShow = true;
    //     },
    //     saveChild: function() {
    //         /*保存当前添加的子节点*/
    //         var parent = this.rows[this.child.parentIndex];
    //         if (parent.isLeaf == 1) {
    //             parent.isLeaf = 0;
    //             this.addToEditMap(parent, "update");
    //         }
    //         this.addToEditMap(this.child.row, "add");
    //         this.rows.insert(this.child.rowIndex, cloneObj(this.child.row));
    //         this.cancelChild();
    //     },
    //     cancelChild: function() {
    //         /*取消保存当前添加的子节点*/
    //         this.child.isShow = false;
    //         this.child.rowIndex = -1;
    //         this.child.orderNumber = -1;
    //         this.child.parentIndex = -1;
    //         this.child.row = {};
    //     },
    //     addToEditMap: function(row, operation) {
    //         /*将操作过的节点放放到map中*/
    //         row.operation = operation;
    //         var obj = cloneObj(row);
    //         if (this.editMap.get(obj.id)) {
    //             this.editMap.remove(obj.id);
    //             this.editMap.put(obj.id, obj);
    //         } else {
    //             this.editMap.put(obj.id, obj);
    //         }
    //     },
    //     getTreeShape: function(index, groupLevel) {
    //         /*节点树状样式*/
    //         var styleStr = "";
    //         if (index == 0 || index == '0') {
    //             switch (groupLevel) {
    //                 case 1:
    //                     styleStr = "";
    //                     break;
    //                 case 2:
    //                     styleStr = "margin-left:10px";
    //                     break;
    //                 case 3:
    //                     styleStr = "margin-left:20px";
    //                     break;
    //                 case 4:
    //                     styleStr = "margin-left:30px";
    //                     break;
    //                 default:
    //                     styleStr = "";
    //                     break;
    //             }
    //         } else {
    //             styleStr = "";
    //         }
    //         return styleStr;
    //     },
    //     loadData: function(res) {
    //         /*第一次加载树的数据*/
    //         res = [{
    //             "id": 1,
    //             "parentId": 0,
    //             "name": "Bmw",
    //             "priceRange": "30k to 200k",
    //             "groupLevel": 1,
    //             "isLeaf": 0,
    //             "launch": false,
    //             "isShow": true,
    //             "orderNumber": 1
    //         }];
    //         this.rows = res;
    //         this.all.topLevelCount = res.length;
    //     }
    // };




    // var loadChild = function(rows, index, callBack) {
    //     $http.get('static/json/business/demo/treeGrid.json').success(function(res) {
    //         addChild(rows, index, res, callBack);
    //     });
    // }

    // var addChild = function(sourceArr, index, arr, callBack) {
    //     if (sourceArr.length == index + 1) {
    //         sourceArr = sourceArr.concat(arr);
    //     } else {
    //         var first = [],
    //             second = [],
    //             third = [];
    //         first = sourceArr.slice(index, index + 1);
    //         second = arr;
    //         third = sourceArr.slice(index + 1);
    //         sourceArr = first.concat(second.concat(third));
    //     }
    //     $scope.huatekTree.rows = sourceArr;
    //     if (angular.isFunction(callBack)) {
    //         callBack($scope.huatekTree, index);
    //     }
    // }

});