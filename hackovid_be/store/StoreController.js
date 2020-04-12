var express = require('express');
var router = express.Router();

var VerifyToken = require('../auth/VerifyToken');

var User = require('./User');

// Creates a new user
router.post('/', function (req, res) {
    User.create({
            name : req.body.name,
            email : req.body.email,
            password : req.body.password
        }, 
        function (err, user) {
            if (err) {
                return res.status(500).json("There was a problem adding the information to the database.");
            }

            res.status(200).json(user);
        });
});

// Returns all users in the db
router.get('/', function (req, res) {
    User.find({}, function (err, users) {
        if (err) {
            return res.status(500).json("There was a problem finding the users.");
        }

        res.status(200).json(users);
    });
});

// GETS A SINGLE USER FROM THE DATABASE
router.get('/:id', function (req, res) {
    User.findById(req.params.id, function (err, user) {
        if (err) {
            return res.status(500).json("There was a problem finding the user.");
        }

        if (!user) {
            return res.status(404).json("No user found.");
        }

        res.status(200).json(user);
    });
});

// Deletes a user from the database
router.delete('/:id', function (req, res) {
    User.findByIdAndRemove(req.params.id, function (err, user) {
        if (err) {
            return res.status(500).json("There was a problem deleting the user.");
        }

        res.status(200).json("User: "+ user.name +" was deleted.");
    });
});

// Updates a single user in the database
// Added VerifyToken middleware to make sure only an authenticated user can put to this route
router.put('/:id', function (req, res) {
    User.findByIdAndUpdate(req.params.id, req.body, {new: true}, function (err, user) {
        if (err) {
            return res.status(500).json("There was a problem updating the user.");
        }

        res.status(200).json(user);
    });
});


module.exports = router;