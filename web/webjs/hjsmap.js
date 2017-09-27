function HjsMap() {
    this.container = new Object();
}

HjsMap.prototype.put = function (key, value) {
    this.container[key] = value;
};

HjsMap.prototype.putArray = function (arr) {
    var that = this;
    $.each(arr, function (i, item) {
        var key;
        if(item.constructor==Object)
        for (key in item) {
            that.container[key] = item[key];
            //console.log(key+"-"+item[key]);
        }
    })
};
HjsMap.prototype.get = function (key) {
    return this.container[key];
};

HjsMap.prototype.keySet = function () {
    var keyset = new Array();
    var count = 0;
    for (var key in this.container) {
// 跳过object的extend函数
        if (key == 'extend') {
            continue;
        }
        keyset[count] = key;
        count++;
    }
    return keyset;
};

HjsMap.prototype.size = function () {
    var count = 0;
    for (var key in this.container) {
// 跳过object的extend函数
        if (key == 'extend') {
            continue;
        }
        count++;
    }
    return count;
};

HjsMap.prototype.remove = function (key) {
    delete this.container[key];
};

HjsMap.prototype.toString = function () {
    var str = "";
    for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
        str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
    }
    return str;
};

HjsMap.prototype.getAllValue = function () {
    var str = "";
    for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
        str += this.container[keys[i]] + ",";
    }
    return str;
};
HjsMap.prototype.getValueArray = function () {
    var arr=[];
    for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
        arr.push(this.container[keys[i]]);
    }
    return arr;
};


HjsMap.prototype.getMaxKey = function () {
    var obj = new Array();
    for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
        obj[i] = keys[i];
    }
    return obj.length > 0 ? parseInt(obj.reverse()[0], 10) + 1 : 0;
};

HjsMap.prototype.getKeyByValue = function (val) {
    for (var _key in this.container) {
        if (this.container[_key] == val) {
            return _key;
        } else {
            continue;
        }
    }
    return false;
}