# Steps
1. Main and Secondary Resources
2. URLs and Methods
3. Requests and Responses

# 1 Resources
/customers
    _view, search, and query products in different ways_
    _add products to shopping cart_
    _make an order of the current shopping cart_
    _cancel an order_
    _view order status_
    _view previously made orders_ 

/clerks
    _CRUD on products_
    _approve customer orders_
    _update order status_

/admins
    _manage the store_
    _manage CRUD clerk accounts_
    _can do anything a clerk can do on the API_

/products

/categories 

# 2 URLs and Methods
/products
    GET: returns paginated products
    POST: adds a new product

/products/{id}
    GET: returns that particular product
    PUT: updates that particular product if it exists, otherwise adds it
    DELETE: deletes the product

/products/{id}/categories
    GET: gets all categories for that product
    POST: adds a new category for that product

/products/{id}/categories/{id}
    GET: gets that category
    DELETE: deletes that category from that product

**/products search**
/products?q="search+string"
    GET: searches for that string in the product name, description, and categories

--------------------------------------------------------

/categories
    GET: returns all categories
    POST: adds a new category

/categories/{id}
    GET: returns that particular category
    PUT: updates the category if it exists, otherwise adds it
    DELETE: deletes the category    

/categories/{id}/products
    GET: view all products for that given id

--------------------------------------------------------

/clerks
    GET
    POST

/clerks/{id}
    GET
    PUT
    DELETE

--------------------------------------------------------

/customers
    GET
    POST

/customers/{id}
    GET
    PUT
    DELETE

/customers/{id}/carts
    GET: get that customer's shopping cart
    PUT: create or update the customer's shopping cart, including adjusting quantities and adding new items
    POST: create the customer's shopping cart, error if it already exists
    DELETE: delete the customer's shopping cart

/customers/{id}/orders
    GET
    POST: creates a new order based on a customer's shopping cart

/customers/{id}/orders/{id}
    GET
    PUT: create or update an order, change order status, approve order, cancel order

--------------------------------------------------------

/carts
    GET: get all carts paginated
    POST: create a shopping cart associated with a logged out customer, uniquely identify it and link it with that customer using something like the session?

/carts/{id}
    GET 
    PUT
    DELETE

--------------------------------------------------------

/orders
    GET: get all orders paginated
    POST: creates a new order based on a customer's shopping cart

/orders/{id}
    GET
    PUT: create or update an order, change order status, approve order, cancel order
