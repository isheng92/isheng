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
	var parentId = $(this).children("a").attr("data-id");
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
	
	$("#left .accordion").hide();
	if (menuNode && menuNode.length > 0) {
		menuNode.show();
	} else {
		$.post(indexJs.url.loadMenu, {
			'parentId': parentId
		}, function(data) {
			alert(data);
			$("#left").html(data);
		})
	}
}


$(document).ready(function() {
	loadRootMenu();
});