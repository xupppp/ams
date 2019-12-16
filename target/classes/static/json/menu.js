{
  "code": 0
  ,"msg": ""
  ,"data": [{
    "title": "主页"
    ,"icon": "layui-icon-home"
    ,"jump": "/"
   
  }, {
    "name": "user"
    ,"title": "用户"
    ,"icon": "layui-icon-user"
    ,"list": [{
      "name": "user"
      ,"title": "普通用户"
      ,"jump": "user/app/list"
    }, {
      "name": "administrators-list"
      ,"title": "后台管理员"
      ,"jump": "user/web/list"
    }]
  }, {
    "name": "club"
    ,"title": "社团"
    ,"icon": "layui-icon-flag"
    ,"list": [{
    	"name": "check"
      ,"title": "审批"
      ,"list": [{
      	"name": "new"
      	,"title": "成立申请"
        ,"jump": "club/check/new"
      },{
      	"name": "modify"
      	,"title": "修改申请"
        ,"jump": "club/check/modify"
      }]
      },{
    	"name": "list"
      ,"title": "查看"
    	  ,"list":[{
          	"name": "list-yes"
               ,"title": "所有社团"
               ,"jump": "club/list/list"
                },{
                	"name": "list-nos"
                  ,"title": "已删除社团"
                  ,"jump": "club/list/list2"
                }]
      },{
    	"name": "add"
      ,"title": "添加"
      ,"jump": "club/add/add"
    }]
}, {
    "name": "activity"
    ,"title": "活动"
    ,"icon": "layui-icon-form"
    ,"list": [{
    	"name": "check"
      ,"title": "审批"
      ,"list": [{
      	"name": "new"
      	,"title": "活动申请"
        ,"jump": "activity/check/new"
      },{
      	"name": "modify"
      	,"title": "活动修改"
        ,"jump": "activity/check/modify"
      }]
    },{
    	"name": "list"
      ,"title": "查看"
      ,"list":[{
        	"name": "list-yes"
             ,"title": "已发布"
             ,"jump": "activity/list/list"
              },{
              	"name": "list-nos"
                ,"title": "已驳回"
                ,"jump": "activity/list/list2"
              }]
    },{
    	"name": "add"
      ,"title": "添加"
      ,"jump": "activity/add/add"
    }]
}, {
    "name": "news"
    ,"title": "新闻"
    ,"icon": "layui-icon-release"
    ,"list": [{
    	"name": "check"
      ,"title": "审核"
      ,"list": [{
      	"name": "new"
      	,"title": "新闻申请"
        ,"jump": "news/check/new"
      },{
      	"name": "modify"
      	,"title": "新闻修改"
        ,"jump": "news/check/modify"
      }]
      },{
    	"name": "list"
      ,"title": "查看"
    	  ,"list":[{
            	"name": "list-yes"
                 ,"title": "已发布"
                 ,"jump": "news/list/list"
                  },{
                  	"name": "list-nos"
                    ,"title": "已驳回"
                    ,"jump": "news/list/list2"
                  }]
      },{
    	"name": "add"
      ,"title": "添加"
    	  ,"jump": "news/add/add"
    }]
}, {
	  "name": "field"
    ,"title": "场地"
    ,"icon": "layui-icon-location"
    ,"list":[{
    	"name": "list"
    	      ,"title": "查看"
    	      ,"jump": "field/list"
    	    },
    	    {
    	   "name": "list"
    	    ,"title": "添加"
    	    ,"jump": "field/add"
    	    	    }    	
    ]
  }, {
	  "name": "notice"
	,"title": "公告"
    ,"icon": "layui-icon-note"
    ,"list":[{
    	"name": "list"
  	      ,"title": "查看"
  	      ,"jump": "notice/list"
  	    },
  	    {
  	   "name": "list"
  	    ,"title": "添加"
  	    ,"jump": "notice/add/add"
  	    	    }    	
  ]
  },{
      "name": "type"
          ,"title": "类别"
          ,"icon": "layui-icon-note"
          ,"list": [{
              "name": "clubType"
              ,"title": "社团类别"
              ,"jump": "type/clubType/list"
          }, {
              "name": "actType"
              ,"title": "活动类别"
              ,"jump": "type/actType/list"
          },{
              "name":"add"
              ,"title":"添加社团类别"
              ,"jump":"type/add/addClubType"
          }
              ,{
                  "name":"add"
                  ,"title":"添加活动类别"
                  ,"jump":"type/add/addActType"
              }
          ]
      }
]
}