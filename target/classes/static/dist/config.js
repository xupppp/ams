/**

 @Name：全局配置
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL（layui付费产品协议）
    
 */
 
layui.define(['laytpl', 'layer', 'element', 'util'], function(exports){
  exports('setter', {
    container: 'LAY_app' //容器ID
    ,base: layui.cache.base //记录layuiAdmin文件夹所在路径
    ,views: layui.cache.base + 'views/' //视图所在目录
    ,entry: 'index' //默认视图文件名
    ,engine: '.html' //视图文件后缀名
    ,pageTabs: false //是否开启页面选项卡功能。单页版不推荐开启
    
    ,name: 'layuiAdmin Pro'
    ,tableName: 'layuiAdmin' //本地存储表名
    ,MOD_NAME: 'admin' //模块事件名
    
    ,debug: true //是否开启调试模式。如开启，接口异常时会抛出异常 URL 等信息
    
    ,interceptor: false //是否开启未登入拦截
    
    //自定义请求字段
    ,request: {
      tokenName: 'access_token' //自动携带 token 的字段名。可设置 false 不携带。
    }
    
    //自定义响应字段
    ,response: {
      statusName: 'code' //数据状态的字段名称
      ,statusCode: {
        ok: 0 //数据状态一切正常的状态码
        ,logout: 1001 //登录状态失效的状态码
      }
      ,msgName: 'msg' //状态信息的字段名称
      ,dataName: 'data' //数据详情的字段名称
    }
    
    //独立页面路由，可随意添加（无需写参数）
    ,indPage: [
      '/user/login' //登入页
      ,'/user/reg' //注册页
      ,'/user/forget' //找回密码
      ,'/template/tips/test' //独立页的一个测试 demo
    ]
    
    //扩展的第三方模块
    ,extend: [
      'echarts', //echarts 核心包
      'echartsTheme' //echarts 主题
    ]
    
    //主题配置
    ,theme: {
      //内置主题配色方案
      color: [{
        main: '#7B8B6F'
        ,selected: '#96A48B'
        ,alias: 'green' //绿色
      },{
        main: '#8696A7'
        ,selected: '#9CA8B8'
        ,alias: 'blue' //蓝色
      },{
        main: '#656565'
        ,selected: '#939391'
        ,alias: 'grey' //灰色
      },{
        main: '#6B5152'
        ,selected: '#A27E7E'
        ,alias: 'brown' //棕色
      },{
        main: '#7A7281'
        ,selected: '#C9C0D3'
        ,alias: 'purple' //紫色
      },{
        main: '#24262F'
        ,selected: '#009688'
        ,alias: 'black' //黑色
      },{
        main: '#F56869'
        ,selected: '#ABD3CD'
        ,alias: 'red' //西瓜红-浅绿
      },{
        main: '#98DAEC'
        ,logo: '#C0C0C0'
        ,header: '#F8B9C5'
        ,selected: '#F8B9C5'
        ,alias: 'ocean' //糖果色1
      },{
        main: '#8B9FCC'
        ,logo: '#C0C0C0'
        ,header: '#B9CFE8'
        ,selected: '#A2ADD4'
        ,alias: 'green' //糖果色2
      },{
        main: '#7B8B6F'
        ,header: '#7B8B6F'
        ,selected: '#96A48B'
        ,alias: 'green' //绿色
      },{
        main: '#8696A7'
        ,header: '#8696A7'
        ,selected: '#9CA8B8'
        ,alias: 'blue' //蓝色
      },{
        main: '#656565'
        ,header: '#656565'
        ,selected: '#939391'
        ,alias: 'grey' //灰色
      },{
        main: '#6B5152'
        ,header: '#6B5152'
        ,selected: '#A27E7E'
        ,alias: 'brown' //棕色
      },{
        main: '#7A7281'
        ,header: '#7A7281'
        ,selected: '#C9C0D3'
        ,alias: 'purple' //紫色
      },{
        main: '#24262F'
        ,header: '#24262F'
        ,selected: '#009688'
        ,alias: 'black' //黑色
      },{
        main: '#F56869'
        ,header: '#F56869'
        ,selected: '#ABD3CD'
        ,alias: 'red' //西瓜红-浅绿
      }]
      
      //初始的颜色索引，对应上面的配色方案数组索引
      //如果本地已经有主题色记录，则以本地记录为优先，除非请求本地数据（localStorage）
      ,initColorIndex: 0
    }
  });
});
