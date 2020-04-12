var mongoose = require('mongoose');

var OrderSchema = new mongoose.Schema({
  products: { type: [mongoose.Schema.ObjectId], ref: "Product" },
  user: { type: mongoose.Schema.ObjectId, ref: "User" },
  price: Number,
});
mongoose.model('Order', OrderSchema);

module.exports = mongoose.model('Order');