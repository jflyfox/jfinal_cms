/***
 *验证组件，通过valid属性进行标识，通过validname和validlname内容进行提示。
 *
 * @param flag {是否验证隐藏域，默认不验证，true验证} TODO 待议
 *
 *@return boolean {验证成功返回true 验证失败返回false，并提示失败信息}
*/
function validForm(flag){
	var visible = '';
	flag = flag || false;
	if(flag) visible = ':visible';
	var having=true;
	var notice ;
	$("[valid^='v']"+visible).each(function() {
		//提示信息，如果validlname存在直接输出，如果不存在通过validname拼接默认提示
		var that = $(this);
		var valid = that.attr("valid");
		var valids = valid.split("_");
		if(isEmpty(that.val())){
			//处理必填项(v*_y)
			if(valids[1]=='y') {
				_valid("不能为空","");
			}
			//处理必填项(vtext)
			if(valids[0]=='vtext') {
				_valid("不能为空","");
			}
			//处理时间组件不能为空,特殊处理
			else if(valids[0]=='vdate'){
				if(isEmpty(that.val())) 	_valid("不能为空","");
			}
		}else{
			//处理下拉选择框
			if(valids[0]=='vselect') {
				if(that.val()=='-1')	_valid("必须选择","-1");
			}
			//处理整数类型(vnum_n_0_31)
			else if(valids[0]=='vnum') {
				if(!isInt(that.val()))	 _valid("必须为整数","");
				 //数字区间验证
				 else if(!checkInt(that.val(),valids[2],valids[3]))
				 	_valid("必须在"+valids[2]+"和"+valids[3]+"之间","");
			}
			//处理数字类型
			else if(valids[0]=='vfloat') {
				 if(!isFloat(that.val()))	_valid("必须为数字","");
				 //数字区间验证
				 else if(!checkFloat(that.val(),valids[2],valids[3]))	
					 _valid("必须在"+valids[2]+"和"+valids[3]+"之间","");
			}
			//处理Email
			else if(valids[0]=='vemail') {
				if(!isEmail(that.val()))	_valid("格式错误","");
			}
			//处理手机号码
			else if(valids[0]=='vmobile') {
				if(!isMobile(that.val()))	_valid("格式错误","");
			}
			//处理电话号码
			else if(valids[0]=='vphone') {
				if(!isPhone(that.val()))	_valid("格式错误","");
			}
		}

		function _valid(noticeValue,defaultValue){
			var content = that.attr('validname') || '';
			var lcontent = that.attr('validlname');
			notice = typeof(lcontent) == 'undefined'?content + noticeValue + '，请重新填写！':lcontent;
			if(having) Alert(notice,function(){
				 if(valids[0]!='vdate') that.focus();
				 if(valids[0]=='vdate') that[0].focus();
			});
			if(valids[0]!='vdate'){ //vdate不做样式处理
				that.css("border","1px dashed red");
				that.bind("change",function(){
					var solid = "1px solid #a5ccf8";
					if(valids[0]=='vnum'){
						if(checkInt(that.val(),valids[2],valids[3]))	that.css("border",solid);
					}					
					else if(valids[0]=='vfloat'){
						if(checkFloat(that.val(),valids[2],valids[3]))	that.css("border",solid);
					}
					else if(valids[0]=='vemail')	{
						if(isEmail(that.val()))	that.css("border",solid);
					}
					else if(valids[0]=='vmobile')	{
						if(isMobile(that.val()))	that.css("border",solid);
					}
					else if(valids[0]=='vphone')	{
						if(isPhone(that.val()))	that.css("border",solid);
					}
					else {
							if(that.val() != defaultValue)	that.css("border",solid);
					};
				});
			}
			having=false;
		}
	});
	return having;
}

/**
 * 验证成功后，提交表单
 * 
 * @param url {提交的URL}
 * @param formObj {form对象}
 * @param flag {是否验证隐藏域，默认不验证，true验证}
 *            
 * @return {Boolean}
 */
function submitForm(url,formObj,flag){
	if(validForm(flag)){
		url = url || '';
		formObj = formObj || document.form1;
		if(url != '') formObj.action=url;
		formObj.submit();
	}
}
/**
 * 判断某个字符是否为空,如果为空则返回true,否则返回false.
 * 
 * @param {我们要验证的字符}
 *            str
 * @return {Boolean}
 */
