<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>骡窝窝系统管理平台</title>
<#include "../common/header.ftl"/>

    <link rel="stylesheet" href="/js/plugins/bootstrap-treeview/bootstrap-treeview.min.css" type="text/css"/>
    <script src="/js/plugins/bootstrap-treeview/bootstrap-treeview.min.js"></script>
    <script type="text/javascript">
        $(function () {

            //页面加载完之后发送请求查询一级地址
            // $.get(url, data, callback)
            $.get("/region/getRegionsMapByParentId.do", function (data) {
                var defaultData = [
                    {
                        text: '全部地区',
                        nodes: data
                    }
                ];

                $('#treeview1').treeview({
                    data: defaultData,
                    showTags: true,
                    lazyLoad: function (node) {




                        //查询当前节点下的子地址的数据
                        $.get("/region/getRegionsMapByParentId.do", {parentId: node.id}, function (data) {
                            //将数据添加到当前节点下面
                            $('#treeview1').treeview("addNode", [data, node])
                        })
                    },
                    onNodeSelected: function (event, data) {
                        //找到该节点对象
                        var temp = $("#treeview1").treeview('findNodes', [data.text, "text"]);
                        //展开该节点
                        $("#treeview1").treeview('toggleNodeExpanded', [temp, {silent: true, ignoreChildren: true}]);

                        $.get("/region/getRegionsByParentId.do", {parentId: data.id}, function (data) {

                            //清空表格中的数据
                            $(".table tbody").empty();

                            //将数据设置到页面中并显示
                            //1.拼接一个页面中需要的元素
                            /*<tr>
                                <td>1</td>
                                <td>广州</td>
                                <td><a href="#">修改</a></td>
                                <td><a href="#">设为推荐</a></td>
                            </tr>*/


                            //2.拷贝一个tr的模板,设置其中的数据,然后将其添加到表格中
                            $(data).each(function (index, region) {
                                //clone:如果参数为true,那么可以拷贝元素中的事件
                                var tr = $("#template tr:first").clone(true);
                                tr.find("td:eq(0)").text(index + 1);
                                tr.find("td:eq(1)").text(region.name);

                                //将每个地区的事件绑定到当前的编辑按钮上
                                tr.find(".btn-input").attr("data-json", JSON.stringify(region));

                                //修改推荐列的元素
                                if (region.state == 1) {
                                    tr.find(".btn-recommend").text("取消推荐").attr("data-json", JSON.stringify(region));
                                } else {
                                    tr.find(".btn-recommend").text("设为推荐").attr("data-json", JSON.stringify(region));
                                }


                                $(".table tbody").append(tr);
                            })

                        })
                    }
                });
            })


            //添加/修改 操作
            $(".btn-input").click(function () {

                //清空表单元素中的数据
                $("#editForm input").val("");


                //如果没有选择节点,那么就不能添加
                var parentNodes = $('#treeview1').treeview('getSelected');
                console.log(parentNodes);
                if (parentNodes.length == 0) {
                    //给用户提示信息
                    $.messager.alert("温馨提示", "请选择要添加地区的父级地区");
                    return;
                }

                //设置上级地区
                console.log(parentNodes);
                $("input[name='parent.id']").val(parentNodes[0].id);
                $("input[name='parent.name']").val(parentNodes[0].text);


                //获取按钮上中的地区数据
                var region = $(this).data("json");
                if (region) {
                    $("input[name=id]").val(region.id);
                    $("input[name=name]").val(region.name);
                }


                //弹出模态框
                $("#regionModal").modal("show");
            })


            //保存数据
            $("#saveBtn").click(function () {
                $("#editForm").submit();
            })

            //设置推荐
            $(".btn-recommend").click(function () {
                //获取到当前按钮上绑定的region的数据
                //然后根据region的状态值来确定发送到服务端的state
                var region = $(this).data("json");
                if (region.state == 0) {
                    window.location.href = "/region/updateState.do?id=" + region.id + "&state=1";
                } else {
                    window.location.href = "/region/updateState.do?id=" + region.id + "&state=0";
                }


            })
        });
    </script>
</head>

<body>

<#--表格模板-->
<table id="template" style="display: none;">
    <tr>

        <td>1</td>
        <td>广州</td>
        <td><a href="#" class="btn-input">修改</a></td>
        <td><a href="#" class="btn-recommend">设为推荐</a></td>
    </tr>
</table>


<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
        <#assign currentMenu = "region" />
				<#include "../common/menu.ftl" /></div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>平台用户管理</h3>
            </div>
            <div class="row">
                <div class="col-sm-4">

                    <div id="treeview1"></div>

                </div>
                <div class="col-sm-8">

                    <div class="row">
                        <!-- 提交分页的表单 -->
                        <form id="searchForm" class="form-inline" method="post"
                              action="/user/list.do">
                            <div class="form-group">
                                <button id="query" type="button" class="btn btn-success btn-input">
                                    <i class="icon-search"></i>
                                    添加
                                </button>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>名称</th>

                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>

                </div>

            </div>


        </div>
    </div>
</div>


<div id="regionModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">编辑/增加</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" method="post" action="/region/saveOrUpdate.do">
                    <input id="regionId" type="hidden" name="id" value=""/>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" placeholder="地区/景区名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">上级地区</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="parent.name" readonly>
                            <input type="hidden" class="form-control" name="parent.id">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript:void(0);" class="btn btn-success" id="saveBtn" aria-hidden="true">保存</a>
                <a href="javascript:void(0);" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>