<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="用户列表" data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:30">
	<thead>
		<tr>
		    <th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:120,align:'center'">用户ID</th>
			<th data-options="field:'name',width:100,align:'center'">用户名</th>
			<th data-options="field:'telephone',width:120,align:'center'">联系电话</th>
			<th data-options="field:'age',width:50,align:'center'">年龄</th>
			<th data-options="field:'email',width:100,align:'center'">邮箱地址</th>
			<th data-options="field:'avatarUrl',width:150,align:'center'">头像</th>
			<th data-options="field:'education',width:100,align:'center'">学历</th>
			<th data-options="field:'adress',width:200,align:'center'">地址</th>
		</tr>
	</thead>
</table>
<script>
	function getSelectionsIds() {
		var itemList = $("#itemList");
		var sels = itemList.datagrid("getSelections");
		var ids = [];
		for(var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}
</script>