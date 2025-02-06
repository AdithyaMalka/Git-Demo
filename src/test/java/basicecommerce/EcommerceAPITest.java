package basicecommerce;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EcommerceAPITest {

	public static void main(String[] args) {
		
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
		
		LoginRequest loginrequest = new LoginRequest();
		
		loginrequest.setUserEmail("noobnewbie@gmail.com");
		loginrequest.setUserPassword("CSurya@1915");
		
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginrequest);
		LoginResponse loginresponse = reqLogin.when().post("api/ecom/auth/login").then().log().all().assertThat().statusCode(200).extract().response().as(LoginResponse.class);
		
		String token = loginresponse.getToken();
		String UserID = loginresponse.getUserId();
		System.out.println("Token is: " + loginresponse.getToken());
		System.out.println("UserID is: " + loginresponse.getUserId());
		
		//AddProduct
		
		RequestSpecification addProductBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Authorization", token).build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBasereq).param("productName", "Plainteeshirt(black)").param("productAddedBy", UserID).param("productCategory", "fashion")
		.param("productSubCategory", "shirts").param("productPrice", "1000").param("productDescription", "Nike").param("productFor", "All")
		.multiPart("productImage", new File("C:\\Users\\nash\\Downloads\\tshirt.jpg"));
		
		String AddProductResponse = reqAddProduct.when().post("api/ecom/product/add-product").then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(AddProductResponse);
		String ProductID = js.getString("productId");
		System.out.println("Product ID is: " + ProductID);
		
		//Create Order
		
		RequestSpecification createOrderBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
				.addHeader("Authorization", token).build();
		
		OrderDetails orderdetails = new OrderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(ProductID);
		
		//As the orders can be multiple and the orders json is an array create a list
		//Also the orders class is having a List of Strings orderdetails
		
		List<OrderDetails> orderdetailsList = new ArrayList<OrderDetails>();
		orderdetailsList.add(orderdetails);
		
		Orders orders = new Orders();
		orders.setOrders(orderdetailsList);
		
		RequestSpecification creatingorder = given().log().all().spec(createOrderBasereq).body(orders);
		
		String AddOrderResponse = creatingorder.when().post("api/ecom/order/create-order").then().log().all().assertThat().statusCode(201).extract().response().asString();
		System.out.println("Add Order Response is: " + AddOrderResponse);
		
		JsonPath js1 = new JsonPath(AddOrderResponse);
		List<String> orderList = js1.getList("orders");
		String OrderID = orderList.get(0);
		System.out.println("OrderIDxyz is: " + OrderID);
		//String ProductOrderID = js1.getString("productOrderId");
		//System.out.println("ProductOrderID is: " + ProductOrderID);
		
		//Get Order Details
		
		RequestSpecification GetOrderBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("id", OrderID)
				.addHeader("Authorization", token).build();
		
		GetOrderResponse orderresponse = given().log().all().spec(GetOrderBasereq).when().get("api/ecom/order/get-orders-details").then().log().all().assertThat().statusCode(200)
				.extract().response().as(GetOrderResponse.class);
		System.out.println("order ID is: " + orderresponse.getData().get_id());
		System.out.println("Country is: " + orderresponse.getData().getCountry());
		System.out.println("Message: " + orderresponse.getMessage());
		System.out.println("OrderBy is: " + orderresponse.getData().getOrderBy());
		System.out.println("OrderById is: " + orderresponse.getData().getOrderById());
		System.out.println("OrderPrice is: " + orderresponse.getData().getOrderPrice());
		System.out.println("ProductDescription is: " + orderresponse.getData().getProductDescription());
		System.out.println("ProductImage path is: " + orderresponse.getData().getProductImage());
		System.out.println("ProductName is: " + orderresponse.getData().getProductName());
		System.out.println("ProductOrderedId is: " + orderresponse.getData().getProductOrderedId());
		
		//Delete Product
		
		RequestSpecification DelProdBasereq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
		
		RequestSpecification DeletingProduct = given().log().all().spec(DelProdBasereq).pathParam("productId", ProductID);
		
		//Pathparameter is case sensitive
		
		String  DeleteResponse = DeletingProduct.when().log().all().delete("api/ecom/product/delete-product/{productId}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Message is: " + DeleteResponse);
	}

}
