(function(win) {
    var pwdString = {
        init: function() {
            var self = pwdString;

        },

        options: {
            defaultPwd: 'helijun'
        },
        encode : function(input) {
            var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
            var output = "",
                chr1, chr2, chr3 = "",
                enc1, enc2, enc3, enc4 = "",
                i = 0;
            while (i < input.length) {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);
                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;
                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }
                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            }
            return output;
        },
        encrypt: function(str, pwd) {
            var self = pwdString;

            if (!str) return '';

            str = encodeURIComponent(str);

            pwd = encodeURIComponent(pwd || self.options.defaultPwd);

            var prand = '';
            for (var i = 0; i < pwd.length; i++) {
                prand += pwd.charCodeAt(i).toString();
            }

            var sPos = Math.floor(prand.length / 5),
                mult = parseInt(prand.charAt(sPos) + prand.charAt(sPos * 2) + prand.charAt(sPos * 3) +
                    prand.charAt(sPos * 4) + prand.charAt(sPos * 5)),
                incr = Math.ceil(pwd.length / 2),
                modu = Math.pow(2, 31) - 1;

            if (mult < 2) return '';

            var salt = Math.round(Math.random() * 1000000000) % 100000000;
            prand += salt;

            while (prand.length > 10) {
                prand = (parseInt(prand.substring(0, 10)) +
                    parseInt(prand.substring(10, prand.length))).toString();
            }

            prand = (mult * prand + incr) % modu;

            var encChr = '',
                encStr = '';
            for (var i = 0, len = str.length; i < len; i += 1) {
                encChr = parseInt(str.charCodeAt(i) ^ Math.floor((prand / modu) * 255));
                if (encChr < 16) {
                    encStr += '0' + encChr.toString(16);
                } else {
                    encStr += encChr.toString(16);
                }
                prand = (mult * prand + incr) % modu;
            }

            salt = salt.toString(16);
            while (salt.length < 8) {
                salt = '0' + salt;
            }
            encStr += salt;

            return encStr;
        },
        decrypt: function(str, pwd) {
            var self = pwdString;

            if (str == '') return '';

            pwd = encodeURIComponent(pwd || self.options.defaultPwd);

            if (str == undefined || str.length < 8) {
                return '';
            }

            var prand = '';
            for (var i = 0; i < pwd.length; i++) {
                prand += pwd.charCodeAt(i).toString();
            }

            var sPos = Math.floor(prand.length / 5),
                mult = parseInt(prand.charAt(sPos) + prand.charAt(sPos * 2) + prand.charAt(sPos * 3) +
                    prand.charAt(sPos * 4) + prand.charAt(sPos * 5)),
                incr = Math.round(pwd.length / 2),
                modu = Math.pow(2, 31) - 1,
                salt = parseInt(str.substring(str.length - 8, str.length), 16);

            str = str.substring(0, str.length - 8);
            prand += salt;

            while (prand.length > 10) {
                prand = (parseInt(prand.substring(0, 10)) +
                    parseInt(prand.substring(10, prand.length))).toString();
            }
            prand = (mult * prand + incr) % modu;

            var encChr = '',
                encStr = '';
            for (var i = 0, len = str.length; i < len; i += 2) {
                encChr = parseInt(parseInt(str.substring(i, i + 2), 16) ^ Math.floor((prand / modu) * 255));
                encStr += String.fromCharCode(encChr);
                prand = (mult * prand + incr) % modu;
            }

            return decodeURIComponent(encStr);
        },

        render: function() {
            var self = pwdString;
        }
    }

    win.pwdString = pwdString;
})(window);