
var Hzr = {};

/*输出日志*/
Hzr.log=function(msg){
	 try{
	 	console.log(msg);
	 }catch(err){
	 }
}

/* RESTful 调用的封装 
 *  示例  Af.rest("XXX.api", req, function(ans){
 * 	
 * });
 */
Hzr.rest = function (URI, ARGS, SUCCESS_CALLBACK, ERROR_CALLBACK)
{
	jQuery.ajax({				
		url: URI, // <--			
		method: "POST", 
		processData: false,	
		data: JSON.stringify(ARGS), // <--
		success: function(data, textStatus, jqXHR){
			SUCCESS_CALLBACK (JSON.parse(data) ); // <--
		},
		error: function( jqXHR, textStatus, errorThrown){
			if(typeof ERROR_CALLBACK != "undefined" && ERROR_CALLBACK != null) 
				ERROR_CALLBACK(errorThrown);
			else
			{
				if(errorThrown.length>0) alert( "error: " + errorThrown );	
			}
		}
	});	
}

/* JSONP 调用 */
Hzr.jsonp = function(URI, req, resultHanlder)
{
	jQuery.ajax({				
			url: URI,	
			method: "GET", // get方式
			dataType: "jsonp", // 1: jsonp 
			//jsonpCallback: "callback",
			data: req, // 参数
			success: resultHanlder
	});	
}


/*  用法: 
 *   var template = $(".template").html();
 *   var fmt = new HzrTemplate(template) ;
 *   var data = { ... }
 *   var html = fmt.replace(data);
 * 
 */
function HzrTemplate(template)
{
	this.map = null;;
	this.template = template;
	
	this.compile = function()
	{
		this.map = {};
		
		var r = new RegExp("\\{#.*?\\}", "g");

		var result;
		while ((result = r.exec( this.template )) != null)  
		{
			//Af.log( result[0] );
			var match = result[0]; // {#xxx}
			var key = match.substr(2, match.length - 3); // 不允许带空格  
			//var key = key1.trim();
			this.map[key] = new RegExp("\\{#" + key + "\\}", "g");
			//Af.log(varName);
		}
		
		return this;
	};
	
	this.replace= function( data )
	{
		// 第一次运行时编译
		if(this.map == null) this.compile();
		
		var html = this.template;
		for( var key in this.map)
		{
			var regex = this.map[key];
			var value = data[ key];
			if(value != null)
				html = html.replace( regex, value);
		}
		return html;
	};
}

/* map 定义 */
/* 可以按ID查询的表 */
function HzrMap()
{
	this.array = [];
	
	this.put = function(id, obj)
	{
		/* 检查重复, 如果已经存在直接替换 */
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(e.id == id) 
			{
				e.obj = obj;
				return;
			}
		}
		/* 添加新的项 */
		var e = {};
		e.id = id;
		e.obj = obj;
		this.array.push( e );
	};
	
	this.get = function(id)
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(e.id == id) 
				return e.obj;
		}
		return null;
	};
	
	/* 遍历 : callback: 如果要continue就return true, 否则返回false */
	this.each = function ( callback )
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(false == callback (e.id, e.obj ) ) break;
		}
	};
	
	this.remove = function ( id )
	{
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			if(e.id == id)
				this.array.splice(i, 1);
		}
	};
	
	this.size = function()
	{
		return this.array.length;
	};
	
	this.clear = function()
	{
		this.array = [];
	};
	
	this.values = function()
	{
		var values = [];
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			values.push(e.obj);
		}
		return values;
	};
	this.ids = function()
	{
		var result = [];
		for(var i=0; i<this.array.length;i++)
		{
			var e = this.array[i];
			result.push(e.id);
		}
		return result;
	};
}

/* 以逗号分隔的ID列表 */
function HzrIdList ()
{
	this.ids = [];	
	
	this.aa = function (str)
	{
		if(str==null || str.length==0) return this;
		var sss = str.split(",");
		for(var i=0; i<sss.length; i++)
		{
			var it = sss[i];
			if(it.length > 0 && ! this.contains( it ))
			{				
				this.ids.push(it);
			}
		}
		return this;
	};
	this.at = function(index)
	{
		if(this.ids.length == 0) return null;
		return this.ids[index];
	};
	this.contains = function (id)
	{
		for(var i=0; i<this.ids.length; i++)
		{
			if( id == this.ids[i]) return true;
		}
		return false;
	};
	this.size = function ()
	{
		return this.ids.length;
	};
	this.toString = function()
	{
		return this.ids.join(",");
	}
}

/* 可以传一个 jQuery对象，也可以传一个 selector字符串 */
Hzr.showDialog = function(selector)
{
	var dlg = selector;
	if(selector.constructor == String)	 
		dlg = $(selector);
	
	// 点击关闭时，关闭对话框
	var closeButton = $('[role="close"]', dlg);
	if(closeButton != null);
	{
		closeButton.click(function(){
			dlg.hide();
		});
	}
	
	dlg.show();
}

