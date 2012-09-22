# clementine

E-commerce platform running a noir-based web server with tools for:

* Publishing products
* Managing orders
* Designing templates
* Learning from customers
* Offering coupons
* Capturing mobile payments (iOS app)

## Usage
Using leiningen 2.x:

    DATABASE_URL=postgresql://DBUSER:PASSWORD@HOST/DBNAME lein run

Using foreman:

	foreman start -e development.env

_Where **development.env** contains the declaration for the DATABASE\_URL_

## License

Copyright &copy; 2012 Datilmedia S.A., Juan Antonio Plaza [@jplazaarguello](http://twitter.com/jplazaarguello), Eduardo Raad [@eraad](http://twitter.com/eraad).

Distributed under the Eclipse Public License, the same as Clojure.
