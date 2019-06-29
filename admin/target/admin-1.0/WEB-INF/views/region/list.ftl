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
            $.get("/region/getRegionsMapByParentId.do",function (data) {
                nodes = data;
            })


            var nodes;
            var defaultData = [
                {
                    text: '全部地区',
                    tags: ['4'],
                    nodes: nodes
                }
            ];

            $('#treeview1').treeview({
                data: defaultData,
                showTags:true
            });

        });
    </script>
</head>

<body>
<div class="container">
<#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3"><#assign currentMenu = "user" />
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
                                <button id="query" type="button" class="btn btn-success">
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
</body>
</html>