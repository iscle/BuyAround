var mongoose = require('mongoose');  

var Direction = require('./Direction').Schema;

var StoreSchema = new mongoose.Schema({  
  name: String,
  direction: Direction,
  description: String,
  rating: Number,
  thumbnail: String,
  category: { type: mongoose.Schema.ObjectId, ref: "StoreCategory" },
  since: Number,
  products: { type: [mongoose.Schema.ObjectId], ref: "Product" },
});
mongoose.model('Store', StoreSchema);

module.exports.Model = mongoose.model('Store');
module.exports.Schema = StoreSchema;