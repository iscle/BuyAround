const express = require('express');
const router = express.Router();

const verifyToken = require('./VerifyToken');

const Order = require('../model/Order');
const Response = require('../model/Response');
const ResponseStatus = require('../model/ResponseStatus');
const OrderResponse = require('../model/OrderResponse');

// Creates a new order
router.post('/', verifyToken, function (req, res) {
    let products = req.body.products;
    let user = req.body.user;
    let price = req.body.price;

    console.log('Creating a new order...')

    if (products && user && price) {
        Order.create({
            products: products,
            user: user,
            price: price,
        },
            function (err, orders) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                res.status(200).json(new OrderResponse(ResponseStatus.OK, [orders]));
            });
    }
});

// Returns all orders in the db
router.get('/', verifyToken, function (req, res) {
    Order.find({}, function (err, orders) {
        if (err) {
            console.err(err);
            return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
        }

        return res.status(200).json(new OrderResponse(ResponseStatus.OK, orders));
    });
});

// Returns a single product from the db
router.get('/:id', function (req, res) {
    if (req.params.id.match(/^[0-9a-fA-F]{24}$/)) {
        Product.findById(req.params.id, function (err, product) {
            if (err) {
                console.err(err);
                return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
            }

            return res.status(200).json(new ProductResponse(ResponseStatus.OK, [product]));
        });
    } else {
        return res.status(200).json(new ProductResponse(ResponseStatus.OK, []));
    }
});

// Deletes a user from the database
router.delete('/:id', function (req, res) {
    User.findByIdAndRemove(req.params.id, function (err, user) {
        if (err) {
            console.err(err);
            return res.status(500).json("There was a problem deleting the user.");
        }

        res.status(200).json("User: " + user.name + " was deleted.");
    });
});

// Updates a single user in the database
// Added VerifyToken middleware to make sure only an authenticated user can put to this route
router.put('/:id', function (req, res) {
    User.findByIdAndUpdate(req.params.id, req.body, { new: true }, function (err, user) {
        if (err) {
            console.err(err);
            return res.status(500).json("There was a problem updating the user.");
        }

        res.status(200).json(user);
    });
});


module.exports = router;