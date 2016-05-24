<%@ include file="/WEB-INF/importlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<template:page adminPage="${true}">

    <template:head title="Alumni System - Admin - Survey Form Builder">

        <template:style>
            <link href="${RouteHelper:generateURL("assets/css/bootstrap-form-builder.css")}" rel="stylesheet">
        </template:style>

    </template:head>

    <template:body>

        <template:navbar />

        <div class="container-admin">

            <div class="page-header">
                <h1>สร้างฟอร์มแบบสอบถาม</h1>
            </div>

            <div class="row clearfix">
                <!-- Building Form. -->
                <div class="col-md-6">
                    <div class="clearfix">
                        <div id="build">
                            <form id="target" class="form-horizontal">
                            </form>
                        </div>
                    </div>
                </div>
                <!-- / Building Form. -->

                <!-- Components -->
                <div class="col-md-6">
                    <ul class="nav nav-tabs" id="formtabs">
                        <!-- Tab nav -->
                    </ul>
                    <form class="form-horizontal" id="components">
                        <fieldset>
                            <div class="tab-content">
                                <!-- Tabs of snippets go here -->
                            </div>
                        </fieldset>
                    </form>
                </div>
                <!-- / Components -->

            </div>

            <div class="row clearfix">
                <div class="col-md-12">
                    <hr>
                    Bootstrap-Form-Builder : Created By Adam Moore (<a href="http://twitter.com/minikomi" >@minikomi</a>).<br/>
                    Modified By Chaniwat Seangchai (<a href="http://twitter.com/meranotemajimo" >@meranotemajimo</a>).<br/>
                </div>
            </div>

        </div> <!-- /container -->

        <template:script skipBootstrap="${true}">
            <script data-main="${RouteHelper:generateURL("assets/js/bootstrap-form-builder.min.js")}" src="${RouteHelper:generateURL("assets/js/lib/require.js")}"></script>
        </template:script>

    </template:body>


</template:page>