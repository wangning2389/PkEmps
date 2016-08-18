$(function() {
	$.ajaxSetup({
				complete : function(jqXHR, status) {

					var response=$.parseJSON(jqXHR.responseText);
						console.log(response);
						// server抛出异常
						if (response && response.exType
								&& !response.success) {
//							if (/^LOGON_EXC.*/.test(response.exType)) {
								// filters
								// 1. logon filter
								if (response.exType == "LOGON_EXC") {
									alert("您尚未登录或登录已超时!");
									window.location.href = '/pkemps/page/logon.jsp';
//								}
							}
//								else {
//								// 使用全局异常处理
//								alert(response.exMsg);
//
//							}
						}
					}
				});
	
		//防止console.log undefined 错误
	/**
	 * Protect window.console method calls, e.g. console is not defined on IE
	 * unless dev tools are open, and IE doesn't define console.debug
	 */
		if (!window.console) {
			window.console = {};
		}
		// union of Chrome, FF, IE, and Safari console methods
		var m = [ "log", "info", "warn", "error", "debug", "trace", "dir",
				"group", "groupCollapsed", "groupEnd", "time", "timeEnd",
				"profile", "profileEnd", "dirxml", "assert", "count",
				"markTimeline", "timeStamp", "clear" ];
		// define undefined methods as noops to prevent errors
		for (var i = 0; i < m.length; i++) {
			if (!window.console[m[i]]) {
				window.console[m[i]] = function() {
				};
			}
		}
	
		
		if (typeof mini != "undefined") {
			window.browserAlert = window.alert;
			window.alert = function(msg) {
				mini.alert(msg);
			}; 
		}

})


////////电话或手机号码验证（电话加区号）////////////
String.prototype.Trim = function() {
	var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);
	return (m == null) ? "" : m[1];
}

String.prototype.isMobile = function() {

	return (/^[0-9\-]{0,}$/.test(this.Trim()));
}

function chkForm(e) {
	if (e.isValid) {
		var tel = e.sender;
		if (tel.value.isMobile())  {
			tel.value = tel.value.Trim();
		} else {
			e.errorText="联系电话只能输入数据数字、横线，例如：025-11111111或手机号";
			e.isValid = false;
		}
	}
}