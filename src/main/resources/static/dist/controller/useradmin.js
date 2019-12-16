/** layuiAdmin.pro-v1.2.1 LPPL License By http://www.layui.com/admin/ */ ;
layui.define(["table", "form"], function(e) {
	var i = (layui.$, layui.admin),
		t = layui.view,
		l = layui.table,
		r = layui.form;
	l.render({
		elem: "#LAY-user-manage",
		url: "./json/useradmin/app.js",
		cols: [
			[{
				type: "checkbox",
				fixed: "left"
			}, {
				field: "id",
				width: 70,
				title: "ID",
				sort: !0
			}, {
				field: "sduid",
				width: 95,
				title: "学号",
				sort: !0
			}, {
				field: "username",
				title: "姓名",
				width: 90,
				sort: !0
			}, {
				field: "password",
				title: "密码",
				width: 110
			}, {
				field: "avatar",
				title: "头像",
				width: 85,
				templet: "#imgTpl"
			}, {
				field: "sex",
				width: 65,
				title: "性别"
			}, {
				field: "major",
				width: 142,
				title: "专业",
				sort: !0
			}, {
				field: "class",
				width: 63,
				title: "班级"
			}, {
				field: "phone",
				width: 117,
				title: "手机"
			}, {
				field: "email",
				title: "邮箱"
			}, {
				field: "state",
				title: "状态",
				width: 75,
				sort: !0
			}, {
				title: "操作",
				width: 113,
				align: "center",
				fixed: "right",
				toolbar: "#table-useradmin-webuser"
			}]
		],
		page: !0,
		limit: 30,
		height: "full-320",
		text: "对不起，加载出现异常！"
	}), l.on("tool(LAY-user-manage)", function(e) {
		var l = e.data;
		"del" === e.event ? layer.prompt({
			formType: 1,
			title: "敏感操作，请验证口令"
		}, function(i, t) {
			layer.close(t), layer.confirm("确定删除此用户？", function(i) {
				e.del(), layer.close(i)
			})
		}) : "edit" === e.event && i.popup({
			title: "编辑用户",
			area: ["500px", "600px"],
			id: "LAY-popup-user-add",
			success: function(e, i) {
				t(this.id).render("user/app/user_modify", l).done(function() {
					r.render(null, "layuiadmin-form-useradmin"), r.on("submit(LAY-user-front-submit)", function(e) {
						e.field;
						layui.table.reload("LAY-user-manage"), layer.close(i)
					})
				})
			}
		})
	}), l.render({
		elem: "#LAY-user-back-manage",
		url: "./json/useradmin/web.js",
		cols: [
			[{
				type: "checkbox",
				fixed: "left"
			}, {
				field: "id",
				width: 70,
				title: "ID",
				sort: !0
			}, {
				field: "emid",
				width: 95,
				title: "工号",
				sort: !0
			}, {
				field: "username",
				title: "姓名",
				width: 90,
				sort: !0
			}, {
				field: "role",
				title: "类型",
				width: 110
			}, {
				field: "avatar",
				title: "头像",
				width: 85,
				templet: "#imgTpl"
			}, {
				field: "sex",
				width: 65,
				title: "性别"
			}, {
				field: "major",
				width: 142,
				title: "专业",
				sort: !0
			}, {
				field: "class",
				width: 63,
				title: "班级"
			}, {
				field: "phone",
				width: 117,
				title: "手机"
			}, {
				field: "email",
				title: "邮箱"
			}, {
				field: "state",
				title: "状态",
				width: 75,
				sort: !0
			}, {
				title: "操作",
				width: 114,
				align: "center",
				fixed: "right",
				toolbar: "#table-useradmin-admin"
			}]
		],
		text: "对不起，加载出现异常！"
	}), l.on("tool(LAY-user-back-manage)", function(e) {
		var l = e.data;
		"del" === e.event ? layer.prompt({
			formType: 1,
			title: "敏感操作，请验证口令"
		}, function(i, t) {
			layer.close(t), layer.confirm("确定删除此管理员？", function(i) {
				console.log(e), e.del(), layer.close(i)
			})
		}) : "edit" === e.event && i.popup({
			title: "编辑管理员",
			area: ["500px", "600px"],
			id: "LAY-popup-user-add",
			success: function(e, i) {
				t(this.id).render("user/web/web_modify", l).done(function() {
					r.render(null, "layuiadmin-form-admin"), r.on("submit(LAY-user-back-submit)", function(e) {
						e.field;
						layui.table.reload("LAY-user-back-manage"), layer.close(i)
					})
				})
			}
		})
	}), l.render({
		elem: "#LAY-user-back-role",
		url: "./json/useradmin/role.js",
		cols: [
			[{
				type: "checkbox",
				fixed: "left"
			}, {
				field: "id",
				width: 80,
				title: "ID",
				sort: !0
			}, {
				field: "rolename",
				title: "角色名"
			}, {
				field: "limits",
				title: "拥有权限"
			}, {
				field: "descr",
				title: "具体描述"
			}, {
				title: "操作",
				width: 150,
				align: "center",
				fixed: "right",
				toolbar: "#table-useradmin-admin"
			}]
		],
		text: "对不起，加载出现异常！"
	}), l.on("tool(LAY-user-back-role)", function(e) {
		var l = e.data;
		"del" === e.event ? layer.confirm("确定删除此角色？", function(i) {
			e.del(), layer.close(i)
		}) : "edit" === e.event && i.popup({
			title: "添加新角色",
			area: ["500px", "480px"],
			id: "LAY-popup-user-add",
			success: function(e, i) {
				t(this.id).render("user/administrators/roleform", l).done(function() {
					r.render(null, "layuiadmin-form-role"), r.on("submit(LAY-user-role-submit)", function(e) {
						e.field;
						layui.table.reload("LAY-user-back-role"), layer.close(i)
					})
				})
			}
		})
	}), e("useradmin", {})
});