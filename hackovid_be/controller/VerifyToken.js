const jwt = require('jsonwebtoken'); // Used to create, sign, and verify tokens
const config = require('../config'); // Get our config file
const ResponseStatus = require('../model/ResponseStatus');
const Response = require('../model/Response');

function verifyToken(req, res, next) {
  // Check header or url parameters or post parameters for token
  console.log(req.originalUrl);
  let token = req.headers['authorization'];
  console.log("Verifying token: " + token);
  if (token) {
    jwt.verify(token, config.secret, function (err, decoded) {
      if (err) {
        return res.status(200).json(new Response(ResponseStatus.INVALID_TOKEN));
      }

      // If everything is good, save to request for use in other routes
      req.userId = decoded.id;
      return next();
    });
  } else {
    return res.status(200).json(new Response(ResponseStatus.INVALID_TOKEN));
  }
}

module.exports = verifyToken;