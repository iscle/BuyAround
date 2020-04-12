var mongoose = require('mongoose');

var Store = require('./Store').Schema;

var ProductSchema = new mongoose.Schema({  
  name: String,
  images: [String],
  category: { type: mongoose.Schema.ObjectId, ref: "ProductCategory" },
  description: String,
  store: Store,
  price: Number,
  rating: Number,
});
mongoose.model('Product', ProductSchema);

module.exports = mongoose.model('Product');