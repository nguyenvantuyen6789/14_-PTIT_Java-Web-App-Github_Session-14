<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>Product Detail</h4>

    <div>
        Id: ${product.id}
    </div>
    <div>
        Product Name: ${product.productName}
    </div>
    <div>
        Price: ${product.price}
    </div>

    <a href="/add-to-cart/${product.id}">Add to cart</a>
</body>
</html>
