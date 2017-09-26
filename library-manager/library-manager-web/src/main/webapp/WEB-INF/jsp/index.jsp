<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>"无微不至"借阅助手</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.content {
	padding: 10px 15px 10px 10px;
}
.desc{
	line-height:150%;
	font-size:15px;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'菜单',split:true"
		style="width: 180px;">
		<ul id="menu" class="easyui-tree"
			style="margin-top: 10px; margin-left: 5px;">
			<li><span>图书管理</span>
				<ul>
					<li data-options="attributes:{'url':'book-list'}">图书管理</li>
					<li data-options="attributes:{'url':'book-category'}">分类管理</li>
				</ul>
			</li>
			<li><span>借阅管理</span>
				<ul>
					<li data-options="attributes:{'url':'javascript:viod(0)'}">已借图书</li>
					<li data-options="attributes:{'url':'javascript:viod(0)'}">预定图书</li>
				</ul>
			</li>
			<li><span>用户管理</span>
				<ul>
					<li data-options="attributes:{'url':'user-list'}">用户列表</li>
				</ul>
			</li>
		</ul>
	</div>
	<div data-options="region:'center',title:''">
		<div id="tabs" class="easyui-tabs">
			<div title="首页" style="padding: 20px;">
	     		<h1>无微不至借阅助手管理系统</h1>
	     		<kbd class="desc">&nbsp;&nbsp;在科技高度发达，信息大爆炸的今天，人们的生活习惯渐渐地发生了改变。阅读就是其中一个很典型的例子。如今我们阅读的渠道很多：报纸、刊物、书籍、邮件、微博、微信、今日头条、腾讯新闻、澎湃、简书等等不胜枚举，不管是在家里还是在单位，我们几乎每时每刻都有机会阅读。但问题是，我们是否还记得曾几何时，我们抱着一本书，完完整整地看完一遍又一遍？我们被碎片化了，信息时代让我们的见识广了，但是我们中的大部分却渐渐丧失了独立思考的能力了。实际上，我们成天处于一种被各种信息“洗脑”的状态。</kbd>
				<br/>
				<kbd class="desc">&nbsp;&nbsp;有人说可以订立读书计划，买纸质书来读。不过老话说得好，“书非借不能读也”，一旦买下来，最终大多束之高阁，不了了之。好的习惯往往需要一些“逼迫”的意味在里面。那种害怕错失的那种感觉会让人在这个节奏飞快的时代里为阅读安排出宝贵的时间。</kbd>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#menu').tree({
				onClick : function(node) {
					if ($('#menu').tree("isLeaf", node.target)) {
						var tabs = $("#tabs");
						var tab = tabs.tabs("getTab", node.text);
						if (tab) {
							tabs.tabs("select", node.text);
						} else {
							tabs.tabs('add', {
								title : node.text,
								href : node.attributes.url,
								closable : true,
								bodyCls : "content"
							});
						}
					}
				}
			});
		});
	</script>
</body>
</html>