const express = require('express');
const router = express.Router();

const verifyToken = require('./VerifyToken');

const User = require('../model/User');
const UserResponse = require('../model/UserResponse');
const ProductResponse = require('../model/ProductResponse');
const ResponseStatus = require('../model/ResponseStatus');

// Creates a new user
router.get('/', verifyToken, function (req, res) {
    User.findOne({ _id: req.userId },
        function (err, user) {
            if (err) {
                return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
            }

            return res.status(200).json(new UserResponse(ResponseStatus.OK, user));
        });
});

router.post('/favouriteProducts', verifyToken, function (req, res) {
    User
        .findOneAndUpdate(
            { _id: req.userId },
            {
                $push: {
                    favouriteProducts: req.body._id,
                }
            },
            {
                new: true
            }, function (err, user) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                return res.status(200).json(new UserResponse(ResponseStatus.OK, user));
            });
});

router.get('/favouriteProducts', verifyToken, function (req, res) {
    let userId = req.userId;

    User
        .findOne({ _id: userId })
        .select('favouriteProducts')
        .populate({ path: 'favouriteProducts', model: 'Product' })
        .exec(function (err, user) {
            if (err) {
                return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
            }

            return res.status(200).json(new ProductResponse(ResponseStatus.OK, user.favouriteProducts));
        });
});

router.post('/favouriteStores', verifyToken, function (req, res) {
    User
        .findOneAndUpdate(
            { _id: req.userId },
            {
                $push: {
                    favouriteStores: req.body._id,
                }
            },
            {
                new: true
            }, function (err, user) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                return res.status(200).json(new UserResponse(ResponseStatus.OK, user));
            });
});

router.get('/favouriteStores', verifyToken, function (req, res) {
    let userId = req.userId;

    User
        .findOne({ _id: userId })
        .select('favouriteStores')
        .populate({ path: 'favouriteStores', model: 'Store' })
        .exec(function (err, user) {
            if (err) {
                return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
            }

            return res.status(200).json(new ProductResponse(ResponseStatus.OK, user.favouriteStores));
        });
});

router.post('/favouritePacks', verifyToken, function (req, res) {
    User
        .findOneAndUpdate(
            { _id: req.userId },
            {
                $push: {
                    favouritePacks: req.body._id,
                }
            },
            {
                new: true
            }, function (err, user) {
                if (err) {
                    return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
                }

                return res.status(200).json(new UserResponse(ResponseStatus.OK, user));
            });
});

router.get('/favouritePacks', verifyToken, function (req, res) {
    let userId = req.userId;

    User
        .findOne({ _id: userId })
        .select('favouritePacks')
        .populate({ path: 'favouritePacks', model: 'Pack' })
        .exec(function (err, user) {
            if (err) {
                return res.status(200).json(new Response(ResponseStatus.INTERNAL_ERROR));
            }

            return res.status(200).json(new ProductResponse(ResponseStatus.OK, user.favouritePacks));
        });
});

module.exports = router;