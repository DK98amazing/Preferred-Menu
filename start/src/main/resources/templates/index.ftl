<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h4>1.取值</h4>
${name}
<h4>2.条件</h4>
<#if name?exists>
    ${name}
</#if>
<#if sex=="男">
    男
<#else>
    女
</#if>

<#list book as b>
    ${b}
</#list>
</body>
</html>