var mongoose = require('mongoose');

var PackSchema = new mongoose.Schema({  
  name: String,
  images: [String],
  description: String,
  products: { type: [mongoose.Schema.ObjectId], ref: "Product" },
  price: Number,
  rating: Number,
});
mongoose.model('Pack', PackSchema);

module.exports = mongoose.model('Pack');