layui.config({
	base : 'static/layuiadmin/' //静态资源所在路径
}).extend({
	index : 'lib/index' //主入口模块
}).use([ 'index', 'layer', 'form', 'table', 'laypage' ], function() {
	var $ = layui.$,
		layer = layui.layer,
		laypage = layui.laypage,
		form = layui.form,
		table = layui.table;
	form.render();

	var studentTable = table.render({
		elem : '#student_table',
		toolbar : '#toolbar',
		defaultToolbar : [],
		url : 'user/getStudentList',
		cols : [ [
			{
				type : 'checkbox'
			}
			, {
				field : 'username',
				width : '14%',
				title : '用户名',
				align : 'center'
			}
			, {
				field : 'stuid',
				width : '15%',
				title : '学号',
				templet : function(d) {
					return d.stuinfo.stuid == undefined ? '' : d.stuinfo.stuid
				},
				align : 'center'
			}
			, {
				field : 'stuname',
				width : '10%',
				title : '姓名',
				templet : function(d) {
					return d.stuinfo.stuname == undefined ? '' : d.stuinfo.stuname
				},
				align : 'center'
			}
			, {
				field : 'gender',
				width : '10%',
				title : '性别',
				templet : function(d) {
					return d.stuinfo.gender == undefined ? '' : d.stuinfo.gender
				},
				align : 'center'
			}
			, {
				field : 'birthday',
				width : '10%',
				title : '出生日期',
				templet : function(d) {
					return d.stuinfo.birthday == undefined ? '' : d.stuinfo.birthday
				},
				align : 'center'
			}
			, {
				field : 'classname',
				width : '10%',
				title : '班级',
				templet : function(d) {
					return d.stuinfo.classname == undefined ? '' : d.stuinfo.classname
				},
				align : 'center'
			}
			, {
				field : 'address',
				width : '15%',
				title : '地址',
				templet : function(d) {
					return d.stuinfo.address == undefined ? '' : d.stuinfo.address
				},
				align : 'center'
			}
			, {
				title : '操作',
				width : '12%',
				align : 'left',
				fixed : 'right',
				toolbar : '#table_operate'
			}
		] ],
		page : {
			layout : [ 'count', 'prev', 'page', 'next', 'limit' ], //,curr: 5 //设定初始在第 5 页
			prev : '上一页',
			next : '下一页',
			first : '首页',
			last : '尾页'
		},
		text : {
			none : '暂无相关数据'
		},
		loading : true
	});

	//处理表格的工具栏按钮事件
	table.on('toolbar(table-operate)', function(obj) {
		
		switch (obj.event) {
		case 'new':
			var index = layer.open({
				type : 2,
				title : '新增用户',
				shadeClose : true,
				area : [ '500px', '610px' ],
				content : 'user/useredit?dialogType=new&username=',				
				//层销毁后触发的回调
	    		end: function(){
	    			table_reload();
	    		}
			});
			break;
		case 'batchdelete':
			var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
			var data = checkStatus.data;
			if (data.length == 0) {
				layer.msg("您还未选择相应的记录！");
				return;
			}
			var ids=[];		    
		    data.forEach((item)=>{
		    	ids.push(item.username);
		    });
		    data=JSON.stringify(ids);
		    data=data.substr(1);
		    data=data.substr(0,data.length-1);
		    data=data.replace(/\"/g,'\'');
			//layer.msg(data);
		    layer.confirm('您确认要删除这些记录吗？删除后不可恢复！',function(index) {
			    var url =  'user/batchdelete';
				$.get(
					url,
					{ids:data},					
					function(data, status, xhr) {
						if (status == "success") {
							if (data.code == -10) {
								//session过期，跳转
								window.top.location.href = data.data;
							} 
							else if(data.code==0) {
								layer.msg(data.msg);
								table_reload();
							}
							else{
								layer.msg(data.msg);
							}
						}
					},"json").fail(function() {
					return layer.msg("删除用户失败！");
				});
				layer.close(index);
		    });
			break;
		}

	});
	//监听操作按钮
	table.on('tool(table-operate)', function(obj) {
		var data = obj.data;
		if (obj.event === 'edit') {
			var username=data.username;
			var index = layer.open({
				type : 2,
				title : '编辑用户',
				shadeClose : true,
				area : [ '500px', '610px' ],
				content : 'user/useredit?dialogType=edit&username='+username,
				//层销毁后触发的回调
	    		end: function(){
	    			table_reload();
	    		}
			});
		} else if (obj.event === 'delete') {
			layer.confirm('您确认要删除该记录吗？删除后不可恢复！',function(index) {
				var username=data.username;
				var url =  'user/delete';
				$.get(
					url,
					{username:username},					
					function(data, status, xhr) {
						if (status == "success") {
							if (data.code == -10) {
								//session过期，跳转
								window.top.location.href = data.data;
							} else {
								layer.msg(data.msg);
								table_reload();
							}
						}
					},"json").fail(function() {
					return layer.msg("删除用户失败！");
				});
				layer.close(index);
		    });
		}
	});
	//重新加载表格数据
	function table_reload(){
		layer.load(2);
		var username=$("#username").val();
		var stuid=$("#stuid").val();
		var stuname=$("#stuname").val();
		var gender=$("input[type='radio']:checked").val();
		studentTable.reload({
			url: 'user/getStudentList',
			where : {
				username:username,
				'stuinfo.stuid':stuid,
				'stuinfo.stuname':stuname,
				'stuinfo.gender':gender
			},
			done: function(res, curr, count){
				//加载后回调
				layer.closeAll('loading');		
			}
		});
	}
	//处理查询按钮事件
	$("#search").click(function () {
		table_reload();
	});
});