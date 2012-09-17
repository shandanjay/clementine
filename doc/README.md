# Clementine shop

## Store core

Clementine online store.
This project is intended to be the foundation of Clojure-based ecommerce software
code named Cafe store.

## Modelo de datos (Cafe core v0.0.1)

### __store__
* email
* name
* templates

### __configuration__

### __products__

### __categories__

### __customers__

### __order__
* id :integer
* number :string
* customer :integer
* date_purchased :datetime
* last_modified :datetime
* payment_method :string

### __order\_line\_items__

### __order_totals__
* id :integer
* order_id :integer
* value :float
* type :string

### __cart__
* id :integer
* customer_id :integer
* product_id :integer
* date_added :datetime

### modules
* module_name :string (machine readable)
* name :string
* type :enumeration

### coupons


## License

Copyright &copy; 2012 - Eduardo Raad @eraad, Juan Antonio Plaza @jplazaarguello