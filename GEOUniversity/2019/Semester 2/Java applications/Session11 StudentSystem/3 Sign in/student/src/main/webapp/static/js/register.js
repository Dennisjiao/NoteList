
layui.config({
	base : 'static/layuiadmin/' //静态资源所在路径
}).extend({
    index: 'lib/index' //主入口模块
  }).use(['index','form','layer' ], function() {
	var $ = layui.$
	,form = layui.form
	,layer=layui.layer;
		

	form.render();
	//登录表单验证
	form.verify({
		username:function(value, item){
			if(value==null || value==undefined || value=="" )
				return '用户名不为能空';
			var isexist=false;
			var url = 'checkuser';
			$.ajax({
				url : url,
				cache : false,
				async : false,			//同步校验
				type : "get",		    //数据发送方式
				dataType : "json", 		//接受数据格式
				data : {
					username : value
				},
				success : function(data, status) {
					if (status == "success") {
						if (data.code)
							isexist=true;						
					}
				}
			});
			if(isexist)
				return "该用户名已被注册！";
		},		
		password:function(value, item){
			if(value==null || value==undefined || value=="" )
				return '密码不为能空';
			if(value.length<4 || value.length>32)
				return '密码长度应在4和32之间';			
		},
		rpasswd:function(value, item){
			if(value==null || value==undefined || value=="" )
				return '确认密码不为能空';
			if(value.length<4 || value.length>32)
				return '确认密码长度应在4和32之间';	
			if(value!=item.form.password.value)
				return "密码与确认密码不相同";
		},
		stuid:function(value, item){
			if(value==null || value==undefined || value=="" )
				return '学号不为能空';
			var isexist=false;
			var url = 'checkStuid';
			$.ajax({
				url : url,
				cache : false,
				async : false,			//同步校验
				type : "get",		    //数据发送方式
				dataType : "json", 		//接受数据格式
				data : {
					stuid : value
				},
				success : function(data, status) {
					if (status == "success") {
						if (data.code)
							isexist=true;						
					}
				}
			});
			if(isexist)
				return "该学号已存在！";
		},	
		
	});
	//提交前也可进行业务处理，取消只需反return false即可。
	form.on('submit(registMember)', function(data){
				
	});
	//用于显示提示信息
	if(window.notifymsg != null && window.notifymsg != undefined && window.notifymsg != "")
		layer.msg(window.notifymsg);
});