(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-decc151e"],{"1ae7":function(t,e,r){},"62e7":function(t,e,r){"use strict";r.r(e);var a=r("1da1"),n=(r("96cf"),r("f8b7")),o=r("5c96"),c={name:"OrderManage",data:function(){return{tableData:[],total:0,currentPage:1,dialogFormVisible:!1,dialogTitle:"",labelPosition:"left",input:"",searchInput:""}},created:function(){this.loadCurrentPageOrder(this.currentPage,8,"")},methods:{loadCurrentPageOrder:function(t,e,r){var o=this;return Object(a.a)(regeneratorRuntime.mark((function a(){var c,l,i;return regeneratorRuntime.wrap((function(a){for(;;)switch(a.prev=a.next){case 0:return c=localStorage.getItem("cinemaId"),a.next=3,Object(n.d)({pageNum:t,limit:e,keyword:r,cinemaId:c});case 3:l=a.sent,i=l.data,200===l.status&&200===i.state&&(o.tableData=i.data.beanList,o.total=i.data.tr);case 7:case"end":return a.stop()}}),a)})))()},currentChange:function(t){var e=this;return Object(a.a)(regeneratorRuntime.mark((function r(){return regeneratorRuntime.wrap((function(r){for(;;)switch(r.prev=r.next){case 0:e.currentPage=t,e.loadCurrentPageOrder(e.currentPage,8,e.searchInput);case 2:case"end":return r.stop()}}),r)})))()},search:function(){this.searchInput=this.input,this.loadCurrentPageOrder(1,8,this.searchInput)},handleDelete:function(t,e){var r=this;o.MessageBox.confirm("此操作将永久删除该订单所有信息, 是否继续？","提示").then(function(){var t=Object(a.a)(regeneratorRuntime.mark((function t(a){var c,l;return regeneratorRuntime.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:if("confirm"!==a){t.next=7;break}return t.next=3,Object(n.a)({orderId:e.id});case 3:c=t.sent,l=c.data,200===c.status&&200===l.state&&(o.Message.success("删除该订单成功！"),r.loadCurrentPageOrder(r.currentPage,8,r.searchInput));case 7:case"end":return t.stop()}}),t)})));return function(e){return t.apply(this,arguments)}}()),console.log(t,e)}}},l=(r("c122"),r("2877")),i=Object(l.a)(c,(function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{attrs:{id:"order-manage"}},[r("div",{staticClass:"top"},[r("el-col",{attrs:{span:12}},[r("el-input",{staticClass:"input-with-select",staticStyle:{width:"100%"},attrs:{placeholder:"请输入订单号"},model:{value:t.input,callback:function(e){t.input=e},expression:"input"}},[r("el-button",{attrs:{slot:"append",icon:"el-icon-search"},on:{click:t.search},slot:"append"},[t._v("搜索")])],1)],1),r("el-col",{attrs:{span:2,offset:1}})],1),r("div",{staticClass:"order-table"},[r("el-table",{staticStyle:{width:"100%"},attrs:{border:"",data:t.tableData}},[r("el-table-column",{attrs:{label:"订单ID",align:"center",width:"70",prop:"id"}}),r("el-table-column",{attrs:{label:"商品ID",width:"70","show-overflow-tooltip":"",prop:"itemId"}}),r("el-table-column",{attrs:{label:"商品类型","show-overflow-tooltip":"",prop:"itemType"}}),r("el-table-column",{attrs:{label:"档期/影院 ID",width:"110","show-overflow-tooltip":"",prop:"relateId"}}),r("el-table-column",{attrs:{label:"补充信息",width:"150","show-overflow-tooltip":"",prop:"describe"}}),r("el-table-column",{attrs:{label:"订单号",width:"120",align:"center",prop:"orderId"}}),r("el-table-column",{attrs:{label:"购买用户",align:"center",prop:"userNick"}}),r("el-table-column",{attrs:{label:"总价",align:"center",prop:"price"}}),r("el-table-column",{attrs:{label:"下单时间",width:"200","show-overflow-tooltip":"",prop:"createTime"}}),r("el-table-column",{attrs:{width:"100",align:"center",label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[r("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(r){return t.handleDelete(e.$index,e.row)}}},[t._v("撤销")])]}}])})],1)],1),t.tableData.length?r("div",{staticClass:"block"},[r("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":8,"page-count":t.total},on:{"current-change":t.currentChange}})],1):t._e()])}),[],!1,null,null,null);e.default=i.exports},c122:function(t,e,r){"use strict";r("1ae7")},f8b7:function(t,e,r){"use strict";r.d(e,"e",(function(){return n})),r.d(e,"a",(function(){return o})),r.d(e,"g",(function(){return c})),r.d(e,"h",(function(){return l})),r.d(e,"b",(function(){return i})),r.d(e,"f",(function(){return u})),r.d(e,"d",(function(){return s})),r.d(e,"c",(function(){return d}));var a=r("a27e"),n=function(t){return Object(a.a)({url:"/admin/order/getOrders",method:"GET",params:t})},o=function(t){return Object(a.a)({url:"/admin/order/deleteOrder",method:"POST",data:t})},c=function(t){return Object(a.a)({url:"/admin/snack/getSnacks",method:"GET",params:t})},l=function(t){return Object(a.a)({url:"/admin/snack/addSnack",method:"POST",data:t})},i=function(t){return Object(a.a)({url:"/admin/snack/deleteSnack",method:"POST",data:t})},u=function(t){return Object(a.a)({url:"/admin/hall/getOptions",method:"GET",params:t})},s=function(t){return Object(a.a)({url:"/admin/order/getOrders",method:"GET",params:t})},d=function(t){return Object(a.a)({url:"/admin/snack/getSnacks",method:"GET",params:t})}}}]);