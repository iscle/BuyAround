var mongoose = require('mongoose');

var Direction = require('./Direction').Schema;

var UserSchema = new mongoose.Schema({  
  name: String,
  password: String,
  direction: Direction,
  profilePicture: String,
  birthday: Number,
  email: String,
  favouriteProducts: { type: [mongoose.Schema.ObjectId], ref: "Product" },
  favouritePacks: { type: [mongoose.Schema.ObjectId], ref: "Pack" },
  favouriteStores: { type: [mongoose.Schema.ObjectId], ref: "Store" },
});
mongoose.model('User', UserSchema);

module.exports = mongoose.model('User');