function isEmpty(str) {
	str = str || '';
	if ((str == null) || (str.length == 0))
		return true;
	else
		return false;
}

/**
 * 判断某个字符是否是数字.
 * 
 * @param {我们要验证的字符}
 *            theNum
 * @return {Boolean}
 */
function isDigit(theNum) {
	var theMask = '0123456789';
	if (isEmpty(theNum))
		return false;
	else if (theMask.indexOf(theNum) == -1)
		return false;
	return true;
}
/**
 * 判断某个字符是不是整型，第二个参数是是否支持负数,默认不支持负数.
 * 
 * @param {我们要验证的字符}
 *            theStr
 * @param {判断是不是支持负数，默认不支持负数}
 *            isNegative
 * @return {} 使用示例： var age = form1.age.value; if(!isInt(age,true)){
 *         alert("年龄必须为整数！"); return ; }
 */
function isInt(theStr, isNegative) {
	isNegative = isNegative || false;
	var flag = true;
	if (isEmpty(theStr)) {
		flag = false;
	} else {
		var i = theStr.length ;
		while (i--) {
			if (isNegative == true && i == 0) {
					if (theStr.substring(0, 1) == '-' && theStr.length == 1) {
						flag = false;
						break;
					} else if (theStr.substring(0, 1) != '-' && isDigit(theStr.substring(0, 1)) == false) {
						flag = false;
						break;
					}
			} else  if (isDigit(theStr.substring(i, i + 1)) == false) {
				flag = false;
				break;
			}
		}
	}
	return flag;
}
/********************************************
检测数字区间
theStr－被检测字符串
min－最小值(为''时不进行此项检测)
max－最大值(为''时不进行此项检测)
********************************************/
function checkInt ( theStr,min, max ) {
	min = min || '';
	max = max || '';
	if (!isInt(theStr)) {
		return false;
	}else{
		if ( !isEmpty(min) && parseInt( theStr ) < min  ) {
			return false;
		}
		if ( !isEmpty(max) && parseInt( theStr ) > max  ) {
			return false;
		}
	}
	return true;
}
/**
 * 判断是不是浮点数,第二个参数是指是否支持负数.
 * 
 * @param {我们要验证的字符}
 *            theStr
 * @param {是否支持负数}
 *            isNegative
 * @return {Boolean} 使用示例： var money = form1.money.value;
 *         if(!isFloat(money,true)){ alert("试用期工资必须为数字格式！"); return ; }
 */
function isFloat(theStr, isNegative) {
	var dot1st = theStr.indexOf('.');
	var dot2nd = theStr.lastIndexOf('.');
	if (isEmpty(theStr))
		return false;
	if (dot1st == -1) {
		if (isInt(theStr, isNegative))
			return true;
		else
			return false;
	} else if (dot1st != dot2nd || dot1st == 0)
		return false;
	else {
		var intPart = theStr.substring(0, dot1st);
		var decPart = theStr.substring(dot2nd + 1);
		// 判断是否支持负数：是指前一部分是否支持负数，后面的部分不支持负数
		if (!isInt(intPart, isNegative) || !isInt(decPart))
			return false;
		else if (isEmpty(decPart))
			return false;
		else
			return true;
	}
}

/********************************************
检测是否是数字类型、并且大于最小值，小于最大值
theStr－被检测字符串
min－最小值(为null时不进行此项检测)
max－最大值(为null时不进行此项检测)
********************************************/
function checkFloat ( theStr,min, max ) {
	min = min || '';
	max = max || '';
	if (!isFloat(theStr)) {
		return false;
	}else{
		if ( !isEmpty(min) && parseFloat( theStr ) < min  ) {
			return false;
		}
		if ( !isEmpty(max) && parseFloat( theStr ) > max  ) {
			return false;
		}
	}
	return true;
}

//邮箱验证方法,如果是空返回false
function isEmail(theStr) {
	var pattern = /(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$)/;  
	return _match(theStr,pattern) ;
}
//手机号码验证信息,如果是空返回false
function isMobile(theStr) {
	var pattern = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
	return _match(theStr,pattern) ;
}
//判断是否为正确的电话号码（可以含"()"、"（）"、"+"、"-"和空格）,如果是空返回false
function isPhone(theStr){
    //国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
	var pattern =/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)/; 
	return _match(theStr,pattern) ;
} 

