<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table class="easyui-datagrid" id="itemList" title="商品列表"
	data-options="singleSelect:false,collapsible:true,pagination:true,url:'/book/list',method:'get',pageSize:30,toolbar:'#tb'">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',width:120,align:'center',">ID</th>
			<th data-options="field:'title',width:120,align:'left',">书名</th>
			<th data-options="field:'subtitle',width:100,align:'left',">子书名</th>
			<th data-options="field:'author',width:80,align:'left',">作者</th>
			<th data-options="field:'pubdate',width:80,align:'center',formatter:TAOTAO.formatDate">出版日期</th>
			<th data-options="field:'publisher',width:110,align:'left'">出版社</th>
			<th data-options="field:'price',width:60,align:'center',">价格(元)</th>
			<th data-options="field:'pages',width:50,align:'center'">页数</th>
			<th data-options="field:'binding',width:50,align:'center'">装订</th>
			<th data-options="field:'translator',width:100,align:'left'">译者</th>
			<th data-options="field:'isbn13',width:100,align:'center'">ISBN13</th>
			<th data-options="field:'isbn10',width:80,align:'center'">ISBN10</th>
			<th data-options="field:'num',width:70,align:'center'">馆藏量(本)</th>
		</tr>
	</thead>
</table>
<div id="tb" style="padding:3px">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()">新增</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doDelete()">删除</a>

	<span>ID:</span>
	<input id="bookId" style="line-height:20px;border:1px solid #ccc;width:120px;">
	<span>书名:</span>
	<input id="bookTitle" style="line-height:20px;border:1px solid #ccc">
	<span>作者:</span>
	<input id="bookAuthor" style="line-height:20px;border:1px solid #ccc">
	<a href="#" class="easyui-linkbutton"  onclick="doSearch()">搜索</a>
</div>
<div id="itemEditWindow" class="easyui-window" title="编辑商品"
	data-options="modal:true,closed:true,iconCls:'icon-save',href:'/book-edit'"
	style="width: 80%; height: 80%; padding: 10px;">
</div>
<div id="itemAddWindow" class="easyui-window" title="添加商品"
	data-options="modal:true,closed:true,iconCls:'icon-add',href:'/book-add'"
	style="width: 80%; height: 80%; padding: 10px;">
</div>
<script>
function doSearch(){
	$('#itemList').datagrid('load',{
		id: $('#bookId').val(),
		title: $('#bookTitle').val(),
		author: $('#bookAuthor').val()
	});
}

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

function doAdd() {
	 $("#itemAddWindow").window("open");
}
function doEdit(){
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
            data.pubdate = TAOTAO.formatDate(data.pubdate); 
            $("#itemeEditForm").form("load", data);
        }
    }).window("open");
}
function doDelete() {
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

  

       /*  var ids = getSelectionsIds();
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
        }); */
</script>