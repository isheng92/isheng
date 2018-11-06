if (!indexJs) {
	var indexJs = {};
}

indexJs.url = {
	login: "/login",
	loadRoot: "/loadRoot",
	loadMenu: "/loadMenu"
}

/**
 * 加载根目录
 * @returns
 */
function loadRootMenu() {
	$.post(indexJs.url.loadRoot, function(data) {
		$("#headerNav").html(data);
	}).fail(function(err) {
		alertErr("用户根菜单加载失败！");
	});
}

/** 根目录菜单点击事件 */
$('body').on("click", "#menu li.menu", function() {
	$("#menu li.menu").removeClass("active");
	$(this).parent().addClass("active");
	var parentId = $(this).attr("data-id");
	if (!parentId) {
		alertErr("菜单ID为空!");
		return;
	}
	showLeftMenu(parentId);
})

/**
 * 加载二级菜单
 * @param parentId 一级菜单ID
 * @returns
 */
function showLeftMenu(parentId) {
	var menuId = "#menu-" + parentId;
	var menuNode = $(menuId);
	
	alert(menuId);
	
	$("#left .accordion").hide();
	if (!menuNode || menuNode.length > 0) {
		menuNode.show();
	} else {
		loadSecondMenu(parentId);
	}
}

/**
 * 加载二级菜单
 * @param parentId
 * @returns
 */
function loadSecondMenu(parentId) {
	$.post(indexJs.url.loadMenu,{
		'parentId': parentId
	}).done(function(data) {
		$("#left").html(data);
	}).fail(function() {
		alertErr("二级菜单加载失败！");
	})
}

$(document).ready(function() {
	loadRootMenu();
});