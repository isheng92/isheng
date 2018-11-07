if (!indexJs) {
	var indexJs = {};
}

/**
 * 设置左侧导航区域宽度
 * */
$("#left").width(leftWidth);
/**
 * openClose手风琴展开和关闭左侧导航事件
 * @returns
 */
$("#openClose").click(function(){
	if($(this).hasClass("close")){
		$(this).removeClass("close");
		$(this).addClass("open");
		$("#left").animate({width:0,opacity:"hide"});
		$("#right").animate({width:$("#content").width()-$("#openClose").width()-5},function(){
			if(typeof openCloseClickCallBack == 'function'){
				openCloseClickCallBack(true);
			}
			wSize();
		});
	}else{
		$(this).addClass("close");
		$(this).removeClass("open");
		$("#left").animate({width:leftWidth,opacity:"show"});
		$("#right").animate({width:$("#content").width()-$("#openClose").width()-leftWidth-9},function(){
			if(typeof openCloseClickCallBack == 'function'){
				openCloseClickCallBack(true);
			}
			wSize();
		});
	}
});

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
	var nodeId = "#menu-" + parentId;
	var nodeObj = $(nodeId);
	
	$("#left .accordion").hide();
	if (nodeObj && nodeObj.length > 0) {
		nodeObj.show();
	} else {
		$.post(indexJs.url.loadMenu, {
			'parentId': parentId
		}, function(data) {
			$("#left").html(data);
		})
	}
}


$(document).ready(function() {
	loadRootMenu();
});