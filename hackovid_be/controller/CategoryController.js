const express = require('express');
const router = express.Router();

const verifyToken = require('./VerifyToken');

const ProductCategory = require('../model/ProductCategory');
const StoreCategory = require('../model/StoreCategory');
const Response = require('../model/Response');
const ResponseStatus = require('../model/ResponseStatus');
const CategoryResponse = require('../model/CategoryResponse');

// Creates a new product category
router.post('/product', verifyToken, function (req, res) {
    let name = req.body.name;

    console.log('Creating a new product category...')

    if (name) {
        ProductCategory.create({
            name: name,
        },
            function (err, category) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                res.status(200).json(new CategoryResponse(ResponseStatus.OK, [category]));
            });
    }
});

// Returns all product categories in the db
router.get('/product', function (req, res) {
    ProductCategory.find({}, function (err, categories) {
        if (err) {
            console.err(err);
            return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
        }

        return res.status(200).json(new CategoryResponse(ResponseStatus.OK, categories));
    });
});

// Creates a new store category
router.post('/store', verifyToken, function (req, res) {
    let name = req.body.name;

    console.log('Creating a new store category...')

    if (name) {
        StoreCategory.create({
            name: name,
        },
            function (err, category) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                res.status(200).json(new CategoryResponse(ResponseStatus.OK, [category]));
            });
    }
});

// Returns all product categories in the db
router.get('/store', function (req, res) {
    StoreCategory.find({}, function (err, categories) {
        if (err) {
            console.err(err);
            return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
        }

        return res.status(200).json(new CategoryResponse(ResponseStatus.OK, categories));
    });
});

// Returns a single product from the db
router.get('/:id', function (req, res) {
    if (req.params.id.match(/^[0-9a-fA-F]{24}$/)) {
        ProductCategory.findById(req.params.id, function (err, product) {
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