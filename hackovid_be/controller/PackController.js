const express = require('express');
const router = express.Router();

const verifyToken = require('./VerifyToken');

const Pack = require('../model/Pack');
const Response = require('../model/Response');
const ResponseStatus = require('../model/ResponseStatus');
const PackResponse = require('../model/PackResponse');

// Creates a new pack
router.post('/', verifyToken, function (req, res) {
    let name = req.body.name;
    let images = req.body.images;
    let description = req.body.description;
    let price = req.body.price;
    let rating = req.body.rating;
    let products = req.body.products;

    console.log('Creating a new pack...')

    if (name && description && price && rating && products) {
        Pack.create({
            name: name,
            description: description,
            price: price,
            rating: rating,
            products: products,
        },
            function (err, packs) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                res.status(200).json(new PackResponse(ResponseStatus.OK, [packs]));
            });
    }
});

// Returns all packs in the db
router.get('/', function (req, res) {
    Pack.find({}, function (err, packs) {
        if (err) {
            console.err(err);
            return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
        }

        return res.status(200).json(new PackResponse(ResponseStatus.OK, packs));
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