//正则匹配字符串
function _match (theStr,pattern) {
	if (isEmpty(theStr))
        return false;
        
	if(!pattern.exec(theStr))
	{
		return false;
	} 
	return true;
}

/**
 * 检测是否是字母
 * @param {} inputVal
 * @return {Boolean}
 */
function isLetter( inputVal )
{
	if (isEmpty(inputVal))
        return false;

	inputVal = inputVal.toUpperCase();
	var strCheck = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var i = inputVal.length;
	while (i--) {
		var s = inputVal.charAt( i );
		var nPos = strCheck.indexOf( s );
		if ( nPos < 0 )
		{
			return false;
		}
	}
	return true;
}
/**
 * 判断某个字符串是否为日期格式,指定的日期格式为2011-02-04.
 * @param {} dateStr
 * @return {Boolean}
 */
function isDate(dateStr) {
	if(isEmpty(dateStr)){
		return false;
	}

	dateStr = dateStr.replace(/\s+/g, "");
	// var reg = /^[1,2][\d]{3}[\-\/]([0]{0,1}[1-9]|1[1,2])[\-\/][0,1,2,3]{0,1}[\d]$/;
	var reg = /^[1,2][\d]{3}[\-]([0]{0,1}[1-9]|1[1,2])[\-][0,1,2,3]{0,1}[\d]$/;
	if (!dateStr.match(reg)) {
		return false;
	}
	var ary, d, day;
	ary = dateStr.replace('/', '-').split('-');
	d = new Date(ary[0], ary[1], 0);
	day = parseInt(ary[2], 10);
	if (day < 1 || day > d.getDate()) {
		return false;
	}
	return true;
}
/**
 * 判断是否是简单的年月，格式为201107.
 * @param {} dateStr
 * @return {Boolean}
 */
function isSimpleDate(dateStr) {
	if(isEmpty(dateStr)){
		return false;
	}
	dateStr = dateStr.replace(/\s+/g, "");
	// var reg = /^[1,2][\d]{3}[\-\/]([0]{0,1}[1-9]|1[1,2])[\-\/][0,1,2,3]{0,1}[\d]$/;
	var reg = /^[1,2][\d]{3}([0]{0,1}[1-9]|1[0,1,2])$/;
	if (!dateStr.match(reg)) {
		return false;
	}
	return true;
} 

/**
 * 限制文本域、文本框的字符数量，如果超过指定的数量，那么把进行字符的截取.
 * 
 * @param field
 *            ：我们的文本域控件
 * @param maxlimit :
 *            最大的字符长度
 * @param countfield :
 *            我们用于显示还可以输入多少个字符的文本框控件，该参数可以省略 使用示例： <form name=myform action="">
 *            <textarea name="message" cols="28" rows="5"
 *            onKeyDown="textCounter(this.form.message,10);"
 *            onKeyUp="textCounter(this.form.message,10);"> </textarea> 您还可以输入:<input
 *            name="remLen" type="text" value="100" size="5"
 *            readonly="readonly">个字符 </form>
 */
function textCounter(field, maxlimit, countfield) {
	// 如果文本域的长度大于最大的长度，那么需要截取字符串
	if (field.value.length > maxlimit) {
		// 如果元素区字符数大于最大字符数，按照最大字符数截断；
		field.value = field.value.substring(0, maxlimit);
	} else {
		if ((countfield || '') != '') {
			// 在记数区文本框内显示剩余的字符数；
			countfield.value = maxlimit - field.value.length;
		}
	}
}

//表单验证特殊符号
function vaildscript(s) {
	var pattern = new RegExp(
			"[`~!@#$^&*()=|{}':;'.,\\[\\].<>/?~！% @#￥……&*（）&mdash;—|{}【】‘；：”“'。，、？]")
	var rs = "";
	for ( var i = 0; i < s.length; i++) {
		var rst = s.substr(i, 1).replace(pattern, '');
		if (rst == "") {
			return "error";
		}
		rs = rs + rst;
	}
	return rs;
}