(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-87635080"],{"31af":function(e,t,r){"use strict";r("5943")},5943:function(e,t,r){},aa4c:function(e,t,r){"use strict";r.r(t);var n=r("1da1"),a=(r("b0c0"),r("96cf"),r("c24f")),s=r("5c96"),o={name:"AdminManage",data:function(){return{tableData:[],total:0,currentPage:1,userInfo:{},dialogFormVisible:!1,dialogTitle:"",labelPosition:"right",input:"",searchInput:"",cinemaList:[],roleList:[],rules:{name:[{required:!0,message:"用户名不能为空",trigger:"change"}],username:[{required:!0,message:"账号不能为空",trigger:"change"}],password:[{required:!0,message:"密码不能为空",trigger:"change"}],roleId:[{required:!0,message:"角色不能为空",trigger:"change"}]}}},created:function(){this.loadCurrentPageUser(this.currentPage,8,"")},methods:{loadCurrentPageUser:function(e,t,r){var s=this;return Object(n.a)(regeneratorRuntime.mark((function n(){var o,i,l;return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:return n.next=2,Object(a.b)({pageNum:e,limit:t,keyword:r});case 2:o=n.sent,i=o.status,l=o.data,200===i&&200===l.state&&(s.tableData=l.data.beanList,s.total=l.data.tr);case 6:case"end":return n.stop()}}),n)})))()},handleEdit:function(e,t){var r=this;return Object(n.a)(regeneratorRuntime.mark((function n(){return regeneratorRuntime.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:r.loadOptions(),r.dialogTitle="编辑管理员信息",r.userInfo=t,r.dialogFormVisible=!0,console.log(e,t);case 5:case"end":return n.stop()}}),n)})))()},loadOptions:function(){var e=this;return Object(n.a)(regeneratorRuntime.mark((function t(){var r,n;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(a.d)({});case 2:r=t.sent,n=r.data,200===r.status&&200===n.state&&(e.roleList=n.data.roles,e.cinemaList=n.data.cinemas);case 6:case"end":return t.stop()}}),t)})))()},manageUserInfo:function(){var e=this;return Object(n.a)(regeneratorRuntime.mark((function t(){var r,n;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if(!(e.userInfo.username&&e.userInfo.password&&e.userInfo.roleId)){t.next=9;break}return t.next=3,Object(a.e)({userId:e.userInfo.id,name:e.userInfo.name,username:e.userInfo.username,password:e.userInfo.password,roleId:e.userInfo.roleId,cineamId:e.userInfo.cineamId});case 3:r=t.sent,n=r.data,200===r.status&&200===n.state?(e.dialogFormVisible=!1,e.loadCurrentPageUser(e.currentPage,8,e.searchInput),s.Message.success("修改管理员信息成功！")):s.Message.error(n.message),t.next=10;break;case 9:s.Message.error("请完成必填内容！");case 10:case"end":return t.stop()}}),t)})))()},currentChange:function(e){var t=this;return Object(n.a)(regeneratorRuntime.mark((function r(){return regeneratorRuntime.wrap((function(r){for(;;)switch(r.prev=r.next){case 0:t.currentPage=e,t.loadCurrentPageUser(t.currentPage,8,t.searchInput);case 2:case"end":return r.stop()}}),r)})))()},cancel:function(){this.loadCurrentPageUser(this.currentPage,8,this.searchInput),this.dialogFormVisible=!1},addAdmin:function(){this.loadOptions(),this.hallInfo={},this.dialogFormVisible=!0},search:function(){this.searchInput=this.input,this.loadCurrentPageUser(1,8,this.searchInput)}}},i=(r("31af"),r("2877")),l=Object(i.a)(o,(function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{attrs:{id:"user-manage"}},[r("div",{staticClass:"top"},[r("el-col",{attrs:{span:12}},[r("el-input",{staticClass:"input-with-select",staticStyle:{width:"100%"},attrs:{placeholder:"请输入内容"},model:{value:e.input,callback:function(t){e.input=t},expression:"input"}},[r("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:e.search},slot:"append"},[e._v("搜索")])],1)],1),r("el-col",{attrs:{span:2,offset:1}},[r("el-button",{attrs:{type:"primary",size:"small"},on:{click:e.addAdmin}},[e._v("添加管理员")])],1)],1),r("div",{staticClass:"user-table"},[r("el-table",{attrs:{border:"",data:e.tableData}},[r("el-table-column",{attrs:{label:"用户 ID",align:"center",width:"80",prop:"id"}}),r("el-table-column",{attrs:{label:"用户名",align:"center",width:"300",prop:"name"}}),r("el-table-column",{attrs:{label:"账号",align:"center",width:"150",prop:"username"}}),r("el-table-column",{attrs:{label:"密码",align:"center",width:"150",prop:"password"}}),r("el-table-column",{attrs:{label:"所属影院",align:"center","show-overflow-tooltip":"",prop:"cinemaNm"}}),r("el-table-column",{attrs:{align:"center",width:"200",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{size:"mini"},on:{click:function(r){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),r("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(r){return e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1),e.tableData.length?r("div",{staticClass:"block"},[r("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":8,"page-count":e.total},on:{"current-change":e.currentChange}})],1):e._e(),r("div",[e.dialogFormVisible?r("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogFormVisible,"modal-append-to-body":!1,showClose:!1},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[r("el-form",{attrs:{"label-position":e.labelPosition,rules:e.rules,"label-width":"80px",model:e.userInfo}},[r("el-form-item",{attrs:{label:"用户名",prop:"name"}},[r("el-col",{attrs:{span:16}},[r("el-input",{model:{value:e.userInfo.name,callback:function(t){e.$set(e.userInfo,"name",t)},expression:"userInfo.name"}})],1)],1),r("el-form-item",{attrs:{label:"账号",prop:"username"}},[r("el-col",{attrs:{span:16}},[r("el-input",{model:{value:e.userInfo.username,callback:function(t){e.$set(e.userInfo,"username",t)},expression:"userInfo.username"}})],1)],1),r("el-form-item",{attrs:{label:"密码",prop:"password"}},[r("el-col",{attrs:{span:16}},[r("el-input",{model:{value:e.userInfo.password,callback:function(t){e.$set(e.userInfo,"password",t)},expression:"userInfo.password"}})],1)],1),r("el-form-item",{attrs:{label:"所属角色",prop:"roleId"}},[r("el-col",{attrs:{span:18}},[r("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择角色"},model:{value:e.userInfo.roleId,callback:function(t){e.$set(e.userInfo,"roleId",t)},expression:"userInfo.roleId"}},e._l(e.roleList,(function(e,t){return r("el-option",{key:t,attrs:{label:e.name,value:e.id}})})),1)],1)],1),r("el-form-item",{attrs:{label:"所属影院",prop:"cineamId"}},[r("el-col",{attrs:{span:18}},[r("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择影院"},model:{value:e.userInfo.cineamId,callback:function(t){e.$set(e.userInfo,"cineamId",t)},expression:"userInfo.cineamId"}},e._l(e.cinemaList,(function(e,t){return r("el-option",{key:t,attrs:{label:e.nm,value:e.id}})})),1)],1)],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:e.cancel}},[e._v("取 消")]),r("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.manageUserInfo()}}},[e._v("确 定")])],1)],1):e._e()],1)])}),[],!1,null,"b0521dce",null);t.default=l.exports},b0c0:function(e,t,r){var n=r("83ab"),a=r("9bf2").f,s=Function.prototype,o=s.toString,i=/^\s*function ([^ (]*)/,l="name";n&&!(l in s)&&a(s,l,{configurable:!0,get:function(){try{return o.call(this).match(i)[1]}catch(e){return""}}})},c24f:function(e,t,r){"use strict";r.d(t,"c",(function(){return a})),r.d(t,"a",(function(){return s})),r.d(t,"b",(function(){return o})),r.d(t,"e",(function(){return i})),r.d(t,"d",(function(){return l}));var n=r("a27e"),a=function(e){return Object(n.a)({url:"/admin/user/getUsers",method:"GET",params:e})},s=function(e){return Object(n.a)({url:"/admin/user/banUser",method:"POST",data:e})},o=function(e){return Object(n.a)({url:"/admin/auser/getAdmins",method:"GET",params:e})},i=function(e){return Object(n.a)({url:"/admin/auser/updateInfo",method:"POST",data:e})},l=function(e){return Object(n.a)({url:"/admin/auser/getOptions",method:"GET",params:e})}}}]);