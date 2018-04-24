<html>

<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
        <#include "../common/nav.ftl">
<#--主要内容区域-->
    <div id="page-content-wrapper">
        <div class="container-Fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名字</label>
                            <input type="text" class="form-control"  name="categoryName" value="${(productCategory.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input type="text" class="form-control"  name="categoryType" value="${(productCategory.categoryType)!''}"/>
                        </div>
                        </div>
                        <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                        <button type="submit" class="btn btn-default">修改</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>