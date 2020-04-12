const express = require('express');
const router = express.Router();

const verifyToken = require('./VerifyToken');

const Product = require('../model/Product');
const Response = require('../model/Response');
const ResponseStatus = require('../model/ResponseStatus');
const ProductResponse = require('../model/ProductResponse');

// Creates a new product
router.post('/', verifyToken, function (req, res) {
    let name = req.body.name;
    let description = req.body.description;
    let thumbnail = req.body.thumbnail;
    let price = req.body.price;
    let rating = req.body.rating;
    let images = req.body.images;
    let category = req.body.category;
    let store = req.body.store;

    console.log('Creating a new product...')

    if (name && description && price !== 'undefined') {
        Product.create({
            name: name,
            description: description,
            price: price,
            rating: 2.5,
        },
            function (err, product) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                res.status(200).json(new ProductResponse(ResponseStatus.OK, [product]));
            });
    }
});

// Returns all products in the db
router.get('/', function (req, res) {
    Product.find({}, function (err, products) {
        if (err) {
            console.err(err);
            return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
        }

        return res.status(200).json(new ProductResponse(ResponseStatus.OK, products));
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