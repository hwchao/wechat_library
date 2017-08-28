<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表"
	data-options="singleSelect:false,collapsible:true,pagination:true,url:'/book/list',method:'get',pageSize:30,toolbar:toolbar">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:120,align:'center',">图书ID</th>
			<th data-options="field:'title',width:120,align:'center',">书名</th>
			<th data-options="field:'subtitle',width:100,align:'center',">子书名</th>
			<th data-options="field:'author',width:80,align:'center',">作者</th>
			<th data-options="field:'pubdate',width:100,align:'center',formatter:TAOTAO.formatDate">出版日期</th>
			<th data-options="field:'publisher',width:110,align:'center'">出版社</th>
			<th data-options="field:'price',width:60,align:'center',">价格</th>
			<th data-options="field:'pages',width:50,align:'center'">页数</th>
			<th data-options="field:'bingding',width:50,align:'center'">装订</th>
			<th data-options="field:'translater',width:100,align:'center'">译者</th>
			<th data-options="field:'isbn13',width:80,align:'center'">ISBN13</th>
			<th data-options="field:'isbn10',width:80,align:'center'">ISBN10</th>
			<th data-options="field:'num',width:50,align:'center'">馆藏量</th>
		</tr>
	</thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/book-edit'"
	style="width: 80%; height: 80%; padding: 10px;"></div>
<script>
function getSelectionsIds() {
    var itemList = $("#itemList");
    var sels = itemList.datagrid("getSelections");
    var ids = [];
    for (var i in sels) {
        ids.push(sels[i].id);
    }
    ids = ids.join(",");
    return ids;
}

var toolbar = [{
    text: '新增',
    iconCls: 'icon-add',
    handler: function() {
        $(".tree-title:contains('新增商品')").parent().click();
    }
},
{
    text: '编辑',
    iconCls: 'icon-edit',
    handler: function() {
        var ids = getSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一个商品才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一个商品!');
            return;
        }

        $("#itemEditWindow").window({
            onLoad: function() {
                /*获取选中列的数据*/
                var data = $("#itemList").datagrid('getSelected');
                $("#itemeEditForm").form("load", data);
            }
        }).window("open");
    }
},
{
    text: '删除',
    iconCls: 'icon-cancel',
    handler: function() {
        var ids = getSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中商品!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的商品吗？',
        function(event) {
            if (event) {
                var params = {
                    "ids": ids
                };
                $.post("/book/delete", params,
                function(data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '删除商品成功!', undefined,
                        function() {
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},
'-', {
    text: '下架',
    iconCls: 'icon-remove',
    handler: function() {
        var ids = getSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中商品!');
            return;
        }
        $.messager.confirm('确认', '确定下架ID为 ' + ids + ' 的商品吗？',
        function(r) {
            if (r) {
                var params = {
                    "ids": ids
                };
                $.post("/rest/item/instock", params,
                function(data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '下架商品成功!', undefined,
                        function() {
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},
{
    text: '上架',
    iconCls: 'icon-remove',
    handler: function() {
        var ids = getSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中商品!');
            return;
        }
        $.messager.confirm('确认', '确定上架ID为 ' + ids + ' 的商品吗？',
        function(r) {
            if (r) {
                var params = {
                    "ids": ids
                };
                $.post("/rest/item/reshelf", params,
                function(data) {
                    if (data.status == 200) {
                        $.messager.alert('提示', '上架商品成功!', undefined,
                        function() {
                            $("#itemList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
}];
</script>