INSERT INTO customer (name, email, telefono, direccion)
VALUES ('Juan Perez', 'juan.perez@gmail.com', '555-1234', 'Calle 123');

INSERT INTO product (name, description, price, stock)
VALUES ('Laptop', 'Laptop de 15 pulgadas', 1000.00, 10);

SELECT * FROM customer;

SELECT * FROM product;

http://localhost:8080/orders (POST)

{
  "customerId": 1,
  "fecha": "2024-10-22",
  "status": "PENDIENTE",
  "orderDetails": [
    {
      "product": {
        "productId": 5
      },
      "quantity": 2,
      "price": 1000.00
    }
  ]
}


http://localhost:8080/orders/5 (GET)


http://localhost:8080/orders/5/status (PUT)

{Pagado}


http://localhost:8080/orders/5 (DELETE)