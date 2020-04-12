const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const db = require('./db');

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/api', function (req, res) {
  res.status(200).send('It works!');
});

const UserController = require('./controller/UserController');
app.use('/api/user', UserController);

const AuthController = require('./controller/AuthController');
app.use('/api/auth', AuthController);

const ProductController = require('./controller/ProductController');
app.use('/api/product', ProductController);

const StoreController = require('./controller/StoreController');
app.use('/api/store', StoreController);

const CategoryController = require('./controller/CategoryController');
app.use('/api/category', CategoryController);

const PackController = require('./controller/PackController');
app.use('/api/pack', PackController);

const OrderController = require('./controller/OrderController');
app.use('/api/order', OrderController);

module.exports = app;