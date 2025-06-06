<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>List Product In Cart</h4>
    <table border="1">
        <thead>
            <tr>
                <td>Id</td>
                <td>Product Name</td>
                <td>Price</td>
                <td>Action</td>
            </tr>
        </thead>
        <tbody>
<%--            <th:forEach items="${products}" var="item">--%>
            <c:forEach items="${products}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.productName}</td>
                    <td>${item.price}</td>
                    <td><a href="/product-detail/${item.id}">Detail</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p>Total product: ${products.size()}</p>
    <p>Total Price: ${totalPrice}</p>
</body>
</html>
