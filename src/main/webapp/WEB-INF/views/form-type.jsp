<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>


<form action="${pageContext.request.contextPath}/type" method="post">
    <input type="text" hidden="" name="id" value="${type.id}">
    <label for="Libelle">Libelle</label>
    <input type="text" name="libelle" value ="${type.libelle}" />

    <label for="typeId">Type d'assurance</label>
    <select name="typeId" id="typeId">
        <option value="">Selectionnez un type</option>
        <c:forEach items="${types}" var="type">
            <option value="${type.id}" ${product.typeProduct != null && product.typeProduct.id == type.id ? 'selected' : ''}>
                    ${type.libelle}
            </option>
        </c:forEach>
    </select>
    <button type="submit">Enregistrer</button>
</form>