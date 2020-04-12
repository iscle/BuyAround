const express = require('express');
const router = express.Router();

const verifyToken = require('./VerifyToken');

const Store = require('../model/Store').Model;
const Response = require('../model/Response');
const ResponseStatus = require('../model/ResponseStatus');
const StoreResponse = require('../model/StoreResponse');

// Creates a new store
router.post('/', verifyToken, function (req, res) {
    let name = req.body.name;
    let description = req.body.description;
    let price = req.body.price;

    console.log('Creating a new store...')

    if (name && description) {
        Store.create({
            name: name,
            description: description,
            rating: 2.5,
        },
            function (err, store) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                return res.status(200).json(new StoreResponse(ResponseStatus.OK, [store]));
            });
    } else {
        return res.status(200).json(new Response(ResponseStatus.MISSING_PARAMETERS));
    }
});

// Returns all products in the db
router.get('/', function (req, res) {
    Store.find({}, function (err, stores) {
        if (err) {
            console.err(err);
            return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
        }

        return res.status(200).json(new StoreResponse(ResponseStatus.OK, stores));
    });
});

// Returns a single product from the db
router.get('/:id', function (req, res) {
    if (req.params.id.match(/^[0-9a-fA-F]{24}$/)) {
        Store.findById(req.params.id, function (err, store) {
            if (err) {
                console.err(err);
                return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
            }

            return res.status(200).json(new StoreResponse(ResponseStatus.OK, [store]));
        });
    } else {
        return res.status(200).json(new StoreResponse(ResponseStatus.OK, []));
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