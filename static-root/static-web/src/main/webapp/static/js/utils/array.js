Array.prototype.insert = function (index, item) {
    if(this.length == index){
        this.push(item);
    }else{
        this.splice(index, 0, item);
    }
};

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

Array.prototype.removeWithIndex = function(index) {
    if (index > -1) {
        this.splice(index, 1);
    }
};