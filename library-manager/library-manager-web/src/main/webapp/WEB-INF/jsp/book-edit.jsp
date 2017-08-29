<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/common.js"></script>
<div style="padding: 10px 10px 10px 10px">
	<form id="itemeEditForm" class="itemForm" method="post">
		<input type="hidden" name="id" />
		<table cellpadding="5">
			<tr>
				<td>图书类目:</td>
				<td><a href="javascript:void(0)"
					class="easyui-linkbutton selectItemCat">选择类目</a> <input
					type="hidden" name="cid" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>图书封面:</td>
				<td><a href="javascript:void(0)"
					class="easyui-linkbutton picFileUpload">上传图片</a> <input
					type="hidden" name="image" /></td>
			</tr>
			<tr>
				<td>书名:</td>
				<td><input class="easyui-textbox" type="text" name="title"
					data-options="required:true" style="width: 280px" /></td>
			</tr>
			<tr>
				<td>子书名:</td>
				<td><input class="easyui-textbox" type="text" name="subtitle"
					data-options="required:false" style="width: 280px" /></td>
			</tr>
			<tr>
				<td>作者:</td>
				<td><input class="easyui-textbox" type="text" name="author"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td>出版日期:</td>
				<td><input class="easyui-datebox" type="text" name="pubdate"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td>出版社:</td>
				<td><input class="easyui-textbox" type="text" name="publisher"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td>价格:</td>
				<td><input class="easyui-numberbox" type="text" name="price"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td>页数:</td>
				<td><input class="easyui-numberbox" type="text" name="pages"
					data-options="required:false" /></td>
			</tr>
			<tr>
				<td>装订:</td>
				<td><input class="easyui-textbox" type="text" name="binding"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<td>译者:</td>
				<td><input class="easyui-textbox" type="text" name="translator"
					data-options="required:false" /></td>
			</tr>
			<tr>
				<td>ISBN13:</td>
				<td><input class="easyui-textbox" type="text" name="isbn13"
					data-options="required:false" /></td>
			</tr>
			<tr>
				<td>ISBN10:</td>
				<td><input class="easyui-textbox" type="text" name="isbn10"
					data-options="required:false" /></td>
			</tr>
			<tr>
				<td>馆藏量:</td>
				<td><input class="easyui-numberbox" type="text" name="num"
					data-options="required:true" /></td>
			</tr>
		</table>
	</form>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$('.easyui-datebox').datebox({
		    required:true
		});
	})
	function submitForm() {
		if (!$('#itemeEditForm').form('validate')) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}
		/* itemEditEditor.sync(); */

		$.post("/book/update", $("#itemeEditForm").serialize(), function(data) {
			if (data.status == 200) {
				$.messager.alert('提示', '修改信息成功!', 'info', function() {
					$("#itemEditWindow").window('close');
					$("#itemList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示','修改信息失败','info',function(){});
			}
		});
	}
</script>
