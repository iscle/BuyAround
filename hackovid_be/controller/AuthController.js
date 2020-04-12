const express = require('express');
const jwt = require('jsonwebtoken'); // Used to create, sign, and verify tokens
const bcrypt = require('bcryptjs');

const VerifyToken = require('./VerifyToken');
const User = require('../model/User');
const LoginResponse = require('../model/LoginResponse');
const RegisterResponse = require('../model/RegisterResponse');
const ResponseStatus = require('../model/ResponseStatus');
const config = require('../config'); // Get config file

let router = express.Router();

router.post('/login', function (req, res) {
  let email = req.body.email;
  let password = req.body.password;

  console.log(JSON.stringify(req.body));

  // If email and password exist
  if (email && password) {
    // Search for a user containing that email
    User.findOne({
      email: email
    }, function (err, user) {
      if (err) {
        // If there was an error while doing the query, return it with the response
        return res.status(200).json(new LoginResponse(ResponseStatus.INTERNAL_ERROR, null));
      }

      // Check if a user with that email is found
      if (typeof user != 'undefined' && user != null) {
        // Check if the password is valid
        if (bcrypt.compareSync(password, user.password)) {
          // Create a token
          let token = jwt.sign({ id: user._id }, config.secret, {
            expiresIn: config.tokenDuration
          });

          // Return the information including token as JSON
          return res.status(200).json(new LoginResponse(ResponseStatus.OK, token));
        }
      }

      // The email doesn't exist, or the password was incorrect
      return res.status(200).json(new LoginResponse(ResponseStatus.WRONG_PASSWORD, null));
    });
  } else {
    // We didn't get the email or passowrd with the request.
    return res.status(200).json(new LoginResponse(ResponseStatus.MISSING_PARAMETERS, null));
  }
});

router.get('/logout', function (req, res) {
  res.status(200).send({ auth: false, token: null });
});

router.post('/register', function (req, res) {
  let email = req.body.email;
  let password = req.body.password;
  let name = req.body.name;

  if (email && password && name) {
    User.findOne({
      email: email
    }, function (err, user) {
      if (err) {
        // If there was an error while doing the query, return it with the response.
        return res.status(200).json(new RegisterResponse(ResponseStatus.INTERNAL_ERROR));
      }

      // Check if a user with that email is found
      if (typeof user == 'undefined' || user == null) {
        let hashedPassword = bcrypt.hashSync(password, config.saltRounds);
        User.create({
          name: name,
          email: email,
          password: hashedPassword
        },
          function (err, user) {
            if (err) {
              // If there was an error while doing the query, return it with the response.
              return res.status(200).json(new RegisterResponse(ResponseStatus.INTERNAL_ERROR));
            }

            // Everything went ok! The user is now registered.
            return res.status(200).json(new RegisterResponse(ResponseStatus.OK));
          });
      } else {
        // The provided email already exists in the database.
        return res.status(200).json(new RegisterResponse(ResponseStatus.EXISTING_EMAIL));
      }
    });
  } else {
    // We dind't get the email or passowrd with the request.
    return res.status(200).json(new RegisterResponse(ResponseStatus.MISSING_PARAMETERS));
  }
});

router.get('/me', VerifyToken, function (req, res, next) {
  User.findById(req.userId, { password: 0 }, function (err, user) {
    if (err) {
      return res.status(500).send("There was a problem finding the user.");
    }

    if (!user) {
      return res.status(404).send("No user found.");
    }

    res.status(200).send(user);
  });
});

module.exports = router;